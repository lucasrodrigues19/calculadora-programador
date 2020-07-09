package view.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.ex.MySQLException;
import ex.MyException;
import ex.MyRuntimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import observer.DadoAlteradoListener;
import subject.NotificaDadoAlteradoListner;
import utils.TryParseUtils;
import view.gui.helpers.ViewHelper;
import view.gui.utils.Alerts;
import view.gui.utils.costraints.Costraints;

public class CadastroViewController implements Initializable, NotificaDadoAlteradoListner {

	private Usuario usuario;

	private UsuarioService usuarioService;

	private ViewHelper helper = new ViewHelper();
	
	@FXML
	private Label lblTitle;
	// id
	@FXML
	private Label lblId;

	@FXML
	private TextField txtId;

	// nome
	@FXML
	private Label lblNome;

	@FXML
	private Label lblNomeErro;

	@FXML
	private TextField txtNome;

	// email
	@FXML
	private Label lblEmail;

	@FXML
	private Label lblEmailErro;

	@FXML
	private TextField txtEmail;

	// telefone
	@FXML
	private Label lblTelefone;

	@FXML
	private Label lblTelefoneErro;

	@FXML
	private TextField txtTelefone;

	// senha
	@FXML
	private Label lblSenha;

	@FXML
	private Label lblSenhaErro;

	@FXML
	private PasswordField psfSenha;
	@FXML
	private Button btSalvar;

	@FXML
	private Button btExcluir;

	private List<DadoAlteradoListener> listeners = new ArrayList<>();

	@FXML
	private void onButtonSalvarAction(ActionEvent event) {
		try {
			getDadosCadastro();
			usuarioService.saveOrUpdate(usuario);
			fecharView(helper.getStageAtual(event));
			Alerts.showAlertInformations("Dados salvos!!");
			notificarListener();
		} catch (MySQLException e) {
			String msg = e.getMessage();
			if (msg.contains("Duplicate")) {
				if (msg.contains("uk_email"))
					msg = "Email ja cadastrado";
				else if (msg.contains("uk_telefone"))
					msg = "Telefone ja cadastrado";

				Alerts.showAlertError(msg);
			} else
				e.printStackTrace();

		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		} catch (MyRuntimeException e) {
			setMsgErros(e);
		}
	}

	@FXML
	private void onButtonExcluirAction(ActionEvent event) {
		try {
			Optional<ButtonType> bt = Alerts.mostrarConfirmacao("Deseja Excluir seu perfil?");
			if (bt.get() == ButtonType.OK) {
				getDadosCadastro();
				usuarioService.delete(usuario);
				fecharView(helper.getStageAtual(event));
				notificarListener();
				Alerts.showAlertInformations("Perifl Excluido!!");
			}
		} catch (MySQLException e) {
			e.printStackTrace();

		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		} catch (MyRuntimeException e) {
			setMsgErros(e);
		}
	}

	// NomeAction
	@FXML
	private void onKeyTxtNomeAction(KeyEvent event) {
		if (txtNome.getText().length() > 0)
			lblNome.setVisible(true);
		else
			lblNome.setVisible(false);
	}

	@FXML
	private void onMouseClickLblNomeAction(KeyEvent event) {
		lblNome.setVisible(false);
	}

	// EmailAction
	@FXML
	private void onKeyTxtEmailAction(KeyEvent event) {
		if (txtEmail.getText().length() > 0)
			lblEmail.setVisible(true);
		else
			lblEmail.setVisible(false);
	}

	@FXML
	private void onMouseClickLblEmailAction(KeyEvent event) {
		lblEmail.setVisible(false);
	}

	// TelefoneAction
	@FXML
	private void onKeyTxtTelefoneAction(KeyEvent event) {
		if (txtTelefone.getText().length() > 0)
			lblTelefone.setVisible(true);
		else
			lblTelefone.setVisible(false);
	}

	@FXML
	private void onMouseClickLblTelefoneAction(KeyEvent event) {
		lblTelefone.setVisible(false);
	}
	// senhaAction
		@FXML
		private void onKeyTxtSenhaAction(KeyEvent event) {
			if (psfSenha.getText().length() > 0)
				lblSenha.setVisible(true);
			else
				lblSenha.setVisible(false);
		}

		@FXML
		private void onMouseClickLblSenhaAction(KeyEvent event) {
			lblSenha.setVisible(false);
		}

	private void hidenControls() {
		lblEmail.setVisible(false);
		lblTelefone.setVisible(false);
		lblId.setVisible(false);
		lblNome.setVisible(false);
		lblSenha.setVisible(false);
	}

	protected void setVisibleBtExcluir(Boolean rsp) {
		btExcluir.setVisible(rsp);
	}

	public void setMsgErros(MyRuntimeException e) {
		lblNomeErro.setText((e.getErros().containsKey("nome") ? e.getErros().get("nome") : ""));
		lblEmailErro.setText((e.getErros().containsKey("email") ? e.getErros().get("email") : ""));
		lblTelefoneErro.setText((e.getErros().containsKey("telefone") ? e.getErros().get("telefone") : ""));
		lblSenhaErro.setText((e.getErros().containsKey("senha") ? e.getErros().get("senha") : ""));
	}

	private void getDadosCadastro() {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		MyRuntimeException runtimeEx = new MyRuntimeException();
		usuario.setUsuid(TryParseUtils.tryParseInt(txtId.getText()));

		if (txtEmail.getText().length() <= 0 || "".trim().equals(txtEmail.getText()) || txtEmail == null)
			runtimeEx.addErros("email", "Vazio");

		if (txtTelefone.getText().length() <= 0 || "".trim().equals(txtTelefone.getText()) || txtTelefone == null)
			runtimeEx.addErros("telefone", "Vazio");

		if (txtNome.getText().length() <= 0 || "".trim().equals(txtNome.getText()) || txtNome == null)
			runtimeEx.addErros("nome", "Vazio");

		if (psfSenha.getText().length() <= 0 || "".trim().equals(psfSenha.getText()) || psfSenha == null)
			runtimeEx.addErros("senha", "Vazio");
		
		if (runtimeEx.getErros().size() > 0)
			throw runtimeEx;

		usuario.setUsunome(txtNome.getText());
		usuario.setUsuemail(txtEmail.getText());
		usuario.setUsutelefone(txtTelefone.getText());
		usuario.setUsusenha(psfSenha.getText());
	}

	public void atualizarDadosFormCadastro() {
		if (usuario == null)
			throw new IllegalArgumentException("Usuario nulo");

		if (usuario.getUsuid() != null)
			txtId.setText(Integer.toString(usuario.getUsuid()));

		if (usuario.getUsunome() != null)
			txtNome.setText(usuario.getUsunome());

		if (usuario.getUsuemail() != null)
			txtEmail.setText(usuario.getUsuemail());

		if (usuario.getUsutelefone() != null)
			txtTelefone.setText(usuario.getUsutelefone());
	
		if (usuario.getUsusenha() != null)
			psfSenha.setText(usuario.getUsusenha());
	}
	

	public void setVisibleLabels() {
		if (txtTelefone.getText().length() > 0)
			lblTelefone.setVisible(true);

		if (txtNome.getText().length() > 0)
			lblNome.setVisible(true);

		if (txtEmail.getText().length() > 0)
			lblEmail.setVisible(true);
		
		if (psfSenha.getText().length() > 0)
			lblSenha.setVisible(true);
	}

	private void initializeNodesConstraints() {
		Costraints.onlyInteger(txtTelefone, false);
		Costraints.maxLength(txtEmail, 40, false);
		Costraints.maxLength(txtNome, 30, false);
		Costraints.maxLength(txtTelefone, 11, false);
		Costraints.maxLength(psfSenha, 12, false);
	}

	private void fecharView(Stage stageAtual) {
		stageAtual.close();
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

	public Label getLblTitle() {
		return lblTitle;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodesConstraints();
		hidenControls();
	}

	@Override
	public List<DadoAlteradoListener> getListeners() {
		return listeners;
	}

}
