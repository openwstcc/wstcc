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
	public boolean adicionarResposta(Resposta r) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO RESPOSTA (ID_USUARIO, ID_DUVIDA, RESPOSTA, RANK, FLAG_PROF, FLAG_ALUNO, DATA_CRIACAO) "
							+ " VALUES (?,?,?,?,?,?,?);");
			pstmt.setInt(1, r.getIdUsuario());
			pstmt.setInt(2, r.getIdDuvida());
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
					"SELECT r.ID_RESPOSTA,r.ID_USUARIO,r.ID_DUVIDA,r.RESPOSTA, r.DATA_CRIACAO, r.FLAG_PROF, r.FLAG_ALUNO, r.RANK, u.NOME FROM RESPOSTA r INNER JOIN usuario u ON u.ID_USUARIO = r.ID_USUARIO WHERE ID_DUVIDA=?");
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
				r.setCriador(rs.getString("NOME"));
				r.setIdDuvida(rs.getInt("ID_DUVIDA"));
				r.setIdUsuario(rs.getInt("ID_USUARIO"));
				r.setIdResposta(rs.getInt("ID_RESPOSTA"));
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
	public boolean adicionaRank(int idResposta,int idUsuario) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("CALL sp_set_like(?,?)");
			pstmt.setInt(1, idResposta);
			pstmt.setInt(2, idUsuario);
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
