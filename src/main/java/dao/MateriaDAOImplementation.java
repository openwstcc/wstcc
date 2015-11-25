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
	public List<Materia> buscarMateriasUsuario(int idUsuario) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT M.ID_MATERIA, M.MATERIA, M.SEMESTRE,IF(EXISTS(SELECT * FROM MATERIA_USUARIO MU WHERE M.ID_MATERIA = MU.ID_MATERIA AND MU.ID_USUARIO = ?), 1, 0) AS MARCADO FROM MATERIA M");
			pstmt.setInt(1, idUsuario);
			ResultSet rs = pstmt.executeQuery();
			List<Materia> materias = new ArrayList<Materia>();
			while (rs.next()) {
				Materia m = new Materia();
				m.setIdMateria(rs.getInt("ID_MATERIA"));
				m.setMateria(rs.getString("MATERIA"));
				m.setSemestre(rs.getInt("SEMESTRE"));
				m.setMarcado(rs.getBoolean("MARCADO"));
				m.setIdUsuario(idUsuario);
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
	public List<Materia> buscarMateriasDuvida(int idDuvida) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT M.ID_MATERIA, M.MATERIA, M.SEMESTRE FROM MATERIA AS M "
							+ "INNER JOIN MATERIA_DUVIDA MD ON M.ID_MATERIA=MD.ID_MATERIA WHERE MD.ID_DUVIDA=?");
			pstmt.setInt(1, idDuvida);
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
	public boolean adicionarMateriaUsuario(int idUsuario, int idMateria) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO MATERIA_USUARIO (ID_MATERIA, ID_USUARIO) VALUES (?,?)");
			pstmt.setInt(1, idMateria);
			pstmt.setInt(2, idUsuario);
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
	public boolean removerMateriaUsuario(int idUsuario) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM MATERIA_USUARIO WHERE ID_USUARIO=?");
			pstmt.setInt(1, idUsuario);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao remover relação de Materias com Usuario.");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int atualizarMateriasUsuario(List<Materia> materias) {
		Connection con;
		try {
			con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt;			
			for (Materia m : materias) {
				if(m.isMarcado()){
					pstmt = con.prepareStatement("INSERT INTO MATERIA_USUARIO (ID_MATERIA, ID_USUARIO) VALUES (?,?)");
					pstmt.setInt(1, m.getIdMateria());
					pstmt.setInt(2, m.getIdUsuario());
					pstmt.executeUpdate();
					pstmt.close();
				}				
			}
			return 200;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir relação de Materia e Usuario.");
			e.printStackTrace();
			return e.getErrorCode();
		}
	}

}
