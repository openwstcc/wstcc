package dao;

import model.Duvida;
import model.Materia;
import model.Usuario;
import java.util.List;

interface DuvidaDAO {
	public void adicionarDuvida (Duvida d,Usuario u,Materia D);
	public List <Duvida> listaDuvida();
}
