package view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.HistoricoService;

public class HistoricoViewController implements Initializable {

	private Usuario usuario;

	private HistoricoService historicoService;

	private Historico historico;

	@FXML
	private TableView<Historico> tableViewHistorico;

	@FXML
	private TableColumn<Historico, String> tableColumnHisDado;

	@FXML
	private TableColumn<Historico, String> tableColumnUsu;

	@FXML
	private TableColumn<Historico, Historico> tableColumnBtRemover;

	
	
	
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public HistoricoService getHistoricoService() {
		return historicoService;
	}

	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
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
