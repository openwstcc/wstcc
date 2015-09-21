package tests;

import java.util.List;
import java.util.Random;

import dao.DuvidaDAO;
import dao.DuvidaDAOImplementation;
import model.Duvida;

public class DuvidaDAOTest {

	public static void main(String[] args) {
		// Insert de Duvida com Materia
		System.out.println("Teste de insert de duvida (1 materia): " + insertDuvidaMateria());
		// Insert de Duvida com Materias
		System.out.println("Teste de insert de duvida (+ materia): " + insertDuvidaMaterias());
		// Insert de Duvida com Materias e Tags
		System.out.println("Teste de insert de duvida (+ materia & tag): " + insertDuvidaMateriasTag());
		// Select de Duvida por Materia
		for (Duvida d : selectDuvidasMateria())
			System.out.println(d);
		// Select de Duvida por Usuario
		for (Duvida d : selectDuvidasUsuario())
			System.out.println(d);
		// Select de Duvida por Tag
		for (Duvida d : selectDuvidasTags())
			System.out.println(d);
	}

	public static boolean insertDuvidaMateria() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		DuvidaTest d = new DuvidaTest();
		
		Random r = new Random();

		int[] materias = { r.nextInt(9) + 1 };
		int[] tags = { };
		
		int idUsuario = r.nextInt(4) + 1;

		return dao.adicionarDuvida(d.insertDuvida(), idUsuario, materias, tags);
	}

	public static boolean insertDuvidaMaterias() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		DuvidaTest d = new DuvidaTest();

		Random r = new Random();
		int min = 10;
		int max = 20;

		int[] materias = { r.nextInt(9) + 1, r.nextInt(max - min) + min };
		int[] tags = { };
		
		int idUsuario = r.nextInt(4) + 1;		
		
		return dao.adicionarDuvida(d.insertDuvida(), idUsuario, materias, tags);
	}

	public static boolean insertDuvidaMateriasTag() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		DuvidaTest d = new DuvidaTest();	

		Random r = new Random();
		int min = 10;
		int max = 20;

		int[] materias = { r.nextInt(9) + 1, r.nextInt(max - min) + min };
		int[] tags = { r.nextInt(9) + 1, r.nextInt(max - min) + min };
		
		int idUsuario = r.nextInt(4) + 1;		
		
		return dao.adicionarDuvida(d.insertDuvida(), idUsuario, materias, tags);
	}

	public static List<Duvida> selectDuvidasMateria() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		Random r = new Random();

		return dao.buscarDuvidasMateria(r.nextInt(5) + 1);
	}

	public static List<Duvida> selectDuvidasTags() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		Random r = new Random();

		return dao.buscarDuvidasTags(r.nextInt(5) + 1);
	}

	public static List<Duvida> selectDuvidasUsuario() {
		DuvidaDAO dao = new DuvidaDAOImplementation();
		Random r = new Random();

		return dao.buscarDuvidasUsuario(r.nextInt(5) + 1);
	}
}
