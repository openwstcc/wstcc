package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class UsuarioDAOImplementation implements UsuarioDAO {

	@Override
	public void adicionarUsuario(Usuario u) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO USUARIO (NOME, SOBRENOME, TELEFONE, DATA_NASC, EMAIL, PERFIL) "
							+ " VALUES (?,?,?,?,?,?,?);");
			pstmt.setString(1, u.getNome());
			pstmt.setString(2, u.getSobrenome());
			pstmt.setInt(3, u.getTelefone());
			pstmt.setString(4, u.getEmail());
			java.sql.Date dataBanco = new java.sql.Date(u.getDataNasc().getTime());
			pstmt.setDate(5, dataBanco);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Usuário.");
			e.printStackTrace();
		}
		
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO SENHA (ID_USUARIO, SENHA) VALUES (?,?);");		
			//DÚVIDA: COMO PEGAR O ID DO USUÁRIO PARA VINCULAR A SENHA?
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println("Erro ao inserir vincular senha ao usuário.");
			e.printStackTrace();
		}		
	}

	@Override
	public void alterarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removerUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Usuario> buscarUsuarios(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

}
