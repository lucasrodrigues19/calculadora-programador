package pilha.modelo;

public interface PilhaDinamica<T> {

	T pop();
	void push(T elemento);
	Boolean isEmpty();
}
