package control;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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

	@POST
	@Path("inserirUsuario")	
	public Response inserirUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		int retorno = dao.adicionarUsuario(u);		
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("alterarUsuario/{jsonUsuario}")
	@Consumes("application/json")
	public Response alterarUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarUsuario(u);
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("alterarSenha")
	@Consumes("application/json")	
	public Response alterarSenha(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		boolean retorno = dao.alterarSenha(u);
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("buscarUsuario")
	@Produces("application/json; charset=utf-8")
	public String buscarUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		Usuario usuario = dao.buscarUsuario(u);
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(usuario);
		return json;
	}

}
