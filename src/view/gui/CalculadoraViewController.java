package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import view.gui.helper.CalculadoraHelper;

public class CalculadoraViewController implements Initializable {
	
	private CalculadoraHelper helper = new CalculadoraHelper();
	
	private UsuarioService usuarioService;

	private Usuario usuario;

	//private HistoricoService historicoService;

	private Scene scenePai;
	
	private Historico historico;
	
	private String strLblRes;
	
	private String strLblOpe;
	
	private String strNum;
	
	@FXML
	private Button btNum1;
	
	@FXML
	private Button btNum2;
	
	@FXML
	private Button btNum3;
	
	@FXML
	private Button btNum4;
	
	@FXML
	private Button btNum5;
	
	@FXML
	private Button btNum6;
	
	@FXML
	private Button btNum7;
	
	@FXML
	private Button btNum8;
	
	@FXML
	private Button btNum9;
	
	@FXML
	private Button btNum0;
	
	@FXML
	private Button btVirgula;
	
	@FXML
	private Button btOpeDiv;
	
	@FXML
	private Button btOpeMult;
	
	@FXML
	private Button btOpeSub;
	
	@FXML
	private Button btOpeAdc;
	
	@FXML
	private Button btOpePor;
	
	@FXML
	private Button btIgual;
	
	@FXML
	private Button btApagarLetra;
	
	@FXML
	private Button btApagarTudo;
	
	@FXML
	private Label lblRes;
	
	@FXML
	private Label lblOpe;
	
	@FXML
	private ListView<String> listHistorico;
	
	
	public CalculadoraViewController() {
		strLblRes = "=";
		strLblOpe = "";
		strNum = "";
	}
	@FXML
	private void onBtNum0Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum0);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum1Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum1);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum2Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum2);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum3Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum3);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum4Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum4);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum5Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum5);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum6Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum6);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum7Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum7);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum8Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum8);
		setLblResAndOpe();
	}
	@FXML
	private void onBtNum9Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(btNum9);
		setLblResAndOpe();
	}
	
	
	
	private void setStrNumOpeResOfBtNums(Button button) {
		strNum+=button.getText();
		strLblRes+=button.getText();
		strLblOpe+= button.getText();
	}
	public void setLblResAndOpe() {
		lblOpe.setText(strLblOpe);
		lblRes.setText(strLblRes);
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

	public Scene getScenePai() {
		return scenePai;
	}

	public void setScenePai(Scene scenePai) {
		this.scenePai = scenePai;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}

}
