package view.gui.utils.costraints;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe criada para acossiar os Listeners com os Controls(Label e TextField).
 * 
 * @author lucas.rodrigues
 *
 */
public class ControlListeners {

	private static Map<Control, ChangeListener<String>> listenerMaxLength = new HashMap<>();
	private static Map<Control, ChangeListener<String>> listenerOnlyInteger = new HashMap<>();
	private static Map<Control, ChangeListener<String>> listenerAnyChar = new HashMap<>();

	
	/**
	 * 
	 * @param obj Control
	 * @return O listener OnlyIntegerControl, associado com a obj
	 */
	private static ChangeListener<String> getListenerMaxLength(Control obj) {
		ChangeListener<String> listener = null;

		if (listenerMaxLength.containsKey(obj))
			listener = listenerMaxLength.get(obj);

		return listener;
	}

	/**
	 * 
	 * @param obj Control
	 * @return O listener OnlyIntegerControl, associado com a obj
	 */
	private static ChangeListener<String> getListenerOnlyInteger(Control obj) {
		ChangeListener<String> listener = null;

		if (listenerOnlyInteger.containsKey(obj))
			listener = listenerOnlyInteger.get(obj);

		return listener;
	}

	/**
	 * 
	 * @param obj Control
	 * @return O listener AnyCharControl, associado com a obj
	 */
	private static ChangeListener<String> getListenerAnyChar(Control obj) {
		ChangeListener<String> listener = null;
		if (listenerAnyChar.containsKey(obj))
			listener = listenerAnyChar.get(obj);

		return listener;
	}

	/**
	 * Associa todos Listeners(OnlyIntegerControl e AnyCharControl), para uma obj
	 * 
	 * @param obj Control
	 * 
	 */
	protected static void associatesInAllListeners(Control obj, int max) {
		if (obj != null) {
			if (obj instanceof Label) {
				if (!listenerOnlyInteger.containsKey(obj)) {
					listenerOnlyInteger.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && !newValue.matches("\\d*")) {
							if (oldValue != null && !oldValue.matches("\\d*"))
								((Label) obj).setText("");
							else
								((Label) obj).setText(oldValue);
						}

					});
				} // end if
				if (!listenerAnyChar.containsKey(obj)) {
					listenerAnyChar.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.matches("\\D*"))
							((Label) obj).setText(newValue);
					});
				} // end if
				if (!listenerMaxLength.containsKey(obj)) {
					listenerMaxLength.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.length() > max) {
							((Label) obj).setText(oldValue);
						}
					});
				} // end if Label
			} else if (obj instanceof TextField) {
				if (!listenerOnlyInteger.containsKey(obj)) {
					listenerOnlyInteger.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && !newValue.matches("\\d*")) {
							if (oldValue != null && !oldValue.matches("\\d*"))
								((TextField) obj).setText("");
							else
								((TextField) obj).setText(oldValue);
						}
					});
				} // end if
				if (!listenerAnyChar.containsKey(obj)) {
					listenerAnyChar.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.matches("\\D*"))
							((TextField) obj).setText(newValue);
					});
				} // end if
				if (!listenerMaxLength.containsKey(obj)) {
					listenerMaxLength.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.length() > max) {
							((TextField) obj).setText(oldValue);
						}
					});
				} // end if
			}
		}
	}

	/**
	 * Remove todos Listeners(OnlyIntegerControl e AnyCharControl) do Control, de
	 * acordo com obj
	 * 
	 * @param obj Control
	 */
	protected static void removeAllListeners(Control obj) {
		if (obj instanceof Label) {
			if (getListenerAnyChar(obj) != null)
				((Label) obj).textProperty().removeListener(getListenerAnyChar(obj));
			if (getListenerOnlyInteger(obj) != null)
				((Label) obj).textProperty().removeListener(getListenerOnlyInteger(obj));
			if (getListenerMaxLength(obj) != null)
				((Label) obj).textProperty().removeListener(getListenerMaxLength(obj));
		} // end if Label
		else if (obj instanceof TextField) {
			if (getListenerAnyChar(obj) != null)
				((TextField) obj).textProperty().removeListener(getListenerAnyChar(obj));
			if (getListenerOnlyInteger(obj) != null)
				((TextField) obj).textProperty().removeListener(getListenerOnlyInteger(obj));
			if (getListenerMaxLength(obj) != null)
				((TextField) obj).textProperty().removeListener(getListenerMaxLength(obj));
		} // end if TextField
	}

	/**
	 * Associa o Control com o Listener:MaxLength.
	 * 
	 * @param obj
	 * @param max
	 */
	protected static void associatesInListenerMaxLength(Control obj, int max) {
		if (obj != null) {
			if (obj instanceof Label) {
				if (!listenerMaxLength.containsKey(obj)) {
					listenerMaxLength.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.length() > max) {
							((Label) obj).setText(oldValue);
						}
					});
				} // end if
			} // end if Label
			else if (obj instanceof TextField) {
				if (!listenerMaxLength.containsKey(obj)) {
					listenerMaxLength.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.length() > max) {
							((TextField) obj).setText(oldValue);
						}
					});
				} // end if
			} // end if TextField

		} // end if
	}

	/**
	 * Associa o Control com o Listener: OnlyInteger.
	 * 
	 * @param obj Control
	 */
	protected static void associatesInListenerOnlyInteger(Control obj) {
		if (obj != null) {
			if (obj instanceof Label) {
				if (!listenerOnlyInteger.containsKey(obj)) {
					listenerOnlyInteger.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && !newValue.matches("\\d*")) {
							if (oldValue != null && !oldValue.matches("\\d*"))
								((Label) obj).setText("");
							else
								((Label) obj).setText(oldValue);
						}

					});
				}
			} // end if Label
			else if (obj instanceof TextField) {
				if (!listenerOnlyInteger.containsKey(obj)) {
					listenerOnlyInteger.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && !newValue.matches("\\d*")) {
							if (oldValue != null && !oldValue.matches("\\d*"))
								((TextField) obj).setText("");
							else
								((TextField) obj).setText(oldValue);
						}
					});
				}

			} // end if TextField

		}
	}

	/**
	 * Associa o Control com o Listener: AnyChar. Na obj
	 * 
	 * @param obj Control
	 */
	protected static void associatesInListenerAnyChar(Control obj) {
		if (obj != null) {
			if (obj instanceof Label) {
				if (!listenerAnyChar.containsKey(obj)) {
					listenerAnyChar.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.matches("\\D*"))
							((Label) obj).setText(newValue);
					});
				}
			} // end if Label
			if (obj instanceof TextField) {
				if (!listenerAnyChar.containsKey(obj)) {
					listenerAnyChar.put(obj, (obs, oldValue, newValue) -> {
						if (newValue != null && newValue.matches("\\D*"))
							((TextField) obj).setText(newValue);
					});
				}
			} // end if TextField
		}
	}

	/**
	 * Adiciona o Listener MaxLength no obj
	 * 
	 * @param obj Controle
	 */
	protected static void addListenerMaxLength(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().addListener(getListenerMaxLength(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().addListener(getListenerMaxLength(obj));

	}

	/**
	 * Adiciona o Listener AnyChar no obj
	 * 
	 * @param obj Controle
	 */
	protected static void addListenerAnyChar(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().addListener(getListenerAnyChar(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().addListener(getListenerAnyChar(obj));

	}

	/**
	 * Adiciona o Listener ListenerOnlyInteger no obj
	 * 
	 * @param obj Controle
	 */
	protected static void addListenerOnlyInteger(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().addListener(getListenerOnlyInteger(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().addListener(getListenerOnlyInteger(obj));

	}

	/**
	 * Remove o Listener MaxLength no obj
	 * 
	 * @param obj Controle
	 */
	protected static void removeLintenerMaxLength(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().removeListener(getListenerMaxLength(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().removeListener(getListenerMaxLength(obj));

	}

	/**
	 * Remove o Listener AnyChar no obj
	 * 
	 * @param obj Controle
	 */
	protected static void removeLintenerAnyChar(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().removeListener(getListenerAnyChar(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().removeListener(getListenerAnyChar(obj));

	}

	/**
	 * Remove o Listener ListenerOnlyInteger no obj
	 * 
	 * @param obj Controle
	 */
	protected static void removeListenerOnlyInteger(Control obj) {
		if (obj instanceof Label)
			((Label) obj).textProperty().removeListener(getListenerOnlyInteger(obj));
		else if (obj instanceof TextField)
			((TextField) obj).textProperty().removeListener(getListenerOnlyInteger(obj));

	}
}
