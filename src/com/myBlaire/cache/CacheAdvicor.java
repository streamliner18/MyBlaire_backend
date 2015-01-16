package com.myBlaire.cache;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class CacheAdvicor implements AfterReturningAdvice ,PlatformTransactionManager{


	public void afterReturning(Object method, Method objs, Object[] obj,
			Object arg3) throws Throwable {
		// TODO Auto-generated method stub
		// System.out.println("Log for  "+obj.getClass().getName()); 
	}

	public void commit(TransactionStatus arg0) throws TransactionException {
		// TODO Auto-generated method stub
	}

	public TransactionStatus getTransaction(TransactionDefinition arg0)
			throws TransactionException {
		// TODO Auto-generated method stub
		CacheUtil.setFLUSH(true);//设置缓存刷新
		return null;
	}

	public void rollback(TransactionStatus arg0) throws TransactionException {
		// TODO Auto-generated method stub
		
	}

}
