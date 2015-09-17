package util;

import com.google.gson.Gson;

import model.Duvida;
import model.Materia;
import model.Resposta;
import model.Tag;
import model.Usuario;

public class GsonObjects {
	private Gson gson = new Gson();

	public Materia materiaFromJson(String json) {
		return gson.fromJson(json, Materia.class);
	}

	public Duvida duvidaFromJson(String json) {
		return gson.fromJson(json, Duvida.class);
	}

	public Resposta respostaFromJson(String json) {
		return gson.fromJson(json, Resposta.class);
	}

	public Usuario usuarioFromJson(String json) {		
		return gson.fromJson(json, Usuario.class);
	}

	public Tag tagFromJson(String json) {
		return gson.fromJson(json, Tag.class);
	}
}
