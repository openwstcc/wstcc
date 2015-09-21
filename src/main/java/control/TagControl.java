package control;

import java.util.List;

import dao.TagDAO;
import dao.TagDAOImplementation;

/**
 * Tag Controller responsável pelos métodos de tags. Estes métodos são
 * disponibilizados a partir do Webservice e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
public class TagControl {
	TagDAO dao = new TagDAOImplementation();

	public int[] inserirTags(String[] tags) {
		List<Integer> temp = dao.inserirTag(tags);
		int[] ids = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++)
			ids[i] = temp.get(i);
		return ids;
	}
}
