package dao;

import java.util.List;

import model.Tag;

public interface TagDAO {
	public void inserirTag (Tag t);
	public Tag buscarTag(Tag t);
	public List<Tag> buscarTags (Tag t);
	public List<Tag> buscarTagsDuvida (int id_duvida);
}
