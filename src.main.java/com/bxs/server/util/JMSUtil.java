package com.bxs.server.util;

import java.util.ResourceBundle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSUtil {
	private static ResourceBundle rb=ResourceBundle.getBundle("jms");
	private static final String brokerURL;
	static{
		brokerURL = rb.getString("brokerURL");
	}
	
	/**
	 * 获取JMS连接
	 * @author yangliehui yangliehui@163.com
	 * @return
	 */
	public static Connection getConnection(){
		//连接工厂
		ConnectionFactory connFactory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,brokerURL);
		//连接到JMS提供者
		Connection conn = null;
		try {
			conn = connFactory.createConnection();
			conn.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 获取会话
	 * @author yangliehui yangliehui@163.com
	 * @param conn
	 * @return
	 */
	public static Session getSession(Connection conn){
		//事务性会话，自动确认消息
		Session session = null;
		try {
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	/**
	 * 关闭连接
	 * @author yangliehui yangliehui@163.com
	 * @param conn
	 * @param session
	 * @param producer
	 */
	public static void jmsClose(Connection conn,Session session,MessageProducer producer){
		try {
			conn.close();
			session.close();
			producer.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
