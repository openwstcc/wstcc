package control;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import model.Materia;

public class MateriaControl {
	public String buscarMariasDuvida() {
		return null;
	}

	public String buscarMariasUsuario() {
		return null;
	}

	public String buscarTodasMaterias() {
		MateriaDAO dao = new MateriaDAOImplementation();		
		List<Materia> materias = dao.buscarTodasMaterias();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}
}
