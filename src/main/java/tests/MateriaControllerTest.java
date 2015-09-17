package tests;

import com.google.gson.Gson;

import control.MateriaControl;

public class MateriaControllerTest {
	public static void main(String[] args) {
		// Seleciona todas as materias retornando JSon.
		System.out.println(selectMaterias());
		// Seleciona todas as materias por usuario.
		System.out.println(selectMateriasUsuario());
	}

	public static String selectMaterias() {
		MateriaControl control = new MateriaControl();
		return control.buscarMaterias();
	}

	public static String selectMateriasUsuario() {
		MateriaControl control = new MateriaControl();
		Gson gson = new Gson();
		String usuario = gson.toJson(UsuarioDAOTest.selectUsuario());
		System.out.println("Usuario JSON: " + usuario);
		return control.buscarMateriasUsuario(usuario);
	}

	public static String selectMateriasDuvida() {
		MateriaControl control = new MateriaControl();
		Gson gson = new Gson();
		return null;
	}
}
