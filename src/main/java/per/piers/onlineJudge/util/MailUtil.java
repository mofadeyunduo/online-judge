package per.piers.onlineJudge.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    private MailUtil() {
    }

    public static void sendEmail(String email, String subject, String content) throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        final Properties properties = new Properties();
        try (InputStream inputStream = MailUtil.class.getClassLoader().getResourceAsStream("config/mail/mail.properties");) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = properties.getProperty("mail.username");
        String password = properties.getProperty("mail.password");
        String domain = properties.getProperty("mail.domain");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.username"), password);
            }

        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username + "@" + domain));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        msg.setSubject(subject);
        msg.setText(content);
        msg.setSentDate(new Date());
        Transport.send(msg);
    }

//    public static void main(String[] args) {
//        MailUtil mailUtil = new MailUtil();
//        try {
//            mailUtil.sendEmail("609092186@qq.com", "HI","HI");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

}
