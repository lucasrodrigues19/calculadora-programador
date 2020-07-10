package calculadora_aritimetica.modelo.services;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import calculadora_aritimetica.modelo.helpers.CalculadoraAritimeticaHelper;
import utils.TryParseUtils;

public class CalculadoraAritimeticaService {

	private CalculadoraEntradaDadosAtributos dadosEntrada;
	private CalculadoraAritimeticaHelper helper;
	// passar tudo para os dados de entrada

	public CalculadoraAritimeticaService(CalculadoraEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
		helper = new CalculadoraAritimeticaHelper(dadosEntrada);
	}

	public void fazerOperacoesNum() {
		if (dadosEntrada.getConContinue()) {
			helper.setDadosAfterOperacaoNum(null);
			return;
		}
		if (!dadosEntrada.getIsPrimeiraOpe()) {
			Double tmpRes = null;
			String strRes = "";
			switch (dadosEntrada.getUltimaOperacao()) {
			case "+":
				tmpRes = helper.adicao();
				if (tmpRes != null) {
					strRes = verifcarDecimais(tmpRes);
					helper.setDadosAfterOperacaoNum(strRes);
				}
				break;
			case "-":
				tmpRes = helper.subtracao();
				if (tmpRes != null) {
					strRes = verifcarDecimais(tmpRes);
					helper.setDadosAfterOperacaoNum(strRes);
				}
				break;
			case "*":
				tmpRes = helper.multilplicacao();
				if (tmpRes != null) {
					strRes = verifcarDecimais(tmpRes);
					helper.setDadosAfterOperacaoNum(strRes);
				}

				break;
			case "/":
				tmpRes = helper.divisao();
				if (tmpRes != null) {
					strRes = verifcarDecimais(tmpRes);
					helper.setDadosAfterOperacaoNum(strRes);
				}
				break;
			}
		}
	}

	public void fazerOperacoesDigOpe() {
		helper.setDadosAfterOperacoesDigOpe();
	}

	public void fazerOperacoesIgual() {
		helper.igual();
	}

	public String limparDigito() {
		return helper.apagarDigito();
	}

	private String verifcarDecimais(Double tmpRes) {
		String[] decimais;
		boolean possuiApenasZero = true;
		String strRes = Double.toString(tmpRes);
		if (tmpRes != null) {
			decimais = strRes.split("\\."); // corta a stirng com o resultado da operacao, de acordo com o '.'
			if (decimais != null && decimais.length > 0) { // caso tenha casas decimais
				strRes = decimais[1]; // pega a string depois do '.'
				for (int i = 0; i < strRes.length(); i++) { // verifica se as casas decimais so possuem zeros
					String charTmp = Character.toString(strRes.charAt(i));
					if (!"0".equals(charTmp)) {
						possuiApenasZero = false;
						break;
					}
				}
			}
		}
		if (possuiApenasZero)
			return String.format("%.0f", tmpRes);
		else
			return String.format("%.2f", tmpRes);
	}
}
