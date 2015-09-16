package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Resposta;

public class RespostaDAOImplementation implements RespostaDAO {

	@Override
	public boolean adicionarResposta(Resposta r, int id_duvida, int id_usuario) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO RESPOSTA (ID_USUARIO, ID_DUVIDA, RESPOSTA, RANK, FLAG_PROF, FLAG_ALUNO, DATA_CRIACAO) "
							+ " VALUES (?,?,?,?,?,?,?);");
			pstmt.setInt(1, id_usuario);
			pstmt.setInt(2, id_duvida);
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
	public List<Resposta> buscarRespostas(int id_duvida) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT RESPOSTA, DATA_CRIACAO, FLAG_PROF, FLAG_ALUNO, RANK	 FROM RESPOSTA WHERE ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
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

}
