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

	private String arrayDigOpe[] = { "%", "/", "*", "-", "+", "=", "," };

	private Boolean virgNumAntes;

	private Boolean virgDpsDec;

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
		iniciarAtributosaCalc();

	}

	private void iniciarAtributosaCalc() {
		strLblRes = "=";
		strLblOpe = "";
		strNum = "";
		virgNumAntes = false;
		virgDpsDec = true;
	}

	@FXML
	private void onBtNum0Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 0

	@FXML
	private void onBtNum1Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 1

	@FXML
	private void onBtNum2Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 2

	@FXML
	private void onBtNum3Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 3

	@FXML
	private void onBtNum4Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 4

	@FXML
	private void onBtNum5Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 5

	@FXML
	private void onBtNum6Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 6

	@FXML
	private void onBtNum7Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 7

	@FXML
	private void onBtNum8Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 8

	@FXML
	private void onBtNum9Action(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), true, null);
		setLblResAndOpe();
	}// end num 9

	@FXML
	private void onBtVirgulaAction(ActionEvent event) {
		if (checkSetVirgula()) {
			setStrNumOpeResOfBt(((Button) event.getSource()), null, null);
			setLblResAndOpe();
		}
	}// end virgula

	@FXML
	private void onBtOpeDivAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}/// end div

	@FXML
	private void onBtOpeMultAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}// end mult

	@FXML
	private void onBtOpeSubAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}// end sub

	@FXML
	private void onBtOpeAdcAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}// end adc

	@FXML
	private void onBtOpePorAction(ActionEvent event) {
		checkVirInStrNumInOpe();
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}// end por

	@FXML
	private void onBtIgualAction(ActionEvent event) {
		checkVirInStrNumInOpe();
		setStrNumOpeResOfBt(((Button) event.getSource()), false, true);
		setLblResAndOpe();
	}/// end igual

	@FXML
	private void onBtApagarLetraAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), null, null);
		setLblResAndOpe();
	}// end apagar letra

	@FXML
	private void onBtApagarTudoAction(ActionEvent event) {
		setStrNumOpeResOfBt(((Button) event.getSource()), null, null);
		setLblResAndOpe();
	}// end apagar tudo

	private void setStrNumOpeResOfBt(Button button, Boolean virgAntes, Boolean virgDps) {

		setStrCalc(button.getText(), isOpe(button.getText()));

		if (virgAntes != null)
			virgNumAntes = virgAntes;

		if (virgDps != null)
			virgDpsDec = virgDps;
	}

	/**
	 * seta os str de acordo com o KeyCode
	 * 
	 * @param code codigo do evento key
	 */
	private void setStrNumOpeResOfBtNums(KeyCode code) {
		String digito = getNameKeyCodeCalc(code);

		if (digito != null) {
			if (digito.equals(",")) {
				if (checkSetVirgula()) {
					setStrCalc(digito, isOpe(digito));
				}
			} else if (isOpe(digito)) {
				setStrCalc(digito, isOpe(digito));
				chekEventTeclVirg(digito, false, true);
			} else {
				setStrCalc(digito, isOpe(digito));
				chekEventTeclVirg(digito, true, null);
			}

		}
	}

	/**
	 * verifica se o digito é um operador aritimetico
	 * 
	 * @param digito
	 * @return
	 */
	private boolean isOpe(String digito) {
		for (int i = 0; i < arrayDigOpe.length - 1; i++) {
			if (digito.equals(getDigOpe(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * seta as strings realcionada a calculadora
	 * 
	 * @param str
	 * @param isOpe
	 */
	private void setStrCalc(String str, Boolean isOpe) {
		if (!isOpe) {
			strNum += str;
			strLblRes += str;
		} else {
			checkVirInStrNumInOpe();
		}
		strLblOpe += str;

	}

	private Boolean checkSetVirgula() {

		if (virgNumAntes != null && virgDpsDec != null) {
			if (virgNumAntes && virgDpsDec) {

				virgDpsDec = false;
				return true;
			}
		}

		return false;
	}

	/**
	 * Checa se o char no ultimo indice de strNum é uma virgula, caso for adiciona
	 * um 0 função deve ser chamada apenas quando for um operador aritimetico
	 */
	private void checkVirInStrNumInOpe() {
		if (strNum != null && !strNum.equals("") && strNum.length() > 0) {
			if (strNum.charAt(strNum.length() - 1) == ',') {
				strNum += "0";
				strLblRes += "0";
				strLblOpe += "0";
			}
		}
	}

	/**
	 * adiciona um EventHandler na scene
	 */
	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {
				setStrNumOpeResOfBtNums(event.getCode());
				setLblResAndOpe();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Retorna uma string com o digito do teclado, de acordo com codigo
	 * 
	 * @param code KeyCode
	 * @return
	 */
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
			return getDigOpe(4);

		else if (code == KeyCode.SUBTRACT)
			return getDigOpe(3);

		else if (code == KeyCode.MULTIPLY)
			return getDigOpe(2);

		else if (code == KeyCode.DIVIDE)
			return getDigOpe(1);

		else if (code == KeyCode.EQUALS)
			return getDigOpe(5);

		else if (code == KeyCode.PROPS)
			return getDigOpe(0);

		else if (code == KeyCode.COMMA)
			return getDigOpe(6);

		return null;

	}

	/**
	 * define os boolean da virgula de acordo com o codigo do teclado
	 * 
	 * @param code
	 * @param virgAntes
	 * @param virgDps
	 */
	private void chekEventTeclVirg(String code, Boolean virgAntes, Boolean virgDps) {

		if (code != null) {
			for (int i = 0; i < arrayDigOpe.length - 1; i++) {
				if (code == getDigOpe(i)) {
					if (virgAntes != null)
						virgNumAntes = virgAntes;

					if (virgDps != null)
						virgDpsDec = virgDps;
					return;
				}
			}

			if (virgAntes != null)
				virgNumAntes = virgAntes;

			if (virgDps != null)
				virgDpsDec = virgDps;
		}
	}

	/**
	 * @return 0 =[%], 1 =[/], 2 =[*], 3 =[-], 4 =[+], 5 [=], 6 =[,]
	 * 
	 */
	private String getDigOpe(int index) {

		return arrayDigOpe[index];
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
