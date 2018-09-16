package com.alekseysamoylov.repair.center.service;



import org.springframework.stereotype.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@org.springframework.stereotype.Service
public class MailSendFile {
    static final String ENCODING = "UTF-8";

//    public void sendMess() throws MessagingException, UnsupportedEncodingException {
//        String subject = "Автосервис КАМЕНКА";
//        String content = "База";
//        String smtpHost="smtp.rambler.ru";
//        String addressFrom="alcoslesar@rambler.ru";
//        String addressTo="alekseysamoylov89@gmail.com";
//        String login="alcoslesar";
//        String password="890280a";
//        String smtpPort="25";
//
//        String attachment = "works.ser";
//        sendMultiMessage(login, password, addressFrom, addressTo, content, subject, attachment, smtpPort, smtpHost);
//    }

    public void sendMessage(String subject, String content) throws MessagingException, UnsupportedEncodingException {

        String smtpHost="smtp.rambler.ru";
        String addressFrom="alcoslesar@rambler.ru";
        String addressTo="alekseysamoylov89@gmail.com";
        String login="alcoslesar";
        String password="890280a";
        String smtpPort="25";

//        String attachment = "works.ser";
        sendSimpleMessage(login, password, addressFrom, addressTo, content, subject, smtpPort, smtpHost);
    }

    private void sendSimpleMessage(String login, String password, String from, String to, String content, String subject, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);

        Authenticator auth = new MyAuthenticator(login, password);
        Session session = Session.getDefaultInstance(props, auth);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setRecipient(Message.RecipientType.CC, new InternetAddress("repaircarcenter@gmail.com"));
        msg.setSubject(subject);
        msg.setText(content);
        Transport.send(msg);
    }

    private void sendMultiMessage(String login, String password, String from, String to, String content, String subject, String attachment, String smtpPort, String smtpHost) throws MessagingException, UnsupportedEncodingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", ENCODING);

        Authenticator auth = new MyAuthenticator(login, password);
        Session session = Session.getDefaultInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setRecipient(Message.RecipientType.CC, new InternetAddress("repaircarcenter@gmail.com"));
        msg.setSubject(subject, ENCODING);

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/plain; charset=" + ENCODING + "");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachment);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(MimeUtility.encodeText(source.getName()));
        multipart.addBodyPart(attachmentBodyPart);

        msg.setContent(multipart);

        Transport.send(msg);
    }
}

class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}

