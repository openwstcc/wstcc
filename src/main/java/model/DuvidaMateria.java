package model;

import java.util.List;

public class DuvidaMateria {

	Duvida d;
	int idUsuario;
	List<Materia> materias;
	List<Tag> tags;
	public Duvida getD() {
		return d;
	}
	public void setD(Duvida d) {
		this.d = d;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
