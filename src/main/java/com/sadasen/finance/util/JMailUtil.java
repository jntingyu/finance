package com.sadasen.finance.util;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的测试程序
 * 
 * @author lwq
 * 
 */
public class JMailUtil {

    public static void main(String[] args) throws MessagingException {
    	sendForUpdatePwd("1769478647@qq.com", "leiys13", UUID.randomUUID().toString());
    }
    
    public static void send(String email, String title, String content) throws MessagingException {
    	 // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.sina.com");
        // 发件人的账号
        props.put("mail.user", "finance_sadasen@sina.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "sds28949613");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(email);
        message.setRecipient(RecipientType.TO, to);
        
        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);
    }
    
    public static void sendForUpdatePwd(String email, String userName, String uuid) throws MessagingException {
    	String title = "萨达森记账系统修改密码验证";
    	String content = "<a href='http://www.sadasen.com:9199/page/validChangePwd?userName=" + userName + "&validId=" + uuid + "'>请点击此处，打开修改密码页面</a><span style='color:red'>一个小时内有效</span>";
        send(email, title, content);
    }
    
    public static void sendForBindingEmail(String email, String userName, String uuid) throws MessagingException {
    	String title = "萨达森记账系统绑定邮箱验证";
    	String content = "<a href='http://www.sadasen.com:9199/check/bindingEmail?userName=" + userName + "&validId=" + uuid + "'>请点击此处，绑定邮箱</a><span style='color:red'>一个小时内有效</span>";
    	send(email, title, content);
    }
    
}
