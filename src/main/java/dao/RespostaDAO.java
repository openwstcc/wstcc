package dao;

import java.util.List;

import model.Resposta;

/**
 * Interface DAO (Data Access Object) responsável pelos métodos de Respostas.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public interface RespostaDAO {
	/**
	 * Método para inserção de novas respostas informando Dúvida e Usuário.
	 * 
	 * @param r
	 *            Reposta
	 * @param idDuvida
	 *            ID da Dúvida relacionada.
	 * @param idUsuario
	 *            ID do Usúario Relacionado.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarResposta(Resposta r);

	/**
	 * Método utilizado para buscar todas as Respostas relacionadas a uma
	 * Dúvida.
	 * 
	 * @param idDuvida
	 *            ID da Dúvida
	 * @return Lista de Respostas relacionadas com a Dúvida.
	 */
	public List<Resposta> buscarRespostas(int idDuvida, int usuarioLogado);

	/**
	 * Método utilizado para adicionar rank a uma reposta.
	 * 
	 * @param idResposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionaRank(int idResposta,int idUsuario,boolean validarResp);

	/**
	 * Método utilizado para alterar Flag de resposta que teve rank acrescido do
	 * mesmo criador da Dúvida, este sendo um aluno.
	 * 
	 * @param idResposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alteraFlagAluno(int idResposta);

	/**
	 * Método utilizado para alterar Flag de resposta que teve rank acrescido do
	 * mesmo criador da Dúvida, este sendo um professor.
	 * 
	 * @param idResposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alteraFlagProfessor(int idResposta);
}