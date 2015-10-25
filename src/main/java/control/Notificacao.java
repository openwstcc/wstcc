package control;

import javax.mail.Transport;
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
import util.NoticacaoDuvidas;

public class Notificacao {
	NotificacaoDAO dao = new NotificacaoDAOImplementation();
	NoticacaoDuvidas nd = new NoticacaoDuvidas();
	public String email = "XXXX@hotmail.com";
	public String senha = "XXX";

	public void notificarNovaResposta(int idResposta) {
		nd = dao.notificarDuvidaResposta(idResposta);
		EnviarEmail();
	}

	public void EnviarEmail() {
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
				return new PasswordAuthentication(email, senha);
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));

			Address[] toUser = InternetAddress.parse(nd.getEmail());

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Nova Resposta");
			message.setText("A dúvida "+ nd.getTituloDuvida() +" teve uma nova resposta !");
			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
