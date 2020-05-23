package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.UsuarioService;
import view.gui.helper.CalculadoraHelper;
import view.gui.utils.Alerts;

public class OperacoesViewController implements Initializable {
	
	private CalculadoraHelper helper = new CalculadoraHelper();
	
	private UsuarioService usuarioService;

	private Usuario usuario;

	//private HistoricoService historicoService;

	private Scene scenePai;
	
	private Historico historico;
	
	@FXML
	private ToolBar toolBarMenu;
	
	@FXML
	private Button btCalculadora;
	
	@FXML
	private Button btConversor;
	
	@FXML
	private Button btHistorico;
	
	@FXML
	private void onBtCalculadora(ActionEvent event) {
		try {
			helper.loadView("/view/gui/CalculadoraView.fxml", helper.getSceneAtual(event), null);
		} catch (MyException e) {
			e.printStackTrace();
			Alerts.showAlertError(e.getMessage());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
