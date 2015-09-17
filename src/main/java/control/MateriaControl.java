package control;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.MateriaDAO;
import dao.MateriaDAOImplementation;
import model.Duvida;
import model.Materia;
import model.Usuario;
import util.GsonObjects;

public class MateriaControl {
	GsonObjects gsonObjects = new GsonObjects();
	
	public String buscarMateriasDuvida(String jsonDuvida) {
		Duvida d = gsonObjects.duvidaFromJson(jsonDuvida);		
		MateriaDAO dao = new MateriaDAOImplementation();		
		List<Materia> materias = dao.buscarMateriasDuvida(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	public String buscarMateriasUsuario(String jsonUsuario) {
		Usuario u = gsonObjects.usuarioFromJson(jsonUsuario);
		MateriaDAO dao = new MateriaDAOImplementation();
		List<Materia> materias = dao.buscarMateriasUsuario(u.getIdUsuario());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

	public String buscarMaterias() {
		MateriaDAO dao = new MateriaDAOImplementation();
		List<Materia> materias = dao.buscarTodasMaterias();
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(materias);
		return json;
	}

}
