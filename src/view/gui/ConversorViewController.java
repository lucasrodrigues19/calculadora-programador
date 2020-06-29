package view.gui;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import conversor_numerico.modelo.ConversorEntradaDadosAtributos;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import view.gui.helpers.ViewHelper;
import view.gui.utils.costraints.Costraints;

public class ConversorViewController extends ConversorEntradaDadosAtributos implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private UsuarioService usuarioService;

	private Usuario usuario;

	// private HistoricoService historicoService;

	private Historico historico;

	private String lblOpeFontName;

	private Double lblOpeFontSize;

	private String lblResFontName;

	private Double lblResFontSize;

	private Scene scenePai;

	private Boolean jaSalvou;
	@FXML
	private Pane calculadoraPane;

	@FXML
	private Label lblStyleMais;

	@FXML
	private Label lblStyleMenos;

	@FXML
	private Label lblStyleIgual;

	@FXML
	private Label lblStyleProg;

	@FXML
	private ComboBox<String> cmbConverEntrada;

	@FXML
	private ComboBox<String> cmbConverSaida;

	@FXML
	ObservableList<String> obsConversores;

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
	private Button btLetra_A;

	@FXML
	private Button btLetra_B;

	@FXML
	private Button btLetra_C;

	@FXML
	private Button btLetra_D;

	@FXML
	private Button btLetra_E;

	@FXML
	private Button btLetra_F;

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

	@FXML
	ObservableList<String> obsHistorico;

	public ConversorViewController() {
		jaSalvou = false;
	}

	@FXML
	private void onBtAllAction(ActionEvent event) {
		Button bt = (Button) (event.getSource());
		lblOpe.setText(bt.getText());
		String str = lblOpe.getText();
		setDadosEntrada(str, this);
		setLblResAndOpe();
	}

	/**
	 * adiciona um EventHandler na scene
	 */
	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void cmbConverEntradaOnAction(ActionEvent event) {
		String valorCmb = cmbConverEntrada.getSelectionModel().getSelectedItem();
		setCostraintLblOperacoes(valorCmb);
		lblRes.setText(valorCmb);
	}

	public void cmbConverSaidaOnAction(ActionEvent event) {
		String valorCmb = cmbConverSaida.getSelectionModel().getSelectedItem();
		lblRes.setText(valorCmb);
	}

	private void escolherFuncao(String digito) {

	}

	private void setLblResAndOpe(String code) {

	}

	private void destacarIgual() {
		lblRes.setFont(Font.font(lblRes.getFont().getName(), FontWeight.BOLD, lblRes.getFont().getSize() + 3));
		lblOpe.setFont(Font.font(lblOpe.getFont().getName(), FontWeight.BOLD, lblRes.getFont().getSize() - 3));
		lblRes.setTextFill(Paint.valueOf("PERU"));
	}

	private void reniciarIgual() {
		lblOpe.setFont(Font.font(lblOpeFontName, FontWeight.BOLD, lblOpeFontSize));
		lblRes.setFont(Font.font(lblResFontName, FontWeight.BOLD, lblResFontSize));
		lblRes.setTextFill(Paint.valueOf("RED"));
	}

	private void getStyleFontLbls() {
		lblOpeFontName = lblOpe.getFont().getName();
		lblResFontName = lblRes.getFont().getName();
		lblOpeFontSize = lblOpe.getFont().getSize();
		lblResFontSize = lblOpe.getFont().getSize();
	}

	private void inicializarObservableHistorico() {
		obsHistorico = FXCollections.observableArrayList();
		obsHistorico.addListener((ListChangeListener<String>) listener -> {
			salvarHistorico();
		});
	}
	
	private void getDadosHistorico() {
		if (historico != null) {
			historico.setHisdado(lblOpe.getText() + "\n" + lblRes.getText());
			historico.setHisusuario(getUsuario());
		}
	}

	private void salvarHistorico() {
		if (obsHistorico != null)
			listHistorico.setItems(obsHistorico);

	}
	
	private void setObsHistorico() {
		if (historico != null)
			obsHistorico.add(historico.getHisdado());

	}
	private void setSaveHistorico(String code) {

	}

	private void showKeyCode(String code) {
		lblOpe.setText(code);
	}

	private void inicializarObservableConversores() {
		obsConversores = FXCollections.observableArrayList();
		setObsConversores();
		setCmbConversores();

	}

	private void setObsConversores() {
		obsConversores.addAll(Arrays.asList("Decimal", "Binario", "Hexa-Decimal"));
	}

	private void setCmbConversores() {
		cmbConverEntrada.getItems().addAll(obsConversores);
		cmbConverSaida.getItems().addAll(obsConversores);
		helper.setColorItemsComboBox(cmbConverEntrada, "#FFDEAD", "#cd853f");
		helper.setColorItemsComboBox(cmbConverSaida, "#FFDEAD", "#cd853f");
		cmbConverEntrada.getSelectionModel().selectFirst();
		cmbConverSaida.setValue("Binario");
	}

	private void setCostraintLblOperacoes(String valorCmb) {
		if ("Hexa-Decimal".equals(valorCmb))
			Costraints.anyChar(lblOpe,true);
		else {
			 Costraints.onlyInteger(lblOpe,true);
		}

	}
	private void setCostraintsInLabels() {
		String valorCmb = cmbConverEntrada.getValue();
		setCostraintLblOperacoes(valorCmb);
	}
	private void setLblResAndOpe() {
		lblRes.setText(getStrRes());
		lblOpe.setText(getStrOpe());
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
		getStyleFontLbls();
		inicializarObservableHistorico();
		inicializarObservableConversores();
		setCostraintsInLabels();
		
	}

}
