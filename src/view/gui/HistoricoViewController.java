package view.gui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import modelo.entites.Historico;
import modelo.entites.Usuario;
import modelo.services.HistoricoService;
import view.gui.utils.Alerts;

public class HistoricoViewController implements Initializable {

	private Usuario usuario;

	private HistoricoService historicoService;

	private Historico historico;

	private Scene scenePai;

	@FXML
	private Label lblTitle;
	
	@FXML
	private TableView<Historico> tableViewHistorico;

	@FXML
	private TableColumn<Historico, String> tableColumnHisDado;

	@FXML
	private TableColumn<Historico, Historico> tableColumnBtRemover;

	/**
	 * Metodo para criar um botao de remocao para cada linha da minha tabela
	 * 
	 * @param tableColumn
	 * @param backgroundColor
	 * @param borderColor
	 * @param textFilColor
	 */
	private void iniciarBotaoRemover(TableColumn<Historico, Historico> tableColumn, Color textFilColor, //
			String borderColor, String backgroundColor) {
		tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumn.setCellFactory(param -> new TableCell<Historico, Historico>() {
			private final Button button = new Button("Remover");

			@Override
			protected void updateItem(Historico obj, boolean empty) {
				if (obj == null) {
					setGraphic(null);
					return;
				}
				button.setStyle("-fx-background-color: " + backgroundColor + ";-fx-border-color: " + borderColor
						+ "; -fx-border-radius: 5px;" + "-fx-background-radius: 4px");
				button.setTextFill(textFilColor);
				button.setCursor(Cursor.HAND);
				setGraphic(button);
				button.setOnAction(event -> removerHistorico(obj));

			}

		});
	}

	private void removerHistorico(Historico obj) {
		Optional<ButtonType> result = Alerts.mostrarConfirmacao("Deseja excluir esse Historico?");
		if (result.get() == ButtonType.OK) {
			if (historicoService == null)
				throw new IllegalArgumentException("Historico Service nulo");

			if (historico == null)
				throw new IllegalArgumentException("Historico nulo");

			historicoService.delete(obj);
			updateTableView();
		}
	}

	protected void updateTableView() {
		if (usuario == null)
			throw new IllegalArgumentException("Usuairo nulo");

		if (historicoService == null)
			throw new IllegalArgumentException("Historico Service nulo");

		if (historico == null)
			throw new IllegalArgumentException("Historico nulo");

		List<Historico> list = historicoService.findByUser(usuario);
		ObservableList<Historico> result = FXCollections.observableArrayList();
		result.addAll(list);
		if (result != null && result.size() > 0) {
			tableViewHistorico.setItems(result);
		}
	}

	private void inicializarElementos() {

	}

	public void inicializarTableView() {
		tableColumnHisDado.setCellValueFactory(new PropertyValueFactory<>("hisdado"));
//		Stage stage = (Stage) scenePai.getWindow(); // referencia para a janela
//		tableViewHistorico.prefHeightProperty().bind(stage.heightProperty());

		iniciarBotaoRemover(tableColumnBtRemover, Color.WHITE, "#fff", "#cd853f");

	}

	protected Usuario getUsuario() {
		return usuario;
	}

	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	protected HistoricoService getHistoricoService() {
		return historicoService;
	}

	protected void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	protected Historico getHistorico() {
		return historico;
	}

	protected void setHistorico(Historico historico) {
		this.historico = historico;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarElementos();
	}

}
