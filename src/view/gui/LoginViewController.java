package view.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import ex.MyException;
import ex.MyRuntimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelo.entites.Logs;
import modelo.entites.Usuario;
import modelo.services.LogsService;
import modelo.services.UsuarioService;
import observer.DadoAlteradoListener;
import utils.CalculadoraUtils;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;
import view.gui.utils.Costraints;

public class LoginViewController implements Initializable,DadoAlteradoListener {

	private CalculadoraHelper helper = new CalculadoraHelper();

	private MyRuntimeException runtimeEx;

	private Usuario usuario;
	
	private Logs logs;
	
	private LogsService logsService;

	private UsuarioService usuarioService;

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
			if(getUsuario().getUsuid() != null) {
				setDadosLog();
				helper.openParentView("/view/gui/MenuView.fxml", helper.getStageAtual(event), (MenuViewController controller)->{
					controller.setLogs(getLogs());
					controller.setLogsService(getLogsService());
					controller.setUsuario(getUsuario());
					controller.setUsuarioService(getUsuarioService());
					controller.setInfoUsuario();
					controller.salvarLog();
					inscreverMeSubject(controller);
				});
			}else {
				Alerts.showAlertInformations("Por favor, verifique seus dados!");
			}
		}catch(MyRuntimeException e) {
			setMsgErros(e);
		}catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}catch(Exception e) {
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
				controller.setTextFields();
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
		if(txtEmail != null)
		usuario.setUsuemail(txtEmail.getText());
		
		if(txtTelefone != null)
		usuario.setUsutelefone(txtTelefone.getText());
	}
	private void setDadosLog() {
		logs.setLogdata(new Date());
		logs.setLogusuario(usuario);
	}
	private void initializeNodesConstraints() {
		Costraints.textFieldInteger(txtTelefone);
		Costraints.textFieldMaxLength(txtEmail, 40);
		Costraints.textFieldMaxLength(txtTelefone, 11);

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
	}
	
}
