package prueba;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.ws.util.ByteArrayDataSource;

public class EmailPDFGenerado {
	
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
		
		String msgBody = "Hola gordi :)";
		
		try {
			Message msg = new MimeMessage( session );
			msg.setFrom( new InternetAddress( "nada@nada.com" ) );
			msg.setRecipients( Message.RecipientType.TO , InternetAddress.parse( "al259543@uji.es" ) );
			msg.setSubject( "Esto es una prueba 6" );
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText( msgBody );
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart( messageBodyPart );
			
			messageBodyPart = new MimeBodyPart();
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				writePdf( outputStream );				
			}
			catch( Exception e ) {
				e.printStackTrace();
			}
			byte[] bytes = outputStream.toByteArray();
			
			DataSource dataSource = new ByteArrayDataSource( bytes , "application/pdf" );
			messageBodyPart.setDataHandler( new DataHandler( dataSource ) );
			messageBodyPart.setFileName( "teQuiero.pdf" );
			
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

	public static void writePdf(OutputStream outputStream) throws Exception {
	    Document document = new Document();
	    PdfWriter.getInstance(document, outputStream);
	     
	    document.open();
	     
	    document.addTitle("Test PDF");
	    document.addSubject("Testing email PDF");
	    document.addKeywords("iText, email");
	    document.addAuthor("David López");
	    document.addCreator("David López");
	     
	    Paragraph paragraph = new Paragraph();
	    paragraph.add(new Chunk("Hola gorda te amo :)"));
	    document.add(paragraph);
	     
	    document.close();
	}

}
