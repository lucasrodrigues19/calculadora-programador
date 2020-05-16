package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
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
			helper.loadView("/view/gui/LoginView.fxml", Main.mainScene, (LoginViewController controller)->{
				controller.setUsuario(new Usuario());
				controller.setUsuarioService(new UsuarioService());
			});
			menuInicio.setVisible(true);
		} catch (MyException e) {
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
		} catch (MyException e) {
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
		} catch (MyException e) {
			Alerts.showAlertError(e.getMessage());
		}

	}

	private void setWithHeigt() {
		scrollMain.setFitToHeight(true);
		scrollMain.setFitToWidth(true);
	}

	private void hidenControls() {
		menuInicio.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hidenControls();
	}

}
