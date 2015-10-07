package dao;

import model.Usuario;

/**
 * Interface DAO (Data Access Object) responsável pelos métodos de Usuário.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public interface UsuarioDAO {
	/**
	 * Método para inserção de novos Usuários.
	 * 
	 * @param u
	 *            Usuário
	 * @return Sucesso ou falha da ação.
	 */
	public int adicionarUsuario(Usuario u);

	/**
	 * Método para alteração de Usuários existentes.
	 * 
	 * @param u
	 *            Usuário
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alterarUsuario(Usuario u);

	/**
	 * Método para alteração de senha de Usuários existentes.
	 * 
	 * @param u
	 *            Usuário com ID e SENHA
	 * @return Sucesso ou falha da ação.
	 */
	public boolean alterarSenha(Usuario u);

	/**
	 * Método para validação de login de usuário relacionando email e senha.
	 * 
	 * @param u
	 *            Usuário com EMAIL e SENHA
	 * @return Sucesso ou falha da ação.
	 */
	public Usuario loginUsuario(Usuario u);

	/**
	 * Método para busca de um Usuário.
	 * 
	 * @param u
	 *            Usuário com ID.
	 * @return Usuário.
	 */
	public Usuario buscarUsuario(Usuario u);
}
