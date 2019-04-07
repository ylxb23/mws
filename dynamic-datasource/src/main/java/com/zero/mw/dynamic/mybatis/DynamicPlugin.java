package com.zero.mw.dynamic.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @author zero
 */
@Intercepts({
	@Signature(type=Executor.class, method="query", args= {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
	@Signature(type=Executor.class, method="update", args= {MappedStatement.class, Object.class}),
})
public class DynamicPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {	// read
			DynamicDataSourceHolder.putRwdHolder(DynamicConstants.READ);
		} else {
			DynamicDataSourceHolder.putRwdHolder(DynamicConstants.WRITE);
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if(target instanceof Executor) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
