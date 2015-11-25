package tests;

import java.util.List;
import java.util.Random;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import model.Materia;

public class MateriaDAOTest {
	static MateriaDAO dao = new MateriaDAOImplementation();
	static Random r = new Random();
	
	public static void main(String[] args) {
		// Select de todas as matérias
		for (Materia m : selectMaterias())
			System.out.println(m.toString());
		// Select de todas as matérias por aluno
		//for (Materia m : selectMateriasUsuario())
		//	System.out.println(m.toString());
		// Select de todas as matérias por dúvida
		//for (Materia m : selectMateriasDuvida())
		//	System.out.println(m.toString());
	}

	public static List<Materia> selectMaterias() {
		return dao.buscarTodasMaterias();
	}

	public static List<Materia> selectMateriasUsuario() {
		return dao.buscarMateriasUsuario(r.nextInt(5) + 1);
	}

	public static List<Materia> selectMateriasDuvida() {
		return dao.buscarMateriasDuvida(r.nextInt(5) + 1);
	}
}
