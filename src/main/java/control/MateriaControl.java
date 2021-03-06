package control;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
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

	@POST
	@Path("buscarMateriasDuvida")
	@Produces("application/json; charset=utf-8")
	public Response buscarMateriasDuvida(String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Materia> materias = dao.buscarMateriasDuvida(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return Response.status(200).entity(json).build();
	}
	
	@POST
	@Path("buscarMateriasUsuario")
	@Produces("application/json; charset=utf-8")	
	public Response buscarMateriasUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Materia> materias = dao.buscarMateriasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return Response.status(200).entity(json).build();
	}
		
	@GET
	@Path("buscarMaterias")
	@Produces("application/json; charset=utf-8")
	public Response buscarMaterias() {
		List<Materia> materias = dao.buscarTodasMaterias();		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);				
		return Response.status(200).entity(json).build();
	}

	@POST
	@Path("atualizarMaterias")		
	public Response atualizarMaterias(String jsonMateria){
		Type listType = new TypeToken<ArrayList<Materia>>(){}.getType();
		List<Materia> materias = objects.fromJson(jsonMateria, listType);
		Materia materia = materias.get(0);
		dao.removerMateriaUsuario(materia.getIdUsuario());
		int retorno = dao.atualizarMateriasUsuario(materias);
		return Response.status(200).entity(retorno).build();
	}
}
