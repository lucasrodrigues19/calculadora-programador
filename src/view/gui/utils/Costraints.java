package view.gui.utils;

import javafx.scene.control.TextField;

public class Costraints {

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
	 * Metodo usado para que o textField aceite apenas caracters
	 * @param textField
	 */
//	public static void textFieldChar(TextField textField) {
//		textField.textProperty().addListener((obs,valorAnterior,novoValor)->{
//			if(novoValor != null && !novoValor.matches("\n")) {
//				textField.setText(valorAnterior);
//			}
//		});
//	}
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
