package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;

public interface LogsDAO {


	void save(Logs logs,Usuario usuario);

	void delete(Logs logs);

	Historico findID(Integer logid);

	Historico findUser(Integer hisusuid);
	
	List<Usuario> findDateLog(Date logdate);

	List<Historico> findAll();

}
