package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import view.gui.helper.ViewHelper;

public class CalculadoraViewController extends CalculadoraEntradaDadosAtributos implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private UsuarioService usuarioService;

	private Usuario usuario;

	// private HistoricoService historicoService;

	private String lblOpeFontName;
	
	private Double lblOpeFontSize;
	
	private String lblResFontName;
	
	private Double lblResFontSize;
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
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 0

	@FXML
	private void onBtNum1Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 1

	@FXML
	private void onBtNum2Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 2

	@FXML
	private void onBtNum3Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 3

	@FXML
	private void onBtNum4Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 4

	@FXML
	private void onBtNum5Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 5

	@FXML
	private void onBtNum6Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 6

	@FXML
	private void onBtNum7Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 7

	@FXML
	private void onBtNum8Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 8

	@FXML
	private void onBtNum9Action(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end num 9

	@FXML
	private void onBtVirgulaAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end virgula

	@FXML
	private void onBtOpeDivAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}/// end div

	@FXML
	private void onBtOpeMultAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end mult

	@FXML
	private void onBtOpeSubAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end sub

	@FXML
	private void onBtOpeAdcAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end adc

	@FXML
	private void onBtOpePorAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end por

	@FXML
	private void onBtIgualAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}/// end igual

	@FXML
	private void onBtApagarLetraAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end apagar letra

	@FXML
	private void onBtApagarTudoAction(ActionEvent event) {
		setEntradaDados(this, ((Button) event.getSource()));
		setLblResAndOpe(((Button) event.getSource()).getText());
	}// end apagar tudo

	/**
	 * adiciona um EventHandler na scene
	 */
	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {
				setEntradaDados(this, event.getCode());
				setLblResAndOpe(event.getCode().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void setLblResAndOpe(String code) {
		lblOpe.setText(getStrLblOpe());
		lblRes.setText(getStrLblRes());
		if(code.equals("=") && !getStrNum().equals(""))
			destacarIgual();
		else 
			reniciarIgual();
		
	}
	private void destacarIgual() {
		lblRes.setFont(Font.font(lblRes.getFont().getName(),FontWeight.BOLD,lblRes.getFont().getSize()+3));
		lblOpe.setFont(Font.font(lblOpe.getFont().getName(),FontWeight.BOLD,lblRes.getFont().getSize()-3));
		lblRes.setTextFill(Paint.valueOf("PERU"));
	}
	private void reniciarIgual() {
		lblOpe.setFont(Font.font(lblOpeFontName, FontWeight.BOLD, lblOpeFontSize));
		lblRes.setFont(Font.font(lblResFontName, FontWeight.BOLD, lblResFontSize));
		lblRes.setTextFill(Paint.valueOf("RED"));
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
		getStyleFontLbls();
	}

	private void hiddenLogo() {
		lblStyleMais.setVisible(false);
		lblStyleMenos.setVisible(false);
		lblStyleIgual.setVisible(false);
		lblStyleProg.setVisible(false);
	}
	private void getStyleFontLbls() {
		lblOpeFontName = lblOpe.getFont().getName();
		lblResFontName = lblRes.getFont().getName();
		lblOpeFontSize = lblOpe.getFont().getSize();
		lblResFontSize = lblOpe.getFont().getSize();
	}
}
