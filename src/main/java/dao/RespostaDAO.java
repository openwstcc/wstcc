package dao;

import java.util.List;

import model.Resposta;

public interface RespostaDAO {
	public void adicionarResposta(Resposta r, int id_duvida, int id_usuario);
	public List<Resposta> buscarRespostas(int id_duvida);
}