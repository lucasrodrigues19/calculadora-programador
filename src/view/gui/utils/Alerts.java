package view.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public static void showAlert(String title, String header, String contenet, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(contenet);
		alert.show();
	}
}
