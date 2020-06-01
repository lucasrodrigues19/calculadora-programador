package calculadora_aritimetica.modelo.helper;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
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
			setDadosAfterOperacao(String.format("%.2f", tmpRes));
		}
	}

	public void divisao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			if (dadosEntrada.getFirstDivisao()) {
				if(tmpNum == 0) {
					dadosEntrada.iniciarAtributosaCalc(dadosEntrada);
					dadosEntrada.setStrLblRes("Não é possivel dividir por 0");
				}
				tmpRes = tmpNum / dadosEntrada.getRes();
			} else {
				tmpRes = dadosEntrada.getRes() / tmpNum;
			}
			setDadosAfterOperacao(String.format("%.2f", tmpRes));
		}
	}

	public void multilplicacao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			if (dadosEntrada.getFirstMult()) {
				tmpRes = tmpNum * 1;
			} else {
				tmpRes = dadosEntrada.getRes() * tmpNum;
			}
			setDadosAfterOperacao(String.format("%.2f", tmpRes));
		}
	}

	public void subtracao() {
		if (dadosEntrada.getStrNum() != null) {
			double tmpNum = ViewUtils.tryParseDouble(dadosEntrada.getStrNum());
			double tmpRes;
			if (dadosEntrada.getFirstSub()) {
				tmpRes = tmpNum - 0;
			} else {
				tmpRes = dadosEntrada.getRes() - tmpNum;
			}
			setDadosAfterOperacao(String.format("%.2f", tmpRes));
		}
	}

	public void setDadosAfterOperacao(String tmpRes) {
		dadosEntrada.setFirstDivisao(false);
		dadosEntrada.setFirstMult(false);
		dadosEntrada.setFirstSub(false);
		dadosEntrada.setStrNum("");
		dadosEntrada.setStrLblRes(tmpRes);
	}
}
