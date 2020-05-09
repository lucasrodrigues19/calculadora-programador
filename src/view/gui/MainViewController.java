package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;

public class MainViewController implements Initializable {

	CalculadoraHelper helper = new CalculadoraHelper();

	@FXML
	private ScrollPane scrollMain;

	@FXML
	private MenuItem menuInicio;
	@FXML
	private MenuItem menuAjuda;

	@FXML
	private MenuItem menuItemSobre;

	@FXML
	private MenuItem menuItemHome;

	@FXML
	private Button btLogin;

	@FXML
	private Button btEntrar;

	@FXML
	private void onBtLoginAction() {
		try {
			System.out.println(btEntrar.getText());
			helper.loadView("/view/gui/LoginView.fxml", Main.mainScene, null);
			menuInicio.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtEntrarAction(ActionEvent event) {

		try {
			System.out.println(btEntrar.getText());

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onMenuItemSobreAction(ActionEvent event) {
		System.out.println(menuItemSobre.getText());

		try {
			helper.loadViewDialog("/view/gui/AboutView.fxml", null, helper.getStageAtual(Main.mainScene));
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onMenuItemHomeAction() {
		System.out.println(menuInicio.getText());
		try {
			helper.loadMainView("/view/gui/MainView.fxml", Main.mainScene);
			menuInicio.setVisible(false);
		} catch (Exception e) {
			Alerts.showAlertError(e.getMessage());
		}

	}

	public void setWithHeigt() {
		scrollMain.setFitToHeight(true);
		scrollMain.setFitToWidth(true);
	}

	public void hidenControls() {
		menuInicio.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hidenControls();
	}

}
