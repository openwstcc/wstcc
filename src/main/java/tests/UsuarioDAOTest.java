package tests;

import java.util.Random;

import dao.UsuarioDAO;
import dao.UsuarioDAOImplementation;
import model.Usuario;

public class UsuarioDAOTest {

	public static void main(String[] args) {
		// Insert de Usuário
		// System.out.println("Teste de insert de usuario: "+insertUsuario());
		// Alteração de Usuário
		System.out.println("Teste de alteração de usuario: " + alterarUsuario());
		// Alteração de Senha
		System.out.println("Teste de alteração de senha: " + alterarSenha());
		// Select de Usuário
		System.out.println("Teste de select de usuario: " + selectUsuario());
	}

	public static boolean insertUsuario() {
		UsuarioDAO dao = new UsuarioDAOImplementation();
		UsuarioTest u = new UsuarioTest();
		return dao.adicionarUsuario(u.randomNoID());
	}

	public static boolean alterarUsuario() {
		UsuarioDAO dao = new UsuarioDAOImplementation();
		UsuarioTest u = new UsuarioTest();
		return dao.alterarUsuario(u.randomID());
	}

	public static boolean alterarSenha() {
		UsuarioDAO dao = new UsuarioDAOImplementation();
		UsuarioTest u = new UsuarioTest();
		return dao.alterarSenha(u.randomID());
	}

	public static Usuario selectUsuario() {
		UsuarioDAO dao = new UsuarioDAOImplementation();
		Usuario u = new Usuario();
		Random r = new Random();
		u.setIdUsuario(r.nextInt(4) + 1);
		return dao.buscarUsuario(u);
	}
}
