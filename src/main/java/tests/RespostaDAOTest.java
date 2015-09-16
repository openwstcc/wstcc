package tests;

import java.util.List;
import java.util.Random;

import dao.RespostaDAO;
import dao.RespostaDAOImplementation;
import model.Resposta;

public class RespostaDAOTest {
	public static void main(String[] args) {
		// Insert de Resposta
		System.out.println("Teste de insert de resposta: " + insertResposta());
		// Select de Respostas
		for(Resposta r : selectRespostas())
			System.out.println(r);
	}

	public static boolean insertResposta() {
		RespostaDAO dao = new RespostaDAOImplementation();
		RespostaTest r = new RespostaTest();
		Random rnd = new Random();
		return dao.adicionarResposta(r.insertResposta(), rnd.nextInt(5)+1, rnd.nextInt(5)+1);
	}
	
	public static List<Resposta> selectRespostas(){
		RespostaDAO dao = new RespostaDAOImplementation();
		Random rnd = new Random();
		return dao.buscarRespostas(rnd.nextInt(5));
	}
}
