package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAOImplementation implements UsuarioDAO {

	@Override
	public boolean adicionarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT IGNORE INTO USUARIO (NOME, SOBRENOME, TELEFONE, DATA_NASC, EMAIL, PERFIL, SENHA) "
							+ " VALUES (?,?,?,?,?,?,MD5(?));");
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
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuário.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alterarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"UPDATE USUARIO SET NOME=?, SOBRENOME=?, TELEFONE=?, DATA_NASC=?, EMAIL=?, PERFIL=?"
							+ "WHERE ID_USUARIO=?;");
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
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar informações de Usuário.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Usuario buscarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"SELECT NOME, SOBRENOME, TELEFONE, EMAIL, DATA_NASC, PERFIL FROM USUARIO WHERE ID_USUARIO=?");
			pstmt.setInt(1, u.getIdUsuario());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u.setNome(rs.getString("NOME"));
				u.setSobrenome(rs.getString("SOBRENOME"));
				u.setTelefone(rs.getString("TELEFONE"));
				u.setEmail(rs.getString("EMAIL"));
				u.setDataNasc(new java.util.Date(rs.getDate("DATA_NASC").getTime()));
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
	public boolean alterarSenha(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE USUARIO SET SENHA=MD5(?) WHERE ID_USUARIO=?;");
			pstmt.setString(1, u.getSenha());
			pstmt.setInt(2, u.getIdUsuario());
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar senha de Usuário.");
			e.printStackTrace();
			return false;
		}
	}

}
