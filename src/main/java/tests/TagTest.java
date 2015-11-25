package tests;

import java.util.Random;

import model.Tag;

public class TagTest {
	private String[] tags = { "LabEng", "SO", "EngSoft", "CasoDeUso", "Android", "Testes", "Java", "Linux", "BD",
			"SQL" };

	public Tag randomTag() {
		Tag t = new Tag();
		Random r = new Random();
		t.setNome(tags[r.nextInt(tags.length)]);
		return t;
	}

	public String[] getTags() {
		return tags;
	}	
	
	
}
