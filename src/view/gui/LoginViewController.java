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
import javafx.scene.control.PasswordField;
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
	private Label lblTitle;
	
	@FXML
	private Label lblEmail;

	@FXML
	private Label lblSenha;

	@FXML
	private Label lblErroSenha;

	@FXML
	private Label lblErroEmail;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField psfSenha;

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
							helper.setLblTitle(controller.getLblTitle(), "");
						});
			} else {
				Alerts.showAlertInformations("Por favor, verifique seus dados!");
				setMsgErros(new MyRuntimeException());
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
				helper.setLblTitle(controller.getLblTitle(), "Cadastro");
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
	private void onKeyPsfSenhaAction(KeyEvent event) {
		if (psfSenha.getText().length() > 0)
			lblSenha.setVisible(true);
		else
			lblSenha.setVisible(false);
	}

	@FXML
	private void onMouseClickLblSenhaAction(MouseEvent event) {
		lblSenha.setVisible(false);

	}

	private void getDadosLogin() {
		if (getUsuario() == null)
			throw new IllegalArgumentException("usuario nulo");

		runtimeEx = new MyRuntimeException();
		if (txtEmail.getText().length() <= 0 || "".equals(txtEmail) || txtEmail == null)
			runtimeEx.addErros("email", "Vazio");

		lblErroEmail.setText("");

		if (psfSenha.getText().length() <= 0 || "".equals(psfSenha) || psfSenha == null)
			runtimeEx.addErros("senha", "Vazio");

		if (runtimeEx.getErros().size() > 0)
			throw runtimeEx;

		getUsuario().setUsuemail(txtEmail.getText());
		getUsuario().setUsusenha(psfSenha.getText());
	}

	private void setMsgErros(MyRuntimeException erros) {

		lblErroSenha.setText(erros.getErros().containsKey("senha") ? erros.getErros().get("senha") : "");
		lblErroEmail.setText(erros.getErros().containsKey("email") ? erros.getErros().get("email") : "");
	}

	private void setDadosUsuario() {
		if (txtEmail != null)
			usuario.setUsuemail(txtEmail.getText());

		if (psfSenha != null)
			usuario.setUsusenha(psfSenha.getText());
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
		Costraints.onlyInteger(psfSenha, false);
		Costraints.maxLength(txtEmail, 40, false);
		Costraints.maxLength(psfSenha, 12, false);

	}

	private void hidenControls() {
		lblEmail.setVisible(false);
		lblSenha.setVisible(false);
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

	public Label getLblTitle() {
		return lblTitle;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodesConstraints();
		hidenControls();
	}

	public void resetLabelsErros() {
		lblErroEmail.setText("");
		lblErroSenha.setText("");
	}

	@Override
	public void onDadosAlterados() {
		resetLabelsErros();
		lblTitle.setText("");
		usuario = new Usuario();
		usuarioService = new UsuarioService();
		
		logs = new Logs();
		logsService = new LogsService();
		
		historico = new Historico();
		historicoService = new HistoricoService();
	}

}
