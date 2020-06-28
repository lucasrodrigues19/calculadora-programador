package conversor_numerico.modelo;

public abstract class ConversorEntradaDadosAtributos implements ConversorEntradaDadosOperacoes{

	private String strNum;
	private String strOpe;
	private String strRes;
	private String opeDaVez;
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
	public String getOpeDaVez() {
		return opeDaVez;
	}
	public void setOpeDaVez(String opeDaVez) {
		this.opeDaVez = opeDaVez;
	}
	public ConversorService getService() {
		return service;
	}
	public void setService(ConversorService service) {
		this.service = service;
	}
	
	
}
