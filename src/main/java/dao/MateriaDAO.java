
package dao;

import java.util.List;

import model.Materia;

/**
 * Interface DAO (Data Access Object) responsável pelos métodos de Matérias.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public interface MateriaDAO {
	/**
	 * Método para relacionamento de Matérias e Usuários.
	 * 
	 * @param id_usuario
	 *            ID do Usuário relacionado.
	 * @param id_materia
	 *            ID da Matéria relacionada
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarMateriaUsuario(int id_usuario, int id_materia);

	/**
	 * Método para remoção de todas as máterias relacionadas com um usuário.
	 * Utilizado para atualização de dados de Matérias.
	 * 
	 * @param id_usuario
	 *            ID do Usuário Relacionado.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean removerMateriaUsuario(int id_usuario);

	/**
	 * Método utilizado para buscar todas as Máterias.
	 * 
	 * @return Lista de todas as Matérias do sistema.
	 */
	public List<Materia> buscarTodasMaterias();

	/**
	 * Método utilizado para buscar todas as Materias relacionadas a um Usuário
	 *
	 * @param id_usuario
	 *            ID do Usuário Relacionado.
	 * @return Lista de todas as Matérias relacionadas a um Usuário.
	 */
	public List<Materia> buscarMateriasUsuario(int id_usuario);

	/**
	 * Método utilizado para buscar todas as Matérias relacionadas a uma Dúvida.
	 * 
	 * @param id_duvida
	 *            ID da Dúvida Relacionada.
	 * @return Lista de todas as Matérias relacionadas a uma Dúvida.
	 */
	public List<Materia> buscarMateriasDuvida(int id_duvida);
}