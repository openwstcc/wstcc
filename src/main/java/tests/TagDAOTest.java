package tests;

import java.util.Random;

import dao.TagDAO;
import dao.TagDAOImplementation;
import model.Tag;

public class TagDAOTest {
	public static void main(String[] args) {
		// Insert de Tag
		System.out.println("Teste de insert de tag: " + insertTag());
		// Select de Tag
		System.out.println(selectTag());
	}

	public static boolean insertTag() {
		TagDAO dao = new TagDAOImplementation();
		TagTest t = new TagTest();
		return dao.inserirTag(t.randomTag());
	}
	
	public static Tag selectTag(){
		TagDAO dao = new TagDAOImplementation();
		Random r = new Random();
		TagTest t = new TagTest();
		Tag tag = new Tag();
		tag.setIdTag(r.nextInt(t.getTags().length)+1);
		return dao.buscarTag(tag); 
	}

}
