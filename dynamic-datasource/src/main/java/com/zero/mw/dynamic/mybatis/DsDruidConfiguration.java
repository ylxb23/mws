package com.zero.mw.dynamic.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;

/**
 * Druid DataSource pool connection properties
 * @author zero
 */
public class DsDruidConfiguration {
    private String  name;

    /**
     * @see DsConfiguration#getUsername()
     */
    private String  username;
    /**
     * @see DsConfiguration#getPassword()
     */
    private String  password;
    /**
     * @see DsConfiguration#getJdbcUrl()
     */
    private String  jdbcUrl;
    private String  driverClassName = JdbcConstants.MYSQL_DRIVER;

    private Integer initialSize = DruidDataSource.DEFAULT_INITIAL_SIZE;
    private Integer maxActive = DruidDataSource.DEFAULT_MAX_ACTIVE_SIZE;
    private Integer minIdle = DruidDataSource.DEFAULT_MIN_IDLE;
    private Integer maxIdle = DruidDataSource.DEFAULT_MAX_IDLE;
    private Integer maxWait = DruidDataSource.DEFAULT_MAX_WAIT;
    private Integer notFullTimeoutRetryCount = 0;

    private String  validationQuery = DruidDataSource.DEFAULT_VALIDATION_QUERY;
    private Integer validationQueryTimeout = -1;
    private Boolean testOnBorrow = DruidDataSource.DEFAULT_TEST_ON_BORROW;
    private Boolean testOnReturn = DruidDataSource.DEFAULT_TEST_ON_RETURN;
    private Boolean testWhileIdle = DruidDataSource.DEFAULT_WHILE_IDLE;
    private Boolean poolPreparedStatements = Boolean.FALSE;
    private Boolean sharePreparedStatements = Boolean.FALSE;
    private Long	timeBetweenEvictionRunsMillis = DruidDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
    private Integer	numTestsPerEvictionRun = DruidDataSource.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
    private Integer maxPoolPreparedStatementPerConnectionSize = 10;

    private Long	minEvictableIdleTimeMillis = DruidDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
    private Long	maxEvictableIdleTimeMillis = DruidDataSource.DEFAULT_MAX_EVICTABLE_IDLE_TIME_MILLIS;
    
	private Integer	queryTimeout = 7;
	private Integer	transactionQueryTimeout = 10;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

	public Integer getNotFullTimeoutRetryCount() {
		return notFullTimeoutRetryCount;
	}

	public void setNotFullTimeoutRetryCount(Integer notFullTimeoutRetryCount) {
		this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public Integer getValidationQueryTimeout() {
		return validationQueryTimeout;
	}

	public void setValidationQueryTimeout(Integer validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public Boolean getSharePreparedStatements() {
		return sharePreparedStatements;
	}

	public void setSharePreparedStatements(Boolean sharePreparedStatements) {
		this.sharePreparedStatements = sharePreparedStatements;
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Integer getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	public Long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public Long getMaxEvictableIdleTimeMillis() {
		return maxEvictableIdleTimeMillis;
	}

	public void setMaxEvictableIdleTimeMillis(Long maxEvictableIdleTimeMillis) {
		this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
	}

	public Integer getQueryTimeout() {
		return queryTimeout;
	}

	public void setQueryTimeout(Integer queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	public Integer getTransactionQueryTimeout() {
		return transactionQueryTimeout;
	}

	public void setTransactionQueryTimeout(Integer transactionQueryTimeout) {
		this.transactionQueryTimeout = transactionQueryTimeout;
	}

	@Override
	public String toString() {
		return "DruidDsConfiguration [name=" + name + ", username=" + username + ", password=" + password + ", jdbcUrl="
				+ jdbcUrl + ", driverClassName=" + driverClassName + ", initialSize=" + initialSize + ", maxActive=" + maxActive
				+ ", minIdle=" + minIdle + ", maxIdle=" + maxIdle + ", maxWait=" + maxWait
				+ ", notFullTimeoutRetryCount=" + notFullTimeoutRetryCount + ", validationQuery=" + validationQuery
				+ ", validationQueryTimeout=" + validationQueryTimeout + ", testOnBorrow=" + testOnBorrow
				+ ", testOnReturn=" + testOnReturn + ", testWhileIdle=" + testWhileIdle + ", poolPreparedStatements="
				+ poolPreparedStatements + ", sharePreparedStatements=" + sharePreparedStatements
				+ ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis + ", numTestsPerEvictionRun="
				+ numTestsPerEvictionRun + ", maxPoolPreparedStatementPerConnectionSize="
				+ maxPoolPreparedStatementPerConnectionSize + ", minEvictableIdleTimeMillis="
				+ minEvictableIdleTimeMillis + ", maxEvictableIdleTimeMillis=" + maxEvictableIdleTimeMillis
				+ ", queryTimeout=" + queryTimeout + ", transactionQueryTimeout=" + transactionQueryTimeout + "]";
	}
}
