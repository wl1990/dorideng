package com.bxs.server.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static final SessionFactory sessionFactory;
	static{
		try{
		sessionFactory=new Configuration().configure().buildSessionFactory();
//		sessionFactory=new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Initial SessionFactory creation failed."+ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static final ThreadLocal threadlocal=new ThreadLocal();
	public static  Session currentSession() throws HibernateException{
		Session s=(Session) threadlocal.get();
		if(s==null){
			s=sessionFactory.openSession();
			threadlocal.set(s);
		}
		return s;	
//		return InstanceSession.s;
	}
	
//	private static class InstanceSession{
//		public static final Session s=sessionFactory.openSession();
//	}
	public static  void closeSession() throws HibernateException{
		Session s=(Session) threadlocal.get();
		if(s!=null)
			s.close();
		threadlocal.set(null);
	}
}
