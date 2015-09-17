package tests;

import control.MateriaControl;

public class MateriaControllerTest {
	public static void main(String[] args) {
		// Seleciona todas as materias em GSON
		System.out.println(selectMaterias());
	}
	
	public static String selectMaterias() {
		MateriaControl control = new MateriaControl();
		return control.buscarTodasMaterias();
	}
}
