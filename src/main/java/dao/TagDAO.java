package dao;

import java.util.List;

import model.Tag;

/**
 * Interface DAO (Data Access Object) responsável pelos métodos de Tag.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public interface TagDAO {
	/**
	 * Método para inserção de novas tags.
	 * 
	 * @param t
	 *            Tag.
	 * @return Sucesso ou falha da ação.
	 */
	public boolean inserirTag(Tag t);

	/**
	 * Método para busca de uma Tag contendo apenas um ID.
	 * 
	 * @param t
	 *            Tag com apenas ID.
	 * @return Tag.
	 */
	public Tag buscarTag(Tag t);

	/**
	 * Método para buscar todas as Tags presente no sistema.
	 * 
	 * @return Lista de todas as Tags
	 */
	public List<Tag> buscarTags();

	/**
	 * Método para buscar Tags relacionadas a uma Dúvida.
	 * 
	 * @param id_duvida
	 *            ID da Dúvida relacionada.
	 * @return Lista de Tags relacionadas a Dúvida.
	 */
	public List<Tag> buscarTagsDuvida(int id_duvida);
}
