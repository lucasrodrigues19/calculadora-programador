package conversor_numerico.modelo;

public interface ConversorEntradaDadosOperacoes {

	default void setDadosEntrada(String str, ConversorEntradaDadosAtributos obj) {
		
		obj.setStrNum(obj.getStrNum()+str.toUpperCase());
		obj.setStrOpe(obj.getStrOpe()+str.toUpperCase());
		String tmpRes = obj.getService().convertHexToDec();
		obj.setStrRes(tmpRes);
	}
	
	default void limparDigito(String str, ConversorEntradaDadosAtributos obj) {
		if(str!= null ) {
			if(!str.equals("")) {
				String tmp = obj.getStrNum().substring(0,(obj.getStrNum().length() - 1));
				inicializarElementos(obj);
				for(int i = 0;i<(tmp.length());i++) {
					str = Character.toString(tmp.charAt(i));
					setDadosEntrada(str, obj);
				}
			}
		}
	}
	default  void limparTudo(ConversorEntradaDadosAtributos obj) {
		inicializarElementos(obj);
	}
	default void inicializarElementos(ConversorEntradaDadosAtributos obj) {
		obj.setStrNum("");
		obj.setStrRes("");
		obj.setStrOpe("");
		obj.setService(new ConversorService(obj));
	}
}
