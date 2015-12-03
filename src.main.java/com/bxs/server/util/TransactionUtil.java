package com.bxs.server.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 事务管理
 * @author Administrator
 *
 */
@Service("transaction")
public class TransactionUtil {
	
	public void magTransaction(ProceedingJoinPoint joinpoint){
		try {
			beginTransaction();
			joinpoint.proceed();
			endTransaction();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	public void beginTransaction(){
		HibernateUtil.currentSession().beginTransaction();
		
	}
	public void endTransaction(){
		HibernateUtil.currentSession().getTransaction().commit();
	}
}
