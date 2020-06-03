package calculadora_aritimetica.modelo.helper;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import view.gui.utils.Alerts;
import view.gui.utils.ViewUtils;

public class CalculadoraAritimeticaHelper {

	private CalculadoraEntradaDadosAtributos dadosEntrada;

	public CalculadoraAritimeticaHelper(CalculadoraEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}

	public void adicao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes = dadosEntrada.getRes() + tmpNum;
			setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
		}
	}

	public void divisao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			if (tmpNum == 0) {
				dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
				Alerts.showAlertError("Não é possivel dividir por 0");
				return;
			}
			tmpRes = dadosEntrada.getRes() / tmpNum;
			setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
		}

	}

	public void multilplicacao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			tmpRes = dadosEntrada.getRes() * tmpNum;
			setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
		}
	}

	public void subtracao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			tmpRes = dadosEntrada.getRes() - tmpNum;
			setDadosAfterOperacaoNum(String.format("%.2f", tmpRes));
		}
	}
	public void igual() {
		dadosEntrada.setConContinue(true);
		
	}
	public void setDadosAfterOperacaoNum(String tmpRes) {
		dadosEntrada.setFirstDivisao(false);
		dadosEntrada.setFirstMult(false);
		dadosEntrada.setFirstSub(false);
		if (tmpRes != null)
			dadosEntrada.setStrLblRes("=" + tmpRes);
	}

	public void setDadosAfterOperacoesDigOpe() {
		setDadosAfterOperacaoNum(null);
		dadosEntrada.setUltimaOperacao(dadosEntrada.getOpeVez());
		dadosEntrada.setStrNum("");
		dadosEntrada.setIsPrimeiraOpe(false);
		String strResTmp = dadosEntrada.getStrLblRes().substring(1);
		if (!strResTmp.equals("")) {
			dadosEntrada.setRes(ViewUtils.tryParseDouble(strResTmp));
		}
		if(dadosEntrada.getConContinue()) {//caso continue a operação
			dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
			dadosEntrada.setStrLblOpe(strResTmp+dadosEntrada.getOpeVez());
			dadosEntrada.setStrLblRes("="+strResTmp);
			dadosEntrada.setRes(ViewUtils.tryParseDouble(strResTmp));
			dadosEntrada.setIsPrimeiraOpe(false);
		}
		dadosEntrada.setConContinue(false);
	}

	
}
