package dao;

import model.Usuario;

public interface UsuarioDAO {
	public void adicionarUsuario(Usuario u);
	public void alterarUsuario(Usuario u);	
	public void alterarSenha(Usuario u);
	public Usuario buscarUsuario(Usuario u);		
}
