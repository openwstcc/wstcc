package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {

	public void adicionarUsuario(Usuario u);
	public void alterarUsuario(Usuario u);
	public void removerUsuario(Usuario u);	
	public List <Usuario> buscarUsuarios(Usuario u);
		
}
