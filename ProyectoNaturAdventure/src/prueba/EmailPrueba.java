package prueba;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/* Go to Gmail security setting (https://www.google.com/settings/security?hl=en) , under "Password", disable to 2-step verification. Under "Account permission", enable "Access for less secure apps" .
that should do it... */

public class EmailPrueba {
	
	public static void main(String[] args) {
		
		final String username = "david.lopez.castellote@gmail.com";
		final String password = "1blqu6ma";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance( props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication( username, password );
			}
		} );
		
		System.out.println( "1" );
		
		String msgBody = "Hola esto es un mensaje de prueba";
		
		try {
			Message msg = new MimeMessage( session );
			System.out.println( "2" );
			msg.setFrom( new InternetAddress( "nada@nada.com" ) );
			System.out.println( "3" );
			msg.setRecipients( Message.RecipientType.TO , InternetAddress.parse( "david_cs_94@hotmail.com" ) );
			System.out.println( "4" );
			msg.setSubject( "Esto es una prueba 5" );
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText( msgBody );
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart( messageBodyPart );
			
			messageBodyPart = new MimeBodyPart();
			String filename = "C:\\Users\\David\\Downloads\\CharlaAndroid.pdf";
			DataSource source = new FileDataSource( filename );
			messageBodyPart.setDataHandler( new DataHandler( source ) );
			messageBodyPart.setFileName( "CharlaAndroid.pdf" );
			multipart.addBodyPart( messageBodyPart );
			
			msg.setContent( multipart );
			
			//msg.setText( msgBody );
			Transport.send( msg );
			System.out.println( "Done" );
		}
		catch( MessagingException e ) {
			e.printStackTrace();
		}
		
	}

}
