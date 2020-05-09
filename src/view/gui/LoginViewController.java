package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import ex.MyRuntimeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;
import view.gui.utils.Costraints;

public class LoginViewController implements Initializable {

	CalculadoraHelper helper = new CalculadoraHelper();
	MyRuntimeException runtimeEx;
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
	private void onBtLoginAction(ActionEvent event) {
		try {
			System.out.println(btLogin.getText());
			checkTextFields();
		} catch (MyRuntimeException e) {
			setMsgErros(e);
		}
	}
	@FXML
	private void onKeyTxtEmailAction(KeyEvent event) {
		try {
			if(txtEmail.getText().length() > 0)
				lblEmail.setVisible(true);
			else
				lblEmail.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}
	@FXML
	private void onMouseClickTxtEmailAction(MouseEvent event) {
		try {
				lblEmail.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}
	
	@FXML
	private void onMouseClickLblEmailAction(MouseEvent event) {
		try {
				lblEmail.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}
	@FXML
	private void onKeyTxtTelefoneAction(KeyEvent event) {
		try {
			if(txtTelefone.getText().length() > 0)
				lblTelefone.setVisible(true);
			else
				lblTelefone.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}
	@FXML
	private void onMouseClickTxtTelefoneAction(MouseEvent event) {
		try {
				lblTelefone.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}
	
	@FXML
	private void onMouseClickLblTelefoneAction(MouseEvent event) {
		try {
				lblTelefone.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	private void checkTextFields() {
		runtimeEx = new MyRuntimeException();
		
		if(txtEmail.getText().length() <= 0 || "".equals(txtEmail) || txtEmail == null) {
			runtimeEx.addErros("email", "Vazio");
		}else {
			lblErroEmail.setText("");
		}
		if(txtTelefone.getText().length() <= 0 || "".equals(txtTelefone) || txtTelefone== null) {
			runtimeEx.addErros("telefone", "Vazio");
		}else {
			lblErroTelefone.setText("");
		}
		if(runtimeEx.getErros().size() > 0)
			throw runtimeEx;
		
	}
	
	private void setMsgErros(MyRuntimeException erros) {
		if(erros.getErros().containsKey("telefone"))
		lblErroTelefone.setText(erros.getErros().get("telefone"));
		
		if(erros.getErros().containsKey("email"))
		lblErroEmail.setText(erros.getErros().get("email"));
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodesConstraints();
		hidenControls();
	}

}
