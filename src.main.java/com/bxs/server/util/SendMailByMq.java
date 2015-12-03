package com.bxs.server.util;

import java.util.ResourceBundle;

import com.bxs.server.model.MailSenderInfo;



import net.sf.json.JSONObject;


public class SendMailByMq {
	private static ResourceBundle rb = ResourceBundle.getBundle("email");
	public static final String MAIL_CONTEXT_TYPE_HTML = "HTML";
	public static final String MAIL_CONTEXT_TYPE_TEXT = "TEXT";
	private static final String mailServerHost;
	private static final String mailServerPort;
	private static final String userName;
	private static final String passWord;
	private static final String fromAddress;
	private static final String queueName="com.dovepay.queue.mailSend";
	static{
		mailServerHost =rb.getString("mailServerHost"); 
        mailServerPort = rb.getString("mailServerPort");
        userName = rb.getString("userName");
        passWord = rb.getString("passWord");
        fromAddress = rb.getString("fromAddress");
	}
	/**
	 * 通过activeMq队列发送邮件
	 * @author yangliehui yangliehui@163.com
	 * @param mailContextType	邮件文本类型：SendMailByMq.MAIL_CONTEXT_TYPE_HTML为HTML格式；SendMailByMq.MAIL_CONTEXT_TYPE_TEXT为普通文本格式
	 * @param toAddress	接收邮件地址
	 * @param subject	邮件主题
	 * @param content	邮件内容
	 * @param attachFileNames	邮件附件的文件名格式说明：数组存放多个附件，每个附件String用逗号隔开,前面实际路径,后面重命名{"d:\\ICBCb2b.txt,ICBCb2b.txt"}，无附件传null
	 * @return	返回发送状态
	 */
	public static boolean send(String mailContextType,String toAddress,String subject,String content,String[] attachFileNames){
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setMailServerHost(mailServerHost);
		mailSenderInfo.setMailServerPort(mailServerPort);
		mailSenderInfo.setFromAddress(fromAddress);
		mailSenderInfo.setUserName(userName);
		mailSenderInfo.setPassword(passWord);
		mailSenderInfo.setMailContentType(mailContextType);
		mailSenderInfo.setToAddress(toAddress);
		mailSenderInfo.setSubject(subject);
		mailSenderInfo.setContent(content);
		mailSenderInfo.setAttachFileNames(attachFileNames);
		JSONObject json = JSONObject.fromObject(mailSenderInfo);
		String sendMessage = json.toString();
		boolean sendStatus = SendTextMessage.send(queueName, sendMessage);
		return sendStatus;
	}
	
}
