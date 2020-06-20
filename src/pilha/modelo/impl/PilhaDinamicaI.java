package pilha.modelo.impl;

import pilha.modelo.Caixinha;
import pilha.modelo.PilhaDinamica;

public class PilhaDinamicaI<T> implements PilhaDinamica<T>{

	private Caixinha<T> topo;
	
	public PilhaDinamicaI() {
		topo = null;
	}
	
	@Override
	public void push(T elemento) {
		Caixinha<T> tmp = new Caixinha<T>();
		tmp.setElemento(elemento); //pega o elemento
		tmp.setProximo(topo); //o proximo da caixinha , é o topo o de antes
		topo = tmp; //topo pega o ultimo elemento inserido
		
	}

	@Override
	public Boolean isEmpty() {
		return (topo == null);
	}

	@Override
	public T pop() {
		Caixinha<T> tmp = topo;
		T elemento = topo.getElemento();
		topo = tmp.getProximo();
		return elemento;
	}

	

}
