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
		// Increment de Rank
		System.out.println("Teste de adição de Rank:"+adicionaRank());
		// Altera Flag de Aluno
		System.out.println("Teste de alteração de Flag de Aluno:"+alteraFlagAluno());
		// Altera Flag de Professor
		System.out.println("Teste de alteração de Flag de Professor:"+alterarFlagProf());
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
		return dao.buscarRespostas(rnd.nextInt(5)+1);
	}
	
	public static boolean adicionaRank(){
		RespostaDAO dao = new RespostaDAOImplementation();
		Random rnd = new Random();
		return dao.adicionaRank(rnd.nextInt(5)+1);
	}
	
	public static boolean alteraFlagAluno(){
		RespostaDAO dao = new RespostaDAOImplementation();
		Random rnd = new Random();
		return dao.alteraFlagAluno(rnd.nextInt(5)+1);
	}
	
	public static boolean alterarFlagProf(){
		RespostaDAO dao = new RespostaDAOImplementation();
		Random rnd = new Random();
		return dao.alteraFlagProfessor(rnd.nextInt(5)+1);
	}
}
