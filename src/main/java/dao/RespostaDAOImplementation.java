package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Resposta;

/**
 * DAO (Data Access Object) responsável pelos métodos de Respostas.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public class RespostaDAOImplementation implements RespostaDAO {

	@Override
	public boolean adicionarResposta(Resposta r, int idDuvida, int idUsuario) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO RESPOSTA (ID_USUARIO, ID_DUVIDA, RESPOSTA, RANK, FLAG_PROF, FLAG_ALUNO, DATA_CRIACAO) "
							+ " VALUES (?,?,?,?,?,?,?);");
			pstmt.setInt(1, idDuvida);
			pstmt.setInt(2, idDuvida);
			pstmt.setString(3, r.getResposta());
			pstmt.setInt(4, r.getRank());
			pstmt.setBoolean(5, r.isFlagProfessor());
			pstmt.setBoolean(6, r.isFlagCriador());
			java.sql.Date dataBanco = new java.sql.Date(r.getDataCriacao().getTime());
			pstmt.setDate(7, dataBanco);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Resposta.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Resposta> buscarRespostas(int idDuvida) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT RESPOSTA, DATA_CRIACAO, FLAG_PROF, FLAG_ALUNO, RANK	 FROM RESPOSTA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, idDuvida);
			ResultSet rs = pstmt.executeQuery();
			List<Resposta> respostas = new ArrayList<Resposta>();

			while (rs.next()) {
				Resposta r = new Resposta();
				r.setResposta(rs.getString("RESPOSTA"));
				r.setDataCriacao(rs.getDate("DATA_CRIACAO"));
				r.setFlagProfessor(rs.getBoolean("FLAG_PROF"));
				r.setFlagCriador(rs.getBoolean("FLAG_ALUNO"));
				r.setRank(rs.getInt("RANK"));
				respostas.add(r);
			}

			pstmt.close();
			return respostas;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Respostas.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean adicionaRank(int idResposta) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE RESPOSTA SET RANK=RANK+1 WHERE ID_RESPOSTA=?");
			pstmt.setInt(1, idResposta);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Rank.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alteraFlagAluno(int idResposta) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE RESPOSTA SET FLAG_ALUNO=TRUE WHERE ID_RESPOSTA=?");
			pstmt.setInt(1, idResposta);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Flag de Aluno.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alteraFlagProfessor(int idResposta) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE RESPOSTA SET FLAG_PROF=TRUE WHERE ID_RESPOSTA=?");
			pstmt.setInt(1, idResposta);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Flag de Professor.");
			e.printStackTrace();
			return false;
		}
	}

}
