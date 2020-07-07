package observer;

import subject.NotificaDadoAlteradoListner;

/**
 * Interface que permite que um objeto(Objeto que implementar essa inteface)
 * escute um evento de um outro objeto.
 * 
 * O objeto que emite o sinal para executar o evento(onDadosAlterados), não
 * conhece a implementação dessa interface(O objeto que esta escutando o evento
 * dele)
 * Este Objeto(observer) aguarda a notificação do subject, se inscrevendo nele.
 * 
 * @author lucas.rodrigues
 *
 */
public interface DadoAlteradoListener {

	/**
	 * função executada quando o subject notifica
	 */
	void onDadosAlterados();

	/**
	 * Metodo para inscrever-me, no subject
	 * 
	 * @param subject
	 * 			subject que vou me inscrever
	 */
	default void inscreverMeSubject(NotificaDadoAlteradoListner subject) {
		subject.addListener(this);
	}
}
