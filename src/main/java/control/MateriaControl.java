package control;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	public String buscarMateriasDuvida(@PathParam("jsonDuvida") String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Materia> materias = dao.buscarMateriasDuvida(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}
	
	@GET
	@Path("buscarMateriasUsuario/{jsonUsuario}")
	@Produces("application/json")	
	public String buscarMateriasUsuario(@PathParam("jsonUsuario") String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Materia> materias = dao.buscarMateriasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}
		
	@GET
	@Path("buscarMaterias")
	@Produces("application/json")
	public String buscarMaterias() {
		List<Materia> materias = dao.buscarTodasMaterias();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	@GET
	@Path("atualizar/{jsonMateria}")	
	public boolean atualizarMaterias(@PathParam("jsonMateria") String jsonMateria){
		JsonMateria temp = objects.fromJson(jsonMateria, JsonMateria.class);
		int idUsuario = temp.getIdUsuario();
		int[] idMaterias = temp.getIdMaterias();
		return dao.atualiarMateriasUsuario(idUsuario, idMaterias);
	}
}
