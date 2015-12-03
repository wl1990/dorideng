package com.bxs.server.model;

import java.io.Serializable;
import java.util.Properties;

public class MailSenderInfo implements Serializable{
	// 发送邮件的服务器的IP和端口
			private String mailServerHost;
			private String mailServerPort = "25";
			// 邮件发送者的地址
			private String fromAddress;
			// 邮件接收者的地址
			private String toAddress;
			// 登陆邮件发送服务器的用户名和密码
			private String userName;
			private String password;
			// 是否需要身份验证
			private boolean validate = false;
			// 邮件主题
			private String subject;
			// 邮件的文本内容
			private String content;
			// 邮件附件的文件名格式说明：数组存放多个附件，每个附件String用逗号隔开,前面实际路径,后面重名名{"d:\\ICBCb2b.txt,ICBCb2b.txt"}
			private String[] attachFileNames;
			// 邮件内容类型 HTML or TEXT
			private String mailContentType = "HTML";

			/**
			 * 获得邮件会话属性
			 */
			public Properties getProperties() {
				Properties p = new Properties();
				p.put("mail.smtp.host", this.mailServerHost);
				p.put("mail.smtp.port", this.mailServerPort);
				p.put("mail.smtp.auth", validate ? "true" : "false");
				return p;
			}

			public String getMailServerHost() {
				return mailServerHost;
			}

			public void setMailServerHost(String mailServerHost) {
				this.mailServerHost = mailServerHost;
			}

			public String getMailServerPort() {
				return mailServerPort;
			}

			public void setMailServerPort(String mailServerPort) {
				this.mailServerPort = mailServerPort;
			}

			public boolean isValidate() {
				return validate;
			}

			public void setValidate(boolean validate) {
				this.validate = validate;
			}

			public String[] getAttachFileNames() {
				return attachFileNames;
			}

			public void setAttachFileNames(String[] fileNames) {
				this.attachFileNames = fileNames;
			}

			public String getFromAddress() {
				return fromAddress;
			}

			public void setFromAddress(String fromAddress) {
				this.fromAddress = fromAddress;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public String getToAddress() {
				return toAddress;
			}

			public void setToAddress(String toAddress) {
				this.toAddress = toAddress;
			}

			public String getUserName() {
				return userName;
			}

			public void setUserName(String userName) {
				this.userName = userName;
			}

			public String getSubject() {
				return subject;
			}

			public void setSubject(String subject) {
				this.subject = subject;
			}

			public String getContent() {
				return content;
			}

			public void setContent(String textContent) {
				this.content = textContent;
			}

			public String getMailContentType() {
				return mailContentType;
			}

			public void setMailContentType(String mailContentType) {
				this.mailContentType = mailContentType;
			}
}
