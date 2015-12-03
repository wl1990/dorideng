package com.bxs.server.util;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;



public class SendTextMessage {
	/**
	 * 发送文本消息
	 * @author yangliehui yangliehui@163.com
	 * @param queueName 消息队列名
	 * @param textMessage 发送文本内容
	 * @return 发送状态
	 */
	public static boolean send(String queueName,String textMessage){
		boolean sendStatus = false;
		try {
			Connection conn = JMSUtil.getConnection();
			Session session = JMSUtil.getSession(conn);
			//消息的目的地
			Destination destination = session.createQueue(queueName);
			//消息生产者
			MessageProducer producer = session.createProducer(destination); 
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT); //不持久化
			//文本消息
			TextMessage textMsg = session.createTextMessage();
			textMsg.setText(textMessage);
			producer.send(textMsg);
			session.commit();//在事务性会话中，只有commit之后，消息才会真正到达目的地
			JMSUtil.jmsClose(conn, session, producer);
			sendStatus = true;
		} catch (JMSException e) {
			sendStatus = false;
			e.printStackTrace();
		} catch (Exception e) {
			sendStatus = false;
			e.printStackTrace();
		}
		return sendStatus;
	}
}
