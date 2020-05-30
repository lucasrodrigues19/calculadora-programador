package calculadora_aritimetica.modelo;

/**
 * Quaquer sub-classe alem de herdar os atributos necessarios para definir a
 * entrada de dados em uma operação aritimetica de uma calculadora, podera
 * tambem ultilizar as funções que definem e controlam esses dados,
 * implementadas como default method na interface
 * CalculadoraEntradaDadosOperacoes, No caso a interface obriga alguém a herdar
 * a classe: AtributosControleCalculadora.java, por que seus metodos dependem de
 * um tipo CalculadoraEntradaDadosAtributos como parametro. Entao a sub-classe
 * vai seguir um modelo com os atributos e funções necessarias para manipular os
 * dados de entrada de uma calculadora.
 * 
 * @author lucas.rodrigues
 *
 */
public abstract class CalculadoraEntradaDadosAtributos implements CalculadoraEntradaDadosOperacoes {

	private String strLblRes;

	private String strLblOpe;

	private String strNum;

	private String arrayDigOpe[] = { "%", "/", "*", "-", "+", "=", "," };

	private Boolean virgNumAntes;

	private Boolean virgDpsDec;

	private Boolean digOperador;

	/**
	 * @return retorna a string para setar em algum controle na view, referente ao
	 *         resultado da operação
	 */
	public String getStrLblRes() {
		return strLblRes;
	}

	/**
	 * seta a string referente ao resultado da operação
	 */
	public void setStrLblRes(String strLblRes) {
		this.strLblRes = strLblRes;
	}

	/**
	 * @return retorna a string para setar em algum controle na view, referente a
	 *         stirng com as operações feitas
	 */
	public String getStrLblOpe() {
		return strLblOpe;
	}

	/**
	 * seta a string referente as operações feitas
	 */
	public void setStrLblOpe(String strLblOpe) {
		this.strLblOpe = strLblOpe;
	}

	/**
	 * @return retorna a string que sera o numero que fara as operaçõs aritimeticas
	 */
	public String getStrNum() {
		return strNum;
	}

	/**
	 * define a string que sera o numero que fara as operaçõs aritimeticas
	 */
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}

	/**
	 * @return retorna o controlador da virgula, para que a mesma não seja digitada
	 *         antes de um numero
	 */
	public Boolean getVirgNumAntes() {
		return virgNumAntes;
	}

	/**
	 * seta o controlador da virgula, para que a mesma não seja digitada antes de um
	 * numero
	 */
	public void setVirgNumAntes(Boolean virgNumAntes) {
		this.virgNumAntes = virgNumAntes;
	}

	/**
	 * @return seta o controlador da virgula, para que a mesma não seja digitada 2X
	 *         em um mesmo numero
	 */
	public Boolean getVirgDpsDec() {
		return virgDpsDec;
	}

	/**
	 * seta o controlador da virgula, para que a mesma não seja digitada 2X em um
	 * mesmo numero
	 */
	public void setVirgDpsDec(Boolean virgDpsDec) {
		this.virgDpsDec = virgDpsDec;
	}

	/**
	 * retorna o controlador do digito de operação, para que o mesmo so seja
	 * digitado quando um numero for setado, e não repeti-lo 2X vezes
	 * 
	 * @return
	 */
	public Boolean getDigOperador() {
		return digOperador;
	}

	/**
	 * seta o controlador do digito de operação, para que o mesmo so seja digitado
	 * quando um numero for setado, e não repeti-lo 2X vezes
	 */
	public void setDigOperador(Boolean digOperador) {
		this.digOperador = digOperador;
	}

	/**
	 * retorna um array com os digitos operadores
	 * 
	 * @return
	 */
	public String[] getArrayDigOpe() {
		return arrayDigOpe;
	}

}
