package view.gui.helper;

import java.io.IOException;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CalculadoraHelper {

	/**
	 * Metodo responsavel por carregar qualquer view
	 * 
	 * @param <T>
	 * @param path
	 * @param mainScene
	 * @param execut
	 */
	public <T> void loadView(String path, Scene mainScene, Consumer<T> execut) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		try {
			VBox newView = loader.load();
			ScrollPane scrolPane = ((ScrollPane) mainScene.getRoot());

			VBox mainVBox = (VBox) scrolPane.getContent();
			Node nodeMain = mainVBox.getChildren().get(0);

			// limpa os filhos da mainView
			mainVBox.getChildren().clear();
			// adiciona o primeiro filho
			mainVBox.getChildren().add(nodeMain);
			// adiciona todos os filhos na view carregada
			mainVBox.getChildren().addAll(newView.getChildren());

			// executa a função passada como parametro
			if (execut != null) {
				T controller = loader.getController();
				execut.accept(controller);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo responsavel por carregar a Mainview
	 * 
	 * @param path
	 * @param mainScene
	 */
	public void loadMainView(String path, Scene mainScene) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		try {
			ScrollPane scrollPane = loader.load();
			scrollPane.setFitToHeight(true); // para que o scrollPane acompanhe o conteudo
			scrollPane.setFitToWidth(true);

			mainScene.setRoot(scrollPane);
		} catch (Exception e) {

		}
	}

	/**
	 * Metodo responsavel para gerar uma caixa de dialog, a mesma aparecera em cima
	 * da view Pai
	 * 
	 * @param <T>
	 * @param path        caminho da view
	 * @param with
	 * @param executar    função que executara algum metodo da controle da view
	 *                    carregada
	 * @param parentStage stage pai que a dialog aparecera em cima
	 */
	public <T> void loadViewDialog(String path, Consumer<T> executar, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			VBox vbox = loader.load();

			if (executar != null) {
				T controller = loader.getController();
				executar.accept(controller);
			}
			Scene scene = new Scene(vbox);
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);// a janela não pode ser redimensionada
			dialogStage.initOwner(parentStage);// stage pai do dialogStage
			dialogStage.initModality(Modality.WINDOW_MODAL); // vai dizer o comportamento da janela
			dialogStage.showAndWait();// vai abrir em cima do stage pai, e vai ter um comportmento modal
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método responsavel para pegar o stage atual quando o controle dispara o
	 * evento(Action)
	 * 
	 * @param event
	 * @return
	 */
	public Stage getStageAtual(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Método responsavel para pegar o stage atual quando o controle dispara o
	 * evento(Mouse)
	 * 
	 * @param event
	 * @return
	 */
	public Stage getStageAtual(MouseEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Método responsavel para pegar o stage atual atraves do scene
	 * @param scene
	 * @return
	 */
	public Stage getStageAtual(Scene scene) {
		return (Stage) scene.getWindow();
	}

}
