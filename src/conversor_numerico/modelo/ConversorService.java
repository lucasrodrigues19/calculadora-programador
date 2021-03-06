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
		return helper.convertBinToDec();
	}

	public String convertBinToHex() {
		return helper.convertBinToHex();
	}

	public String convertDecToHex() {
		if (checkDecimal(dadosEntrada.getStrNum()))
			return "";

		return helper.convertDecToHex();
	}

	public String convertDecToBin() {
		if (checkDecimal(dadosEntrada.getStrNum()))
			return "";

		return helper.convertDecToBin();
	}

	public String convertHexToBin() {
		return helper.convertHexToBin();
	}

	public String convertHexToDec() {
		return helper.convertHexToDec();
	}

	protected String checkBinario(String str) {
		boolean temDecHex = false;
		int length = str.length();
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

	protected Boolean checkDecimal(String str) {
		boolean noDec = false;
		// int length = str.length();
		String charFirst = Character.toString(str.charAt(0));
		if ("0".equals(charFirst))
			noDec = true;
		if (noDec) {
			String tmp = dadosEntrada.getStrNum().substring(1);
			dadosEntrada.setStrNum(tmp);
			dadosEntrada.setStrOpe(tmp);
		}
		return noDec;
	}
}
