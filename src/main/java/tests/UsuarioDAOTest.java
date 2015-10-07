package tests;

import java.util.Random;

import dao.UsuarioDAO;
import dao.UsuarioDAOImplementation;
import model.Usuario;

public class UsuarioDAOTest {
	static UsuarioDAO dao = new UsuarioDAOImplementation();
	static UsuarioTest u = new UsuarioTest();

	public static void main(String[] args) {
		// Insert de Usuário
		System.out.println("Teste de insert de usuario: " + insertUsuario());
		// Alteração de Usuário
		System.out.println("Teste de alteração de usuario: " + alterarUsuario());
		// Alteração de Senha
		System.out.println("Teste de alteração de senha: " + alterarSenha());
		// Select de Usuário
		System.out.println("Teste de select de usuario: " + selectUsuario());
		// Teste de Login de Usuário
		System.out.println("Teste de login de usuario: " + loginUsuario());
	}

	public static int insertUsuario() {
		return dao.adicionarUsuario(u.randomNoID());
	}

	public static boolean alterarUsuario() {
		return dao.alterarUsuario(u.randomID());
	}

	public static boolean alterarSenha() {
		return dao.alterarSenha(u.randomID());
	}

	public static Usuario selectUsuario() {
		Usuario u = new Usuario();
		Random r = new Random();
		u.setIdUsuario(r.nextInt(4) + 1);
		return dao.buscarUsuario(u);
	}

	public static Usuario loginUsuario() {
		Usuario u = new Usuario();
		u.setEmail("GABRIEL.FATEC@HOTMAIL.COM");
		u.setSenha("GABRIEL");
		return dao.loginUsuario(u);
	}
}
