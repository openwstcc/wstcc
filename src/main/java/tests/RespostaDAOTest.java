package tests;

import java.util.List;
import java.util.Random;

import dao.RespostaDAO;
import dao.RespostaDAOImplementation;
import model.Resposta;

public class RespostaDAOTest {
	static RespostaDAO dao = new RespostaDAOImplementation();
	static Random rnd = new Random();

	public static void main(String[] args) {
		// Insert de Resposta
		System.out.println("Teste de insert de resposta: " + insertResposta());
		// Select de Respostas
		for (Resposta r : selectRespostas())
			System.out.println(r);
		// Increment de Rank
		System.out.println("Teste de adição de Rank: " + adicionaRank());
		// Altera Flag de Aluno
		System.out.println("Teste de alteração de Flag de Aluno: " + alteraFlagAluno());
		// Altera Flag de Professor
		System.out.println("Teste de alteração de Flag de Professor: " + alterarFlagProf());
	}

	public static boolean insertResposta() {
		RespostaTest r = new RespostaTest();
		//*return dao.adicionarResposta(r.insertResposta(), rnd.nextInt(5) + 1, rnd.nextInt(5) + 1);
		return false;
	}

	public static List<Resposta> selectRespostas() {
		return dao.buscarRespostas(rnd.nextInt(5) + 1);
	}

	public static boolean adicionaRank() {		
		return dao.adicionaRank(rnd.nextInt(5) + 1);
	}

	public static boolean alteraFlagAluno() {		
		return dao.alteraFlagAluno(rnd.nextInt(5) + 1);
	}

	public static boolean alterarFlagProf() {		
		return dao.alteraFlagProfessor(rnd.nextInt(5) + 1);
	}
}
