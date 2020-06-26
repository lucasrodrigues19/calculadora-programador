package view.gui.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.gui.helper.CostraintsLabelListenersHelper;

public class Costraints {

	private static CostraintsLabelListenersHelper lblHelper = new CostraintsLabelListenersHelper();
	/**
	 * Metodo usado para que o textField aceite apenas números Inteiros
	 * @param textField
	 */
	public static void textFieldInteger(TextField textField) {
		textField.textProperty().addListener((obs,valorAnterior, novoValor)->{
			if(novoValor !=  null && !novoValor.matches("\\d*")) {
				textField.setText(valorAnterior);
			}
		});
	}
	/**
	 * Metodo usado para que o Label aceite apenas números Inteiros
	 * @param Label
	 */
	public static void onlyIntegerLabel(Label label) {
		lblHelper.removeAllListenersLabel(label);
		lblHelper.addLabelInListenerOnlyInteger(label);
		label.textProperty().addListener(lblHelper.getListenerOnlyIntegerLabel(label));
	}
	/**
	 * Metodo usado para que o Label aceite todos os caracters
	 * @param Label
	 */
	public static void anyCharLabel(Label label) {
		lblHelper.removeAllListenersLabel(label);
		lblHelper.addLabelInListenerAnyChar(label);
		label.textProperty().addListener(lblHelper.getListenerAnyCharLabel(label));
	}
	/**
	 * Metodo usado para que o text field aceite apenas um maximo de caracteres
	 * @param textField
	 * @param max
	 */
	public static void textFieldMaxLength(TextField textField, int max) {
		textField.textProperty().addListener((obs,valorAnterior,novoValor)->{
			if(novoValor != null && novoValor.length() > max) {
				textField.setText(valorAnterior);
			}
		});
	}
}
