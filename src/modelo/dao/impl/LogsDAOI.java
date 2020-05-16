package modelo.dao.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

import modelo.dao.LogsDAO;
import modelo.entites.Logs;
import modelo.entites.Usuario;

public class LogsDAOI implements LogsDAO {
	private Connection con;
	private String sql;

	public LogsDAOI(Connection con) {
		this.con = con;
	}
	@Override
	public void save(Logs logs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Logs logs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Logs findByID(Integer logid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Logs> findByUser(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Logs> findByDateLog(Logs logs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Logs> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
