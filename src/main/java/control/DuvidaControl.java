package control;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DuvidaDAOImplementation;
import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;
import util.JsonDuvida;
import dao.DuvidaDAO;

/**
 * Duvida Controller responsável pelos métodos de dúvidas. Estes métodos são
 * disponibilizados a partir de Webservices e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
@Path("duvidas")
public class DuvidaControl {

	private Gson objects = new Gson();
	DuvidaDAO dao = new DuvidaDAOImplementation();

	@POST
	@Path("adicionarDuvida")
	@Produces("application/json; charset=utf-8")
	public Response adicionarDuvida(String jsonDuvida) {
		JsonDuvida temp = objects.fromJson(jsonDuvida, JsonDuvida.class);
		Duvida d = new Duvida();
		d.setIdDuvida(temp.getIdDuvida());
		d.setTitulo(temp.getTitulo());
		d.setConteudo(temp.getConteudo());
		d.setDataCriacao(temp.getDataCriacao());
		int idUsuario = temp.getIdUsuario();
		int[] materias = temp.getMaterias();

		if (temp.getTags() == null) {
			TagControl tagController = new TagControl();
			String[] tags = temp.getTags();
			int[] idsTags = tagController.inserirTags(tags);
			boolean retorno = dao.adicionarDuvida(d, idUsuario, materias, idsTags);
			return Response.status(200).entity(retorno).build();
		} else {
			boolean retorno = dao.adicionarDuvida(d, idUsuario, materias, null);
			return Response.status(200).entity(retorno).build();
		}		
	}

	@POST
	@Path("buscarDuvidasMateria")
	@Produces("application/json; charset=utf-8")
	public Response buscarDuvidasMateria(String jsonMateria) {
		Materia m = objects.fromJson(jsonMateria, Materia.class);
		List<Duvida> duvidas = dao.buscarDuvidasMateria(m.getIdMateria());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return Response.status(200).entity(json).build();
	}

	@POST
	@Path("buscarDuvidasUsuario")
	@Produces("application/json; charset=utf-8")
	public Response buscarDuvidasUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Duvida> duvidas = dao.buscarDuvidasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return Response.status(200).entity(json).build();
	}

	@POST
	@Path("buscarDuvidasTag")
	@Produces("application/json; charset=utf-8")
	public Response buscarDuvidasTag(String jsonTag) {
		Tag t = objects.fromJson(jsonTag, Tag.class);
		List<Duvida> duvidas = dao.buscarDuvidasTags(t.getIdTag());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("buscarDuvidas")
	@Produces("application/json; charset=utf-8")
	public Response buscarDuvidas() {		
		List<Duvida> duvidas = dao.buscarDuvidas();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return Response.status(200).entity(json).build();
	}
	@POST
	@Path("buscarDuvidasMateriaPorUsuario")
	@Produces("application/json; charset=utf-8")
	public Response buscarDuvidasMateriaPorUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Duvida> duvidas = dao.buscarDuvidasUsuarioRelacionadoMateria(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return Response.status(200).entity(json).build();
	}
	
}
