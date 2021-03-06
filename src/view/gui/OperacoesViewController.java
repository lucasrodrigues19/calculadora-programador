package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.HistoricoService;
import modelo.services.UsuarioService;
import view.gui.helpers.ViewHelper;
import view.gui.utils.Alerts;

public class OperacoesViewController implements Initializable {

	private ViewHelper helper = new ViewHelper();

	private UsuarioService usuarioService;

	private Usuario usuario;

	private HistoricoService historicoService;

	private Historico historico;

	private String btStyle;

	private Scene scenePai;

	@FXML
	private Label lblTitle;

	@FXML
	private ToolBar toolBarMenu;

	@FXML
	private Button btCalculadora;

	@FXML
	private Button btConversor;

	@FXML
	private Button btHistorico;

	@FXML
	private Button btSair;

	@FXML
	private void onBtCalculadoraAction(ActionEvent event) {
		try {
			helper.loadView("/view/gui/CalculadoraView.fxml", helper.getSceneAtual(event), 2,
					(CalculadoraViewController controller) -> {
						controller.setUsuario(getUsuario());
						controller.setUsuarioService(getUsuarioService());
						controller.setHistorico(getHistorico());
						controller.setHistoricoService(getHistoricoService());
						controller.setScenePai(helper.getSceneAtual(event));
						controller.setEventHandler();
						controller.iniciarAtributosaCalc(controller);
						controller.iniciarLabels();
						helper.setLblTitle(controller.getLblTitle(), "");
					});
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtConversorAction(ActionEvent event) {
		try {
			helper.loadView("/view/gui/ConversorView.fxml", helper.getSceneAtual(event), 2,
					(ConversorViewController controller) -> {
						controller.setUsuario(getUsuario());
						controller.setUsuarioService(getUsuarioService());
						controller.setHistorico(getHistorico());
						controller.setHistoricoService(getHistoricoService());
						controller.setScenePai(helper.getSceneAtual(event));
						controller.setEventHandler();
						controller.inicializarElementos(controller);
						helper.setLblTitle(controller.getLblTitle(), "");
					});
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onBtHistoricoAction(ActionEvent event) {
		try {
			helper.loadView("/view/gui/HistoricoView.fxml", helper.getSceneAtual(event), 2,
					(HistoricoViewController controller) -> {
						controller.setUsuario(getUsuario());
						controller.setHistorico(getHistorico());
						controller.setHistoricoService(getHistoricoService());
						controller.setScenePai(helper.getSceneAtual(event));
						controller.inicializarTableView();
						controller.updateTableView();
						helper.setLblTitle(controller.getLblTitle(), "");
					});
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onMouseBtCalculadoraHouver(MouseEvent event) {
		try {
			btStyle = btCalculadora.getStyle();
			btCalculadora.setStyle(btCalculadora.getStyle() + "-fx-background-color: gray");

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onMouseBtCalculadoraOut(MouseEvent event) {
		try {
			btCalculadora.setStyle(btStyle);

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onMouseBtConversorHouver(MouseEvent event) {
		try {
			btStyle = btConversor.getStyle();
			btConversor.setStyle(btConversor.getStyle() + "-fx-background-color: gray");

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onMouseBtConversorOut(MouseEvent event) {
		try {
			btConversor.setStyle(btStyle);

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onMouseBtHistoricoHouver(MouseEvent event) {
		try {
			btStyle = btHistorico.getStyle();
			btHistorico.setStyle(btHistorico.getStyle() + "-fx-background-color: gray");

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}
	}

	@FXML
	private void onMouseBtHistoricoOut(MouseEvent event) {
		try {
			btHistorico.setStyle(btStyle);

		} catch (Exception e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	@FXML
	private void onButtonSairAction(ActionEvent event) {
		try {
			helper.openMainView(helper.getStageAtual(event), Main.mainScene);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
		}

	}

	protected void hiddenBts() {
		if ((usuario != null && usuarioService != null) && (historico != null && historicoService != null)) {
			btHistorico.setVisible(true);
			btSair.setVisible(false);
		} else {
			btHistorico.setVisible(false);
			btSair.setVisible(true);
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

	public Label getLblTitle() {
		return lblTitle;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
