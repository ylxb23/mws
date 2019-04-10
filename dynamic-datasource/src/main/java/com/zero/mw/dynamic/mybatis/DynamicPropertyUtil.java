package com.zero.mw.dynamic.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.env.Environment;

/**
 * 
 * @date 2019年4月6日 下午10:24:26
 * @author zero
 */
public final class DynamicPropertyUtil {
	private static final Logger logger = LoggerFactory.getLogger(DynamicPropertyUtil.class);
	private DynamicPropertyUtil() {}

	
	/**
	 * 
	 * @param env
	 * @param dsName
	 * @return
	 */
	public static DsDruidConfiguration parseDsConnectionConfiguration(Environment env, String dsName) {
		Binder binder = Binder.get(env);
		BindResult<Map<String, Object>> map = binder.bind(DynamicConstants.DS_CONNECTION_PREFIX + dsName, Bindable.mapOf(String.class, Object.class));
		if(!map.isBound()) {
			return null;
		}
		ConfigurationPropertySource source = new MapConfigurationPropertySource(map.get());
		Binder clazzBinder = new Binder(new ConfigurationPropertySource[] {source});
		BindResult<DsDruidConfiguration> result = clazzBinder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(DsDruidConfiguration.class));
		if(logger.isDebugEnabled()) {
			logger.debug("Parse dataSource Connection configuration for {}, result:{}", dsName, result.get());
		}
		return result.get();
	}
	

	/**
	 * 
	 * @param env
	 * @return
	 * {@code Map<dsName, Map<read/write/default, DsConfiguration>>}
	 */
	@SuppressWarnings("unchecked")
	protected static Map<String, Map<String, DsConfiguration>> parseDataSourceConfiguration(Environment env) {
		// dsName, read/write/default, DsConfiguration
		Map<String, Map<String, DsConfiguration>> dsInfoMap = new HashMap<>();
		Binder binder = Binder.get(env);
		Map<String, Object> pairs = binder.bind(DynamicConstants.DS_MYSQL, Bindable.mapOf(String.class, Object.class)).get();
		for(Entry<String, Object> entry : pairs.entrySet()) {
			String db = entry.getKey();
			Map<String, String> confMap = (Map<String, String>) entry.getValue();
			dsInfoMap.putIfAbsent(db, new HashMap<>());
			String mapperLocations = confMap.get(DynamicConstants.MAPPER_LOCATIONS);
			String basePackage = confMap.get(DynamicConstants.BASE_PACKAGE);
			String defaultusername = confMap.get(DynamicConstants.USERNAME);
			String defaultpassword = confMap.get(DynamicConstants.PASSWORD);
			String url = confMap.get(DynamicConstants.DEFAULT_URL);
			if(url != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.DEFAULT, new DsConfiguration(DynamicConstants.DEFAULT));
				DsConfiguration _default = dsInfoMap.get(db).get(DynamicConstants.DEFAULT);
				_default.setJdbcUrl(url);
				_default.setUsername(defaultusername);
				_default.setPassword(defaultpassword);
				_default.setBasePackage(basePackage);
				_default.setMapperLocations(mapperLocations);
			}
			
			String rurl = confMap.get(DynamicConstants.READ_URL);
			if(rurl != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.READ, new DsConfiguration(DynamicConstants.READ));
				DsConfiguration _read = dsInfoMap.get(db).get(DynamicConstants.READ);
				_read.setJdbcUrl(rurl);
				String readusername = confMap.get(DynamicConstants.RUSERNAME);
				String readpassword = confMap.get(DynamicConstants.RPASSWORD);
				_read.setUsername(readusername == null ? defaultusername : readusername);
				_read.setPassword(readpassword == null ? defaultpassword : readpassword);
				_read.setBasePackage(basePackage);
				_read.setMapperLocations(mapperLocations);
			}
			String wurl = confMap.get(DynamicConstants.WRITE_URL);
			if(wurl != null) {
				dsInfoMap.get(db).putIfAbsent(DynamicConstants.WRITE, new DsConfiguration(DynamicConstants.WRITE));
				DsConfiguration _write = dsInfoMap.get(db).get(DynamicConstants.WRITE);
				_write.setJdbcUrl(wurl);
				String writeusername = confMap.get(DynamicConstants.WUSERNAME);
				String writepassword = confMap.get(DynamicConstants.WPASSWORD);
				_write.setUsername(writeusername == null ? defaultusername : writeusername);
				_write.setPassword(writepassword == null ? defaultpassword : writepassword);
				_write.setBasePackage(basePackage);
				_write.setMapperLocations(mapperLocations);
			}
			if(rurl != null && wurl != null && url != null) {
				logger.warn("Duplicate configuration dataSource urls configuration, dsName:{}", db);
			}
			if(rurl == null && wurl == null && url == null) {
				logger.info("DataSource urls configuration without url configurated, dsName:{}", db);
			}
		}
		if(logger.isDebugEnabled()) {
			logger.debug("mysql env pairs: {} \n dsInfos: {}", pairs, dsInfoMap);
		}
		return dsInfoMap;
	}
	
	/**
	 * Get not null value from T
	 * @param current
	 * @param _default
	 * @param func
	 * @return
	 */
	public static <T> Object notNullValueOrElse(T _default, T current, Function<T, Object> func) {
		if(current == null) {
			return func.apply(_default);
		}
		Object value = func.apply(current);
		return value == null ? func.apply(_default) : value;
	}
	
}
