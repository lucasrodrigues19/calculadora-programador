package modelo.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import modelo.dao.HistoricoDAO;
import modelo.dao.UsuarioDAO;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;

public class UsuarioDAOI implements UsuarioDAO {

	private Connection con;
	private String sql;

	public UsuarioDAOI(Connection con) {
		this.con = con;
	}

	@Override
	public Boolean login(Usuario usuario) {
		if (con == null)
			throw new IllegalArgumentException("Conexao nula");

		sql = "SELECT * FROM usuario WHERE usuemail = ? AND usutelefone = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, usuario.getUsuemail());
			ps.setString(2, usuario.getUsutelefone());
			rs = ps.executeQuery();
			Integer id = rs.getInt("usuid");
			if (id != null)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return false;
	}

	@Override
	public void save(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "INSERT INTO usuario VALUES (default,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getUsunome());
			ps.setString(2, usuario.getUsuemail());
			ps.setString(3, usuario.getUsutelefone());
			
			int rows= ps.executeUpdate();
			if(rows == 0)
				throw new SQLException("Nemhum registro inserido");
			
			rs = ps.getGeneratedKeys();
			usuario.setUsuid(rs.getInt(1));
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erro no rollback");
			}
			e.printStackTrace();
		} finally {
			DB.closeStatment(ps);
			DB.closeResultSet(rs);
		}
	}

	

	@Override
	public void update(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "UPDATE usuario set usunome = ?,usuemail = ?,usutlefone = ? WHERE usuid = ?";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(4, usuario.getUsuid());
			ps.setString(1, usuario.getUsunome());
			ps.setString(2, usuario.getUsuemail());
			ps.setString(3, usuario.getUsutelefone());
			
			int rows= ps.executeUpdate();
			if(rows == 0)
				throw new SQLException("Nemhum registro atualizado");
			
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erro no rollback");
			}
			e.printStackTrace();
		} finally {
			DB.closeStatment(ps);
		}
	}

	@Override
	public void delete(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "DELETE FROM usuario WHERE usuid = ?";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, usuario.getUsuid());
			int rows= ps.executeUpdate();
			if(rows == 0)
				throw new SQLException("Nemhum registro deletado");
			
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erro no rollback");
			}
			e.printStackTrace();
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public Usuario findID(Integer usuid) {
		if (usuid == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "DELETE FROM usuario WHERE usuid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usuario usuario = null;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, usuid);
			rs = ps.executeQuery();
			while(rs.next()) {
				usuario = getUsuarioRS(rs);
			}
			
		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erro no rollback");
			}
			e.printStackTrace();
		} finally {
			DB.closeStatment(ps);
		}
		return usuario;
	}

	@Override
	public Usuario findHistorico(Integer hisid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findLog(Integer logid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findDateLog(Date logdate, Integer usuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private Historico getHistoricoRS(ResultSet rs) throws SQLException {
		Historico historico = new Historico();
		historico.setHisid(rs.getInt("hisid"));
		historico.setHisdado(rs.getString("hisdado"));
		return historico;
	}
	private Usuario getUsuarioRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setUsuid(rs.getInt("usuid"));
		usuario.setUsunome(rs.getString("usunome"));
		usuario.setUsuemail(rs.getString("usuemail"));
		usuario.setUsutelefone(rs.getString("usutelefone"));
		usuario.getUsuhistorico().addAll(null);
		usuario.getUsulogs().addAll(null);
		return usuario;
	}
	private Logs getLogsRS(ResultSet rs) throws SQLException {
		Logs logs = new Logs();
		
		logs.setLogid(rs.getInt("logid"));
		logs.setLogdata(new java.util.Date(rs.getDate("logdata").getTime()));
		return logs;
		
	}
}
