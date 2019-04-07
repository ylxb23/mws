package com.zero.mw.dynamic.mybatis;

/**
 * 
 * @author zero
 */
class DsConfiguration {
	// read/write/default
	private String isolate;
	private String jdbcUrl;
	private String username;
	private String password;
	private String mapperLocations;
	private String basePackage;
	
	public DsConfiguration(String isolate) {
		this.isolate = isolate;
	}
	
	public String getIsolate() {
		return isolate;
	}
	public void setIsolate(String isolate) {
		this.isolate = isolate;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}
