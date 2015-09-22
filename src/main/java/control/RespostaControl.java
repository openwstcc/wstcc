package control;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RespostaDAO;
import dao.RespostaDAOImplementation;
import json.JsonResposta;
import model.Duvida;
import model.Resposta;

/**
 * Resposta Controller responsável pelos métodos de respostas. Estes métodos são
 * disponibilizados a partir de Webservices e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
public class RespostaControl {

	private Gson objects = new Gson();
	RespostaDAO dao = new RespostaDAOImplementation();

	public boolean adicionarResposta(String jsonResposta) {
		JsonResposta temp = objects.fromJson(jsonResposta, JsonResposta.class);
		Resposta r = new Resposta();
		r.setCriador(temp.getCriador());
		r.setResposta(temp.getResposta());
		r.setRank(temp.getRank());
		r.setDataCriacao(temp.getDataCriacao());
		r.setFlagCriador(temp.isFlagCriador());
		r.setFlagProfessor(temp.isFlagProfessor());
		int idDuvida = temp.getIdDuvida();
		int idUsuario = temp.getIdUsuario();
		return dao.adicionarResposta(r, idDuvida, idUsuario);
	}

	public String buscarRespostas(String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Resposta> respostas = dao.buscarRespostas(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(respostas);
		return json;
	}

	/**
	 * Adiciona Rank para a resposta selecionada. Caso o usuário que esteja
	 * adicionando Rank seja criador da duvida, altere a flag de criador para
	 * "TRUE" Caso o usuário que esteja adicionando Rank seja professor da
	 * duvida, altere a flag de professor para "TRUE".
	 */

	public boolean adicionarRank(String jsonResposta) {
		Resposta r = objects.fromJson(jsonResposta, Resposta.class);
		return dao.adicionaRank(r.getIdResposta());
	}

}
