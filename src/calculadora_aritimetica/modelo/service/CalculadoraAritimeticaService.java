package calculadora_aritimetica.modelo.service;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import calculadora_aritimetica.modelo.helper.CalculadoraAritimeticaHelper;
import view.gui.utils.ViewUtils;

public class CalculadoraAritimeticaService {

	private CalculadoraEntradaDadosAtributos dadosEntrada;
	private CalculadoraAritimeticaHelper helper;
	// passar tudo para os dados de entrada

	public CalculadoraAritimeticaService(CalculadoraEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
		helper = new CalculadoraAritimeticaHelper(dadosEntrada);
	}

	public void fazerOperacoesNum() {
		// se for a primeira operação ou se o usuario clicar no igual
		if (!dadosEntrada.getIsPrimeiraOpe()) {
			dadosEntrada.setUltimaOperacao(dadosEntrada.getOpeVez()); // pega a operaçao da vez
			switch (dadosEntrada.getUltimaOperacao()) {
			case "+":
				helper.adicao();
				break;
			case "-":
				helper.subtracao();
				break;
			case "*":
				helper.multilplicacao();
				break;
			case "/":
				helper.divisao();
				break;
			}
		}
	}

	public void fazerOperacoesDigOpe() {
		if (dadosEntrada.getConContinue()) {
			String strResTmp = dadosEntrada.getStrLblRes().substring(1);
			if (!strResTmp.equals("")) { // caso for continuar a operacao
				dadosEntrada.setRes(ViewUtils.tryParseDouble(strResTmp));
			}
			helper.setDadosAfterOperacoesDigOpe();
		}

	}
}
