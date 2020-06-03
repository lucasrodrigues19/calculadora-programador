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
		if(dadosEntrada.getConContinue()) {
			String strNumTmp = dadosEntrada.getStrNum();
			dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
			dadosEntrada.setStrNum(strNumTmp);
			dadosEntrada.setStrLblOpe(strNumTmp);
			dadosEntrada.setStrLblRes("="+strNumTmp);
			dadosEntrada.setDigOperador(true);
			return;
		}
		if (!dadosEntrada.getIsPrimeiraOpe()) {
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
		helper.setDadosAfterOperacoesDigOpe();
	}
	public void fazerOperacoesIgual() {
		helper.igual();
	}
}
