package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.ex.MySQLException;
import ex.MyException;
import ex.MyRuntimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import utils.CalculadoraUtils;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;
import view.gui.utils.Costraints;

public class CadastroViewController implements Initializable {

	private Usuario usuario;

	private UsuarioService usuarioService;

	private CalculadoraHelper helper = new CalculadoraHelper();
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

	@FXML
	private Button btSalvar;

	@FXML
	private void onButtonSalvarAction(ActionEvent event) {
		try {
			getDadosCadastro();
			usuarioService.saveOrUpdate(usuario);
			fecharView(helper.getStageAtual(event));
			Alerts.showAlertInformations("Dados salvos!!");

		} catch (MySQLException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
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

	private void hidenControls() {
		lblEmail.setVisible(false);
		lblTelefone.setVisible(false);
		lblId.setVisible(false);
		lblNome.setVisible(false);
	}

	public void setMsgErros(MyRuntimeException e) {
		lblNomeErro.setText((e.getErros().containsKey("nome") ? e.getErros().get("nome") : ""));
		lblEmailErro.setText((e.getErros().containsKey("email") ? e.getErros().get("email") : ""));
		lblTelefoneErro.setText((e.getErros().containsKey("telefone") ? e.getErros().get("telefone") : ""));
	}

	private synchronized void getDadosCadastro() {
		if (usuario == null)
			throw new IllegalArgumentException("usuario nulo");

		MyRuntimeException runtimeEx = new MyRuntimeException();
		usuario.setUsuid(CalculadoraUtils.tryParseInt(txtId.getText()));

		if (txtEmail.getText().length() <= 0 || "".trim().equals(txtEmail.getText()) || txtEmail == null)
			runtimeEx.addErros("email", "Vazio");

		if (txtTelefone.getText().length() <= 0 || "".trim().equals(txtTelefone.getText()) || txtTelefone == null)
			runtimeEx.addErros("telefone", "Vazio");

		if (txtNome.getText().length() <= 0 || "".trim().equals(txtNome.getText()) || txtNome == null)
			runtimeEx.addErros("nome", "Vazio");

		if (runtimeEx.getErros().size() > 0)
			throw runtimeEx;

		usuario.setUsunome(txtNome.getText());
		usuario.setUsuemail(txtEmail.getText());
		usuario.setUsutelefone(txtTelefone.getText());

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
	}

	public void setTextFields() {
		if (txtTelefone.getText().length() > 0)
			lblTelefone.setVisible(true);

		if (txtNome.getText().length() > 0)
			lblNome.setVisible(true);

		if (txtEmail.getText().length() > 0)
			lblEmail.setVisible(true);
	}

	private void initializeNodesConstraints() {
		Costraints.textFieldInteger(txtTelefone);
		Costraints.textFieldMaxLength(txtEmail, 40);
		Costraints.textFieldMaxLength(txtNome, 30);
		Costraints.textFieldMaxLength(txtTelefone, 11);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodesConstraints();
		hidenControls();
	}

}
