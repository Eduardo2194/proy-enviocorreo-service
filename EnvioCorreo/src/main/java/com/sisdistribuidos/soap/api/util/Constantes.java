package com.sisdistribuidos.soap.api.util;

import java.io.Serializable;

public class Constantes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String MAIL_SMTP_HOST ="mail.smtp.host";
	public static final String MAIL_SMTP_USER ="mail.smtp.user";
	public static final String MAIL_SMTP_CLAVE ="mail.smtp.clave";
	public static final String MAIL_SMTP_AUTH ="mail.smtp.auth";
	public static final String MAIL_SMTP_STARTTLS ="mail.smtp.starttls.enable";
	public static final String MAIL_SMTP_PORT ="mail.smtp.port";	
	public static final String TRUE ="true";	
	public static final String MAIL_SMTP_GMAIL ="smtp.gmail.com";	
	public static final String PORT ="587";	
	public static final String SMTP ="smtp";	
	public static final String VACIO = "";
	
	public static final String PLANTILLA_MSJ = "<p><span style=\"color: #333399;\"><strong>Hola [usuario], su recarga virtual &nbsp;se realizo de manera exitosa:</strong></span></p>\r\n" + 
			"<p><span style=\"color: #333399;\"><strong>C&oacute;digo de usuario: [codUsuario]</strong></span></p>\r\n" + 
			"<p><span style=\"color: #333399;\"><strong>Nro Operaci&oacute;n: [nrooperacion]</strong></span></p>\r\n" + 
			"<p><span style=\"color: #333399;\"><strong>Fecha: [fecha]</strong></span></p>\r\n" + 
			"<p><span style=\"color: #333399;\"><strong>Monto:&nbsp;&nbsp;S/. [monto]</strong></span></p>\r\n" + 
			"<p>&nbsp;</p>\r\n" + 
			"<p><a href=\"https://imgbb.com/\"><img src=\"https://i.ibb.co/njvmkbK/Screenshot-1.png\" alt=\"Screenshot-1\" width=\"122\" height=\"74\" border=\"0\" /></a></p>";
	
	
}
