package tests;

import java.util.Date;
import java.util.Random;

import model.Duvida;

/**
 * Gerador de texto utilizado para geração de dúvidas:
 * http://www.4devs.com.br/gerador_de_texto_lorem_ipsum
 */
public class DuvidaTest {
	private Duvida d;
	private String[] titulo = { "Lorem ipsum nisi semper, pretium?", "Lorem ipsum tincidunt?",
			"Primis mattis facilisis varius, nostra nib?", "Elit imperdiet orci, iaculis?",
			"Inceptos rutrum odio augue sodales?" };
	private String[] conteudo = { "Morbi tincidunt malesuada suspendisse feugiat, mi fames euismod.",
			"Lorem ipsum consectetur arcu, inceptos molestie facilisis condimentum, inceptos rhoncus",
			"Dictum vivamus semper aliquam imperdiet facilisis, praesent vestibulum accumsan hac.",
			"Congue semper aliquam condimentum arcu vel tincidunt fermentum, sem ligula senectus euismod urna.",
			"Varius donec pretium arcu class in nostra tortor, fusce molestie venenatis litora senectus" };

	public Duvida insertDuvida() {
		d = new Duvida();
		Random r = new Random();
		d.setTitulo(titulo[r.nextInt(4)]);
		d.setConteudo(conteudo[r.nextInt(4)]);
		d.setDataCriacao(new Date());
		System.out.println("Duvida Teste: " + d.toString());
		return d;
	}
}
