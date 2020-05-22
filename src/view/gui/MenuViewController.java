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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import observer.DadoAlteradoListener;
import subject.NotificaDadoAlteradoListner;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;

public class MenuViewController implements Initializable,NotificaDadoAlteradoListner {

	private CalculadoraHelper helper = new CalculadoraHelper();

	private List<DadoAlteradoListener> listeners = new ArrayList<>();

	private UsuarioService usuarioService;

	private Usuario usuario;

	private LogsService logsService;

	private Logs logs;

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
	private Button btFechar;

	@FXML
	private void onButtonFecharction(ActionEvent event) {
		try {
			helper.openMainView(helper.getStageAtual(event), Main.mainScene);
			notificarListener();
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

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

	}

	private void fecharView(Stage stageAtual) {
		stageAtual.close();
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
