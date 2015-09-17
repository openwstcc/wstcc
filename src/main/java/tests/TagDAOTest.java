package tests;

import java.util.List;
import java.util.Random;

import dao.TagDAO;
import dao.TagDAOImplementation;
import model.Tag;

public class TagDAOTest {
	public static void main(String[] args) {
		// Insert de Tag
		System.out.println("Teste de insert de tag: " + insertTag());
		// Select de Tag
		System.out.print("Teste de select de tag: ");
		System.out.println(selectTag());
		// Select de todas as Tags
		System.out.println("Teste de select de tags: ");
		for (Tag t : selectTags())
			System.out.println(t);
		// Select de todas as Tags por duvida
		System.out.println("Teste de select de tags por duvida: ");
		for (Tag t : selectTagsDuvida())
			System.out.println(t);
	}

	public static boolean insertTag() {
		TagDAO dao = new TagDAOImplementation();
		TagTest t = new TagTest();
		return dao.inserirTag(t.randomTag());
	}

	public static Tag selectTag() {
		TagDAO dao = new TagDAOImplementation();
		Random r = new Random();
		Tag t = new Tag();
		t.setIdTag(r.nextInt(3) + 1);
		return dao.buscarTag(t);
	}

	public static List<Tag> selectTags() {
		TagDAO dao = new TagDAOImplementation();
		return dao.buscarTags();
	}

	public static List<Tag> selectTagsDuvida() {
		TagDAO dao = new TagDAOImplementation();
		Random r = new Random();
		return dao.buscarTagsDuvida(r.nextInt(3) + 1);
	}
}
