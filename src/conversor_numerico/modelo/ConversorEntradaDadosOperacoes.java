package conversor_numerico.modelo;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import javafx.scene.input.KeyCode;

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
	/**
	 * Retorna uma string com o digito do teclado, de acordo com codigo.
	 * 
	 * @param obj
	 * @param code KeyCode
	 * @return
	 */
	default String getNameKeyCodeCalc(ConversorEntradaDadosAtributos obj, KeyCode code) {
		String nameCode = code.getName();

		if (code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1)
			return KeyCode.DIGIT1.getName();

		else if (code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2)
			return KeyCode.DIGIT2.getName();

		else if (code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3)
			return KeyCode.DIGIT3.getName();

		else if (code == KeyCode.DIGIT4 || code == KeyCode.NUMPAD4)
			return KeyCode.DIGIT4.getName();

		else if (code == KeyCode.DIGIT5 || code == KeyCode.NUMPAD5)
			return KeyCode.DIGIT5.getName();

		else if (code == KeyCode.DIGIT6 || code == KeyCode.NUMPAD6)
			return KeyCode.DIGIT6.getName();

		else if (code == KeyCode.DIGIT7 || code == KeyCode.NUMPAD7)
			return KeyCode.DIGIT7.getName();

		else if (code == KeyCode.DIGIT8 || code == KeyCode.NUMPAD8)
			return KeyCode.DIGIT8.getName();

		else if (code == KeyCode.DIGIT9 || code == KeyCode.NUMPAD9)
			return KeyCode.DIGIT9.getName();

		else if (code == KeyCode.DIGIT0 || code == KeyCode.NUMPAD0)
			return KeyCode.DIGIT0.getName();

		else if (code == KeyCode.BACK_SPACE)
			return "<X";
		
		else if (code == KeyCode.EQUALS)
			return "=";

		else if (nameCode.matches("[A-F]"))
			return nameCode;
		
		return null;

	}

}
