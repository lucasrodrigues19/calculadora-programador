package modelo.dao;

import java.util.List;

import modelo.entites.Historico;
import modelo.entites.Usuario;

public interface HistoricoDAO {


	void save(Historico historico);

	void delete(Historico historico);

	void deleteByUser(Usuario usuario);
	
	Historico findByID(Integer hisid);

	List<Historico> findByUser(Usuario usuario);
	
	List<Historico> findAll();

}
