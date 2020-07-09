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
import javafx.scene.input.MouseEvent;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.HistoricoService;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import view.gui.helpers.ViewHelper;
import view.gui.utils.Alerts;

public class MainViewController implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private String getStyleBtOpcoes;

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
			System.out.println(btLogin.getText());
			helper.loadView("/view/gui/LoginView.fxml", Main.mainScene, 1, (LoginViewController controller) -> {
				controller.setUsuario(new Usuario());
				controller.setUsuarioService(new UsuarioService());
				controller.setLogs(new Logs());
				controller.setLogsService(new LogsService());
				controller.setHistorico(new Historico());
				controller.setHistoricoService(new HistoricoService());
				helper.setLblTitle(controller.getLblTitle(), "");
			});
			menuItemHome.setVisible(true);
			menuItemSobre.setVisible(false);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtEntrarAction(ActionEvent event) {

		try {
			helper.openParentView("/view/gui/OperacoesView.fxml", helper.getStageAtual(event),
					(OperacoesViewController controller) -> {
						controller.setHistorico(new Historico());
						controller.hiddenBts();
						helper.setLblTitle(controller.getLblTitle(), "");
					});
			menuItemHome.setVisible(false);
			menuItemSobre.setVisible(true);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtOpcoesMouseHover(MouseEvent event) {
		try {
			getStyleBtOpcoes = menuButtonOpcoes.getStyle();
			menuButtonOpcoes.setStyle(
					getStyleBtOpcoes + "-fx-border-color: #fff; -fx-border-radius: 7px; -fx-background-radius: 7px");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void onBtOpcoesMouseOut(MouseEvent event) {
		try {
			menuButtonOpcoes.setStyle(getStyleBtOpcoes);
		} catch (Exception e) {
			e.printStackTrace();
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
			helper.backView("/view/gui/MainView.fxml", Main.mainScene, null);
			menuItemHome.setVisible(false);
			menuItemSobre.setVisible(true);
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
