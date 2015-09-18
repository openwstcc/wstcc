package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Materia;

/**
 * DAO (Data Access Object) responsável pelos métodos de Matérias.
 * 
 * @author Bruno Henrique Calil, Gabriel Queiroz e Victor Hugo.
 *
 */
public class MateriaDAOImplementation implements MateriaDAO {

	@Override
	public List<Materia> buscarTodasMaterias() {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT ID_MATERIA, MATERIA, SEMESTRE FROM MATERIA");
			ResultSet rs = pstmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();

			while (rs.next()) {
				Materia m = new Materia();
				m.setIdMateria(rs.getInt("ID_MATERIA"));
				m.setMateria(rs.getString("MATERIA"));
				m.setSemestre(rs.getInt("SEMESTRE"));
				materias.add(m);
			}
			pstmt.close();
			return materias;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de Matérias");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Materia> buscarMateriasUsuario(int id_usuario) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT M.ID_MATERIA, M.MATERIA, M.SEMESTRE FROM MATERIA_USUARIO AS MA "
							+ "INNER JOIN USUARIO U ON MA.ID_USUARIO=U.ID_USUARIO INNER JOIN MATERIA M ON MA.ID_MATERIA=M.ID_MATERIA "
							+ "WHERE U.ID_USUARIO=?");
			pstmt.setInt(1, id_usuario);
			ResultSet rs = pstmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();
			while (rs.next()) {
				Materia m = new Materia();
				m.setIdMateria(rs.getInt("ID_MATERIA"));
				m.setMateria(rs.getString("MATERIA"));
				m.setSemestre(rs.getInt("SEMESTRE"));
				materias.add(m);
			}
			pstmt.close();
			return materias;

		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de Matérias por usuário");
			System.out.println(e);
			return null;
		}

	}

	@Override
	public List<Materia> buscarMateriasDuvida(int id_duvida) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT M.ID_MATERIA, M.MATERIA, M.SEMESTRE FROM MATERIA AS M "
							+ "INNER JOIN MATERIA_DUVIDA MD ON M.ID_MATERIA=MD.ID_MATERIA WHERE MD.ID_DUVIDA=?");
			pstmt.setInt(1, id_duvida);
			ResultSet rs = pstmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();
			while (rs.next()) {
				Materia m = new Materia();
				m.setIdMateria(rs.getInt("ID_MATERIA"));
				m.setMateria(rs.getString("MATERIA"));
				m.setSemestre(rs.getInt("SEMESTRE"));
				materias.add(m);
			}
			pstmt.close();
			return materias;

		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de Matérias por dúvida");
			System.out.println(e);
			return null;
		}

	}

	@Override
	public boolean adicionarMateriaUsuario(int id_usuario, int id_materia) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO MATERIA_USUARIO (ID_MATERIA, ID_USUARIO) VALUES (?,?)");
			pstmt.setInt(1, id_materia);
			pstmt.setInt(2, id_usuario);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir relação de Materia e Usuario.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removerMateriaUsuario(int id_usuario) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM MATERIA_USUARIO WHERE ID_USUARIO=?");
			pstmt.setInt(1, id_usuario);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao remover relação de Materias com Usuario.");
			e.printStackTrace();
			return false;
		}
	}

}
