package modelo.dao;

import java.util.List;

import modelo.entites.Logs;
import modelo.entites.Usuario;

public interface LogsDAO {


	void save(Logs logs);

	void delete(Logs logs);

	Logs findByID(Integer logid);

	List<Logs> findByUser(Usuario usuario);
	
	List<Logs> findByDateLog(Logs logs);

	List<Logs> findAll();

}
