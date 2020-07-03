package modelo.services;

import java.util.List;

import db.ex.MySQLException;
import modelo.dao.HistoricoDAO;
import modelo.dao.factory.DaoFactory;
import modelo.entites.Historico;
import modelo.entites.Usuario;

public class HistoricoService {
	
	public void save(Historico historico) {
		if(historico == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			getHistoricoDAO().save(historico);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public void delete(Historico historico) {
		if(historico == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			getHistoricoDAO().delete(historico);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public Historico findByID(Integer hisid) {
		if(hisid == null)
			throw new IllegalArgumentException("log nulo");
		
		try {
			return getHistoricoDAO().findByID(hisid);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public List<Historico> findByUser(Usuario usuario){
		if(usuario == null)
			throw new IllegalArgumentException("usuario nulo");
		
		try {
			return getHistoricoDAO().findByUser(usuario);
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}
	

	public List<Historico> findAll(){
		try {
			return getHistoricoDAO().findAll();
		}catch(MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	private HistoricoDAO getHistoricoDAO() {
		return DaoFactory.getHistoricoDAO();
	}
}
