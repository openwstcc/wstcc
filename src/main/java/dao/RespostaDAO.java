package dao;

import java.util.List;

import model.Resposta;

public interface RespostaDAO {
	public boolean adicionarResposta(Resposta r, int id_duvida, int id_usuario);
	public List<Resposta> buscarRespostas(int id_duvida);
	public boolean adicionaRank(int id_resposta);
	public boolean alteraFlagAluno(int id_resposta);
	public boolean alteraFlagProfessor(int id_resposta);
}