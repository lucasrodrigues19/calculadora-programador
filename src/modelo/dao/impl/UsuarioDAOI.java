package modelo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import db.ex.MySQLException;
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
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getUsunome());
			ps.setString(2, usuario.getUsuemail());
			ps.setString(3, usuario.getUsutelefone());

			int rows = ps.executeUpdate();
			if (rows == 0)
				throw new SQLException("Nemhum registro inserido");

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção: "+e.getMessage());
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback: "+e.getMessage());
			}
		} finally {
			DB.closeStatment(ps);
		}
	}

	@Override
	public void update(Usuario usuario){
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

			int rows = ps.executeUpdate();
			if (rows == 0)
				throw new SQLException("Nemhum registro atualizado");

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback");
			}
			
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
			int rows = ps.executeUpdate();
			if (rows == 0)
				throw new SQLException("Nemhum registro deletado");

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
				e.printStackTrace();
				throw new MySQLException("Erro na transanção");
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new MySQLException("Erro no rollback");
			}
		} finally {
			DB.closeStatment(ps);
		}

	}

	@Override
	public Usuario findByID(Integer usuid) {
		if (usuid == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "SELECT usuario.* , logs.logid, logs.logdata FROM usuario INNER JOIN logs on logs.logusuid = usuario.usuid WHERE usuario.usuid = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<Integer, Usuario> mapUsuairo = new HashMap<Integer, Usuario>();
		Usuario usuario = null;
		Logs logs = null;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, usuid);
			rs = ps.executeQuery();
			while (rs.next()) {
				// usuario 1 - N logs
				// usuario 1 - N historico
				// um unico usuario vai apontar para varios logs que ele tem
				logs = getLogsRS(rs);
				usuario = mapUsuairo.get(rs.getInt("usuid"));
				if (usuario == null) {
					usuario = getUsuarioRS(rs);
					mapUsuairo.put(rs.getInt("usuid"), usuario);
				}

				logs.setLogusuario(usuario);
				usuario.getUsulogs().add(logs);
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
	public List<Usuario> findAll() {

		sql = "SELECT usuario.* , logs.logid, logs.logdata FROM usuario INNER JOIN logs on logs.logusuid = usuario.usuid ORDER by usuario.usuid";
		ResultSet rs = null;
		Statement st = null;
		List<Usuario> result = new ArrayList<Usuario>();
		Map<Integer, Usuario> mapUsuairo = new HashMap<Integer, Usuario>();
		Logs logs = null;
		Usuario usuario = null;
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				// usuario 1 - N logs
				// usuario 1 - N historico
				// um unico usuario vai apontar para varios logs que ele tem
				logs = getLogsRS(rs);
				usuario = mapUsuairo.get(rs.getInt("usuid"));
				if (usuario == null) {
					usuario = getUsuarioRS(rs);
					mapUsuairo.put(rs.getInt("usuid"), usuario);
					result.add(usuario);
				}

				logs.setLogusuario(usuario);
				usuario.getUsulogs().add(logs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatment(st);
		}

		return result;
	}

//	private Historico getHistoricoRS(ResultSet rs) throws SQLException {
//		Historico historico = new Historico();
//		historico.setHisid(rs.getInt("hisid"));
//		historico.setHisdado(rs.getString("hisdado"));
//		return historico;
//	}

	private Usuario getUsuarioRS(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setUsuid(rs.getInt("usuid"));
		usuario.setUsunome(rs.getString("usunome"));
		usuario.setUsuemail(rs.getString("usuemail"));
		usuario.setUsutelefone(rs.getString("usutelefone"));
		usuario.setUsulogs(new ArrayList<Logs>());
		usuario.setUsuhistorico(new ArrayList<Historico>());
		return usuario;
	}

	private Logs getLogsRS(ResultSet rs) throws SQLException {
		Logs logs = new Logs();

		logs.setLogid(rs.getInt("logs.logid"));
		logs.setLogdata(new java.util.Date(rs.getDate("logs.logdata").getTime()));
		return logs;

	}
}
