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
import modelo.dao.HistoricoDAO;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;

public class HistoricoDAOI implements HistoricoDAO {
	private Connection con;
	private String sql;

	public HistoricoDAOI(Connection con) {
		this.con = con;
	}
	
	@Override
	public void save(Historico historico) {
		if (historico == null)
			throw new IllegalArgumentException("Log nulo");

		sql = "INSERT INTO historico VALUES (default,?,?)";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, historico.getHisdado());
			ps.setInt(2, historico.getHisusuid());
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
	public void delete(Historico historico) {
		if (historico == null)
			throw new IllegalArgumentException("Historico nulo");

		sql = "DELETE FROM historico WHERE hisid = ?";
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, historico.getHisid());
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
	public Historico findByID(Integer hisid) {
		sql = "SELECT  historico.*, usuario.usuemail FROM historico INNER JOIN usuario on historico.hisusuid = usuario.usuid WHERE historico.hisid = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Historico obj = null;
		try{
			Usuario user = null;
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,hisid);
			rs = ps.executeQuery();
			while(rs.next()) {
				obj = getHistorico(rs);
				user = getUsuario(rs);
				user.getUsuhistorico().add(obj);
				obj.setHisusuario(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return obj;
	}

	@Override
	public List<Historico> findByUser(Usuario usuario) {
		sql = "SELECT  historico.*, usuario.usuemail FROM historico INNER JOIN usuario on historico.hisusuid = usuario.usuid WHERE hisusuid = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		Historico obj = null;
		List<Historico> result = new ArrayList<Historico>();
		Map<Integer,Usuario>mapUser = new HashMap<>();
		try{
			Usuario user = null;
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1,usuario.getUsuid());
			rs = ps.executeQuery();
			while(rs.next()) {
				
				user = mapUser.get(rs.getInt("hisusuid"));
				if(user == null) {
					user = getUsuario(rs);
					mapUser.put(user.getUsuid(), user);
				}
				obj = getHistorico(rs);
				obj.setHisusuario(user);
				user.getUsuhistorico().add(obj);
				result.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatment(ps);
		}
		return result;
	}

	@Override
	public List<Historico> findAll() {
		sql = "SELECT  historico.*, usuario.usuemail FROM historico INNER JOIN usuario on historico.hisusuid = usuario.usuid";
		ResultSet rs = null;
		Statement st = null;
		Historico obj = null;
		List<Historico> result = new ArrayList<Historico>();
		Map<Integer,Usuario>mapUser = new HashMap<>();
		try{
			Usuario user = null;
			st = (Statement) con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				
				user = mapUser.get(rs.getInt("hisusuid"));
				if(user == null) {
					user = getUsuario(rs);
					mapUser.put(user.getUsuid(), user);
				}
				obj = getHistorico(rs);
				obj.setHisusuario(user);
				user.getUsuhistorico().add(obj);
				result.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatment(st);
		}
		return result;
	}

	private Usuario getUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		Integer usuid = rs.getInt("hisusuid");

		if (usuid != null)
			usuario.setUsuid(usuid);

//		if (rs.getString("usuario.usunome") != null)
//			usuario.setUsunome(rs.getString("usuario.usunome"));

		if (rs.getString("usuario.usuemail") != null)
			usuario.setUsuemail(rs.getString("usuario.usuemail"));
//
//		if (rs.getString("usuario.usutelefone") != null)
//			usuario.setUsutelefone(rs.getString("usuario.usutelefone"));

		usuario.setUsulogs(new ArrayList<Logs>());
		usuario.setUsuhistorico(new ArrayList<Historico>());
		return usuario;
	}

	private Historico getHistorico(ResultSet rs) throws SQLException {
		Historico historico = new Historico();
		Integer hisid = rs.getInt("hisid");
		String hisdado = rs.getString("hisdado");
		if(hisid != null) 
				historico.setHisid(hisid);
		
		if(hisdado != null)
			historico.setHisdado(hisdado);
		
		
	
		return historico;
	}

	@Override
	public void deleteByUser(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		sql = "DELETE FROM historico WHERE hisusuid = ?";
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
