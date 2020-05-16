package modelo.dao.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

import modelo.dao.HistoricoDAO;
import modelo.entites.Historico;
import modelo.entites.Usuario;

public class HistoricoDAOI implements HistoricoDAO {
	private Connection con;
	private String sql;

	public HistoricoDAOI(Connection con) {
		this.con = con;
	}
	
	@Override
	public void save(Historico historico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Historico historico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Historico findByID(Integer hisid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historico> findByUser(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Historico> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
