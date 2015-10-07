package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Duvida;

/**
 * DAO (Data Access Object) responsável pelos métodos de Dúvidas.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public class DuvidaDAOImplementation implements DuvidaDAO {

	@Override
	public boolean adicionarDuvida(Duvida d, int idUsuario, int[] materias, int[] tags) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO DUVIDA (ID_USUARIO, TITULO_DUVIDA, CONTEUDO_DUVIDA, DATA_CRIACAO) "
							+ "VALUES (?,?,?,?)");
			pstmt.setInt(1, idUsuario);
			pstmt.setString(2, d.getTitulo());
			pstmt.setString(3, d.getConteudo());
			java.sql.Date dataCriacao = new java.sql.Date(d.getDataCriacao().getTime());
			pstmt.setDate(4, dataCriacao);
			pstmt.executeUpdate();

			for (int idMateria : materias) {
				pstmt = con.prepareStatement(
						"INSERT INTO MATERIA_DUVIDA (ID_MATERIA, ID_DUVIDA) VALUES (?,LAST_INSERT_ID());");
				pstmt.setInt(1, idMateria);
				pstmt.executeUpdate();
			}

			if (tags != null) {
				for (int idTag : tags) {
					pstmt = con.prepareStatement(
							"INSERT INTO DUVIDA_TAG (ID_DUVIDA, ID_TAG) VALUES (LAST_INSERT_ID(),?);");
					pstmt.setInt(1, idTag);
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
	public boolean removerDuvida(int idDuvida) {
		if (this.validaDuvida(idDuvida) == false)
			return false;

		try {
			Connection con;
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, idDuvida);
			pstmt.executeQuery();
			pstmt = con.prepareStatement("DELETE FROM DUVIDA_TAG  WHERE ID_DUVIDA=?");
			pstmt.setInt(1, idDuvida);
			pstmt.executeQuery();
			pstmt = con.prepareStatement("DELETE FROM DUVIDA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, idDuvida);
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
	public List<Duvida> buscarDuvidasMateria(int idMateria) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME FROM MATERIA_DUVIDA MD "
							+ "INNER JOIN duvida D ON D.ID_DUVIDA=MD.ID_DUVIDA "
							+ "INNER JOIN usuario U ON D.ID_USUARIO=U.ID_USUARIO WHERE MD.ID_MATERIA=?");
			pstmt.setInt(1, idMateria);
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
			System.out.println("Erro ao carregar das Duvidas relacioandas ao ID matéria" + idMateria);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasUsuario(int idUsuario) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA,D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME FROM DUVIDA D "
							+ "INNER JOIN USUARIO U ON D.ID_USUARIO=U.ID_USUARIO WHERE U.ID_USUARIO=?");
			pstmt.setInt(1, idUsuario);
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
			System.out.println("Erro ao carregar das Duvidas relacioandas ao ID usuário: " + idUsuario);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasTags(int idTag) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, U.NOME FROM DUVIDA D "
							+ "INNER JOIN DUVIDA_TAG DT ON D.ID_DUVIDA=DT.ID_DUVIDA "
							+ "INNER JOIN USUARIO U ON U.ID_USUARIO=D.ID_USUARIO WHERE DT.ID_TAG=?");
			pstmt.setInt(1, idTag);
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
			System.out.println("Erro ao carregar das Duvidas relacionadas ao ID Tag: " + idTag);
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean validaDuvida(int idDuvida) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(ID_DUVIDA) AS TOTAL_RESPOSTAS "
					+ "FROM RESPOSTA WHERE ID_RESPOSTA=? ORDER BY ID_RESPOSTA");
			pstmt.setInt(1, idDuvida);
			ResultSet rs = pstmt.executeQuery();
			int totalResposta = rs.getInt("TOTAL_RESPOSTAS");
			/*
			 * Se totalResposta for igual a 0, portanto a Duvida nao possui
			 * resposta e ela poderá ser removida
			 */
			if (totalResposta == 0)
				return true;

			return false;
		} catch (SQLException e) {
			System.out.println("Erro ao validar Duvida: "+idDuvida);
			System.out.println(e);
			return false;
		}
	}

}
