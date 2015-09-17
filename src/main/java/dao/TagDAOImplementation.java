package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Tag;

public class TagDAOImplementation implements TagDAO {

	@Override
	public boolean inserirTag(Tag t) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT IGNORE INTO TAG (NOME) VALUES (?)");
			pstmt.setString(1, t.getNome());
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Tag.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Tag> buscarTags() {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT ID_TAG, NOME FROM TAG");
			ResultSet rs = pstmt.executeQuery();
			List<Tag> tags = new ArrayList<Tag>();

			while (rs.next()) {
				Tag t = new Tag();
				t.setIdTag(rs.getInt("ID_TAG"));
				t.setNome(rs.getString("NOME"));
				tags.add(t);
			}

			pstmt.close();
			return tags;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Usuário.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Tag buscarTag(Tag t) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT ID_TAG, NOME FROM TAG WHERE ID_TAG=?");
			pstmt.setInt(1, t.getIdTag());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
				t.setNome(rs.getString("NOME"));

			pstmt.close();
			return t;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Tag.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Tag> buscarTagsDuvida(int id_duvida) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT T.ID_TAG, T.NOME FROM TAG AS T INNER JOIN "
					+ "DUVIDA_TAG AS TD ON T.ID_TAG=TD.ID_TAG WHERE TD.ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
			ResultSet rs = pstmt.executeQuery();
			List<Tag> tags = new ArrayList<Tag>();
			Tag t = new Tag();

			while (rs.next()) {
				t.setIdTag(rs.getInt("ID_TAG"));
				t.setNome(rs.getString("NOME"));
				tags.add(t);
			}

			pstmt.close();
			return tags;
		} catch (SQLException e) {
			System.out.println("Erro ao buscar Usuário.");
			e.printStackTrace();
			return null;
		}
	}

}
