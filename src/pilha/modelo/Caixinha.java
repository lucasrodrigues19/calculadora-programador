package pilha.modelo;

public class Caixinha<T> {

	private Caixinha<T> proximo;
	private T elemento;
	
	public Caixinha() {
		
	}

	public Caixinha<T> getProximo() {
		return proximo;
	}

	public void setProximo(Caixinha<T> proximo) {
		this.proximo = proximo;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	
	
}
