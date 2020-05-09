package ex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8516679123283730778L;

	private	Map<String,String> erros = new HashMap<String,String>();
	
	public MyRuntimeException(String msg) {
		super(msg);
	}
	public MyRuntimeException() {
	}
	public void addErros(String field,String msg) {
		erros.put(field,msg);
	}
	public Map<String,String> getErros() {
		return erros;
	}
	
}
