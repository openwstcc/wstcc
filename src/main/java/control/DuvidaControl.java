package control;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DuvidaDAOImplementation;
import model.Duvida;
import model.JsonDuvida;
import model.Materia;
import model.Tag;
import model.Usuario;
import dao.DuvidaDAO;

/**
 * Duvida Controller responsável pelos métodos de dúvidas. Estes métodos são
 * disponibilizados a partir do Webservice e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
public class DuvidaControl {

	private Gson objects = new Gson();
	DuvidaDAO dao = new DuvidaDAOImplementation();

	public boolean adicionarDuvida(String jsonDuvida) {
		JsonDuvida temp = objects.fromJson(jsonDuvida, JsonDuvida.class);
		Duvida d = new Duvida();
		d.setIdDuvida(temp.getIdDuvida());
		d.setTitulo(temp.getTitulo());
		d.setConteudo(temp.getConteudo());
		d.setDataCriacao(temp.getDataCriacao());
		int idUsuario = temp.getIdUsuario();
		int[] materias = temp.getMaterias();
		int[] tags = temp.getTags();
		return dao.adicionarDuvida(d, idUsuario, materias, tags);
	}

	public String buscarDuvidasMateria(String jsonMateria) {
		Materia m = objects.fromJson(jsonMateria, Materia.class);
		List<Duvida> duvidas = dao.buscarDuvidasMateria(m.getIdMateria());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return json;	
	}

	public String buscarDuvidasUsuario(String jsonUsuario) {
		Usuario u = objects.fromJson(jsonUsuario, Usuario.class);
		List<Duvida> duvidas = dao.buscarDuvidasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return json;
	}

	public String buscarDuvidaTag(String jsonTag) {
		Tag t = objects.fromJson(jsonTag, Materia.class);
		List<Duvida> duvidas = dao.buscarDuvidasTags(t.getIdTag());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(duvidas);
		return json;
	}
}
