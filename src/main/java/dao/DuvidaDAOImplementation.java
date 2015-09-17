package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Duvida;
import model.Materia;
import model.Tag;

/**
 * DAO (Data Access Object) responsável pelos métodos de Dúvidas.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public class DuvidaDAOImplementation implements DuvidaDAO {

	/**
	 * Método utilizado para pegar informações de data do momento de inserção da
	 * dúvida.
	 * 
	 * @return Dia Atual
	 */
	public Date getToday() {
		return new Date();
	}

	@Override
	public boolean adicionarDuvida(Duvida d, int id_usuario, List<Materia> m, List<Tag> t) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO DUVIDA (ID_USUARIO, TITULO_DUVIDA, CONTEUDO_DUVIDA, DATA_CRIACAO) "
							+ "VALUES (?,?,?,?)");
			pstmt.setInt(1, id_usuario);
			pstmt.setString(2, d.getTitulo());
			pstmt.setString(3, d.getConteudo());
			java.sql.Date dataCriacao = new java.sql.Date(getToday().getTime());
			pstmt.setDate(4, dataCriacao);
			pstmt.executeUpdate();

			for (Materia materia : m) {
				pstmt = con.prepareStatement(
						"INSERT INTO MATERIA_DUVIDA (ID_MATERIA, ID_DUVIDA) VALUES (?,LAST_INSERT_ID());");
				pstmt.setInt(1, materia.getIdMateria());
				pstmt.executeUpdate();
			}

			if (!t.isEmpty()) {
				for (Tag tag : t) {
					pstmt = con.prepareStatement(
							"INSERT INTO DUVIDA_TAG (ID_DUVIDA, ID_TAG) VALUES (LAST_INSERT_ID(),?);");
					pstmt.setInt(1, tag.getIdTag());
					pstmt.executeUpdate();
				}
			}

			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o Dúvida");
			System.out.println(e);
			return false;
		}

	}

	@Override
	public boolean removerDuvida(int id_duvida) {
		if (this.validaDuvida(id_duvida) == false)
			return false;

		try {
			Connection con;
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
			pstmt.executeQuery();
			pstmt = con.prepareStatement("DELETE FROM DUVIDA_TAG  WHERE ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
			pstmt.executeQuery();
			pstmt = con.prepareStatement("DELETE FROM DUVIDA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
			pstmt.executeQuery();
			pstmt.close();
			System.out.println("Duvida removida com sucesso!");

			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao remover duvida.");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Duvida> buscarDuvidasMateria(int id_materia) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME FROM MATERIA_DUVIDA MD "
							+ "INNER JOIN duvida D ON D.ID_DUVIDA=MD.ID_DUVIDA "
							+ "INNER JOIN usuario U ON D.ID_USUARIO=U.ID_USUARIO WHERE MD.ID_MATERIA=?");
			pstmt.setInt(1, id_materia);
			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();
			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				d.setCriador(rs.getString("NOME"));
				duvidas.add(d);
			}
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar das Duvidas relacioandas ao ID matéria" + id_materia);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasUsuario(int id_usuario) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA,D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME FROM duvida D "
							+ "INNER JOIN usuario u ON D.ID_USUARIO=U.ID_USUARIO WHERE U.ID_USUARIO=?");
			pstmt.setInt(1, id_usuario);
			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();
			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				d.setCriador(rs.getString("NOME"));
				duvidas.add(d);
			}
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar das Duvidas relacioandas ao ID usuário" + id_usuario);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasTags(int id_tag) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA,D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME FROM duvida D "
							+ "INNER JOIN duvida_tag DT on D.ID_DUVIDA=DT.ID_DUVIDA "
							+ "INNER JOIN usuario U ON U.ID_USUARIO=D.ID_USUARIO WHERE DT.ID_TAG=?");
			pstmt.setInt(1, id_tag);
			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();
			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				d.setCriador(rs.getString("NOME"));
				duvidas.add(d);
			}
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar das Duvidas relacioandas ao ID Tag." + id_tag);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean validaDuvida(int id_duvida) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("select COUNT(id_duvida) as totalDeRespostas "
					+ "from RESPOSTA where id_resposta=? ORDER BY id_resposta");
			pstmt.setInt(1, id_duvida);
			ResultSet rs = pstmt.executeQuery();
			int totalResposta = rs.getInt("totalDeRespostas");
			/*
			 * Se totalResposta for igual a 0, portanto a Duvida nao possui
			 * resposta e ela poderá ser removida
			 */
			if (totalResposta == 0)
				return true;

			return false;
		} catch (SQLException e) {
			System.out.println("Erro ao validar Duvida.");
			System.out.println(e);
			return false;
		}
	}

}
