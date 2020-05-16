package modelo.services;

import java.util.List;

import db.ex.MySQLException;
import modelo.dao.UsuarioDAO;
import modelo.dao.factory.DaoFactory;
import modelo.entites.Usuario;

public class UsuarioService {
	
	public Boolean login(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");
		try {
			return getUsuarioDAO().login(usuario);
		} catch (MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}

	}

	public void saveOrUpdate(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		try {
			if (usuario.getUsuid() == null)
				getUsuarioDAO().save(usuario);
			else
				getUsuarioDAO().update(usuario);
		} catch (MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}

	}

	public void delete(Usuario usuario) {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");
		try {
			getUsuarioDAO().delete(usuario);
		} catch (MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public Usuario findByID(Integer usuid) {
		if (usuid == null)
			throw new IllegalArgumentException("id nulo");
		try {
			return getUsuarioDAO().findByID(usuid);
		} catch (MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}

	public List<Usuario> findAll() {
		try {
			return getUsuarioDAO().findAll();
		} catch (MySQLException e) {
			e.printStackTrace();
			throw new MySQLException(e.getMessage());
		}
	}
	
	private UsuarioDAO getUsuarioDAO() {
		return DaoFactory.getUsuarioDAO();
	}
}
