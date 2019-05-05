package com.shangyong.backend.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangyong.backend.bo.MailSenderInfo;
import com.shangyong.backend.bo.MyAuthenticator;

public class SimpleMailSender {

    private static Logger logger = LoggerFactory.getLogger("jinong");

    private static String[] toAddressStr = {"381429200@qq.com", "412354337@qq.com", "malujun999@163.com", "fengzheng7453853@163.com", "961084497@qq.com"};
    private static String[] ccAddressStr = {"xiexy30@sina.com"};

    private static String mailServerPort = "25";
    private static String userName = "";
    private static String password = "";

    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public static boolean sendTextMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 以HTML格式发送邮件
     *
     * @param mailInfo 待发送的邮件信息
     */
    public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    /**
     * 以文本格式发送邮件(批量发送)
     *
     * @return
     * @throws Exception
     */
    public static boolean batchSendTextMail(String subject, String content) {

        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.exmail.qq.com");
        mailInfo.setMailServerPort(mailServerPort);
        mailInfo.setValidate(true);
        mailInfo.setUserName(userName);
        mailInfo.setPassword(password);//您的邮箱密码
        mailInfo.setFromAddress(userName);
        // 需要身份认证，创建一个密码验证器
        MyAuthenticator authenticator = new MyAuthenticator(userName, password);

        Properties prop = mailInfo.getProperties();
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(prop, authenticator);

        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(userName);
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);

            // 设置收件人们
            final int toAddressNum = toAddressStr.length;
            InternetAddress[] toAddress = new InternetAddress[toAddressNum];
            for (int i = 0; i < toAddressNum; i++) {
                toAddress[i] = new InternetAddress(toAddressStr[i]);
            }

            Address[] ccAddress = new InternetAddress[ccAddressStr.length];
            for (int index = 0; index < ccAddressStr.length; index++) {
                ccAddress[index] = new InternetAddress(ccAddressStr[index]);
            }
            // 设置邮件消息的接收者，发送，抄送
            mailMessage.setRecipients(Message.RecipientType.TO, toAddress);
            mailMessage.setRecipients(Message.RecipientType.CC, ccAddress);

            // 设置邮件消息的主题
            mailMessage.setSubject(subject);
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(Calendar.getInstance().getTime());
            // 设置邮件消息的主要内容
            mailMessage.setText(content);
            // 发送邮件
            Transport.send(mailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败");
            e.printStackTrace();
            return false;
        }
        logger.info("邮件发送成功");
        return true;
    }


    /**
     * 以HTML格式发送邮件(批量发送)
     *
     * @param mailInfo 邮件信息
     *                 mailType 邮件类型 1-error；2-warning；（默认）3-notify；
     * @return
     * @throws Exception
     */
    public static boolean batchSendHtmlMail(MailSenderInfo mailInfo) {

        // 需要身份认证，创建一个密码验证器
        MyAuthenticator authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());

        Properties prop = mailInfo.getProperties();
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(prop, authenticator);

        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getUserName());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);

            // 创建邮件的接收者地址 to：发送；cc：抄送
            // 设置收件人们
            final int toAddressNum = toAddressStr.length;
            InternetAddress[] toAddress = new InternetAddress[toAddressNum];
            for (int i = 0; i < toAddressNum; i++) {
                toAddress[i] = new InternetAddress(toAddressStr[i]);
            }

            Address[] ccAddress = new InternetAddress[ccAddressStr.length];
            for (int index = 0; index < ccAddressStr.length; index++) {
                ccAddress[index] = new InternetAddress(ccAddressStr[index]);
            }

            // 设置邮件消息的接收者，发送，抄送
            mailMessage.setRecipients(Message.RecipientType.TO, toAddress);
            mailMessage.setRecipients(Message.RecipientType.CC, ccAddress);

            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(Calendar.getInstance().getTime());

            // MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart multiPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart bodyPart = new MimeBodyPart();
            // 设置html邮件消息内容
            bodyPart.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            multiPart.addBodyPart(bodyPart);
            // 设置邮件消息的主要内容
            mailMessage.setContent(multiPart);
            // 发送邮件
            Transport.send(mailMessage);

        } catch (Exception e) {
            logger.error("邮件发送失败");
            e.printStackTrace();
            return false;
        }
        logger.info("邮件发送成功");
        return true;
    }
}
