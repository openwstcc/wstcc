package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Duvida;
import model.Materia;
import model.Tag;
import model.Usuario;

public class DuvidaDAOImplementation implements DuvidaDAO {

	public Date getToday() {
		return new Date();
	}

	@Override
	public void adicionarDuvida(Duvida d, Usuario u, List<Materia> m, List<Tag> t) {

		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO DUVIDA (ID_USUARIO, TITULO_DUVIDA, CONTEUDO_DUVIDA, DATA_CRIACAO)"
							+ "VALUES (?,?,?,?)");
			pstmt.setInt(1, u.getIdUsuario());
			pstmt.setString(2, d.getTitulo());
			pstmt.setString(3, d.getConteudo());
			java.sql.Date dataCriacao = new java.sql.Date(getToday().getTime());
			pstmt.setDate(4, dataCriacao);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir o Dúvida");
			System.out.println(e);
		}

	}

	@Override
	public void removerDuvida(int id_duvida) {
		if (this.validaDuvida(id_duvida) == true) {

			try {
				Connection con;
				con = JDBCUtil.getInstance().getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("DELETE FROM MATERIA_DUVIDA WHERE ID_DUVIDA = " + id_duvida);
				pstmt.executeQuery();
				pstmt = con.prepareStatement("DELETE FROM DUVIDA_TAG  WHERE ID_DUVIDA = " + id_duvida);
				pstmt.executeQuery();
				pstmt = con.prepareStatement("DELETE FROM DUVIDA WHERE ID_DUVIDA" + id_duvida);
				pstmt.executeQuery();
				pstmt.close();
				System.out.println("Duvida removida com sucesso!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public List<Duvida> buscarDuvidasMateria(int id_materia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Duvida> buscarDuvidasUsuario(int id_usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Duvida> buscarDuvidasTags(int id_tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validaDuvida(int id_duvida) {
		Boolean valida = false;
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("select COUNT(id_duvida) as totalDeRespostas"
					+ "from RESPOSTA where id_resposta=? ORDER BY id_resposta");
			pstmt.setInt(1, id_duvida);
			ResultSet rs = pstmt.executeQuery();
			int totalResposta = rs.getInt("totalDeRespostas");
			if (totalResposta == 0) {
				valida = true;
				/*
				 * Se totalResposta for igual a 0, portanto a Duvida nao possui
				 * resposta e ela poderá ser removida
				 */
			}
		} catch (SQLException e) {
			System.out.println("Erro ao validar Duvida");
			System.out.println(e);
		}
		return valida;
	}

}
