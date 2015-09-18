package control;

/**
 * Resposta Controller responsável pelos métodos de respostas. Estes métodos são
 * disponibilizados a partir do Webservice e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
public class RespostaControl {
	public boolean adicionarResposta(String jsonResposta) {
		return false;
	}

	public String buscarRespostas() {
		return null;
	}

	/**
	 * Adiciona Rank para a resposta selecionada. Caso o usuário que esteja
	 * adicionando Rank seja criador da duvida, altere a flag de criador para
	 * "TRUE" Caso o usuário que esteja adicionando Rank seja professor da
	 * duvida, altere a flag de professor para "TRUE"
	 */

	public void adicionarRank(String jsonResposta) {

	}

}
