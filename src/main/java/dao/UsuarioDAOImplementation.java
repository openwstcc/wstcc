package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAOImplementation implements UsuarioDAO {

	@Override
	public void adicionarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO USUARIO (NOME, SOBRENOME, TELEFONE, DATA_NASC, EMAIL, PERFIL, MD5(SENHA)) "
							+ " VALUES (?,?,?,?,?,?,?,?);");
			pstmt.setString(1, u.getNome());
			pstmt.setString(2, u.getSobrenome());
			pstmt.setString(3, u.getTelefone());
			java.sql.Date dataBanco = new java.sql.Date(u.getDataNasc().getTime());
			pstmt.setDate(4, dataBanco);
			pstmt.setString(5, u.getEmail());
			pstmt.setString(6, u.getPerfil());
			pstmt.setString(7, u.getSenha());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuário.");
			e.printStackTrace();
		}
	}

	@Override
	public void alterarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE USUARIO SET NOME=?, SOBRENOME=?, TELEFONE=?, DATA_NASC=?, EMAIL=?, PERFIL=?"
							+ "WHERE USUARIO_ID=?);");
			pstmt.setString(1, u.getNome());
			pstmt.setString(2, u.getSobrenome());
			pstmt.setString(3, u.getTelefone());
			java.sql.Date dataBanco = new java.sql.Date(u.getDataNasc().getTime());
			pstmt.setDate(4, dataBanco);
			pstmt.setString(5, u.getEmail());
			pstmt.setString(6, u.getPerfil());
			pstmt.setInt(7, u.getIdUsuario());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar informações de Usuário.");
			e.printStackTrace();
		}
	}

	@Override
	public Usuario buscarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM USUARIO WHERE ID_USUARIO=?");
			pstmt.setInt(1, u.getIdUsuario());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u.setNome(rs.getString("NOME"));
				u.setSobrenome(rs.getString("SOBRENOME"));
				u.setTelefone(rs.getString("TELEFONE"));
				u.setEmail(rs.getString("EMAIL"));
				u.setDataNasc(rs.getDate("DATA_NASC"));
				u.setPerfil(rs.getString("PERFIL"));
			}

			pstmt.close();
			return u;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Usuário.");
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void alterarSenha(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE USUARIO SET SENHA=MD5(?) WHERE USUARIO_ID=?);");
			pstmt.setString(1, u.getSenha());
			pstmt.setInt(2, u.getIdUsuario());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar senha de Usuário.");
			e.printStackTrace();
		}
	}

}
