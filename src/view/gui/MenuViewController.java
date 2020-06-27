package view.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import observer.DadoAlteradoListener;
import subject.NotificaDadoAlteradoListner;
import view.gui.helpers.ViewHelper;
import view.gui.utils.Alerts;

public class MenuViewController implements Initializable, NotificaDadoAlteradoListner {

	private ViewHelper helper = new ViewHelper();

	private List<DadoAlteradoListener> listeners = new ArrayList<>();

	private UsuarioService usuarioService;

	private Usuario usuario;

	private LogsService logsService;

	private Logs logs;

	@FXML
	ScrollPane menuScrollPane;

	@FXML
	private Label lblInfoUsuario;

	@FXML
	private ToolBar toolBarMenu;

	@FXML
	private MenuButton menuButtonOpcoes;

	@FXML
	private MenuItem menuItemPerfil;

	@FXML
	private MenuItem menuItemOperacoes;

	@FXML
	private Button btSair;

	@FXML
	private Button btVoltar;

	@FXML
	private void onButtonSairAction(ActionEvent event) {
		try {
			helper.openMainView(helper.getStageAtual(event), Main.mainScene);
			notificarListener();
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onButtonVoltarAction(ActionEvent event) {
		try {
			helper.backView("/view/gui/MenuView.fxml", getMenuViewScene(), (MenuViewController controller)->{
				controller.setUsuario(getUsuario());
				controller.setUsuarioService(getUsuarioService());
				//controller.setHistorico(new Historico());
				controller.setInfoUsuario();
			});
			btVoltar.setVisible(false);
			notificarListener();
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onMenuItemOperacoesAction() {
		try {
			helper.loadView("/view/gui/OperacoesView.fxml", getMenuViewScene(),1,
					(OperacoesViewController controller) -> {
						controller.setUsuario(getUsuario());
						controller.setUsuarioService(getUsuarioService());
						controller.setScenePai(getMenuViewScene());
						controller.setHistorico(new Historico());
					});
			btVoltar.setVisible(true);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Scene getMenuViewScene() {
		return menuScrollPane.getScene();
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(LogsService logsService) {
		this.logsService = logsService;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hiddenControlls();
	}

//	private void fecharView(Stage stageAtual) {
//		stageAtual.close();
//	}
	private void hiddenControlls() {
		btVoltar.setVisible(false);
	}

	public void setInfoUsuario() {
		if (getUsuario() == null)
			throw new IllegalArgumentException("usuario nulo");

		if (getUsuario().getUsuemail() != null)
			lblInfoUsuario.setText(getUsuario().getUsuemail());
	}

	public void salvarLog() {
		getLogsService().save(getLogs());
	}

	@Override
	public List<DadoAlteradoListener> getListeners() {
		return listeners;
	}

}
