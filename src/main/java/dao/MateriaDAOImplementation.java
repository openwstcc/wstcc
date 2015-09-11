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

			return materias;
		} catch (SQLException e) {
			System.out.println("Erro ao carregar lista de Matérias");
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Materia> buscarMateriasUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Materia> buscarMateriasDuvida(Duvida d) {
		// TODO Auto-generated method stub
		return null;
	}

}
