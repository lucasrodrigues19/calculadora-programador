package modelo.dao;

import java.sql.Date;
import java.util.List;

import modelo.entites.Usuario;

public interface UsuarioDAO {

	void login(Usuario usuario);

	void save(Usuario usuario);

	void update(Usuario usuario);

	void delete(Usuario usuario);

	Usuario findID(Integer usuid);

	Usuario findHistorico(Integer hisid);

	Usuario findLog(Integer logid);

	List<Usuario> findDateLog(Date logdate, Integer usuid);

	List<Usuario> findAll();

}
