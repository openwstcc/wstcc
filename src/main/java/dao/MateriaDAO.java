
package dao;

import java.util.List;

import model.Duvida;
import model.Materia;
import model.Usuario;

public interface MateriaDAO {

	public List< Materia > buscarTodasMaterias();
	public List< Materia > buscarMateriasUsuario(Usuario u);	
	public List< Materia > buscarMateriasDuvida(Duvida d);	
}