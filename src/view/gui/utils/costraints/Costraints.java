package view.gui.utils.costraints;

import javafx.scene.control.Control;

public class Costraints {

	/**
	 * Metodo usado para que o Control aceite apenas um maximo de caracteres
	 * @param obj
	 * 		 Control(TextField ou Label)
	 * @param max
	 *       maximo de char
	 * @param removeAllListenersBefore
	 * 		
	 */
	public static void maxLength(Control obj, int max, boolean removeAllListenersBefore) {
		if (removeAllListenersBefore)
			ControlListeners.removeAllListeners(obj);

		ControlListeners.associatesInListenerMaxLength(obj, max);
		ControlListeners.addListenerMaxLength(obj);
	}

	/**
	 * Metodo usado para que o Control aceite apenas números Inteiros
	 * @param obj
	 * 			Control(TextField ou Label)
	 * @param removeAllListenersBefore
	 */
	public static void onlyInteger(Control obj, boolean removeAllListenersBefore) {
		if (removeAllListenersBefore)
			ControlListeners.removeAllListeners(obj);

		ControlListeners.associatesInListenerOnlyInteger(obj);
		ControlListeners.addListenerOnlyInteger(obj);
	}

	/**
	 * Metodo usado para que o Control aceite todos os caracters
	 * 
	 * @param obj
	 * 			Control (TextField ou Label)
	 * @param removeAllListenersBefore
	 */
	public static void anyChar(Control obj, boolean removeAllListenersBefore) {
		if (removeAllListenersBefore)
			ControlListeners.removeAllListeners(obj);

		ControlListeners.associatesInListenerAnyChar(obj);
		ControlListeners.addListenerAnyChar(obj);
	}

}
