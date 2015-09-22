package control;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import model.Duvida;
import model.JsonMateria;
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
public class MateriaControl {

	private Gson objects = new Gson();
	MateriaDAO dao = new MateriaDAOImplementation();

	public String buscarMateriasDuvida(String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Materia> materias = dao.buscarMateriasDuvida(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	public String buscarMateriasUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Materia> materias = dao.buscarMateriasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	public String buscarMaterias() {
		List<Materia> materias = dao.buscarTodasMaterias();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	public boolean atualizarMaterias(String jsonMateria){
		JsonMateria temp = objects.fromJson(jsonMateria, JsonMateria.class);
		int idUsuario = temp.getIdUsuario();
		int[] idMaterias = temp.getIdMaterias();
		return dao.atualiarMateriasUsuario(idUsuario, idMaterias);
	}
}
