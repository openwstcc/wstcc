package dao;

import java.util.List;

import util.NoticacaoDuvidas;

public interface NotificacaoDAO {
	public List<NoticacaoDuvidas> notificarDuvidaRespostaRelacionadaMateriaData();
	public List<NoticacaoDuvidas> notificarDuvidaRespostaRelacionadaUsuario();
}
