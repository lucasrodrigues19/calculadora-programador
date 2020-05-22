package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import db.ex.MySQLException;
import ex.MyException;

public class DB {

	private static Connection con = null;

	/**
	 * Método responsavel por pegar uma conexão com o banco mysql
	 * @param path caminho do arquivo .propierties com as propriedades do banco
	 * @return
	 */
	public static Connection getConnection(String path) {
		if (con == null) {
			try {
				Properties prop = loadProperties(path);
				String url = prop.getProperty("dburl");
				con = (Connection) DriverManager.getConnection(url, prop);
				System.out.println("Conexao com o banco realizada");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MySQLException(e.getMessage());
			}catch(MyException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	/**
	 * Método responsavel por fechar a conexão com o banco de dados mysql
	 */
	public static void closeConnection() {
		if(con !=  null) {
			try {
				con.close();
				System.out.println("Conexao com o banco fechada");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MySQLException(e.getMessage());
			}
		}
	}
	
	/**
	 * Método responsavel por fechar um ResultSet
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		if(rs !=  null) {
			try {
				rs.close();
				System.out.println("ResultSet fechada");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MySQLException(e.getMessage());
			}
		}
	}
	
	/**
	 *Método responsavel por fechar um Statement 
	 * @param st
	 */
	public static void closeStatment(Statement st) {
		if(st !=  null) {
			try {
				st.close();
				System.out.println("Statement fechada");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MySQLException(e.getMessage());
			}
		}
	}
	
	/**
	 * Método responsavel por carregar as propriedades de um arquivo .proprieties
	 * @param path	Caminho do arquivo
	 * 				
	 * @return
	 * @throws MyException 
	 */
	private static Properties loadProperties(String path) throws MyException {

		try (FileInputStream fis = new FileInputStream(path)) {
			Properties prop = new Properties();
			prop.load(fis);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

}
