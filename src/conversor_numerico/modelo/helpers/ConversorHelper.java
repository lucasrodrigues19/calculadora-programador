package conversor_numerico.modelo.helpers;

import conversor_numerico.modelo.ConversorEntradaDadosAtributos;
import pilha.modelo.PilhaDinamica;
import pilha.modelo.impl.PilhaDinamicaI;
import utils.TryParseUtils;

public class ConversorHelper {

	private ConversorEntradaDadosAtributos dadosEntrada;
	private PilhaDinamica<Integer> pilha = new PilhaDinamicaI<>();
	
	public ConversorHelper(ConversorEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}
	
	public ConversorHelper() {
		
	}
	
	public String convertDecToBin() {
		int resto = 0;
		int n = 0;
		String res = "";
		if(TryParseUtils.tryParseInt(dadosEntrada.getStrNum()) != null)
			n = TryParseUtils.tryParseInt(dadosEntrada.getStrNum());
		
		while(n > 0) {
			resto = n % 2;
			pilha.push(resto);
			n/=2;
		}
		
		while(!pilha.isEmpty()) {
			n = pilha.pop();
			res+= Integer.toString(n);
		}
		return completarBinario(res);
	}
	public String convertBinToDec() {
		String str = dadosEntrada.getStrNum();
		int length = str.length() -1;
		int n = 0;
		double soma = 0;
		for(int i  = length; i>=0;i--) {
			double  num = Math.pow(2, n);
			if(str.charAt(i) == '1')
				soma+= num;
			n++;
		}
	
		return Double.toString(soma);
	}
	private String completarBinario(String str) {
		if(str != null) {
			switch(str) {
			case "1":
				str = "000"+str;
				break;
			case "10":
				str = "00"+str;
			 	break;
			case "11":
				str = "00"+str;
				break;
			case "100":
				str = "0"+str;
				break;
			case "101":
				str = "0"+str;
				break;
			case "110":
				str = "0"+str;
				break;
			case "111":
				str = "0"+str;
				break;
			}
		}
		return str;
		
	}
}
