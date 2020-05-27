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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import view.gui.helper.ViewHelper;

public class CalculadoraViewController implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private UsuarioService usuarioService;

	private Usuario usuario;

	// private HistoricoService historicoService;

	@FXML
	private Pane calculadoraPane;

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
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 0

	@FXML
	private void onBtNum1Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 1

	@FXML
	private void onBtNum2Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 2

	@FXML
	private void onBtNum3Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 3

	@FXML
	private void onBtNum4Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 4

	@FXML
	private void onBtNum5Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 5

	@FXML
	private void onBtNum6Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 6

	@FXML
	private void onBtNum7Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 7

	@FXML
	private void onBtNum8Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 8

	@FXML
	private void onBtNum9Action(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 9

	@FXML
	private void onBtVirgulaAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end virgula

	@FXML
	private void onBtOpeDivAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}/// end div

	@FXML
	private void onBtOpeMultAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end mult

	@FXML
	private void onBtOpeSubAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end sub

	@FXML
	private void onBtOpeAdcAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end adc

	@FXML
	private void onBtOpePorAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end por

	@FXML
	private void onBtIgualAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}/// end igual

	@FXML
	private void onBtApagarLetraAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end apagar letra

	@FXML
	private void onBtApagarTudoAction(ActionEvent event) {
		setStrNumOpeResOfBtNums(((Button) event.getSource()));
		setLblResAndOpe();
	}// end apagar tudo

	private void setStrNumOpeResOfBtNums(Button button) {
		strNum += button.getText();
		strLblRes += button.getText();
		strLblOpe += button.getText();
	}

	private void setStrNumOpeResOfBtNums(KeyCode code) {
		String digito = getNameKeyCodeCalc(code);

		if (digito != null) {
			strNum += digito;
			strLblRes += digito;
			strLblOpe += digito;
		}
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

	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {
				setStrNumOpeResOfBtNums(event.getCode());
				setLblResAndOpe();
			} catch (Exception ex) {
			}
		});
	}

	private String getNameKeyCodeCalc(KeyCode code) {
		if (code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1)
			return KeyCode.DIGIT1.getName();

		else if (code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2)
			return KeyCode.DIGIT2.getName();

		else if (code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3)
			return KeyCode.DIGIT3.getName();

		else if (code == KeyCode.DIGIT4 || code == KeyCode.NUMPAD4)
			return KeyCode.DIGIT4.getName();

		else if (code == KeyCode.DIGIT5 || code == KeyCode.NUMPAD5)
			return KeyCode.DIGIT5.getName();

		else if (code == KeyCode.DIGIT6 || code == KeyCode.NUMPAD6)
			return KeyCode.DIGIT6.getName();

		else if (code == KeyCode.DIGIT7 || code == KeyCode.NUMPAD7)
			return KeyCode.DIGIT7.getName();

		else if (code == KeyCode.DIGIT8 || code == KeyCode.NUMPAD8)
			return KeyCode.DIGIT8.getName();

		else if (code == KeyCode.DIGIT9 || code == KeyCode.NUMPAD9)
			return KeyCode.DIGIT9.getName();

		else if (code == KeyCode.DIGIT0 || code == KeyCode.NUMPAD0)
			return KeyCode.DIGIT0.getName();

		else if (code == KeyCode.ADD)
			return "+";

		else if (code == KeyCode.SUBTRACT)
			return "-";

		else if (code == KeyCode.MULTIPLY)
			return "*";

		else if (code == KeyCode.DIVIDE)
			return "/";

		else if (code == KeyCode.EQUALS)
			return "=";

		else if (code == KeyCode.PROPS)
			return "%";
		else if (code == KeyCode.COMMA)
			return ",";

		return null;

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
