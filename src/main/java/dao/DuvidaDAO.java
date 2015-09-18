package dao;

import model.Duvida;
import model.Materia;
import model.Tag;
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
	 * @param id_usuario
	 *            ID do Usuário relacionado.
	 * @param m
	 *            Lista de Máterias.
	 * @param t
	 *            Lista de Tags relacionadas.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarDuvida(Duvida d, int id_usuario, List<Materia> m, List<Tag> t);

	/**
	 * Método para remoção de dúvida.
	 * 
	 * @param id_duvida
	 *            ID da Dúvida a ser removida.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean removerDuvida(int id_duvida);

	/**
	 * Método utilizado para remover dúvidas. Método para verificar se existem
	 * respostas relacionadas a dúvida.
	 * 
	 * @param id_duvida
	 *            ID da Dúvida a ser verificada.
	 * @return Se a resposta pode ser remoivda ou não.
	 */
	public boolean validaDuvida(int id_duvida);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionadas a uma Matéria.
	 * 
	 * @param id_materia
	 *            ID da Materia relacionada.
	 * @return Lista de Dúvidas relacionadas com a Máteria.
	 */
	public List<Duvida> buscarDuvidasMateria(int id_materia);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionadas a um Usuário.
	 * 
	 * @param id_usuario
	 *            ID do Usuário relacionado.
	 * @return Lista de Dúvidas relacionadas com o Usuário.
	 */
	public List<Duvida> buscarDuvidasUsuario(int id_usuario);

	/**
	 * Método utilizado para buscar todas as Dúvidas relacionados a uma Tag.
	 * 
	 * @param id_tag
	 *            ID da Tag relacionada.
	 * @return Lista de Dúvidas relacionadas com a Tag.
	 */
	public List<Duvida> buscarDuvidasTags(int id_tag);
}