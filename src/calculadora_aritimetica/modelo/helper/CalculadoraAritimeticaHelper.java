package calculadora_aritimetica.modelo.helper;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import view.gui.utils.Alerts;
import view.gui.utils.ViewUtils;

public class CalculadoraAritimeticaHelper {

	private CalculadoraEntradaDadosAtributos dadosEntrada;

	public CalculadoraAritimeticaHelper(CalculadoraEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}

	public Double adicao() {
		Double tmpRes = null;
		if (dadosEntrada.getStrNum() != null) {
			Double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			tmpRes = dadosEntrada.getRes() + tmpNum;
		}
		return tmpRes;
	}

	public Double divisao() {
		Double tmpRes = null;
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			if (tmpNum == 0) {
				dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
				Alerts.showAlertError("Não é possivel dividir por 0");
			} else
				tmpRes = dadosEntrada.getRes() / tmpNum;
		}
		return tmpRes;
	}

	public Double multilplicacao() {
		Double tmpRes = null;
		if (dadosEntrada.getStrNum() != null) {
			Double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			tmpRes = dadosEntrada.getRes() * tmpNum;
		}
		return tmpRes;
	}

	public Double subtracao() {
		Double tmpRes = null;
		if (dadosEntrada.getStrNum() != null) {
			Double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			tmpRes = dadosEntrada.getRes() - tmpNum;
		}
		return tmpRes;
	}

	public void igual() {
		dadosEntrada.setConContinue(true);

	}

	public void setDadosAfterOperacaoNum(String tmpRes) {
		if (tmpRes != null)
			dadosEntrada.setStrLblRes("=" + tmpRes);

		setDadosContinueOperacaoNum();

	}

	private void setDadosContinueOperacaoNum() {
		if (dadosEntrada.getConContinue()) {
			String strNumTmp = dadosEntrada.getStrNum();
			dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
			dadosEntrada.setStrNum(strNumTmp);
			dadosEntrada.setStrLblOpe(strNumTmp);
			dadosEntrada.setStrLblRes("=" + strNumTmp);
			dadosEntrada.setDigOperador(true);
		}
	}

	public void setDadosAfterOperacoesDigOpe() {
		dadosEntrada.setUltimaOperacao(dadosEntrada.getOpeVez());
		dadosEntrada.setStrNum("");
		dadosEntrada.setIsPrimeiraOpe(false);
		String strResTmp = dadosEntrada.getStrLblRes().substring(1);
		setDadosContinueDigOpe(strResTmp);
		dadosEntrada.setRes(ViewUtils.tryParseDouble(strResTmp));

	}

	private void setDadosContinueDigOpe(String strResTmp) {
		if (dadosEntrada.getConContinue() && strResTmp != null) {// caso continue a operação
			dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
			dadosEntrada.setStrLblOpe(strResTmp + dadosEntrada.getOpeVez());
			dadosEntrada.setStrLblRes("=" + strResTmp);
			dadosEntrada.setIsPrimeiraOpe(false);
			dadosEntrada.setConContinue(false);
		}

	}

	public String apagarDigito() {
		String digitoRetirado = null;
		if (!dadosEntrada.getStrNum().equals("") && dadosEntrada.getStrNum().length() > 0) {
			String strNumTemp = dadosEntrada.getStrNum();
			digitoRetirado = strNumTemp.substring(strNumTemp.length() - 1);
			String novaStr = strNumTemp.substring(0, strNumTemp.length() - 1);
			dadosEntrada.setStrNum(novaStr);
		}
		if (!dadosEntrada.getStrLblOpe().equals("") && dadosEntrada.getStrLblOpe().length() > 0) {
			String strOpeTemp = dadosEntrada.getStrLblOpe();
			digitoRetirado = strOpeTemp.substring(strOpeTemp.length() - 1);
			String novaStr = strOpeTemp.substring(0, strOpeTemp.length() - 1);
			dadosEntrada.setStrLblOpe(novaStr);
		}
		return digitoRetirado;
	}

}
