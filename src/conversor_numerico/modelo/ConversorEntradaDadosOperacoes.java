package conversor_numerico.modelo;

public interface ConversorEntradaDadosOperacoes {

	default void setDadosEntrada(String str, ConversorEntradaDadosAtributos obj) {
		
		obj.setStrNum(obj.getStrNum()+str);
		obj.setStrOpe(obj.getStrOpe()+str);
		String tmpRes = obj.getService().convertHexToDec();
		obj.setStrRes(tmpRes);
	}
	default void inicializarElementos(ConversorEntradaDadosAtributos obj) {
		obj.setStrNum("");
		obj.setStrRes("");
		obj.setStrOpe("");
		obj.setService(new ConversorService(obj));
	}
}
