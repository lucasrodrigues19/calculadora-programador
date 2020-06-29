package conversor_numerico.modelo;

import conversor_numerico.modelo.helpers.ConversorHelper;

public class ConversorService {

	private ConversorEntradaDadosAtributos dadosEntrada;
	private ConversorHelper helper;

	public ConversorService(ConversorEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
		this.helper = new ConversorHelper(dadosEntrada);

	}

	public String convertBinToDec() {
		String res = checkBinario();
		if (!res.equals(""))
			return res;

		return helper.convertBinToDec();
	}

	public String convertBinToHex() {
		String res = checkBinario();
		if (!res.equals(""))
			return res;

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

	private String checkBinario() {
		boolean temDecHex = false;
		int length = dadosEntrada.getStrNum().length();
		for (int i = 0; i < length; i++) {
			String dig = Character.toString(dadosEntrada.getStrNum().charAt(i));
			if (!dig.equals("1") && dig.equals("0"))
				temDecHex = false;
			else if (!dig.equals("0") && dig.equals("1"))
				temDecHex = false;
			else {
				temDecHex = true;
				break;
			}
		}
		if (temDecHex) {
			String tmp = dadosEntrada.getStrNum().substring(0, (dadosEntrada.getStrNum().length() - 1));
			dadosEntrada.setStrNum(tmp);
			dadosEntrada.setStrOpe(tmp);
			return dadosEntrada.getStrRes();
		}
		return "";
	}
}
