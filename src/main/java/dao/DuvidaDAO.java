package dao;

import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;
import java.util.List;

interface DuvidaDAO {
	public void adicionarDuvida (Duvida d, Usuario u, List <Materia> m, List<Tag> t);
	public void removerDuvida (int id_duvida);
	public boolean validaDuvida (int id_duvida);
	public List <Duvida> buscarDuvidasMateria (int id_materia);
	public List <Duvida> buscarDuvidasUsuario (int id_usuario);
	public List <Duvida> buscarDuvidasTags (int id_tag);
}