package control;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RespostaDAO;
import dao.RespostaDAOImplementation;
import model.Duvida;
import model.Resposta;

/**
 * Resposta Controller responsável pelos métodos de respostas. Estes métodos são
 * disponibilizados a partir de Webservices e permitem a integração entre o
 * sistema Mobile e o Banco de Dados.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 * 
 */
@Path("respostas")
public class RespostaControl {

	private Gson objects = new Gson();
	RespostaDAO dao = new RespostaDAOImplementation();

	@POST
	@Path("adicionarResposta")
	@Consumes("application/json")
	public Response adicionarResposta(String resposta) {
		Resposta r = objects.fromJson(resposta, Resposta.class);
		boolean retorno = dao.adicionarResposta(r);
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("buscarRespostas")
	@Produces("application/json; charset=utf-8")
	public Response buscarRespostas(String jsonDuvida) {
		Duvida d = objects.fromJson(jsonDuvida, Duvida.class);
		List<Resposta> respostas = dao.buscarRespostas(d.getIdDuvida());
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(respostas);
		return Response.status(200).entity(json).build();
	}

	/**
	 * Adiciona Rank para a resposta selecionada. Caso o usuário que esteja
	 * adicionando Rank seja criador da duvida, altere a flag de criador para
	 * "TRUE" Caso o usuário que esteja adicionando Rank seja professor da
	 * duvida, altere a flag de professor para "TRUE".
	 */
	@POST
	@Path("adicionarRank")
	@Consumes("application/json")
	public Response adicionarRank(String jsonResposta) {
		Resposta r = objects.fromJson(jsonResposta, Resposta.class);
		boolean retorno = dao.adicionaRank(r.getIdResposta());
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("alteraFlagAluno")
	@Consumes("application/json")
	public Response alteraFlagAluno(String jsonResposta) {
		Resposta r = objects.fromJson(jsonResposta, Resposta.class);
		boolean retorno = dao.alteraFlagAluno(r.getIdResposta());
		return Response.status(200).entity(retorno).build();
	}

	@POST
	@Path("alteraFlagProfessor")
	@Consumes("application/json")
	public Response alteraFlagProfessor(String jsonResposta) {
		Resposta r = objects.fromJson(jsonResposta, Resposta.class);
		boolean retorno = dao.alteraFlagProfessor(r.getIdResposta());
		return Response.status(200).entity(retorno).build();
	}

}
