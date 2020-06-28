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
	
	
}
