package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Duvida;
import model.Materia;
import model.Usuario;

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
	public List<Materia> buscarMateriasUsuario(Usuario u) {
		try {
			Connection con = JDBCUtil.getInstance().getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT M.ID_MATERIA, M.MATERIA, M.SEMESTRE FROM MATERIA_USUARIO AS MA "
							+ "INNER JOIN USUARIO U ON MA.ID_USUARIO=U.ID_USUARIO INNER JOIN MATERIA M ON MA.ID_MATERIA=M.ID_MATERIA"
							+ "WHERE U.ID_USUARIO=?");
			pstmt.setInt(1, u.getIdUsuario());
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
	public List<Materia> buscarMateriasDuvida(Duvida d) {
		// TODO Auto-generated method stub
		return null;
	}

}
