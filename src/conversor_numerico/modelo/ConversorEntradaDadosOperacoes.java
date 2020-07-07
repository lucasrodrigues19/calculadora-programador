package conversor_numerico.modelo;

public interface ConversorEntradaDadosOperacoes {

	default void setDadosEntrada(String str, ConversorEntradaDadosAtributos obj) {
		if (obj != null && (str != null && str.length() >= 0)) {
			obj.setStrNum(str);
			obj.setStrOpe(str);
			String tmpRes = "";
			OperacoesConversores opeEnt = obj.getOpeEntrada();
			OperacoesConversores opeSai = obj.getOpeSaida();
			
			if (opeEnt.equals(OperacoesConversores.BINARIO)) {
				String binTmp = obj.getService().checkBinario(obj.getStrNum());
				tmpRes = binTmp;
				if("".equals(binTmp)) {
				if (opeSai.equals(OperacoesConversores.DECIMAL))
					tmpRes = obj.getService().convertBinToDec();
				else if (opeSai.equals(OperacoesConversores.HEXA_DECIMAL))
					tmpRes = obj.getService().convertBinToHex();
				else
					tmpRes = obj.getStrNum();
				}
					
			} // end if binario
			else if (opeEnt.equals(OperacoesConversores.DECIMAL)) {
				boolean noDec = obj.getService().checkDecimal(obj.getStrNum());
				if(!noDec) {
				if (opeSai.equals(OperacoesConversores.BINARIO))
					tmpRes = obj.getService().convertDecToBin();
				else if (opeSai.equals(OperacoesConversores.HEXA_DECIMAL))
					tmpRes = obj.getService().convertDecToHex();
				else
					tmpRes = obj.getStrNum();
				}
			} // end if decimal
			else if (opeEnt.equals(OperacoesConversores.HEXA_DECIMAL)) {
				if (opeSai.equals(OperacoesConversores.BINARIO))
					tmpRes = obj.getService().convertHexToBin();
				else if (opeSai.equals(OperacoesConversores.DECIMAL))
					tmpRes = obj.getService().convertHexToDec();
				else
					tmpRes = obj.getStrNum();
			} // end if hexa

			obj.setStrRes(tmpRes);
		}
	}

	default void limparDigito(ConversorEntradaDadosAtributos obj) {
		if (obj != null) {
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
