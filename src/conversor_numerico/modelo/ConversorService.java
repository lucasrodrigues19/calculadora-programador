package conversor_numerico.modelo;

import conversor_numerico.modelo.helpers.ConversorHelper;

public class ConversorService {

	private ConversorEntradaDadosAtributos dadosEntrada;
	private ConversorHelper helper = new ConversorHelper();
	public ConversorService(ConversorEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
		this.helper = new ConversorHelper(dadosEntrada);
	}
	
	public String convertDecToBin() {
		return helper.convertDecToBin();
	}
	public String convertBinToDec() {
		return helper.convertBinToDec();
	}
}
