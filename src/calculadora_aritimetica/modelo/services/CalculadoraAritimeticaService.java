package calculadora_aritimetica.modelo.services;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import calculadora_aritimetica.modelo.helpers.CalculadoraAritimeticaHelper;

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
			switch (dadosEntrada.getUltimaOperacao()) {
			case "+":
				tmpRes = helper.adicao();
				helper.setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
				break;
			case "-":
				tmpRes = helper.subtracao();
				helper.setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
				break;
			case "*":
				tmpRes = helper.multilplicacao();
				helper.setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));

				break;
			case "/":
				tmpRes = helper.divisao();
				if (tmpRes != null)
				helper.setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));

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
}
