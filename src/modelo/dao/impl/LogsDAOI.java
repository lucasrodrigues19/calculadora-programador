package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.ex.MySQLException;
import ex.MyException;
import modelo.dao.LogsDAO;
import modelo.dao.UsuarioDAO;
import modelo.dao.factory.DaoFactory;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import utils.DataUtils;

public class LogsDAOI implements LogsDAO {
	private Connection con;
	private String sql;

	public LogsDAOI(Connection con) {
		this.con = con;
	}

	@Override
	public void save(Logs logs) {
		if (logs == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "INSERT INTO logs VALUES (default,?,?)";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setTimestamp(1, DataUtils.parseTimeStamp(logs.getLogdata()));
			ps.setInt(2, logs.getLogusuid());
			int rows = ps.executeUpdate();
			if (rows <= 0)
				throw new SQLException("Nemhum registro inserido");

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção: " + e.getMessage());
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback: " + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MySQLException("Erro: " + e.getMessage());
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public void delete(Logs logs) {
		if (logs == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "DELETE FROM logs WHERE logid = ?";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, logs.getLogid());
			ps.execute();

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção: " + e.getMessage());
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback: " + e.getMessage());
			}
		} finally {
			DB.closeStatment(ps);
		}
	}

	@Override
	public Logs findByID(Integer logid) {
		if (logid == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "SELECT logs.* , usuario.usuemail AS usuemail FROM logs INNER JOIN usuario on logs.logusuid = usuario.usuid where logs.logid= ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Logs logs = null;
		Usuario usuario;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, logid);
			rs = ps.executeQuery();

			while (rs.next()) {
				usuario = getUsuarioDAO().findByID(rs.getInt("logusuid"));// para que o usuario tenha todos os seus logs
																			// registradoss
				logs = getLogsRS(rs);
				logs.setLogusuario(usuario);
				usuario.getUsulogs().add(logs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return logs;
	}

	@Override
	public List<Logs> findByUser(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("Usuario nulo");

		sql = "SELECT logs.* , usuario.usuemail AS usuemail FROM logs INNER JOIN usuario on logs.logusuid = usuario.usuid where usuario.usuid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Logs> result = new ArrayList<Logs>();
		Map<Integer, Usuario> mapUsusario = new HashMap<Integer, Usuario>();

		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, usuario.getUsuid());
			rs = ps.executeQuery();
			usuario = null;
			while (rs.next()) {
				usuario = mapUsusario.get(rs.getInt("logusuid"));
				// todos os logs vão apontar para o mesmo usuario
				// usuario 1-tem-N logs
				if (usuario == null) {
					usuario = getUsuarioRS(rs);
					mapUsusario.put(rs.getInt("logusuid"), usuario);
				}

				Logs logs = getLogsRS(rs);
				logs.setLogusuario(usuario);
				// no usuario de todos os logs sera setado toda sua lista de logs, pois é para o
				// mesmo usuario que todos os logs apontam
				usuario.getUsulogs().add(logs);
				result.add(logs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return result;
	}

	@Override
	public List<Logs> findByDateLog(Logs logs) {
		if (logs == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "SELECT logs.* , usuario.usuemail AS usuemail FROM logs INNER JOIN usuario on logs.logusuid = usuario.usuid where logs.logdate = ? order by logs.logusuid";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Logs> result = new ArrayList<Logs>();
		Map<Integer, Usuario> mapUsusario = new HashMap<Integer, Usuario>();

		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(logs.getLogdata().getTime()));
			rs = ps.executeQuery();
			Usuario usuario = null;
			logs = null;
			while (rs.next()) {
				usuario = mapUsusario.get(rs.getInt("logusuid"));
				// todos os logs vão apontar para o mesmo usuario
				// usuario 1-tem-N logs
				if (usuario == null) {
					usuario = getUsuarioRS(rs);
					mapUsusario.put(rs.getInt("logusuid"), usuario);
				}

				logs = getLogsRS(rs);
				logs.setLogusuario(usuario);
				// no usuario de todos os logs sera setado toda sua lista de logs, pois é para o
				// mesmo usuario que todos os logs apontam
				usuario.getUsulogs().add(logs);
				result.add(logs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return result;
	}

	@Override
	public List<Logs> findAll() {
		sql = "SELECT logs.* , usuario.usuemail AS usuemail FROM logs INNER JOIN usuario on logs.logusuid = usuario.usuid order by logs.logusuid";
		Statement st = null;
		ResultSet rs = null;
		List<Logs> result = new ArrayList<Logs>();
		Map<Integer, Usuario> mapUsusario = new HashMap<Integer, Usuario>();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery(sql);
			Usuario usuario = null;
			Logs logs = null;
			while (rs.next()) {
				usuario = mapUsusario.get(rs.getInt("logusuid"));
				// todos os logs vão apontar para o mesmo usuario
				// usuario 1-tem-N logs
				if (usuario == null) {
					usuario = getUsuarioRS(rs);
					mapUsusario.put(rs.getInt("logusuid"), usuario);
				}

				logs = getLogsRS(rs);
				logs.setLogusuario(usuario);
				// no usuario de todos os logs, sera setado toda sua lista de logs, pois é para
				// o
				// mesmo usuario que todos os logs apontam
				usuario.getUsulogs().add(logs);
				result.add(logs);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(st);
		}
		return result;
	}

	private Usuario getUsuarioRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		Integer usuid = rs.getInt("logusuid");

		if (usuid != null)
			usuario.setUsuid(rs.getInt("logusuid"));

//		if (rs.getString("usunome") != null)
//			usuario.setUsunome(rs.getString("usunome"));

		if (rs.getString("usuemail") != null)
			usuario.setUsuemail(rs.getString("usuemail"));

//		if (rs.getString("usutelefone") != null)
//			usuario.setUsutelefone(rs.getString("usutelefone"));

		usuario.setUsulogs(new ArrayList<Logs>());
		usuario.setUsuhistorico(new ArrayList<Historico>());
		return usuario;
	}

	private Logs getLogsRS(ResultSet rs) throws SQLException {
		Logs logs = new Logs();

		logs.setLogid(rs.getInt("logid"));
		try {
			// formata a data que vem, depois da um parse
			logs.setLogdata(DataUtils.parse(DataUtils.format(rs.getDate("logdata"), "dd/MM/yyyy HH:mm:ss"),
					"dd/MM/yyyy HH:mm:ss"));
		} catch (MyException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		}
		return logs;

	}

	private UsuarioDAO getUsuarioDAO() {
		return DaoFactory.getUsuarioDAO();
	}

	@Override
	public void deleteByUser(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "DELETE FROM logs WHERE logusuid = ?";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, usuario.getUsuid());
			ps.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção: " + e.getMessage());
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback: " + e.getMessage());
			}
		} finally {
			DB.closeStatment(ps);
		}
		
	}
}
