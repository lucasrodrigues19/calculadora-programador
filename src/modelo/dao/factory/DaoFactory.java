package modelo.dao.factory;

import db.DB;
import modelo.dao.HistoricoDAO;
import modelo.dao.LogsDAO;
import modelo.dao.UsuarioDAO;
import modelo.dao.impl.HistoricoDAOI;
import modelo.dao.impl.LogsDAOI;
import modelo.dao.impl.UsuarioDAOI;

public class DaoFactory {
	private static final String db_properties = "db-prod.propierties";

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOI(DB.getConnection(db_properties));
	}

	public static HistoricoDAO getHistoricoDAO() {
		return new HistoricoDAOI(DB.getConnection(db_properties));
	}

	public static LogsDAO getLogsDAO() {
		return new LogsDAOI(DB.getConnection(db_properties));
	}
}
