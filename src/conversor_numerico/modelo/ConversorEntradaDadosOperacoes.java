package conversor_numerico.modelo;

public interface ConversorEntradaDadosOperacoes {

	default void setDadosEntrada(String str, ConversorEntradaDadosAtributos obj) {
		if (obj != null && (str != null && str.length() >= 0)) {
			obj.setStrNum(str);
			obj.setStrOpe(str);
			String tmpRes = "";
			String opeEnt = obj.getOpeEntrada();
			String opeSai = obj.getOpeSaida();
			if (opeEnt.equals(opeSai)) {
				tmpRes = obj.getStrNum();
			} else if (opeEnt.toUpperCase().equals("BINARIO")) {
				if (opeSai.toUpperCase().equals("DECIMAL"))
					tmpRes = obj.getService().convertBinToDec();
				else if (opeSai.toUpperCase().equals("HEXA-DECIMAL"))
					tmpRes = obj.getService().convertBinToHex();
			} // end if binario
			else if (opeEnt.toUpperCase().equals("DECIMAL")) {
				if (opeSai.toUpperCase().equals("BINARIO"))
					tmpRes = obj.getService().convertDecToBin();
				else if (opeSai.toUpperCase().equals("HEXA-DECIMAL"))
					tmpRes = obj.getService().convertDecToHex();
			} // end if decimal
			else if (opeEnt.toUpperCase().equals("HEXA-DECIMAL")) {
				if (opeSai.toUpperCase().equals("BINARIO"))
					tmpRes = obj.getService().convertHexToBin();
				else if (opeSai.toUpperCase().equals("DECIMAL"))
					tmpRes = obj.getService().convertHexToDec();
			} // end if hexa

			obj.setStrRes(tmpRes);
		}
	}

	default void limparDigito(ConversorEntradaDadosAtributos obj) {
		if (obj!= null) {
			if (!obj.getStrNum().equals("") && obj.getStrNum().length() >= 0) {
				String tmp = obj.getStrNum().substring(0, (obj.getStrNum().length() - 1));
				inicializarElementos(obj);
				setDadosEntrada(tmp, obj);
			}
		}
	}

	default void limparTudo(ConversorEntradaDadosAtributos obj) {
		inicializarElementos(obj);
	}

	default void inicializarElementos(ConversorEntradaDadosAtributos obj) {
		obj.setStrNum("");
		obj.setStrRes("");
		obj.setStrOpe("");
		obj.setService(new ConversorService(obj));
	}
}
