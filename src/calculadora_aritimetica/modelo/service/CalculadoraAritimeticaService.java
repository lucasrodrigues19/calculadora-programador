package calculadora_aritimetica.modelo.service;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import calculadora_aritimetica.modelo.helper.CalculadoraAritimeticaHelper;

public class CalculadoraAritimeticaService {

	private CalculadoraEntradaDadosAtributos dadosEntrada;
	private CalculadoraAritimeticaHelper helper;
	// passar tudo para os dados de entrada
	

	public CalculadoraAritimeticaService(CalculadoraEntradaDadosAtributos dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}

	
}
