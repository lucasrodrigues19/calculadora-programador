package view.gui.helper;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;

/**
 * Classe criada para acossiar os Listeners com as labels.
 * 
 * @author lucas.rodrigues
 *
 */
public class CostraintsLabelListenersHelper {
	private Map<Label, ChangeListener<String>> listenerOnlyIntegerLabel = new HashMap<Label, ChangeListener<String>>();
	private Map<Label, ChangeListener<String>> listenerAnyCharLabel = new HashMap<Label, ChangeListener<String>>();

	public CostraintsLabelListenersHelper() {

	}

	/**
	 * 
	 * @param label
	 * @return O listener OnlyIntegerLabel, de acordo com a label
	 */
	public ChangeListener<String> getListenerOnlyIntegerLabel(Label label) {
		ChangeListener<String> listener = null;
		if (listenerOnlyIntegerLabel.containsKey(label))
			listener = listenerOnlyIntegerLabel.get(label);

		return listener;
	}

	/**
	 * 
	 * @param label
	 * @return O listener AnyCharLabel, de acordo com a label
	 */
	public ChangeListener<String> getListenerAnyCharLabel(Label label) {
		ChangeListener<String> listener = null;
		if (listenerAnyCharLabel.containsKey(label))
			listener = listenerAnyCharLabel.get(label);

		return listener;
	}

	/**
	 * Cria um os Listeners: OnlyIntegerLabel e AnyCharLabel. Para uma label
	 * 
	 * @param label
	 * 
	 */
	public void addLabelInAllListeners(Label label) {
		if (label != null) {
			if (!listenerOnlyIntegerLabel.containsKey(label)) {
				this.listenerOnlyIntegerLabel.put(label, (obs, oldValue, newValue) -> {
					if (newValue != null && !newValue.matches("\\d*"))
						label.setText(oldValue);
				});
			}
			if (!listenerAnyCharLabel.containsKey(label)) {
				this.listenerAnyCharLabel.put(label, (obs, oldValue, newValue) -> {
					if (newValue != null && newValue.matches("\\D*"))
						label.setText(newValue);
				});
			}
		}
	}

	/**
	 * Remove os Listeners: OnlyIntegerLabel e AnyCharLabel. De acordo com label
	 * 
	 * @param label
	 */
	public void removeAllListenersLabel(Label label) {
		
		if (getListenerAnyCharLabel(label) != null)
			label.textProperty().removeListener(getListenerAnyCharLabel(label));
		if (getListenerOnlyIntegerLabel(label) != null)
			label.textProperty().removeListener(getListenerOnlyIntegerLabel(label));
	}

	/**
	 * Adiciona o Listener: OnlyIntegerLabel. Na label
	 * 
	 * @param label
	 */
	public void addLabelInListenerOnlyInteger(Label label) {
		if (label != null) {
			if (!listenerOnlyIntegerLabel.containsKey(label)) {
				this.listenerOnlyIntegerLabel.put(label, (obs, oldValue, newValue) -> {
					if (newValue != null && !newValue.matches("\\d*"))
						label.setText(oldValue);
				});
			}
		}
	}

	/**
	 * Adiciona o Listener: AnyCharLabel. Na label
	 * 
	 * @param label
	 */
	public void addLabelInListenerAnyChar(Label label) {
		if (label != null) {
			if (!listenerAnyCharLabel.containsKey(label)) {
				this.listenerAnyCharLabel.put(label, (obs, oldValue, newValue) -> {
					if (newValue != null && newValue.matches("\\D*"))
						label.setText(newValue);
				});
			}
		}
	}
}
