package tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("teste")
public class RestTest {
	
	@GET
	public String teste() {
		return "HEAL";
	}
	
	@Path("metodo")
	@GET
	public String metodo() {
		return "METHOD";
	}

	@GET
	@Path("/{param}")
	public String teste(@PathParam("param") String msg) {
		return msg;
	}
	
}
