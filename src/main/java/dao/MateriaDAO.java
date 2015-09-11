
package dao;

import java.util.List;

import model.Materia;

public interface MateriaDAO {

	public void adicionarMateria(Materia m );
	public void removerMateria(Materia m );
	public void alterarMateria(Materia m );
	public List< Materia > buscarMaterias(Materia m );
		
}