package subject;

import java.util.List;

import observer.DadoAlteradoListener;

/**
 * Objeto(subject) que notifica outro object(observer), quando um dado no subject  for alterado.
 * @author lucas.rodrigues
 *
 */
public interface NotificaDadoAlteradoListner {
	
	public List<DadoAlteradoListener>getListeners();
	
	default  void notificarListener() {
		if (getListeners() != null) {
			for (DadoAlteradoListener obj : getListeners()) {
				obj.onDadosAlterados();
			}
		}
	}
	default void addListener(DadoAlteradoListener obj) {
		if(obj != null)
			getListeners().add(obj);
	}
}
