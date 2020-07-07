package view.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import ex.MyException;
import ex.MyRuntimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelo.entites.Historico;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.HistoricoService;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import observer.DadoAlteradoListener;
import utils.DataUtils;
import view.gui.helpers.ViewHelper;
import view.gui.utils.Alerts;
import view.gui.utils.costraints.Costraints;

public class LoginViewController implements Initializable, DadoAlteradoListener {

	private ViewHelper helper = new ViewHelper();

	private MyRuntimeException runtimeEx;

	private Usuario usuario;

	private Logs logs;

	private Historico historico;

	private LogsService logsService;

	private UsuarioService usuarioService;

	private HistoricoService historicoService;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblTelefone;

	@FXML
	private Label lblErroTelefone;

	@FXML
	private Label lblErroEmail;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtTelefone;

	@FXML
	private Button btLogin;

	@FXML
	private Button btCadastro;

	@FXML
	private void onBtLoginAction(ActionEvent event) {
		try {
			getDadosLogin();
			usuario = getUsuarioService().login(getUsuario());
			if (getUsuario().getUsuid() != null) {
				setDadosLog();
				helper.openParentView("/view/gui/MenuView.fxml", helper.getStageAtual(event),
						(MenuViewController controller) -> {
							controller.setLogs(getLogs());
							controller.setLogsService(getLogsService());
							controller.setUsuario(getUsuario());
							controller.setUsuarioService(getUsuarioService());
							controller.setHistorico(getHistorico());
							controller.setHistoricoService(getHistoricoService());	
							controller.setInfoUsuario();
							controller.salvarLog();
							inscreverMeSubject(controller);
						});
			} else {
				Alerts.showAlertInformations("Por favor, verifique seus dados!");
			}
		} catch (MyRuntimeException e) {
			setMsgErros(e);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtCadastroAction(ActionEvent event) {
		try {
			setDadosUsuario();
			helper.loadViewDialog("/view/gui/CadastroView.fxml", (CadastroViewController controller) -> {
				controller.setUsuario(usuario);
				controller.setUsuarioService(usuarioService);
				controller.atualizarDadosFormCadastro();
				controller.setVisibleLabels();
				controller.setVisibleBtExcluir(false);
			}, helper.getStageAtual(event));
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onKeyTxtEmailAction(KeyEvent event) {
		if (txtEmail.getText().length() > 0)
			lblEmail.setVisible(true);
		else
			lblEmail.setVisible(false);

	}

	@FXML
	private void onMouseClickLblEmailAction(MouseEvent event) {
		lblEmail.setVisible(false);

	}

	@FXML
	private void onKeyTxtTelefoneAction(KeyEvent event) {
		if (txtTelefone.getText().length() > 0)
			lblTelefone.setVisible(true);
		else
			lblTelefone.setVisible(false);
	}

	@FXML
	private void onMouseClickLblTelefoneAction(MouseEvent event) {
		lblTelefone.setVisible(false);

	}

	private void getDadosLogin() {
		if (getUsuario() == null)
			throw new IllegalArgumentException("usuario nulo");

		runtimeEx = new MyRuntimeException();
		if (txtEmail.getText().length() <= 0 || "".equals(txtEmail) || txtEmail == null)
			runtimeEx.addErros("email", "Vazio");

		lblErroEmail.setText("");

		if (txtTelefone.getText().length() <= 0 || "".equals(txtTelefone) || txtTelefone == null)
			runtimeEx.addErros("telefone", "Vazio");

		if (runtimeEx.getErros().size() > 0)
			throw runtimeEx;

		getUsuario().setUsuemail(txtEmail.getText());
		getUsuario().setUsutelefone(txtTelefone.getText());
	}

	private void setMsgErros(MyRuntimeException erros) {

		lblErroTelefone.setText(erros.getErros().containsKey("telefone") ? erros.getErros().get("telefone") : "");
		lblErroEmail.setText(erros.getErros().containsKey("email") ? erros.getErros().get("email") : "");
	}

	private void setDadosUsuario() {
		if (txtEmail != null)
			usuario.setUsuemail(txtEmail.getText());

		if (txtTelefone != null)
			usuario.setUsutelefone(txtTelefone.getText());
	}

	private void setDadosLog() throws MyException {
		// formata a data que vem, depois da um parse
		if (getUsuario() != null && getUsuario().getUsuid() != null) {
			logs.setLogdata(
					DataUtils.parse(DataUtils.format(new Date(), "dd/MM/yyyy HH:mm:ss"), "dd/MM/yyyy HH:mm:ss"));
			logs.setLogusuario(usuario);
		}
	}

	private void initializeNodesConstraints() {
		Costraints.onlyInteger(txtTelefone, false);
		Costraints.maxLength(txtEmail, 40, false);
		Costraints.maxLength(txtTelefone, 11, false);

	}

	private void hidenControls() {
		lblEmail.setVisible(false);
		lblTelefone.setVisible(false);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	public LogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(LogsService logsService) {
		this.logsService = logsService;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public HistoricoService getHistoricoService() {
		return historicoService;
	}

	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodesConstraints();
		hidenControls();
	}

	public void resetLabelsErros() {
		lblErroEmail.setText("");
		lblErroTelefone.setText("");
	}

	@Override
	public void onDadosAlterados() {
		resetLabelsErros();
		usuario = new Usuario();
		usuarioService = new UsuarioService();
		
		logs = new Logs();
		logsService = new LogsService();
		
		historico = new Historico();
		historicoService = new HistoricoService();
	}

}
