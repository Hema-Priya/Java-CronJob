package cron123;

//Java program to send email 

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Mail {

    private String from;
    private String to;
    private String subject;
    private String messageBody;
    private String fileName;
    private String host;

    private Properties properties;

    private MimeMessage message;
    private BodyPart messageBodyPart;
    private Multipart multipart;

    private Authenticator authenticator;
    
    public Mail(String r) {
        from = "your_email@gmail.com";
        to = r;
        subject = "HAPPY BIRTHDAY";
        messageBody = "<html><body> <h1>HAPPY BIRTHDAY" +
                    "STAY BLESSED</h1></body></html>";
        fileName = "E:\\Workshop\\eclipse\\cron\\src\\cron123\\file.txt";
        host = "smtp.gmail.com";

        authenticator = new SMTPAuthenticator ();
        properties = System.getProperties ();
        properties.put ( "mail.smtp.host", host );
        properties.put ( "mail.smtp.starttls.enable", "true" );
        properties.put ( "mail.smtp.port", "587" );
        properties.put ( "mail.smtp.auth", "true" );
    }

    

	private void sendMail ( String from, String to,
                    String subject, String messageBody, String fileName ) {
        try {
            Session session = Session.getDefaultInstance ( properties, authenticator );
            message = new MimeMessage ( session );
            message.setFrom ( new InternetAddress ( from ) );
            message.addRecipient ( Message.RecipientType.TO,
                                new InternetAddress ( to ) );
            message.setSubject ( subject );

            multipart = new MimeMultipart ();
            messageBodyPart = new MimeBodyPart ();
            messageBodyPart.setContent ( messageBody, "text/html" );
            multipart.addBodyPart ( messageBodyPart );

            messageBodyPart = new MimeBodyPart ();
            DataSource source = new FileDataSource ( fileName );
            messageBodyPart.setDataHandler ( new DataHandler ( source ) );
            messageBodyPart.setFileName ( fileName );
            multipart.addBodyPart ( messageBodyPart );

            message.setContent ( multipart );

            Transport.send ( message );
            System.out.println("Success");
            
        } catch ( Exception me ) {
            me.printStackTrace ();
        }
		
    } 

    public void performTask () {
        sendMail ( from, to, subject, messageBody, fileName );
    }
  
   
}

/**
  * SimpleAuthenticator is used to do simple authentication
  * when the SMTP server requires it.
  */

class SMTPAuthenticator extends Authenticator {

    private static final String SMTP_AUTH_USER = "youremail@gmail.com";
    private static final String SMTP_AUTH_PASSWORD = "xxxxxxxxx";

    public PasswordAuthentication getPasswordAuthentication () {
    	String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PASSWORD;

        return new PasswordAuthentication( username,  password );
    }
}
    