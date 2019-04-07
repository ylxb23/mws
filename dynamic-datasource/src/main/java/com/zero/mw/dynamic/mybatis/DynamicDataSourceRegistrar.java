package com.zero.mw.dynamic.mybatis;

import java.util.Map;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @date 2019年3月30日 下午6:14:41
 * @author zero
 */
public class DynamicDataSourceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private Environment env;
	
	
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		// default data source configuration properties
		DsDruidConfiguration defaultDsConnectionConfig = DynamicPropertyUtil.parseDsConnectionConfiguration(this.env, "default");
		// dbName, write/read/default, URL
		Map<String, Map<String, DsConfiguration>> dsInfoMap = DynamicPropertyUtil.parseDataSourceConfiguration(this.env);
		if(dsInfoMap.isEmpty()) {
			return;
		}
		// plugin
		DynamicPlugin dynamicPlugin = BeanUtils.instantiateClass(DynamicPlugin.class);
		
		for(Map.Entry<String, Map<String, DsConfiguration>> dsInfo : dsInfoMap.entrySet()) {
			ManagedMap<String, RuntimeBeanReference> dsReferenceMap = new ManagedMap<>();
			RuntimeBeanReference dDsReference = null;
			RuntimeBeanReference wDsReference = null;
			RuntimeBeanReference rDsReference = null;

			String dsName = dsInfo.getKey();
			Map<String, DsConfiguration> rwdMap = dsInfo.getValue();
			String mapperLocations = null;
			String basePackage = null;
			DsConfiguration dDsInfo = rwdMap.get(DynamicConstants.DEFAULT);
			if(dDsInfo != null) {
				// write ds 
				BeanDefinitionHolder dataSourceBeanDefinitionHolder = buildDataSourceBeanDefinitionHolder(dsName, dDsInfo, defaultDsConnectionConfig, registry);
				BeanDefinitionReaderUtils.registerBeanDefinition(dataSourceBeanDefinitionHolder, registry);
				dDsReference = new RuntimeBeanReference(dataSourceBeanDefinitionHolder.getBeanName());
				dsReferenceMap.put(DynamicConstants.DEFAULT, dDsReference);
				mapperLocations = dDsInfo.getMapperLocations();
				basePackage = dDsInfo.getBasePackage();
			}
			DsConfiguration wDsInfo = rwdMap.get(DynamicConstants.WRITE);
			if(wDsInfo != null) {
				// write ds 
				BeanDefinitionHolder dataSourceBeanDefinitionHolder = buildDataSourceBeanDefinitionHolder(dsName, wDsInfo, defaultDsConnectionConfig, registry);
				BeanDefinitionReaderUtils.registerBeanDefinition(dataSourceBeanDefinitionHolder, registry);
				wDsReference = new RuntimeBeanReference(dataSourceBeanDefinitionHolder.getBeanName());
				dsReferenceMap.put(DynamicConstants.WRITE, wDsReference);
				if(mapperLocations == null) {
					mapperLocations = wDsInfo.getMapperLocations();
					basePackage = wDsInfo.getBasePackage();
				}
			}
			DsConfiguration rDsInfo = rwdMap.get(DynamicConstants.READ);
			if(rDsInfo != null) {
				// read ds 
				BeanDefinitionHolder dataSourceBeanDefinitionHolder = buildDataSourceBeanDefinitionHolder(dsName, rDsInfo, defaultDsConnectionConfig, registry);
				BeanDefinitionReaderUtils.registerBeanDefinition(dataSourceBeanDefinitionHolder, registry);
				rDsReference = new RuntimeBeanReference(dataSourceBeanDefinitionHolder.getBeanName());
				dsReferenceMap.put(DynamicConstants.READ, rDsReference);
				if(mapperLocations == null) {
					mapperLocations = rDsInfo.getMapperLocations();
					basePackage = rDsInfo.getBasePackage();
				}
			}
			BeanDefinitionHolder dynamicDataSourceBeanDefinitionHolder = builderDynamicDataSourceBeanDefinitionHolder(dsName, 
					dDsReference, wDsReference, rDsReference, dsReferenceMap);
			if(dynamicDataSourceBeanDefinitionHolder != null) {
				BeanDefinitionReaderUtils.registerBeanDefinition(dynamicDataSourceBeanDefinitionHolder, registry);
			} else {
				logger.warn("Dynamic DataSource for {} configuration warn, ignore all configurated {}DataSource", dsName, dsName);
				// TODO: remove invalid dynamic first hand data source 
				continue;
			}
			// Transaction manager bean definition
			BeanDefinitionHolder transactionManagerBeanDefinitionHolder = buildTransactionManagerBeanDefinitionHolder(dsName, dynamicDataSourceBeanDefinitionHolder);
			BeanDefinitionReaderUtils.registerBeanDefinition(transactionManagerBeanDefinitionHolder, registry);
			// SQL session factory bean definition
			BeanDefinitionHolder sqlSessionFactoryBeanDefinitionHolder = buildSqlSessionFactoryBeanDefinitionHolder(dsName, dynamicDataSourceBeanDefinitionHolder, mapperLocations, dynamicPlugin);
			BeanDefinitionReaderUtils.registerBeanDefinition(sqlSessionFactoryBeanDefinitionHolder, registry);
			// SQL session template bean definition
			BeanDefinitionHolder sqlSessionTemplateBeanDefinitionHolder = buildSqlSessionTemplateBeanDefinitionHolder(dsName, sqlSessionFactoryBeanDefinitionHolder);
			BeanDefinitionReaderUtils.registerBeanDefinition(sqlSessionTemplateBeanDefinitionHolder, registry);
			// SQL mapper scanner configuration bean definition
			BeanDefinitionHolder mapperScannerConfigurerBeanDefinitionHolder = buildMapperScannerConfigurerBeanDefinitionHolder(dsName, sqlSessionTemplateBeanDefinitionHolder, 
					sqlSessionFactoryBeanDefinitionHolder, basePackage);
			BeanDefinitionReaderUtils.registerBeanDefinition(mapperScannerConfigurerBeanDefinitionHolder, registry);
		}
	}
	
	/**
	 * MapperScannerConfigurer
	 * @param dsName
	 * @param sqlSessionTemplateBeanDefinitionHolder
	 * @param sqlSessionFactoryBeanDefinitionHolder 
	 * @param basePackages
	 * @return
	 */
	private BeanDefinitionHolder buildMapperScannerConfigurerBeanDefinitionHolder(String dsName, BeanDefinitionHolder sqlSessionTemplateBeanDefinitionHolder, 
			BeanDefinitionHolder sqlSessionFactoryBeanDefinitionHolder, String basePackage) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperScannerConfigurer.class);
		builder.setScope(BeanDefinition.SCOPE_SINGLETON);
		builder.addPropertyValue("basePackage", basePackage);
		builder.addPropertyValue("sqlSessionTemplate", sqlSessionTemplateBeanDefinitionHolder);
//		builder.addPropertyValue("sqlSessionFactory", sqlSessionFactoryBeanDefinitionHolder);
		String beanName = dsName + "DynamicMapperScannerConfigurer";
		if(logger.isDebugEnabled()) {
			logger.debug("Build MapperScannerConfigurer BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, beanName);
		}
		return new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
	}

	/**
	 * SqlSessionTemplate
	 * @param dsName
	 * @param sqlSessionFactoryBeanDefinitionHolder
	 * @return
	 */
	private BeanDefinitionHolder buildSqlSessionTemplateBeanDefinitionHolder(String dsName, BeanDefinitionHolder sqlSessionFactoryBeanDefinitionHolder) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionTemplate.class);
		builder.setScope(BeanDefinition.SCOPE_SINGLETON);
		builder.addConstructorArgValue(sqlSessionFactoryBeanDefinitionHolder);
		String beanName = dsName + "DynamicSqlSessionTemplate";
		if(logger.isDebugEnabled()) {
			logger.debug("Build SqlSessionTemplate BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, beanName);
		}
		return new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
	}

	/**
	 * SqlSessionFactoryBean
	 * @param dsName
	 * @param dynamicDataSourceBeanDefinitionHolder
	 * @param mapperLocations
	 * @param dynamicPlugin
	 * @return
	 */
	private BeanDefinitionHolder buildSqlSessionFactoryBeanDefinitionHolder(String dsName, BeanDefinitionHolder dynamicDataSourceBeanDefinitionHolder, 
			String mapperLocations, DynamicPlugin dynamicPlugin) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);
		builder.setScope(BeanDefinition.SCOPE_SINGLETON);
		builder.addPropertyValue("dataSource", dynamicDataSourceBeanDefinitionHolder);
		builder.addPropertyValue("mapperLocations", mapperLocations);
		builder.addPropertyValue("plugins", new Interceptor[] {dynamicPlugin});
		String beanName = dsName + "DynamicSqlSessionFactory";
		if(logger.isDebugEnabled()) {
			logger.debug("Build SqlSessionFactoryBean BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, beanName);
		}
		return new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
	}

	/**
	 * DynamicDataSourceTransactionManager
	 * @param dsName
	 * @param dynamicDataSourceBeanDefinitionHolder
	 * @return
	 */
	private BeanDefinitionHolder buildTransactionManagerBeanDefinitionHolder(String dsName, BeanDefinitionHolder dynamicDataSourceBeanDefinitionHolder) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DynamicDataSourceTransactionManager.class);
		builder.setScope(BeanDefinition.SCOPE_SINGLETON);
		builder.addPropertyValue("dataSource", dynamicDataSourceBeanDefinitionHolder);
		String beanName = dsName + "DynamicTransactionManager";
		if(logger.isDebugEnabled()) {
			logger.debug("Build DynamicDataSourceTransactionManager BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, beanName);
		}
		return new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
	}

	/**
	 * DynamicDataSource
	 * @param dsName
	 * @param dDsReference
	 * @param wDsReference
	 * @param rDsReference
	 * @param dsReferenceMap
	 * @return
	 */
	private BeanDefinitionHolder builderDynamicDataSourceBeanDefinitionHolder(String dsName,
			RuntimeBeanReference dDsReference, RuntimeBeanReference wDsReference, RuntimeBeanReference rDsReference,
			ManagedMap<String, RuntimeBeanReference> dsReferenceMap) {
		if(dsReferenceMap.isEmpty()) {
			logger.warn("Dynamic DataSource bean definition for {} without any usefull DataSource", dsName);
			return null;
		}
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DynamicDataSource.class);
		builder.setScope(BeanDefinition.SCOPE_SINGLETON);
		if(dDsReference != null) {
			builder.addPropertyValue("defaultDataSource", dDsReference);
		}
		if(wDsReference != null) {
			builder.addPropertyValue("writeDataSource", wDsReference);
		}
		if(rDsReference != null) {
			builder.addPropertyValue("readDataSource", rDsReference);
		}
		builder.addPropertyValue("dataSourceMap", dsReferenceMap);
		String beanName = dsName + "DynamicDataSource";
		if(logger.isDebugEnabled()) {
			logger.debug("Build DynamicDataSource BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, beanName);
		}
		return new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
	}

	/**
	 * DruidDataSource
	 * @param dsName
	 * @param dsInfo
	 * @param _defaultConf
	 * @param registry
	 * @return
	 */
	private BeanDefinitionHolder buildDataSourceBeanDefinitionHolder(String dsName, DsConfiguration dsInfo, DsDruidConfiguration _defaultConf, BeanDefinitionRegistry registry) {
		try {
			logger.debug("Create dataSource bean definition holder for {}", dsName);
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
			builder.setScope(BeanDefinition.SCOPE_SINGLETON);
			builder.addPropertyValue("driverClassName", _defaultConf.getDriverClassName());
			builder.addPropertyValue("url", dsInfo.getJdbcUrl());
			builder.addPropertyValue("username", dsInfo.getUsername());
			builder.addPropertyValue("password", dsInfo.getPassword());
			DsDruidConfiguration currentConf = DynamicPropertyUtil.parseDsConnectionConfiguration(this.env, dsName);
			builder.addPropertyValue("initialSize", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getInitialSize));
			builder.addPropertyValue("maxActive", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMaxActive));
			builder.addPropertyValue("minIdle", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMinIdle));
//			builder.addPropertyValue("maxIdle", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMaxIdle));
			builder.addPropertyValue("maxWait", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMaxWait));
			builder.addPropertyValue("notFullTimeoutRetryCount", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getNotFullTimeoutRetryCount));
			builder.addPropertyValue("validationQuery", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getValidationQuery));
			builder.addPropertyValue("validationQueryTimeout", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getValidationQueryTimeout));
			builder.addPropertyValue("testOnBorrow", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getTestOnBorrow));
			builder.addPropertyValue("testOnReturn", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getTestOnReturn));
			builder.addPropertyValue("testWhileIdle", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getTestWhileIdle));
			builder.addPropertyValue("poolPreparedStatements", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getPoolPreparedStatements));
			builder.addPropertyValue("sharePreparedStatements", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getSharePreparedStatements));
			builder.addPropertyValue("timeBetweenEvictionRunsMillis", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getTimeBetweenEvictionRunsMillis));
			builder.addPropertyValue("numTestsPerEvictionRun", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getNumTestsPerEvictionRun));
			builder.addPropertyValue("maxPoolPreparedStatementPerConnectionSize", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMaxPoolPreparedStatementPerConnectionSize));
			builder.addPropertyValue("minEvictableIdleTimeMillis", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMinEvictableIdleTimeMillis));
			builder.addPropertyValue("maxEvictableIdleTimeMillis", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getMaxEvictableIdleTimeMillis));
			builder.addPropertyValue("queryTimeout", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getQueryTimeout));
			builder.addPropertyValue("transactionQueryTimeout", DynamicPropertyUtil.notNullValueOrElse(_defaultConf, currentConf, DsDruidConfiguration::getTransactionQueryTimeout));
			String dataSourceBeanName = dsName + StringUtils.capitalize(dsInfo.getIsolate()) + "DataSource";
			if(logger.isDebugEnabled()) {
				logger.debug("Build DataSource BeanDefinitionHolder success, dsName:{}, beanName:{}", dsName, dataSourceBeanName);
			}
			return new BeanDefinitionHolder(builder.getBeanDefinition(), dataSourceBeanName);
		} catch(Exception e) {
			throw new BeanDefinitionStoreException("Create DataSource bean definition holder for " + dsName + " error", e);
		}
	}
	
}
