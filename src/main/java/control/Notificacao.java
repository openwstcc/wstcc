package control;

import javax.mail.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.NotificacaoDAO;
import dao.NotificacaoDAOImplementation;
import model.Duvida;
import util.NoticacaoDuvidas;

public class Notificacao {
	NotificacaoDAO dao = new NotificacaoDAOImplementation();
	List<NoticacaoDuvidas> nds = new ArrayList<NoticacaoDuvidas>();
	public String emailServer = "XXXX@hotmail.com";
	public String senhaServer = "XXXX";

	public void notificarNovaResposta() {
		nds = dao.notificarDuvidaRespostaRelacionadaMateriaData();
		for (NoticacaoDuvidas nd : nds) {
			EnviarEmail(nd.getNomeUsuario(), nd.getEmailUsuario(), nd.getMateriaDuvida(), nd.getTituloDuvida(),
					nd.getQuantidadeResposta());
			;
		}
	}

	public void EnviarEmail(String usuario,String email,String materia,String titulo, int quant ) {
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");


		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailServer, senhaServer);
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailServer));

			Address[] toUser = InternetAddress.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Nova Duvida da matéria "+materia);
			message.setText("Olá "+usuario+" a matéria "+materia+" teve uma nova dúvida,"+titulo+", até o momento tem "+quant+" respostas, gostaria de comentar?");
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
