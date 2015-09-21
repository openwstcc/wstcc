package control;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DuvidaDAOImplementation;
import model.Duvida;
import model.DuvidaMateria;
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
		DuvidaMateria dm = objects.fromJson(jsonDuvida, DuvidaMateria.class);
		Duvida d = dm.getD();
		int idUsuario = dm.getIdUsuario();
		List<Materia> materias = dm.getMaterias();
		List<Tag> tags = dm.getTags();
		dao.adicionarDuvida(d, idUsuario, materias, tags);

		return true;
	}

	public boolean alterarDuvida(String jsonDuvida) {
		return false;
	}

	public String buscarDuvidasMateria() {
		return null;
	}

	public String buscarDuvidasUsuario() {
		return null;
	}

	public String buscarDuvidaTags() {
		return null;
	}
}
