package calculadora_aritimetica.modelo;

import calculadora_aritimetica.modelo.service.CalculadoraAritimeticaService;

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

	private String arrayDigOpe[] = { "%", "/", "*", "-", "+", "=", "." };

	private Boolean pontoNumAntes;

	private Boolean pontoDpsDec;

	private Boolean digOperador;

	private Double res;

	private Boolean conContinue; 

	private Boolean isPrimeiraOpe; 

	private String ultimaOperacao;

	private String opeVez;

	
	private CalculadoraAritimeticaService service;
	
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
	 * @return retorna o controlador do ponto, para que o mesmo não seja digitado
	 *         antes de um numero
	 */
	public Boolean getPontoNumAntes() {
		return pontoNumAntes;
	}

	/**
	 * seta o controlador do ponto, para que o mesmo não seja digitado antes de um
	 * numero
	 */
	public void setPontoNumAntes(Boolean pontoNumAntes) {
		this.pontoNumAntes = pontoNumAntes;
	}

	/**
	 * @return seta o controlador do ponto, para que a mesmo não seja digitado 2X
	 *         em um mesmo numero
	 */
	public Boolean getPontoDpsDec() {
		return pontoDpsDec;
	}

	/**
	 * seta o controlador do ponto para que a mesmo não seja digitado 2X em um
	 * mesmo numero
	 */
	public void setPontoDpsDec(Boolean pontoDpsDec) {
		this.pontoDpsDec = pontoDpsDec;
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

	public CalculadoraAritimeticaService getService() {
		return service;
	}

	public void setService(CalculadoraAritimeticaService service) {
		this.service = service;
	}

	/**
	 * retorna um array com os digitos operadores
	 * 
	 * @return
	 */
	public String[] getArrayDigOpe() {
		return arrayDigOpe;
	}

	/**
	 * retorna o resultado da operação
	 * 
	 * @return
	 */
	public Double getRes() {
		return res;
	}

	/**
	 * seta o resultado da operação
	 * 
	 * @param res
	 */
	public void setRes(Double res) {

		this.res = res;
	}

	/**
	 * retorna se pode continuar a operação, mesmo se strNum == ""
	 * @return
	 */
	public Boolean getConContinue() {
		return conContinue;
	}

	/**
	 * seta se pode continuar a operação, mesmo se strNum == ""
	 * deve ser true quando clicado no botão igual, dever ser iniciado como false
	 * @param conContinue
	 */
	public void setConContinue(Boolean conContinue) {
		this.conContinue = conContinue;
	}

	/**
	 * retorna se é a primeira operação feita
	 * @return
	 */
	public Boolean getIsPrimeiraOpe() {
		return isPrimeiraOpe;
	}

	/**
	 *  define se é a primeira operação feita, deve iniciar como true
	 * @param isPrimeiraOpe
	 */
	public void setIsPrimeiraOpe(Boolean isPrimeiraOpe) {
		this.isPrimeiraOpe = isPrimeiraOpe;
	}

	/**
	 *  retorna a ultima operação operação feita
	 * @return
	 */
	public String getUltimaOperacao() {
		return ultimaOperacao;
	}

	/**
	 * seta a ultima operação operação feita, deve ser setado ao final de cada operação
	 * @param ultimaOperacao
	 */
	public void setUltimaOperacao(String ultimaOperacao) {
		this.ultimaOperacao = ultimaOperacao;
	}

	/**
	 * retorna a operação da vez
	 * @return
	 */
	public String getOpeVez() {
		return opeVez;
	}

	/**
	 * seta a operação da vez, deve ser setado assim que clicar no botao de operação
	 * @param opeVez
	 */
	public void setOpeVez(String opeVez) {
		this.opeVez = opeVez;
	}

	
}
