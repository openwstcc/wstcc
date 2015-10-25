package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.NoticacaoDuvidas;

public class NotificacaoDAOImplementation implements NotificacaoDAO {

	@Override
	public NoticacaoDuvidas notificarDuvidaResposta(int idResposta) {
		NoticacaoDuvidas nd = new NoticacaoDuvidas();
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT D.TITULO_DUVIDA, U.NOME,U.EMAIL FROM resposta R"
					+ " INNER JOIN DUVIDA D ON R.ID_DUVIDA = D.ID_DUVIDA INNER JOIN USUARIO U"
					+ " ON D.ID_USUARIO=U.ID_USUARIO WHERE R.ID_RESPOSTA=?");
			pstmt.setInt(1, idResposta);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				nd.setTituloDuvida(rs.getString("TITULO_DUVIDA"));
				nd.setNome(rs.getString("NOME"));
				nd.setEmail(rs.getString("EMAIL"));
			}
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("Erro na  NotificacaoDAOImplementation ao consultar uma notificação");
			e.printStackTrace();
		}
		return nd;
	}

}
