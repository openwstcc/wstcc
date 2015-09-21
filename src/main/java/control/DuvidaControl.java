package control;

import com.google.gson.Gson;

import dao.DuvidaDAOImplementation;
import model.Duvida;
import model.JsonDuvida;
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
