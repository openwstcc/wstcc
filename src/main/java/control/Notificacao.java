package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.DuvidaDAO;
import dao.DuvidaDAOImplementation;
import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import dao.UsuarioDAO;
import dao.UsuarioDAOImplementation;
import model.Duvida;
import model.Materia;
import model.Usuario;

public class Notificacao implements Job {

	List<Usuario> usuarios = new ArrayList<Usuario>();
	UsuarioDAO uDao = new UsuarioDAOImplementation();

	MateriaDAO mDao = new MateriaDAOImplementation();
	List<Materia> materias;

	DuvidaDAO dDao = new DuvidaDAOImplementation();
	List<Duvida> duvidas;

	StringBuilder mensagem = new StringBuilder();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		usuarios = uDao.listaUsuarios();
		for (Usuario u : usuarios) {

			materias = new ArrayList<Materia>();
			materias = mDao.buscarMateriasUsuario(u.getIdUsuario());

			for (Materia m : materias) {

				duvidas = new ArrayList<Duvida>();
				duvidas = dDao.buscarDuvidasUsuarioRelacionadoMateriaData(u.getIdUsuario(), m.getIdMateria());
				if (duvidas.size() != 0)
					mensagem.append("Matéria: " + m.getMateria() + "\n \n");
				
				for (Duvida d : duvidas) {
					if (d.getQtdRespostas() != 0) {
						mensagem.append("A Dúvida '" + d.getTitulo() + "' feita em " + d.getDataCriacao() + " pelo "
								+ d.getCriador() + " teve " + d.getQtdRespostas() + " respostas.\n \n");
					} else {
						mensagem.append("A Dúvida '" + d.getTitulo() + "' feita em " + d.getDataCriacao() + " pelo "
								+ d.getCriador() + " teve nenhuma resposta.\n \n");
					}

				}

			}
			if (mensagem.length() != 0)
				EnviarEmail(u, mensagem);
			mensagem.setLength(0);
		}
	}

	public void EnviarEmail(Usuario u, StringBuilder mensagem) {

		final String username = "openwstcc@gmail.com";
		final String password = "tccfateczl01";
		Properties props = new Properties();
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			Address[] toUser = InternetAddress.parse(u.getEmail());

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Relatório das dúvida relacionadas");
			message.setText("Olá " + u.getNome() + ", \n \n"
					+ "Algumas matérias que você se relacionou tiveram dúvidas que foram comentadas, são elas: \n \n"
					+ mensagem.toString() + "\n\n Atenciosamente,\n \n openwstcc");

			Transport.send(message);
			System.out.println("E-mail enviado...!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}