package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuAjuda;

	@FXML
	private MenuItem menuItemSobre;
	
	@FXML 
	private Button btLogin;
	
	@FXML 
	private Button btEntrar;
	
	@FXML
	private void onBtLoginAction() {
		System.out.println(btLogin.getText());
	}
	@FXML
	private void onBtEntrarAction() {
		System.out.println(btEntrar.getText());
	}
	@FXML
	private void onMenuItemSobreAction() {
		System.out.println(menuItemSobre.getText());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
