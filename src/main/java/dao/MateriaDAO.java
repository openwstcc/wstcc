
package dao;

import java.util.List;

import model.Materia;

public interface MateriaDAO {
	public List< Materia > buscarTodasMaterias();
	public List< Materia > buscarMateriasUsuario(int id_usuario);	
	public List< Materia > buscarMateriasDuvida(int id_duvida);	
}