package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import calculadora_aritimetica.modelo.service.CalculadoraAritimeticaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import view.gui.helper.ViewHelper;

public class CalculadoraViewController extends CalculadoraEntradaDadosAtributos implements Initializable {

	private ViewHelper helper = new ViewHelper();
	
	private CalculadoraAritimeticaService calcService = new CalculadoraAritimeticaService(this);

	private UsuarioService usuarioService;

	private Usuario usuario;

	// private HistoricoService historicoService;

	@FXML
	private Pane calculadoraPane;

	private Scene scenePai;

	private Historico historico;

	@FXML
	private Label lblStyleMais;

	@FXML
	private Label lblStyleMenos;

	@FXML
	private Label lblStyleIgual;

	@FXML
	private Label lblStyleProg;

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

	}

	@FXML
	private void onBtNum0Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 0

	@FXML
	private void onBtNum1Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 1

	@FXML
	private void onBtNum2Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 2

	@FXML
	private void onBtNum3Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 3

	@FXML
	private void onBtNum4Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 4

	@FXML
	private void onBtNum5Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 5

	@FXML
	private void onBtNum6Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 6

	@FXML
	private void onBtNum7Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 7

	@FXML
	private void onBtNum8Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 8

	@FXML
	private void onBtNum9Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end num 9

	@FXML
	private void onBtVirgulaAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end virgula

	@FXML
	private void onBtOpeDivAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}/// end div

	@FXML
	private void onBtOpeMultAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end mult

	@FXML
	private void onBtOpeSubAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end sub

	@FXML
	private void onBtOpeAdcAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end adc

	@FXML
	private void onBtOpePorAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end por

	@FXML
	private void onBtIgualAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}/// end igual

	@FXML
	private void onBtApagarLetraAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end apagar letra

	@FXML
	private void onBtApagarTudoAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe();
	}// end apagar tudo

	/**
	 * adiciona um EventHandler na scene
	 */
	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {
				setEntradaDados(this, event.getCode());
				setLblResAndOpe();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void setLblResAndOpe() {
		lblOpe.setText(getStrLblOpe());
		lblRes.setText(getStrLblRes());
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
		hiddenLogo();
		iniciarAtributosaCalc(this);
	}

	private void hiddenLogo() {
		lblStyleMais.setVisible(false);
		lblStyleMenos.setVisible(false);
		lblStyleIgual.setVisible(false);
		lblStyleProg.setVisible(false);
	}
}
