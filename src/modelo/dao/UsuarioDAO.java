package modelo.dao;

import java.util.List;

import modelo.entites.Usuario;

public interface UsuarioDAO {

	Boolean login(Usuario usuario);

	void save(Usuario usuario);

	void update(Usuario usuario);

	void delete(Usuario usuario);

	Usuario findByID(Integer usuid);

	List<Usuario> findAll();

}
