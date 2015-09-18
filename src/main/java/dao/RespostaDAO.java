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
	 * @param id_duvida
	 *            ID da Dúvida relacionada.
	 * @param id_usuario
	 *            ID do Usúario Relacionado.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarResposta(Resposta r, int id_duvida, int id_usuario);

	/**
	 * Método utilizado para buscar todas as Respostas relacionadas a uma
	 * Dúvida.
	 * 
	 * @param id_duvida
	 *            ID da Dúvida
	 * @return Lista de Respostas relacionadas com a Dúvida.
	 */
	public List<Resposta> buscarRespostas(int id_duvida);

	/**
	 * Método utilizado para adicionar rank a uma reposta.
	 * 
	 * @param id_resposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionaRank(int id_resposta);

	/**
	 * Método utilizado para alterar Flag de resposta que teve rank acrescido do
	 * mesmo criador da Dúvida, este sendo um aluno.
	 * 
	 * @param id_resposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alteraFlagAluno(int id_resposta);

	/**
	 * Método utilizado para alterar Flag de resposta que teve rank acrescido do
	 * mesmo criador da Dúvida, este sendo um professor.
	 * 
	 * @param id_resposta
	 *            ID da Resposta.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alteraFlagProfessor(int id_resposta);
}