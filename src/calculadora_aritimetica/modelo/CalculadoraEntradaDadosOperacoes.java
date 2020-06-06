package calculadora_aritimetica.modelo;

import java.util.Locale;

import calculadora_aritimetica.modelo.service.CalculadoraAritimeticaService;
import javafx.scene.input.KeyCode;
import view.gui.utils.ViewUtils;

/**
 * Essa interface foi criada para ser implementada especialmente na classe
 * abstrat CalculadoraEntradaDadosAtributos. Por que todos os metodos dessa
 * interface,em seu parametro, possui um atributo do tipo
 * CalculadoraEntradaDadosAtributos.
 * 
 * @author lucas.rodrigues
 *
 */
public interface CalculadoraEntradaDadosOperacoes {

	/**
	 * Define e controla os dados que serao setados nas strings e controles do obj,
	 * esse dados são entrada de dados para as operações. Checa se a virgula pode
	 * ser setada. Checa se pode setar um sinal de operação. Tudo de acordo com o
	 * digito
	 * 
	 * @param obj    objeto em que os dados serao setados
	 * @param digito
	 */
	default void setEntradaDados(CalculadoraEntradaDadosAtributos obj, String digito) {

		if (digito != null) {
			if (digito.equals(".")) {
				if (checkPonto(obj)) {
					setStrCalcAntes(obj, digito);
					setBoolPontoAndOpe(obj, null, null, true);
				}
			} else if (isOpe(obj, digito)) {
				if (checkOperadores(obj) || obj.getConContinue()) {
					setStrCalcAntes(obj, digito);
					if (digito.equals("=") && !obj.getStrNum().equals("")) {
						obj.getService().fazerOperacoesIgual();
						setBoolPontoAndOpe(obj, false, true, false);
						return;
					}
					setBoolPontoAndOpe(obj, false, true, false);
					obj.setOpeVez(digito);
					obj.getService().fazerOperacoesDigOpe();
				}
			} else {
				setStrCalcAntes(obj, digito);
				setBoolPontoAndOpe(obj, true, null, true);
				obj.getService().fazerOperacoesNum();
			}
		}
	}

	/**
	 * quando limpa um caracter, renicia os atributos, e refaz toda a operacao
	 * @param obj
	 * @param digito
	 */
	default void limparDados(CalculadoraEntradaDadosAtributos obj, String digito) {
		if (digito != null) {
			if (digito.equals("X")) {
				if (!obj.getStrLblOpe().equals("")) {
					String digitoRetirado = obj.getService().limparDigito();
					if (digitoRetirado != null) {
						String strLblOpe = obj.getStrLblOpe();
						iniciarAtributosaCalc(obj);
						int leng = strLblOpe.length();
						for (int i = 0; i < leng; i++) {
							Character charOp = strLblOpe.charAt(i);
							String strDig = Character.toString(charOp);
							setEntradaDados(obj, strDig);
						}
					}
				}
			}else if (digito.equals("C")) {
				if (!obj.getStrLblOpe().equals("")) {
					iniciarAtributosaCalc(obj);
				}
			}

		}
	}


	/**
	 * Retorna o ultimo digito de operador de uma string 
	 *  EX: 70*10+100
	 *  	return +
	 * @param obj
	 * @param strLblOpe
	 * @return
	 */
	default String digOpeInStr(CalculadoraEntradaDadosAtributos obj, String strLblOpe) {
		int leng = strLblOpe.length();
		for (int i = 1; i <= leng; i++) {
			Character charOp = strLblOpe.charAt(leng - i);
			String digito = Character.toString(charOp);
			if (isOpe(obj, digito)) {
				return digito;
			}
		}
		return null;
	}

	/**
	 * Pega o ultimo numero da operacao 
	 * 	EX: 
	 *  70*80*90 return 90
	 * @param obj
	 * @param strLblOpe
	 * @return
	 */
	default String getStrNumInOpe(CalculadoraEntradaDadosAtributos obj, String strLblOpe) {
		int leng = strLblOpe.length();
		String result = "";
		for (int i = 1; i <= leng; i++) {
			Character charOp = strLblOpe.charAt(leng - i);
			String digito = Character.toString(charOp);
			if (isOpe(obj, digito)) {
				break;
			} else {
				result = digito + result;
			}
		}

		return result;
	}

	/**
	 * verifica se o digito é um operador aritimetico.
	 * 
	 * @param digito
	 * @param obj    objeto de onde a string de digito é comparada
	 * @return
	 */
	default boolean isOpe(CalculadoraEntradaDadosAtributos obj, String digito) {
		for (int i = 0; i < obj.getArrayDigOpe().length - 1; i++) {
			if (digito.equals(getDigOpe(obj, i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * seta as strings do objeto realcionado a calculadora.
	 * 
	 * @param content
	 * @param isOpe
	 * @param obj
	 */
	default void setStrCalcAntes(CalculadoraEntradaDadosAtributos obj, String content) {
		if (obj != null && content != null) {
			if (!isOpe(obj, content)) {
				if (obj.getConContinue()) {// caso seja depois de clicar no igual
					obj.setStrNum(content);
				} else {
					obj.setStrNum(obj.getStrNum() + content);
				}
				obj.setStrLblRes(obj.getStrLblRes() + content); // res vai pegar essa string caso for a primeira
																// operacao
			} else {
				checkLastCharInStrNum(obj);
			}
			if (!content.equals("=")) // a stirng de operação so seta se nao for igual
				obj.setStrLblOpe(obj.getStrLblOpe() + content);
		}
	}

	/**
	 * Checa se a virgula pode ser setada
	 * 
	 * @param obj objeto de onde é verificado os controladores da virgula
	 * @return
	 */
	default Boolean checkPonto(CalculadoraEntradaDadosAtributos obj) {

		if (obj.getPontoNumAntes() != null && obj.getPontoDpsDec() != null) {
			if (obj.getPontoNumAntes() && obj.getPontoDpsDec()) {

				obj.setPontoDpsDec(false);
				return true;
			}
		}

		return false;
	}

	/**
	 * checa se pode setar o digito referente ao operador
	 * 
	 * @param obj objeto de onde é verificado os controladores do digito de
	 *            operações
	 * 
	 * @return
	 */
	default Boolean checkOperadores(CalculadoraEntradaDadosAtributos obj) {

		if (obj.getDigOperador()) {
			obj.setDigOperador(false);
			return true;
		}
		return false;
	}

	/**
	 * Checa se o char no ultimo indice de strNum é uma virgula, caso for, adiciona
	 * um 0, função deve ser chamada apenas quando for um operador aritimetico.
	 */
	default void checkLastCharInStrNum(CalculadoraEntradaDadosAtributos obj) {
		if (obj.getStrNum() != null && !obj.getStrNum().equals("") && obj.getStrNum().length() > 0) {
			if (obj.getStrNum().charAt(obj.getStrNum().length() - 1) == ',') {
				obj.setStrNum(obj.getStrNum() + "0");
				obj.setStrLblRes(obj.getStrLblRes() + "0");
				obj.setStrLblOpe(obj.getStrLblOpe() + "0");
			}
		}
	}

	/**
	 * Retorna uma string com o digito do teclado, de acordo com codigo.
	 * 
	 * @param obj
	 * @param code KeyCode
	 * @return
	 */
	default String getNameKeyCodeCalc(CalculadoraEntradaDadosAtributos obj, KeyCode code) {
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

		else if (code == KeyCode.ADD)
			return getDigOpe(obj, 4);

		else if (code == KeyCode.SUBTRACT)
			return getDigOpe(obj, 3);

		else if (code == KeyCode.MULTIPLY)
			return getDigOpe(obj, 2);

		else if (code == KeyCode.DIVIDE)
			return getDigOpe(obj, 1);

		else if (code == KeyCode.EQUALS)
			return getDigOpe(obj, 5);

		else if (code == KeyCode.PROPS)
			return getDigOpe(obj, 0);

		else if (code == KeyCode.COMMA)
			return getDigOpe(obj, 6);

		return null;

	}

	/**
	 * seta os boolean's que controlam a virgula e operadores
	 * 
	 * @param code
	 * @param virgAntes
	 * @param virgDps
	 * @param obj
	 */
	default void setBoolPontoAndOpe(CalculadoraEntradaDadosAtributos obj, Boolean pontoAntes, Boolean pontoDps,
			Boolean digOpe) {

		if (pontoAntes != null)
			obj.setPontoNumAntes(pontoAntes);

		if (pontoDps != null)
			obj.setPontoDpsDec(pontoDps);

		if (digOpe != null)
			obj.setDigOperador(digOpe);
	}

	/**
	 * @return 0 =[%], 1 =[/], 2 =[*], 3 =[-], 4 =[+], 5 [=], 6 =[.]
	 * 
	 */
	default String getDigOpe(CalculadoraEntradaDadosAtributos obj, int index) {
		String[] result = obj.getArrayDigOpe();
		return result[index];
	}

	/**
	 * inicia os atributos do objeto
	 * 
	 * @param obj
	 */
	default void iniciarAtributosaCalc(CalculadoraEntradaDadosAtributos obj) {
		obj.setStrLblRes("=");
		obj.setStrLblOpe("");
		obj.setStrNum("");
		obj.setPontoNumAntes(false);
		obj.setPontoDpsDec(true);
		obj.setDigOperador(false);
		obj.setRes(0d);
		obj.setFirstDivisao(true);
		obj.setFirstMult(true);
		obj.setFirstSub(true);
		obj.setConContinue(false);
		obj.setIsPrimeiraOpe(true);
		obj.setService(new CalculadoraAritimeticaService(obj));
		Locale.setDefault(Locale.US);
	}

}
