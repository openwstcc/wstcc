package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Duvida;
import util.JsonPesquisa;

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
							+ "VALUES (?,?,?,NOW())");
			pstmt.setInt(1, idUsuario);
			pstmt.setString(3, d.getConteudo());
			pstmt.setString(2, d.getTitulo());
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
					"SELECT DISTINCT D.ID_DUVIDA,D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME,U.ID_USUARIO, "
							+ "(SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)  AS QTD_RESPOSTAS FROM MATERIA_DUVIDA MD "
							+ "INNER JOIN DUVIDA D ON D.ID_DUVIDA=MD.ID_DUVIDA "
							+ "INNER JOIN USUARIO U ON D.ID_USUARIO=U.ID_USUARIO WHERE MD.ID_MATERIA=?");
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
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				duvidas.add(d);
			}
			
			//Relaciona as materias das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT ID_MATERIA FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<Integer> result = new ArrayList<Integer>();
				while(rs.next())
					result.add(rs.getInt("ID_MATERIA"));
				
				int[] temp = new int[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setMaterias(temp);
			}

			//Relaciona as tags das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT T.NOME FROM TAG T INNER JOIN DUVIDA_TAG DT ON DT.ID_TAG=T.ID_TAG WHERE DT.ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next())
					result.add(rs.getString("NOME"));
				
				String[] temp = new String[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setTags(temp);
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
					"SELECT D.ID_DUVIDA,D.TITULO_DUVIDA,D.CONTEUDO_DUVIDA,D.DATA_CRIACAO,U.NOME,U.ID_USUARIO, "
							+ "(SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)  AS QTD_RESPOSTAS FROM DUVIDA D "
							+ "INNER JOIN USUARIO U ON D.ID_USUARIO=U.ID_USUARIO WHERE U.ID_USUARIO=? ORDER BY D.ID_DUVIDA DESC");
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
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				duvidas.add(d);
			}
			
			//Relaciona as materias das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT ID_MATERIA FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<Integer> result = new ArrayList<Integer>();
				while(rs.next())
					result.add(rs.getInt("ID_MATERIA"));
				
				int[] temp = new int[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setMaterias(temp);
			}

			//Relaciona as tags das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT T.NOME FROM TAG T INNER JOIN DUVIDA_TAG DT ON DT.ID_TAG=T.ID_TAG WHERE DT.ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next())
					result.add(rs.getString("NOME"));
				
				String[] temp = new String[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setTags(temp);
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
					"SELECT D.ID_DUVIDA, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, U.NOME, U.ID_USUARIO,"
							+ "(SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)  AS QTD_RESPOSTAS FROM DUVIDA D "
							+ "INNER JOIN DUVIDA_TAG DT ON D.ID_DUVIDA=DT.ID_DUVIDA "
							+ "INNER JOIN USUARIO U ON U.ID_USUARIO=D.ID_USUARIO WHERE DT.ID_TAG=? ORDER BY D.ID_DUVIDA DESC");
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
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
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
			System.out.println("Erro ao validar Duvida: " + idDuvida);
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Duvida> buscarDuvidas() {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT D.ID_DUVIDA, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, U.NOME, U.ID_USUARIO, "
							+ "(SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)  AS QTD_RESPOSTAS FROM DUVIDA D "
							+ "INNER JOIN USUARIO U ON U.ID_USUARIO=D.ID_USUARIO ORDER BY D.ID_DUVIDA DESC");
			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();
			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				d.setCriador(rs.getString("NOME"));
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				d.setIdUsuario(rs.getInt("ID_USUARIO"));
				duvidas.add(d);
			}
			
			//Relaciona as materias das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT ID_MATERIA FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<Integer> result = new ArrayList<Integer>();
				while(rs.next())
					result.add(rs.getInt("ID_MATERIA"));
				
				int[] temp = new int[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setMaterias(temp);
			}

			//Relaciona as tags das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT T.NOME FROM TAG T INNER JOIN DUVIDA_TAG DT ON DT.ID_TAG=T.ID_TAG WHERE DT.ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next())
					result.add(rs.getString("NOME"));
				
				String[] temp = new String[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setTags(temp);
			}
			
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar todas as Dúvidas");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasUsuarioRelacionadoMateria(int idUsuario) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT DISTINCT D.ID_DUVIDA, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, U.NOME,  U.ID_USUARIO, (SELECT COUNT(ID_RESPOSTA)"
							+ "FROM RESPOSTA R WHERE R.ID_DUVIDA = MD.ID_DUVIDA) AS QTD_RESPOSTAS FROM MATERIA_DUVIDA MD "
							+ "INNER JOIN MATERIA_USUARIO MU ON MU.ID_MATERIA = MD.ID_MATERIA INNER JOIN USUARIO U on MU.ID_USUARIO = U.ID_USUARIO "
							+ "INNER JOIN DUVIDA D ON D.ID_DUVIDA = MD.ID_DUVIDA WHERE U.ID_USUARIO = ? ORDER BY D.ID_DUVIDA DESC");
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
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				d.setIdUsuario(rs.getInt("ID_USUARIO"));
				duvidas.add(d);
			}
			
			//Relaciona as materias das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT ID_MATERIA FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<Integer> result = new ArrayList<Integer>();
				while(rs.next())
					result.add(rs.getInt("ID_MATERIA"));
				
				int[] temp = new int[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setMaterias(temp);
			}

			//Relaciona as tags das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT T.NOME FROM TAG T INNER JOIN DUVIDA_TAG DT ON DT.ID_TAG=T.ID_TAG WHERE DT.ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next())
					result.add(rs.getString("NOME"));
				
				String[] temp = new String[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setTags(temp);
			}
			
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar todas as Dúvidas");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasUsuarioRelacionadoMateriaData(int idUsuario, int idMateria) {
		try {

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			calendar.add(Calendar.DAY_OF_YEAR, -1); // data de ontem

			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT DISTINCT D.ID_DUVIDA, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, U.NOME, U.ID_USUARIO, (SELECT COUNT(ID_RESPOSTA)"
							+ "FROM RESPOSTA R WHERE R.ID_DUVIDA = MD.ID_DUVIDA) AS QTD_RESPOSTAS FROM MATERIA_DUVIDA MD "
							+ "INNER JOIN MATERIA_USUARIO MU ON MU.ID_MATERIA = MD.ID_MATERIA INNER JOIN USUARIO U on MU.ID_USUARIO = U.ID_USUARIO "
							+ "INNER JOIN DUVIDA D ON D.ID_DUVIDA = MD.ID_DUVIDA "
							+ "INNER JOIN RESPOSTA R1 ON D.ID_DUVIDA = MD.ID_DUVIDA  "
							+ "WHERE R1.DATA_CRIACAO BETWEEN ? AND ? AND U.ID_USUARIO = ? AND MU.ID_MATERIA=?");
			pstmt.setString(1, sdf.format(calendar.getTime())); // data inicial
			calendar.add(Calendar.DAY_OF_YEAR, +1); // data de hoje
			pstmt.setString(2, sdf.format(calendar.getTime())); // data final
			pstmt.setInt(3, idUsuario);
			pstmt.setInt(4, idMateria);

			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();
			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				d.setCriador(rs.getString("NOME"));
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				d.setIdUsuario(rs.getInt("ID_USUARIO"));
				duvidas.add(d);
			}
			return duvidas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar todas as Dúvidas");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Duvida> buscarDuvidasFiltro(JsonPesquisa jsonPesquisa) {
		try {

			Connection con = JDBCUtil.getInstance().getConnection();
			StringBuilder stringBuilder = new StringBuilder();
			StringBuilder stringDuvida = new StringBuilder();
			StringBuilder stringWhereDuvida  = new StringBuilder();
			StringBuilder stringTAG = new StringBuilder();
			StringBuilder stringWhereTAG = new StringBuilder();
			StringBuilder stringMateria  = new StringBuilder();
			StringBuilder stringWhereMateria = new StringBuilder();
			
			for (String pesquisa : jsonPesquisa.getPesquisa()) {
				if (jsonPesquisa.isTitulo()){
					if (stringDuvida.toString().equals(""))
						stringDuvida.append("SELECT  DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO,"
								+ " (SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA) AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME"
								+ " FROM DUVIDA D INNER JOIN MATERIA_DUVIDA MD ON MD.ID_DUVIDA = D.ID_DUVIDA "
								+ " INNER JOIN MATERIA M ON M.ID_MATERIA = MD.ID_MATERIA WHERE");
					
					if(stringWhereDuvida.toString().equals(""))
						stringWhereDuvida.append(" D.TITULO_DUVIDA LIKE '%"+pesquisa+"%'");
					else
						stringWhereDuvida.append(" OR D.TITULO_DUVIDA LIKE '%"+pesquisa+"%'");
				}			
				if (jsonPesquisa.isConteudo()){
					if (stringDuvida.toString().equals(""))
						stringDuvida.append("SELECT  DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO,"
								+ " (SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA) AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME"
								+ " FROM DUVIDA D INNER JOIN MATERIA_DUVIDA MD ON MD.ID_DUVIDA = D.ID_DUVIDA "
								+ " INNER JOIN MATERIA M ON M.ID_MATERIA = MD.ID_MATERIA WHERE");
					if(stringWhereDuvida.toString().equals(""))
						stringWhereDuvida.append(" D.CONTEUDO_DUVIDA LIKE '%"+pesquisa+"%'");
					else
						stringWhereDuvida.append(" OR D.CONTEUDO_DUVIDA LIKE '%"+pesquisa+"%'");
				}			
				if (jsonPesquisa.isTag()){
					if(stringTAG.toString().equals("")){
						if(stringDuvida.toString().equals(""))
							stringTAG.append("SELECT DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, (SELECT COUNT(ID_RESPOSTA) FROM"
									+ " RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA) AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME FROM DUVIDA D"
									+ " INNER JOIN DUVIDA_TAG DT ON DT.ID_DUVIDA=D.ID_DUVIDA INNER JOIN TAG T ON T.ID_TAG = DT.ID_TAG WHERE ");
						else
							stringTAG.append(" UNION SELECT DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO, (SELECT COUNT(ID_RESPOSTA) FROM"
									+ " RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA) AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME FROM DUVIDA D"
									+ " INNER JOIN DUVIDA_TAG DT ON DT.ID_DUVIDA=D.ID_DUVIDA INNER JOIN TAG T ON T.ID_TAG = DT.ID_TAG WHERE ");
					}
						if(stringWhereTAG.toString().equals(""))
							stringWhereTAG.append(" T.NOME LIKE '%"+pesquisa+"%'");
						else
							stringWhereTAG.append(" OR T.NOME LIKE '%"+pesquisa+"%'");
						
					}
				if (jsonPesquisa.isMateria()){
					if(stringWhereMateria.toString().equals("")){
						if(stringDuvida.toString().equals("") && stringTAG.toString().equals(""))
							stringMateria.append("SELECT  DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO,"
									+ " (SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)"
									+ " AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME FROM DUVIDA D INNER JOIN MATERIA_DUVIDA MD ON MD.ID_DUVIDA = D.ID_DUVIDA"
									+ " INNER JOIN MATERIA M ON M.ID_MATERIA = MD.ID_MATERIA WHERE ");
						else
							stringMateria.append(" UNION SELECT DISTINCT D.ID_DUVIDA, D.ID_USUARIO, D.TITULO_DUVIDA, D.CONTEUDO_DUVIDA, D.DATA_CRIACAO,"
									+ " (SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R WHERE R.ID_DUVIDA = D.ID_DUVIDA)"
									+ " AS QTD_RESPOSTAS, (SELECT U.NOME FROM USUARIO U WHERE U.ID_USUARIO=D.ID_USUARIO) AS NOME FROM DUVIDA D INNER JOIN MATERIA_DUVIDA MD ON MD.ID_DUVIDA = D.ID_DUVIDA"
									+ " INNER JOIN MATERIA M ON M.ID_MATERIA = MD.ID_MATERIA WHERE");
					}
					if(stringWhereMateria.toString().equals(""))
						stringWhereMateria.append(" M.MATERIA LIKE '%"+pesquisa+"%'");
					else
						stringWhereMateria.append(" OR M.MATERIA LIKE '%"+pesquisa+"%'");
					}
				
			}
			
			
			stringBuilder.append(stringDuvida.toString()+stringWhereDuvida.toString()+stringTAG.toString()+stringWhereTAG.toString()+stringMateria.toString()+stringWhereMateria.toString());
			PreparedStatement pstmt = con.prepareStatement(stringBuilder.toString());
			ResultSet rs = pstmt.executeQuery();
			List<Duvida> duvidas = new ArrayList<Duvida>();

			while (rs.next()) {
				Duvida d = new Duvida();
				d.setIdDuvida(rs.getInt("ID_DUVIDA"));
				d.setTitulo(rs.getString("TITULO_DUVIDA"));
				d.setConteudo(rs.getString("CONTEUDO_DUVIDA"));
				d.setDataCriacao(rs.getDate("DATA_CRIACAO"));	
				d.setQtdRespostas(rs.getInt("QTD_RESPOSTAS"));
				d.setIdUsuario(rs.getInt("ID_USUARIO"));
				d.setCriador(rs.getString("NOME"));
				duvidas.add(d);
			}
			
			//Relaciona as materias das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT ID_MATERIA FROM MATERIA_DUVIDA WHERE ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<Integer> result = new ArrayList<Integer>();
				while(rs.next())
					result.add(rs.getInt("ID_MATERIA"));
				
				int[] temp = new int[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setMaterias(temp);
			}

			//Relaciona as tags das dúvidas
			for(Duvida d : duvidas){
				pstmt = con.prepareStatement("SELECT T.NOME FROM TAG T INNER JOIN DUVIDA_TAG DT ON DT.ID_TAG=T.ID_TAG WHERE DT.ID_DUVIDA=?");
				pstmt.setInt(1, d.getIdDuvida());
				rs = pstmt.executeQuery();
				
				ArrayList<String> result = new ArrayList<String>();
				while(rs.next())
					result.add(rs.getString("NOME"));
				
				String[] temp = new String[result.size()];
				for(int i=0;i<result.size();i++)
					temp[i]=result.get(i);
					
				d.setTags(temp);
			}
			
			return duvidas;
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Dúvidas");
			System.out.println(e);

			List<Duvida> duvidas = new ArrayList<Duvida>();
			Duvida d = new Duvida();
			d.setTitulo("Erro ao buscar Dúvidas");
			d.setConteudo(e.toString());
			duvidas.add(d);

			return duvidas;
		}
	}
}
