package com.zero.mw.dynamic.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @date 2019年3月30日 下午5:28:36
 * @author zero
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	
	private Object defaultDataSource;
	private Object writeDataSource;
	private Object readDataSource;
	// Map<read/write/default, DataSource>
	private Map<String, Object> dataSourceMap;
	
	@Override
	protected Object determineCurrentLookupKey() {
		String rwd = DynamicDataSourceHolder.getHoldedRwd();
		if(logger.isDebugEnabled()) {
			logger.debug("DynamicDataSource get holded rwd:{}", rwd);
		}
		if(dataSourceMap.containsKey(rwd)) {
			return rwd;
		}
		return DynamicConstants.DEFAULT;
	}

	@Override
	public void afterPropertiesSet() {
		if(dataSourceMap == null) {
			throw new IllegalArgumentException("DynamicDataSource with null dataSourceMap value");
		}
		if(!dataSourceMap.containsKey(DynamicConstants.DEFAULT)) {
			dataSourceMap.put(DynamicConstants.DEFAULT, writeDataSource != null ? writeDataSource : readDataSource);
			defaultDataSource = (writeDataSource == null ? readDataSource : writeDataSource);
		}
		setDefaultTargetDataSource(defaultDataSource != null ? defaultDataSource : (writeDataSource != null ? writeDataSource : readDataSource));
		Map<Object, Object> targetDataSourceMap = new HashMap<>();
		targetDataSourceMap.putAll(dataSourceMap);
		setTargetDataSources(targetDataSourceMap);
		super.afterPropertiesSet();
	}
	
	public Object getDefaultDataSource() {
		return defaultDataSource;
	}

	public void setDefaultDataSource(Object defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}

	public Object getWriteDataSource() {
		return writeDataSource;
	}

	public void setWriteDataSource(Object writeDataSource) {
		this.writeDataSource = writeDataSource;
	}

	public Object getReadDataSource() {
		return readDataSource;
	}

	public void setReadDataSource(Object readDataSource) {
		this.readDataSource = readDataSource;
	}

	public Map<String, Object> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(Map<String, Object> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

}
