package dao;

import model.Duvida;
import java.util.List;

/**
 * Interface DAO (Data Access Object) responsável pelos métodos de Dúvidas.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 */
public interface DuvidaDAO {
	/**
	 * Método para inserção de novas dúvidas informando Usuário, materia e tags
	 * relacionadas. Este método também irá adicionar uma relação entre Duvida e
	 * Tag, e Materia e tag.
	 * 
	 * @param d
	 *            Dúvida
	 * @param idUsuario
	 *            ID do Usuário relacionado.
	 * @param materias
	 *            Array de ids de Máterias relacionadas.
	 * @param tags
	 *            Array de ids de Tags relacionadas. Não é obrigatório.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarDuvida(Duvida d, int idUsuario, int[] materias, int[] tags);

	/**
	 * Método para remoção de dúvida.
	 * 
	 * @param idDuvida
	 *            ID da Dúvida a ser removida.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean removerDuvida(int idDuvida);

	/**
	 * Método utilizado para remover dúvidas. Método para verificar se existem
	 * respostas relacionadas a dúvida.
	 * 
	 * @param idDuvida
	 *            ID da Dúvida a ser verificada.
	 * @return Se a resposta pode ser remoivda ou não.
	 */
	public boolean validaDuvida(int idDuvida);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionadas a uma Matéria.
	 * 
	 * @param idMateria
	 *            ID da Materia relacionada.
	 * @return Lista de Dúvidas relacionadas com a Máteria.
	 */
	public List<Duvida> buscarDuvidasMateria(int idMateria);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionadas a um Usuário.
	 * 
	 * @param idUsuario
	 *            ID do Usuário relacionado.
	 * @return Lista de Dúvidas relacionadas com o Usuário.
	 */
	public List<Duvida> buscarDuvidasUsuario(int idUsuario);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionados a uma Tag.
	 * 
	 * @param idTag
	 *            ID da Tag relacionada.
	 * @return Lista de Dúvidas relacionadas com a Tag.
	 */
	public List<Duvida> buscarDuvidasTags(int idTag);
	
	/**
	 * 
	 * @return
	 */
	public List<Duvida> buscarDuvidas();

	public List<Duvida> buscarDuvidasUsuarioRelacionadoMateria(int idUsuario);
}