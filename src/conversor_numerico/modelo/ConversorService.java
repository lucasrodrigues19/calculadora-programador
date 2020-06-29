package conversor_numerico.modelo;

import conversor_numerico.modelo.helpers.ConversorHelper;

public class ConversorService {

	private ConversorEntradaDadosAtributos dadosEntrada;
	private ConversorHelper helper;

	public ConversorService(ConversorEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;

	}

	

	public String convertBinToDec() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertBinToDec();
	}

	public String convertBinToHex() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertBinToHex();
	}

	public String convertDecToHex() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertDecToHex();
	}
	public String convertDecToBin() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertDecToBin();
	}
	public String convertHexToBin() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertHexToBin();
	}
	public String convertHexToDec() {
		this.helper = new ConversorHelper(dadosEntrada);
		return helper.convertHexToDec();
	}
}
