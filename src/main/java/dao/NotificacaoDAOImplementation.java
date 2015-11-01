package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import util.NoticacaoDuvidas;

public class NotificacaoDAOImplementation implements NotificacaoDAO {

	@Override
	public List<NoticacaoDuvidas> notificarDuvidaRespostaRelacionadaMateriaData() {
			
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		
				List<NoticacaoDuvidas> notificacoes = new ArrayList<NoticacaoDuvidas>();
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT DISTINCT U.NOME,U.EMAIL,M.MATERIA,D.TITULO_DUVIDA,"
							+ "(SELECT COUNT(ID_RESPOSTA) FROM RESPOSTA R1 WHERE R1.ID_DUVIDA=D.ID_DUVIDA) AS QTD_RESPOSTAS FROM USUARIO U "
							+ "INNER JOIN MATERIA_USUARIO MU ON (U.ID_USUARIO=MU.ID_USUARIO) "
							+ "INNER JOIN MATERIA M ON (M.ID_MATERIA=MU.ID_MATERIA) "
							+ "INNER JOIN MATERIA_DUVIDA MD ON (MD.ID_MATERIA=M.ID_MATERIA)"
							+ "INNER JOIN DUVIDA D ON (D.ID_DUVIDA=MD.ID_DUVIDA)"
							+ "INNER JOIN RESPOSTA R ON (R.ID_DUVIDA=D.ID_DUVIDA) WHERE R.DATA_CRIACAO=?");
			String dataBanco = sdf.format(calendar.getTime());
			pstmt.setString(1, dataBanco);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NoticacaoDuvidas nd = new NoticacaoDuvidas();
				nd.setNomeUsuario(rs.getString("NOME"));
				nd.setEmailUsuario(rs.getString("EMAIL"));
				nd.setMateriaDuvida(rs.getString("MATERIA"));
				nd.setTituloDuvida(rs.getString("TITULO_DUVIDA"));
				nd.setQuantidadeResposta(rs.getInt("QTD_RESPOSTAS"));
				notificacoes.add(nd);
			}
			pstmt.close();
			return notificacoes;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de Matérias");
			System.out.println(e);
			return null;
		}

	}

	@Override
	public List<NoticacaoDuvidas> notificarDuvidaRespostaRelacionadaUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
