package dao;

import java.util.List;

import model.Duvida;
import model.Resposta;

public interface RespostaDAO {
	public void adicionarResposta(Resposta r, Duvida d);
	public List<Resposta> buscarRespostas(Duvida d);
}