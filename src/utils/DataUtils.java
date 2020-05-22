package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ex.MyException;

public class DataUtils {

	private static SimpleDateFormat sdf;

	/**
	 * Converte uma string para data
	 * @param date string para ser convertida
	 * @param formato formato desejado
	 * @return
	 * @throws MyException
	 */
	public static Date parse(String date, String formato) throws MyException {
		if (date == null || formato == null)
			throw new IllegalArgumentException("data, ou formato nulo");

		sdf = new SimpleDateFormat(formato);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}
	}
	
	/**
	 * retorna um Timestamp para salvar no banco de dados
	 * para salvar data no banco nao precisa formata-la
	 * @param date
	 * @return
	 * @throws MyException
	 */
	public static Timestamp parseTimeStamp(Date date) throws MyException {
		if (date == null)
			throw new IllegalArgumentException("data nulo");

		
		try {
			return new Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}
	}

	/**
	 * Formmada uma data para Stirng
	 * @param date data pra ser formatada
	 * @param formato formato da data.
	 * @return
	 * @throws MyException
	 */
	public static String format(Date date, String formato) throws MyException {
		if (date == null || formato == null)
			throw new IllegalArgumentException("data, ou formato nulo");

		sdf = new SimpleDateFormat(formato);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}
	}
}
