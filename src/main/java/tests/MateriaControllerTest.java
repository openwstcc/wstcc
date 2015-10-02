package tests;

import java.util.Random;

import com.google.gson.Gson;

import control.MateriaControl;
import model.Duvida;

public class MateriaControllerTest {
	static MateriaControl control = new MateriaControl();;
	static Gson gson = new Gson();;

	public static void main(String[] args) {
		// Seleciona todas as materias retornando JSon.
		System.out.println("Todas as materias:");
		System.out.println(selectMaterias());
		// Seleciona todas as materias por usuario.
		System.out.println("Todas as materias por usuário:");
		System.out.println("Materias: " + selectMateriasUsuario());
		// Seleciona todas as materias por duvida.
		System.out.println("Todas as materias por dúvida:");
		System.out.println("Materias: " + selectMateriasDuvida());
	}

	public static String selectMaterias() {
		return control.buscarMaterias().toString();
	}

	public static String selectMateriasUsuario() {
		String usuario = gson.toJson(UsuarioDAOTest.selectUsuario());
		System.out.println("Usuario JSON: " + usuario);
		return control.buscarMateriasUsuario(usuario).toString();
	}

	public static String selectMateriasDuvida() {
		Duvida d = new Duvida();
		Random r = new Random();
		d.setIdDuvida(r.nextInt(5) + 1);
		String duvida = gson.toJson(d);
		System.out.println("Duvida JSON: " + duvida);
		return control.buscarMateriasDuvida(duvida).toString();
	}
}
