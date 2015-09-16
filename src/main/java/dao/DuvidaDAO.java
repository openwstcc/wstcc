package dao;

import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;
import java.util.List;

public interface DuvidaDAO {
	public boolean adicionarDuvida (Duvida d, int id_usuario, List <Materia> m, List<Tag> t);
	public boolean removerDuvida (int id_duvida);
	public boolean validaDuvida (int id_duvida);
	public List <Duvida> buscarDuvidasMateria (int id_materia);
	public List <Duvida> buscarDuvidasUsuario (int id_usuario);
	public List <Duvida> buscarDuvidasTags (int id_tag);
}