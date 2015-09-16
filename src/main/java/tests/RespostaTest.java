package tests;

import java.util.Date;
import java.util.Random;

import model.Resposta;

public class RespostaTest {
	private Resposta r;
	private String[] resposta = { "Morbi tincidunt malesuada suspendisse feugiat, mi fames euismod.",
			"Lorem ipsum consectetur arcu, inceptos molestie facilisis condimentum, inceptos rhoncus",
			"Dictum vivamus semper aliquam imperdiet facilisis, praesent vestibulum accumsan hac.",
			"Congue semper aliquam condimentum arcu vel tincidunt fermentum, sem ligula senectus euismod urna.",
			"Varius donec pretium arcu class in nostra tortor, fusce molestie venenatis litora senectus" };	
	
	public Resposta insertResposta(){
		r = new Resposta();
		Random rnd = new Random();
		r.setDataCriacao(new Date());
		r.setFlagCriador(false);
		r.setFlagProfessor(false);
		r.setResposta(resposta[rnd.nextInt(5)]);
		System.out.println(r.toString());
		return r;
	}
}
