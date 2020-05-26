package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;

public class MainViewController implements Initializable {

	CalculadoraHelper helper = new CalculadoraHelper();

	@FXML
	private ScrollPane scrollMain;

	@FXML
	private ToolBar toolBarMenu;
	
	@FXML
	private MenuButton menuButtonOpcoes;
	
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
			helper.loadView("/view/gui/LoginView.fxml", Main.mainScene,1, (LoginViewController controller)->{
				controller.setUsuario(new Usuario());
				controller.setUsuarioService(new UsuarioService());
				controller.setLogs(new Logs());
				controller.setLogsService(new LogsService());
			});
			menuItemHome.setVisible(true);
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
		System.out.println(menuItemHome.getText());
		try {
			helper.backView("/view/gui/MainView.fxml", Main.mainScene,null);
			menuItemHome.setVisible(false);
		} catch (MyException e) {
			Alerts.showAlertError(e.getMessage());
		}

	}

	private void setWithHeigt() {
		scrollMain.setFitToHeight(true);
		scrollMain.setFitToWidth(true);
	}

	private void hidenControls() {
		menuItemHome.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hidenControls();
	}

}
