package model;

import java.util.Date;

public class Resposta {
	private int idResposta;
	private String resposta;
	private Date dataCriacao;
	private int rank;
	private boolean flagProfessor;
	private boolean flagCriador;

	public int getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(int idResposta) {
		this.idResposta = idResposta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isFlagProfessor() {
		return flagProfessor;
	}

	public void setFlagProfessor(boolean flagProfessor) {
		this.flagProfessor = flagProfessor;
	}

	public boolean isFlagCriador() {
		return flagCriador;
	}

	public void setFlagCriador(boolean flagCriador) {
		this.flagCriador = flagCriador;
	}

}
