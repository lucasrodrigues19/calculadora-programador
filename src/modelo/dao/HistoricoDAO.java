package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.entites.Historico;
import modelo.entites.Logs;

public interface HistoricoDAO {


	void save(Historico historico);

	void delete(Historico historico);

	Logs findID(Integer hisid);

	Logs findUser(Integer logusuid);
	
	List<Logs> findDate(Date logdate);

	List<Logs> findAll();

}
