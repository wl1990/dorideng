package com.bxs.server.util;

import java.io.File;     
import java.io.UnsupportedEncodingException; 
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;




/**
 * 邮件发送辅助类
 * @author RingoHu
 *
 */
public class SendEmailUtil {
	private static ResourceBundle rb = ResourceBundle.getBundle("system");
	private static final JavaMailSenderImpl senderImpl= new JavaMailSenderImpl();
	public static final String MAIL_CONTEXT_TYPE_HTML = "HTML";
	public static final String MAIL_CONTEXT_TYPE_TEXT = "TEXT";
	private static final String mailServerHost;
	private static final String mailServerPort;
	private static final String userName;
	private static final String passWord;
//	private static final String fromAddress;
	static{
		mailServerHost =rb.getString("mailServerHost"); 
        mailServerPort = rb.getString("mailServerPort");
        userName = rb.getString("userName");
        passWord = rb.getString("passWord");
//        fromAddress = rb.getString("fromAddress");
	}
	static {
		senderImpl.setHost(mailServerHost);
		senderImpl.setPort(Integer.parseInt(mailServerPort));
		senderImpl.setUsername(userName);
		senderImpl.setPassword(passWord);
		Properties props=System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", "25000") ; 
		senderImpl.setJavaMailProperties(props);
//		senderImpl.set
	}
	
	/**
	 * 初始化mail server
	 */
//	static {
//		senderImpl.setHost("smtp.exmail.qq.com");
//		senderImpl.setPort(25);
//		senderImpl.setUsername("support@lingdongnet.net");
//		senderImpl.setPassword("lingdongcms123");
//	}

	/**
	 * 给一个或多个人发送邮件
	 * @param emailAll 所有收件人的Email地址,以逗号隔开
	 * @param title 邮件主题
	 * @param context 邮件内容,context必须为HTML格式字符串
	 * @param files 所有附件的路径,以逗号隔开,可以为空
	 * @param object 
	 */
	public static void sendEmail(String emailAll, String sender, String title, String context, String files) {
		String[] emails = emailAll.split(",");
		MimeMessage[] mimeMessages = new MimeMessage[emails.length];
		for(int i=0; i<emails.length; i++) {
			String email = emails[i];
			mimeMessages[i] = createMimeMessage(email, sender, title, context, files);
		}
		senderImpl.send(mimeMessages);
	}
	
	/**
	 * 创建一条MimeMessage
	 * @param email 一个收件人邮箱
	 * @param title	主题
	 * @param context 邮件内容
	 * @param files	附件的路径
	 * @return 返回创建的MimeMessage
	 */
	private static MimeMessage createMimeMessage(String email, String sender, String title,
			String context, String files) {
		
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// true表示开始附件模式
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			// 设置收件人，寄件人
			messageHelper.setTo(email);
			messageHelper.setFrom(sender);
			messageHelper.setSubject(title);
			messageHelper.setText(context, true);
			
			if(files != null) {
				String[] fileNames = files.split(",");
				for(String fileName : fileNames) {
					File file = new File(fileName);
					FileSystemResource fileRS = new FileSystemResource(file);
					messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), fileRS);
				}
				
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return mailMessage;
	}

	/**
	 * 用spring mail 发送邮件,依赖jar：spring.jar，activation.jar，mail.jar
	 * @return 发送验证邮件,同时返回邮件激活验证码以便保存
	 * @throws Exception 
	 */
	public static String sendEmail4Verify(String email, String sender, String basepath) {

		String title = "新用户激活信！";
		// true 表示启动HTML格式的邮件

		String finalCode = getFinalCode(email);

		String context = "<div><br></div><div><includetail><style type=\"text/css\"> &lt;!-- .te2 {WIDTH: 80px;	HEIGHT: 17px;BACKGROUND-COLOR: #ffffff;border: 1px solid #A6BEE0;}.font_line28 {line-height: 32px; ; ;}.font_blue14_bold {color:#1369BF;font-weight: bold;	font-size: 14px;line-height: 23px;} .font_blue122_bold {color:#4D728F;font-weight: bold;;} .font_red12 { font-size:12px;color:#FF0000;} .fant_hui12 { font-size:12px;color:#B9B9B9;} .font_blue12{color:#4D728F;	padding-right: 7px;} .font_blue12 a:link{ color:#4D728F; text-decoration:underline;} .font_blue12 a:visited{color:#4D728F; text-decoration:underline;} .font_blue12 a:hover {color:#4D728F; text-decoration:underline;} --&gt; </style> <table width=\"500\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"#0BA5EB\"> <tbody><tr>  <td align=\"left\" valign=\"top\" bgcolor=\"#FFFFFF\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tbody><tr> <td width=\"39\" height=\"26\" background=\"http://ucenter.51cto.com/images/reg_r2_c4.jpg\"><img src=\"http://ucenter.51cto.com/images/reg_r2_c21.jpg\" width=\"34\" height=\"26\"></td> <td width=\"577\" align=\"left\" valign=\"bottom\" background=\"http://ucenter.51cto.com/images/reg_r2_c4.jpg\" class=\"font_blue14_bold\">确认邮件</td> <td width=\"32\" align=\"left\" valign=\"middle\" background=\"http://ucenter.51cto.com/images/reg_r2_c4.jpg\"><img src=\"http://ucenter.51cto.com/images/gif-0081.gif\" width=\"23\" height=\"17\"></td> </tr> </tbody></table></td></tr> <tr> <td align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\"><table width=\"92%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody><tr>  <td height=\"12\"></td></tr>  <tr>  <td align=\"left\" valign=\"top\"><span class=\"font_red12\">"
								+ email
								+ "</span>，您好：<br>&nbsp;&nbsp;&nbsp; 欢迎加入我站，使用CMS网站免费发布系统，请妥善保管您的登录信息：<br>&nbsp;&nbsp;&nbsp; 用户名：<span class=\"font_red12\">"
								+ email
								+ " </span>&nbsp;&nbsp;&nbsp; 密码：<span class=\"font_red12\">******</span></td> </tr> <tr>  <td height=\"13\" align=\"left\" valign=\"top\"></td>  </tr>  <tr> <td height=\"13\" align=\"left\" valign=\"top\" background=\"http://ucenter.51cto.com/images/reg_line.jpg\"></td> </tr>   <tr> <td align=\"left\" valign=\"top\"><span class=\"font_blue122_bold\">点击以下激活链接，以激活您的账号：</span><br> <a href=\""
								+ basepath
								+ "/show/member/AfterActivate/"
								+ finalCode
								+ "\" class=\"font_blue12\" target=\"_blank\">"
								+ basepath
								+ "/show/member/AfterActivate/"
								+ finalCode
								+ "</a><span class=\"fant_hui12\"><br> (如果不能点击该链接地址，请复制并粘贴到浏览器的地址输入框)</span></td>  </tr> <tr>  <td height=\"13\" align=\"left\" valign=\"top\"></td></tr> <tr> <td height=\"13\" align=\"left\" valign=\"top\" background=\"http://ucenter.51cto.com/images/reg_line.jpg\"></td> </tr><tr> <td align=\"left\" valign=\"top\"><p> 此邮件为系统所发，请勿直接回复。&nbsp; &nbsp;  <br>    <a href=\"http://www.lingdongnet.net\" class=\"font_blue12\" target=\"_blank\">www.lingdongnet.net</a>  技术成就梦想<br>社区专线：xxxxxxxxx (8:30-19:00) &nbsp; <br>社区邮箱：xx@lingdongnet.net<br>  </p>    </td>  </tr>  <tr>  <td align=\"left\" valign=\"top\">&nbsp;</td>   </tr> </tbody></table> </td></tr></tbody></table></includetail></div>";

		sendEmail(email, sender, title, context, null);

		return finalCode;
	}

	public static String sendEmail4ChangePassword(String email, String basePath) {
		
		String title = "找回您的帐户密码";
		String finalCode = getFinalCode(email);
		String context = "<div><span style=\"line-height: 1.5;\">亲爱的用户 " +
				email +
				"：您好！</span></div><div><includetail><br>&nbsp;&nbsp;&nbsp; 您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。<br><br>&nbsp;&nbsp;&nbsp; 要使用新的密码, 请使用以下链接启用密码。<br><br>&nbsp;&nbsp;&nbsp; " +
				basePath + "?identity=" + finalCode +
				"<br><br>&nbsp;&nbsp;&nbsp; (如果无法点击该URL链接地址，请将它复制并粘帖到浏览器的地址输入框，然后单击回车即可。该链接使用后将立即失效。)<br>&nbsp;&nbsp;&nbsp; <br>&nbsp;&nbsp;&nbsp; 注意:请您在收到邮件24小时内使用，否则该链接将会失效。<br><br></includetail></div>";
		
		sendEmail(email,userName, title, context, null);

		return finalCode;
	}

	/**
	 * 
	 * @param email
	 * @return 根据email生成邮件激活验证码
	 */
	public static String getFinalCode(String email) {
		String code = ShortUrlGenerator.getMD5OfStr(email);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + UUID.randomUUID().toString().replaceAll("-", "");
		if (uuid.length() > 50) {
			uuid = uuid.substring(0, 50);
		}
		
		String finalCode = code + uuid;
		return finalCode;
	}

}
