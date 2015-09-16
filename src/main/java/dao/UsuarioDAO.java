package dao;

import model.Usuario;

public interface UsuarioDAO {
	public boolean adicionarUsuario(Usuario u);
	public boolean alterarUsuario(Usuario u);	
	public boolean alterarSenha(Usuario u);
	public Usuario buscarUsuario(Usuario u);		
}
