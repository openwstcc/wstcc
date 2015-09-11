package dao;

import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;
import java.util.List;

interface DuvidaDAO {
	public void adicionarDuvida (Duvida d,Usuario u,List <Materia> m, List<Tag> t);
	public void alterarDuvida (Duvida d);
	public void removerDuvida (Duvida d);
	public List <Duvida> buscarDuvidasMateria (Materia m);
	public List <Duvida> buscarDuvidasUsuario (Usuario u);
	public List <Duvida> buscarDuvidasTags( Tag t);
	
}