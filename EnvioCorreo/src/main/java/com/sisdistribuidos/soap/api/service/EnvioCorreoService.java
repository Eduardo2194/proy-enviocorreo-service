package com.sisdistribuidos.soap.api.service;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.sisdistribuidos.soap.api.enviocorreo.GetEnvioCorreoRequest;
import com.sisdistribuidos.soap.api.enviocorreo.GetEnvioCorreoResponse;
import com.sisdistribuidos.soap.api.util.Constantes;

@Service
public class EnvioCorreoService {

	private static final String CLAVE ="AB123456c";	
	private static final String REMITENTE ="metropagoap@gmail.com";	

	public GetEnvioCorreoResponse checkLoanEligibility(GetEnvioCorreoRequest request) {
		GetEnvioCorreoResponse respuesta = new GetEnvioCorreoResponse();
		
    try {
    	
    	//Validar si el mensaje es ingresado como texto o toma plantilla por default
    	if(request.getMensaje() ==null || request.getMensaje().isEmpty()) {
    	
    		MimeMultipart multipart = new MimeMultipart("related");		
    		MimeBodyPart messageBodyPart = new MimeBodyPart();
    		
    		String cuerpo = Constantes.PLANTILLA_MSJ;
    		System.out.println(request.getUsuario());
    	    cuerpo = cuerpo.replace("[usuario]", request.getUsuario());
    	    cuerpo = cuerpo.replace("[codUsuario]", request.getCodUsuario());
    	    cuerpo = cuerpo.replace("[nrooperacion]", request.getNroOperacion());
    	    cuerpo = cuerpo.replace("[fecha]", request.getFecha());
    	    cuerpo = cuerpo.replace("[monto]", request.getMonto());
    		
    	    System.out.println(cuerpo);    
    	    messageBodyPart.setContent(cuerpo, "text/html;charset=UTF-8");
    		multipart.addBodyPart(messageBodyPart);
    	   
    	    enviarConGMail(request.getDestinatario(), request.getAsunto(), multipart,request);
    	
    	}else {
    		System.out.println("mensaje de tipo texto");
    	    enviarConGMail(request.getDestinatario(), request.getAsunto(), null,request);
    	    
    	}
	    	
		respuesta.setCodidoRespuesta("200");
		respuesta.setMensajeRespuesta("Envio exitoso");
	
    } catch (MessagingException e) {
    	respuesta.setCodidoRespuesta("-1");
    	respuesta.setMensajeRespuesta(e.getMessage());
		e.printStackTrace();
    } catch (Exception ex) {
    	respuesta.setCodidoRespuesta("-2");
    	respuesta.setMensajeRespuesta(ex.getMessage());
		ex.printStackTrace();
    }
	    return respuesta;

	}

	
	private static void enviarConGMail(String destinatario, String asunto, MimeMultipart multipart, GetEnvioCorreoRequest request) throws Exception {
	    String remitente = REMITENTE;
	    String clave = CLAVE;
	    Properties props = System.getProperties();
	    props.put(Constantes.MAIL_SMTP_HOST, Constantes.MAIL_SMTP_GMAIL);
	    props.put(Constantes.MAIL_SMTP_USER, remitente);
	    props.put(Constantes.MAIL_SMTP_CLAVE, clave);
	    props.put(Constantes.MAIL_SMTP_AUTH, Constantes.TRUE);
	    props.put(Constantes.MAIL_SMTP_STARTTLS, Constantes.TRUE);
	    props.put(Constantes.MAIL_SMTP_PORT, Constantes.PORT);

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);
	        message.setSubject(asunto);
	        
	        if(request.getMensaje().isEmpty() || request.getMensaje()==null) {
		        message.setContent(multipart);
	        }else {
	        	message.setText(request.getMensaje());
	        }
	        Transport transport = session.getTransport(Constantes.SMTP);
	        transport.connect(Constantes.MAIL_SMTP_GMAIL, remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	        throw new Exception(me.getMessage());
	    }
	}

}
