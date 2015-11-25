package tests;

import java.util.Date;
import java.util.Random;
import model.Usuario;

public class UsuarioTest {
	private Usuario u;
	private String[] nome = { "Joao", "Jose", "Maria", "Caio", "Ana" };
	private String[] sobrenome = { "Silva", "Santos", "Freire", "Freitas", "Oliveira" };
	private String[] senha = { "1234", "4321", "1423", "2134", "4123", "3124" };
	private String[] telefone = { "+551191238423", "+551191124423", "+551199138588", "+5511933216523",
			"+5511951231123" };
	@SuppressWarnings("deprecation")
	private Date[] dataNasc = { new Date("20/04/1996"), new Date("19/09/1995"), new Date("10/05/1997"),
			new Date("08/12/1998"), new Date("14/07/1999"), };

	public Usuario randomNoID() {
		u = new Usuario();
		Random r = new Random();
		u.setNome(nome[r.nextInt(4)]);
		u.setSobrenome(sobrenome[r.nextInt(4)]);
		u.setSenha(senha[r.nextInt(4)]);
		u.setTelefone(telefone[r.nextInt(4)]);
		u.setDataNasc(dataNasc[r.nextInt(4)]);
		u.setEmail(u.getNome() + "." + u.getSobrenome() + "@TESTE.COM");
		u.setPerfil("A");
		System.out.println("Usuario Teste: " + u.toString());
		return u;
	}

	public Usuario randomID() {
		u = new Usuario();
		Random r = new Random();
		u.setIdUsuario(r.nextInt(4) + 1);
		u.setNome(nome[r.nextInt(4)]);
		u.setSobrenome(sobrenome[r.nextInt(4)]);
		u.setSenha(senha[r.nextInt(4)]);
		u.setTelefone(telefone[r.nextInt(4)]);
		u.setDataNasc(dataNasc[r.nextInt(4)]);
		u.setEmail(u.getNome() + "@TESTE.COM");
		u.setPerfil("A");
		System.out.println("Usuario Teste: " + u.toString());
		return u;
	}
}
