package com.ithome.web.EmailController;

import com.ithome.web.Constances.Constance;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


public class SendMail {


    public static boolean send(String sendName, String senderPhoneNumber, String senderEmailAddress, String body) {
        String subject = "Նոր էլ. Փոստ ՝ " + sendName + " oferta.am ղեկավարներին ";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", Constance.HOST);
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty(Constance.SOCKET_FACTORY, Constance.PORT);
        props.put("mail.smtp.host", Constance.HOST);//change accordingly
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Constance.USERNAME, Constance.PASSWORD);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constance.USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(Constance.USERNAME));
            message.setSubject(subject, "UTF-8");
            message.setText(body + "\n\n\n Հարգանքներով \n Ուղարկողի էլ. փոստի հասցեն ՝ "
                            + senderEmailAddress + " \nՀեռախոսահամարն է " + senderPhoneNumber + "\n Դուք ստանում եք այս հաղորդագրությունը Oferta.am- ից"
                    , "UTF-8");

            //3rd step)send message
            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public static boolean sendBank(String senderEmailAddress, String body) {
        String subject = "Նոր էլ. Փոստ ՝ oferta.am ղեկավարներին ";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "mail.oferta.am");
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty(Constance.SOCKET_FACTORY, Constance.PORT);
        props.put("mail.smtp.host", "mail.oferta.am");//change accordingly
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("info@oferta.am", Constance.PASSWORD);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@oferta.am"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(senderEmailAddress));
            message.setSubject(subject, "UTF-8");
            message.setText(body + "\n\n\n Հարգանքներով \n Ուղարկողի էլ. փոստի հասցեն ՝ "
                            + senderEmailAddress + " \nՀեռախոսահամարն է Դուք ստանում եք այս հաղորդագրությունը Oferta.am- ից"
                    , "UTF-8");

            //3rd step)send message
            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


      /*  // Recipient's email ID needs to be mentioned.
        String to = senderEmailAddress;
        // Sender's email ID needs to be mentioned
        String from = "info@oferta.am";
        // Assuming you are sending email from localhost
        String host = "mail.oferta.am";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        //properties.setProperty(Constance.SOCKET_FACTORY, "143");
        properties.setProperty("pop.oferta.am", host);


        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(to));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            // Set Subject: header field
            message.setSubject("oferta.am ");
            // Now set the actual message
            message.setText(body);
            Transport.send(message);
            System.out.println("Done");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return false;*/

    }
}
