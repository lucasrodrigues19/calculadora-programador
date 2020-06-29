package conversor_numerico.modelo.helpers;

import conversor_numerico.modelo.ConversorEntradaDadosAtributos;
import ex.MyRuntimeException;
import pilha.modelo.PilhaDinamica;
import pilha.modelo.impl.PilhaDinamicaI;
import utils.TryParseUtils;

public class ConversorHelper {

	private ConversorEntradaDadosAtributos dadosEntrada;
	

	public ConversorHelper(ConversorEntradaDadosAtributos dadosEntrada) {
		if(dadosEntrada ==null)
			throw new MyRuntimeException("Entrada de dados para a conversão de numero nula");
		
		this.dadosEntrada = dadosEntrada;
	}

	public ConversorHelper() {

	}
	/**
	 * 
	 * Dec -> bin
	 * @return
	 */
	public String convertDecToBin() {
		PilhaDinamica<Integer> pilha = new PilhaDinamicaI<>();
		int resto = 0;
		int n = 0;
		String res = "";
		if (TryParseUtils.tryParseInt(dadosEntrada.getStrNum()) != null)
			n = TryParseUtils.tryParseInt(dadosEntrada.getStrNum());

		while (n > 0) {
			resto = n % 2;
			pilha.push(resto);
			n /= 2;
		}

		while (!pilha.isEmpty()) {
			n = pilha.pop();
			res += Integer.toString(n);
		}
		return completarBinario(res);
	}
	/**
	 * Dec-> Bin -> hex
	 * @return
	 */
	public String convertDecToHex() {
		String str = dadosEntrada.getStrNum();//pega strNUm para não perdcer a string original
		dadosEntrada.setStrNum(convertDecToBin()); //convert a string de entrada decimal pra um binario
		String res = convertBinToHex();
		dadosEntrada.setStrNum(str); //pega a string de entrada decimal original
		return res;
	}
	/**
	 * Bin -> hex
	 * @return
	 */
	public String convertBinToDec() {
		String str = dadosEntrada.getStrNum();
		int length = str.length() - 1;
		int n = 0;
		double soma = 0;
		for (int i = length; i >= 0; i--) {
			double num = Math.pow(2, n);
			if (str.charAt(i) == '1')
				soma += num;
			n++;
		}

		return String.format("%.0f", soma);
	}
	/**
	 * Bin -> dec-> hex
	 * @return
	 */
	public String convertBinToHex() {

		PilhaDinamica<String> pilhaTmp = new PilhaDinamicaI<String>();
		String str = dadosEntrada.getStrNum();
		String numTemp = dadosEntrada.getStrNum();
		if (!str.equals("") && str.length() > 0) {
			str = completarBinario(str);
			int casas = str.length() / 4; // divide o numero binario a cada 4 digitos(pega a quantidade de quarteto no
											// numero binario)
			int length = str.length() - 1;
			if (str.length() % 4 != 0)// caso tenha mais um quarteto
				casas++;

			for (int i = 0; i < casas; i++) { // pecorre cada quarteto na Stirng do numero binario
				dadosEntrada.setStrNum(""); 
				for (int k = 0; k < 4; k++) { // pecorre o numero binario do quarteto de tras para frente
					if (length >= 0) {
						String tmp = Character.toString(str.charAt(length));
						dadosEntrada.setStrNum(tmp + dadosEntrada.getStrNum()); // pega o numero binario no quarteto
					} else// caso acabe de iterar a String não faz mais nada
						break;
					length--;
				}
				String res = convertBinToDec(); // converte o numero binario do quarteto
				res = getDigHexIsDec(res); // pega o digito hexa-decimal
				// ja que esta iterando a string de numero binario de tras pra frente, o
				// primeiro digito hexa decimal tem q ser o
				// ultimo a sair
				pilhaTmp.push(res);
			}
			String result = "";
			while (!pilhaTmp.isEmpty()) {
				result += pilhaTmp.pop();
			}
			dadosEntrada.setStrNum(numTemp); //pega a string binaria original
			return result;
		}

		return "";
	}
	/**
	 * Hex -> Bin
	 * @return
	 */
	public String convertHexToBin() {
		PilhaDinamica<String> pilha = new PilhaDinamicaI<>();
		String strTmp =  dadosEntrada.getStrNum();
		int length = strTmp.length() -1;
		for(int i=length;i>=0;i--) {
			dadosEntrada.setStrNum(getDigDecIsHex(Character.toString(strTmp.charAt(i))));	
			String bin = convertDecToBin();
			pilha.push(bin);
			bin = "";
		}
		String res = "";
		while(!pilha.isEmpty()) {
			res+=pilha.pop();
		}
			
		dadosEntrada.setStrNum(strTmp);
		return res;
	}
	/**
	 * Hex -> Bin -> Dec
	 * @return
	 */
	public String convertHexToDec() {
		String strTmp =  dadosEntrada.getStrNum();
		dadosEntrada.setStrNum(convertHexToBin());
		String res = convertBinToDec();
		dadosEntrada.setStrNum(strTmp);
		return res;
	}
	private String completarBinario(String str) {
		if (str != null) {
			switch (str) {
			case "1":
				str = "000" + str;
				break;
			case "10":
				str = "00" + str;
				break;
			case "11":
				str = "00" + str;
				break;
			case "100":
				str = "0" + str;
				break;
			case "101":
				str = "0" + str;
				break;
			case "110":
				str = "0" + str;
				break;
			case "111":
				str = "0" + str;
				break;
			}
		}
		return str;

	}

	private String getDigHexIsDec(String str) {
		if (str != null) {
			switch (str) {
			case "10":
				str = "A";
				break;
			case "11":
				str = "B";
				break;
			case "12":
				str = "C";
				break;
			case "13":
				str = "D";
				break;
			case "14":
				str = "E";
				break;
			case "15":
				str = "F";
				break;
			}

		}
		return str;
	}

	private String getDigDecIsHex(String str) {
		if (str != null) {
			switch (str) {
			case "A":
				str = "10";
				break;
			case "B":
				str = "11";
				break;
			case "C":
				str = "12";
				break;
			case "D":
				str = "13";
				break;
			case "E":
				str = "14";
				break;
			case "F":
				str = "15";
				break;
			}

		}
		return str;
	}
	

}
