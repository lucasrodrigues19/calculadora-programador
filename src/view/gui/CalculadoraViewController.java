package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import calculadora_aritimetica.modelo.CalculadoraEntradaDadosAtributos;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import modelo.services.HistoricoService;
import modelo.services.UsuarioService;
import view.gui.helpers.ViewHelper;

public class CalculadoraViewController extends CalculadoraEntradaDadosAtributos implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private UsuarioService usuarioService;

	private Usuario usuario;

	private HistoricoService historicoService;

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
	private Label lblTitle;
	
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

	@FXML
	ObservableList<String> obsHistorico;

	public CalculadoraViewController() {
		jaSalvou = false;
	}

	@FXML
	private void onBtAllAction(ActionEvent event) {
		String digito = ((Button) event.getSource()).getText();
		escolherFuncao(digito);
	}

	/**
	 * adiciona um EventHandler na scene
	 */
	public void setEventHandler() {
		scenePai.addEventHandler((KeyEvent.KEY_PRESSED), (KeyEvent event) -> {
			try {
				String digito = getNameKeyCodeCalc(this, event.getCode());
				escolherFuncao(digito);
				// showKeyCode(event.getCode().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void escolherFuncao(String digito) {
		if (digito != null) {
			if (digito.equals(","))
				digito = ".";

			if (digito.equals("X") || digito.equals("C")) {
				limparDados(this, digito);
			} else {
				setEntradaDados(this, digito);
			}
			if (!digito.equals("."))
				setSaveHistorico(digito);

			setLblResAndOpe(digito);
		}
	}

	private void setLblResAndOpe(String code) {
		if (!getConContinue()) {
			lblOpe.setText(getStrLblOpe());
			lblRes.setText(getStrLblRes());
		}
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

	private void inicializarObservableHistorico() {
		obsHistorico = FXCollections.observableArrayList();
		obsHistorico.addListener((ListChangeListener<String>) listener -> {
			salvarHistorico();
		});
	}

	private void salvarHistorico() {
		if (historico != null && usuario != null)
			historicoService.save(historico);

		if (obsHistorico != null)
			listHistorico.setItems(obsHistorico);

	}

	private void setObsHistorico() {
		if (historico != null)
			obsHistorico.add(historico.getHisdado());

	}

	private void getDadosHistorico() {
		if (historico != null) {
			historico.setHisdado(lblOpe.getText() + "\n" + lblRes.getText());
			if (usuario != null)
				historico.setHisusuario(getUsuario());
		}
	}

	private void setSaveHistorico(String code) {
		if (code != null) {
			if (code.equals("=") && !getStrNum().equals("")) {
				if (!jaSalvou) {
					destacarIgual();
					getDadosHistorico();
					setObsHistorico();
					jaSalvou = true;
				}
			} else {
				reniciarIgual();
				jaSalvou = false;
			}
		}
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

	public Label getLblTitle() {
		return lblTitle;
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

	public HistoricoService getHistoricoService() {
		return historicoService;
	}

	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hiddenLogo();
		getStyleFontLbls();
		inicializarObservableHistorico();
	}

	public void iniciarLabels() {
		lblOpe.setText(getStrLblOpe());
		lblRes.setText(getStrLblRes());
	}

	private void showKeyCode(String code) {
		lblOpe.setText(code);
	}

}
