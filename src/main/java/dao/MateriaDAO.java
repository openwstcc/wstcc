
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
	public int atualizarMateriasUsuario(List<Materia> materias);
	
	/**
	 * Método de inserção de Matérias informando Usuários.
	 * 
	 * @param idUsuario
	 *            ID do Usuário relacionado.
	 * @param idMateria
	 *            ID da Matéria relacionada
	 * @return Sucesso ou falha da ação.
	 */
	public boolean adicionarMateriaUsuario(int idUsuario, int idMateria);

	/**
	 * Método para remoção de todas as máterias relacionadas com um usuário.
	 * Utilizado para atualização de dados de Matérias.
	 * 
	 * @param idUsuario
	 *            ID do Usuário Relacionado.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean removerMateriaUsuario(int idUsuario);

	/**
	 * Método utilizado para buscar todas as Máterias.
	 * 
	 * @return Lista de todas as Matérias do sistema.
	 */
	public List<Materia> buscarTodasMaterias();

	/**
	 * Método utilizado para buscar todas as Materias relacionadas a um Usuário
	 *
	 * @param idUsuario
	 *            ID do Usuário Relacionado.
	 * @return Lista de todas as Matérias relacionadas a um Usuário.
	 */
	public List<Materia> buscarMateriasUsuario(int idUsuario);

	/**
	 * Método utilizado para buscar todas as Matérias relacionadas a uma Dúvida.
	 * 
	 * @param idDuvida
	 *            ID da Dúvida Relacionada.
	 * @return Lista de todas as Matérias relacionadas a uma Dúvida.
	 */
	public List<Materia> buscarMateriasDuvida(int idDuvida);
}