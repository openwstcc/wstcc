package dao;

import java.util.List;

import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;

public class DuvidaDAOImplementation implements DuvidaDAO {

	@Override
	public void adicionarDuvida(Duvida d, Usuario u, List<Materia> m, List<Tag> t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterarDuvida(Duvida d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerDuvida(int id_duvida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Duvida> buscarDuvidasMateria(int id_materia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Duvida> buscarDuvidasUsuario(int id_usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Duvida> buscarDuvidasTags(int id_tag) {
		// TODO Auto-generated method stub
		return null;
	}

}
