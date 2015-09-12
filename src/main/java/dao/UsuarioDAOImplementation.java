package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAOImplementation implements UsuarioDAO {

	@Override
	public void adicionarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO USUARIO (NOME, SOBRENOME, TELEFONE, DATA_NASC, EMAIL, PERFIL, MD5(SENHA)) "
							+ " VALUES (?,?,?,?,?,?,?);");
			pstmt.setString(1, u.getNome());
			pstmt.setString(2, u.getSobrenome());
			pstmt.setString(3, u.getTelefone());
			pstmt.setString(4, u.getEmail());
			java.sql.Date dataBanco = new java.sql.Date(u.getDataNasc().getTime());
			pstmt.setDate(5, dataBanco);
			pstmt.setString(6, u.getSenha());
			pstmt.executeUpdate();
			pstmt.close();			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuário.");
			e.printStackTrace();
		}		
	}

	@Override
	public void alterarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario buscarUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

}
