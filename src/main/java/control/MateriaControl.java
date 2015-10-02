package control;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import json.JsonMateria;
import model.Duvida;
import model.Materia;
import model.Usuario;

/**
 * Materia Controller responsável pelos métodos de matérias. Estes métodos são
 * disponibilizados a partir de Webservices e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
@Path("materias")
public class MateriaControl {

	private Gson objects = new Gson();
	MateriaDAO dao = new MateriaDAOImplementation();

	@GET
	@Path("buscarMateriasDuvida/{jsonDuvida}")
	@Produces("application/json")
	public Response buscarMateriasDuvida(@PathParam("jsonDuvida") String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Materia> materias = dao.buscarMateriasDuvida(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return Response.status(200).entity(json).build();
	}
	
	@GET
	@Path("buscarMateriasUsuario/{jsonUsuario}")
	@Produces("application/json")	
	public Response buscarMateriasUsuario(@PathParam("jsonUsuario") String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Materia> materias = dao.buscarMateriasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return Response.status(200).entity(json).build();
	}
		
	@GET
	@Path("buscarMaterias")
	@Produces("application/json")
	public Response buscarMaterias() {
		List<Materia> materias = dao.buscarTodasMaterias();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);		
		return Response.status(200).entity(json).build();
	}

	@POST
	@Path("atualizarMaterias")	
	@Consumes("application/json")
	public Response atualizarMaterias(String jsonMateria){
		JsonMateria temp = objects.fromJson(jsonMateria, JsonMateria.class);
		int idUsuario = temp.getIdUsuario();
		int[] idMaterias = temp.getIdMaterias();
		boolean retorno = dao.atualiarMateriasUsuario(idUsuario, idMaterias);
		return Response.status(200).entity(retorno).build();
	}
}
