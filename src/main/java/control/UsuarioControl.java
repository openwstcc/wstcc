package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.UsuarioDAO;
import dao.UsuarioDAOImplementation;
import model.Usuario;

/**
 * Usuario Controller responsável pelos métodos de usuários. Estes métodos são
 * disponibilizados a partir do Webservice e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
public class UsuarioControl {

	private Gson objects = new Gson();
	UsuarioDAO dao = new UsuarioDAOImplementation();

	public boolean inserirUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.adicionarUsuario(u);
		return retorno;
	}

	public boolean alterarUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarUsuario(u);
		return retorno;
	}

	public boolean alterarSenha(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarSenha(u);
		return retorno;
	}

	public String buscarUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		Usuario usuario = dao.buscarUsuario(u);
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(usuario);
		return json;
	}

}
