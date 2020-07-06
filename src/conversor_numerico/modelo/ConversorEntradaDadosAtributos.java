package conversor_numerico.modelo;

public abstract class ConversorEntradaDadosAtributos implements ConversorEntradaDadosOperacoes{

	private String strNum;
	private String strOpe;
	private String strRes;
	private OperacoesConversores opeEntrada;
	private OperacoesConversores opeSaida;
	private ConversorService service;
	
	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	public String getStrOpe() {
		return strOpe;
	}
	public void setStrOpe(String strOpe) {
		this.strOpe = strOpe;
	}
	public String getStrRes() {
		return strRes;
	}
	public void setStrRes(String strRes) {
		this.strRes = strRes;
	}
	public OperacoesConversores getOpeEntrada() {
		return opeEntrada;
	}
	public void setOpeEntrada(OperacoesConversores opeEntrada) {
		this.opeEntrada = opeEntrada;
	}
	public OperacoesConversores getOpeSaida() {
		return opeSaida;
	}
	public void setOpeSaida(OperacoesConversores opeSaida) {
		this.opeSaida = opeSaida;
	}
	public ConversorService getService() {
		return service;
	}
	public void setService(ConversorService service) {
		this.service = service;
	}
	
}
