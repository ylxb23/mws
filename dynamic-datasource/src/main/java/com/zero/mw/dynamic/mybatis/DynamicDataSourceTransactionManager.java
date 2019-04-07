package com.zero.mw.dynamic.mybatis;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 
 * @author zero
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
		
	}
	
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		
	}
}
