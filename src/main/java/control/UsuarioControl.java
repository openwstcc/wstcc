package control;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
@Path("usuarios")
public class UsuarioControl {

	private Gson objects = new Gson();
	UsuarioDAO dao = new UsuarioDAOImplementation();

	@PUT
	@Path("inserirDuvida/{jsonUsuario}")
	@Produces("application/json")
	public boolean inserirUsuario(@PathParam("jsonUsuario") String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.adicionarUsuario(u);
		return retorno;
	}

	@GET
	@Path("alterarUsuario/{jsonUsuario}")
	@Produces("application/json")
	public boolean alterarUsuario(@PathParam("jsonUsuario") String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarUsuario(u);
		return retorno;
	}

	@GET
	@Path("alterarSenha/{jsonUsuario}")	
	public boolean alterarSenha(@PathParam("jsonUsuario") String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarSenha(u);
		return retorno;
	}

	@GET
	@Path("todos/{jsonUsuario}")
	@Produces("application/json")
	public String buscarUsuario(@PathParam("jsonUsuario") String jsonUsuario) {		
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		Usuario usuario = dao.buscarUsuario(u);
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(usuario);		
		return json;
	}

}
