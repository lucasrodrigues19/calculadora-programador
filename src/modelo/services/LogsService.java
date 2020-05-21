package modelo.services;

import java.util.List;

import db.ex.MySQLException;
import modelo.dao.LogsDAO;
import modelo.dao.factory.DaoFactory;
import modelo.entites.Logs;
import modelo.entites.Usuario;

public class LogsService {
	
	public void save(Logs logs) {
		if(logs == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			getLogsDAO().save(logs);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public void delete(Logs logs) {
		if(logs == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			getLogsDAO().delete(logs);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public Logs findByID(Integer logid) {
		if(logid == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			return getLogsDAO().findByID(logid);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public List<Logs> findByUser(Usuario usuario){
		if(usuario == null)
			throw new IllegalArgumentException("usuario nulo");
		
		try {
			return getLogsDAO().findByUser(usuario);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}
	
	public List<Logs> findByDateLog(Logs logs){
		if(logs == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			return getLogsDAO().findByDateLog(logs);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public List<Logs> findAll(){
		try {
			return getLogsDAO().findAll();
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	private LogsDAO getLogsDAO() {
		return DaoFactory.getLogsDAO();
	}
}
