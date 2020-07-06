package view.gui.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ex.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Classe criada para auxiliar as views
 * 
 * @author lucas.rodrigues
 *
 */
public class ViewHelper {

	/**
	 * Metodo responsavel por carregar qualquer view, no scene da view pai
	 * 
	 * @param <T>
	 * @param path            Caminho da view
	 * @param mainScene       Secne da view pai que, a view filha sera carregada
	 * @param qtdNodesViewPai quantidades de elementos que serao pegos da scena pai,
	 *                        de cima para baixo, em ordem
	 * @param execut          função que sera executada na controller da view
	 *                        carregada
	 * @throws MyException
	 */
	public synchronized <T> void loadView(String path, Scene mainScene, int qtdNodesViewPai, Consumer<T> execut)
			throws MyException {
		if (path == null || mainScene == null)
			throw new MyException("Parametros nulos");

		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		List<Node> nodesMain = new ArrayList<Node>();
		try {
			VBox newView = loader.load();
			ScrollPane scrolPane = null;
			VBox mainVBox = null;
			if (mainScene.getRoot() instanceof ScrollPane) {
				scrolPane = (ScrollPane) mainScene.getRoot();
				mainVBox = (VBox) scrolPane.getContent();
			}else if  (mainScene.getRoot() instanceof VBox) {
				mainVBox = (VBox) mainScene.getRoot();
			}

			for (int i = 0; i < qtdNodesViewPai; i++) {
				nodesMain.add(mainVBox.getChildren().get(i));
			}

			// limpa os filhos da view pai
			mainVBox.getChildren().clear();
			// adiciona os filhos da view pai
			for (Node nodeMain : nodesMain)
				mainVBox.getChildren().add(nodeMain);
			// adiciona todos os filhos na view carregada
			mainVBox.getChildren().addAll(newView.getChildren());

			// executa a função passada como parametro
			if (execut != null) {
				T controller = loader.getController();
				execut.accept(controller);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

	/**
	 * Metodo responsavel por carregar uma view e adicionar o content(Root) no scene
	 * 
	 * @param <T>
	 * 
	 * @param path
	 * @param mainScene
	 * @param execut
	 */
	public synchronized <T> void backView(String path, Scene mainScene, Consumer<T> execut) throws MyException {
		if (path == null || mainScene == null)
			throw new MyException("Parametros nulos");

		FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
		try {
			ScrollPane scrollPane = loader.load();

			scrollPane.setFitToHeight(true); // para que o scrollPane acompanhe o conteudo
			scrollPane.setFitToWidth(true);
			if (execut != null) {
				T controller = loader.getController();
				execut.accept(controller);
			}
			mainScene.setRoot(scrollPane);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
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
	public synchronized <T> void loadViewDialog(String path, Consumer<T> execut, Stage parentStage) throws MyException {
		if (path == null || parentStage == null)
			throw new MyException("Parametros nulos");

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			VBox vbox = loader.load();

			if (execut != null) {
				T controller = loader.getController();
				execut.accept(controller);
			}
			Scene scene = new Scene(vbox);
			Stage dialogStage = new Stage();
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);// a janela não pode ser redimensionada
			dialogStage.initOwner(parentStage);// stage pai do dialogStage
			dialogStage.initModality(Modality.WINDOW_MODAL); // vai dizer o comportamento da janela
			dialogStage.showAndWait();// vai abrir em cima do stage pai, e vai ter um comportmento modal
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

	/**
	 * Método responsavel para pegar o stage atual quando o controle dispara o
	 * evento(Action)
	 * 
	 * @param event
	 * @return
	 */
	public Stage getStageAtual(ActionEvent event) throws MyException {
		if (event == null)
			throw new MyException("Parametro nulo");

		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Método responsavel para pegar o stage atual quando o controle dispara o
	 * evento(Mouse)
	 * 
	 * @param event
	 * @return
	 */
	public Stage getStageAtual(MouseEvent event) throws MyException {
		if (event == null)
			throw new MyException("Parametro nulo");

		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	/**
	 * Método responsavel para pegar o stage atual atraves do scene
	 * 
	 * @param scene
	 * @return
	 */
	public Stage getStageAtual(Scene scene) throws MyException {
		if (scene == null)
			throw new MyException("Parametro nulo");

		return (Stage) scene.getWindow();
	}

	/**
	 * Retorna a scene da view de acordo com o evento
	 * 
	 * @param event ActionEvent
	 * @return
	 */
	public Scene getSceneAtual(ActionEvent event) {
		return (Scene) ((Node) event.getSource()).getScene();
	}

	/**
	 * Retorna para a view Pai e fechar a view Atual
	 * 
	 * @param stageThis stage da sua view atual
	 * @param mainScene
	 * @throws MyException
	 */
	public void openMainView(Stage stageThis, Scene mainScene) throws MyException {
		try {
			stageThis.close();
			Stage stageParent = new Stage();
			stageParent.setScene(mainScene);
			stageParent.show();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

	/**
	 * Abre uma nova view e fecha a atual
	 * 
	 * @param <T>
	 * @param pathParent caminho da view para abrir
	 * @param stageThis  stage da view para fechar
	 * @throws MyException
	 */
	public <T> void openParentView(String pathParent, Stage stageThis, Consumer<T> executar) throws MyException {

		try {

			FXMLLoader loaderParent = new FXMLLoader(getClass().getResource(pathParent));
			Parent nodeParent = loaderParent.load();

			if (nodeParent instanceof ScrollPane) {
				((ScrollPane) nodeParent).setFitToHeight(true); // para que o scrollPane acompanhe o conteudo
				((ScrollPane) nodeParent).setFitToWidth(true);
			}
			// executa a fuçao passada como parametro da controler que ira ser aberta
			if (executar != null) {
				T controller = loaderParent.getController();
				executar.accept(controller);
			}
			Scene sceneParent = new Scene(nodeParent);

			Stage stageParent = new Stage();
			stageParent.setScene(sceneParent);
			stageParent.setResizable(false);// para não ser maximizada
			// stageParent.initStyle(StageStyle.UNDECORATED); //desbilita os 3
			// botoes(maximizar,minimizar e fechar)
			stageThis.close();
			stageParent.show();

		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(e.getMessage());
		}

	}

	/**
	 * Define a cor dos items da ComboBox
	 * @param <T>
	 * 
	 * @param cmb
	 * @param background_color
	 * @param text_fill_color
	 */
	public <T> void setColorItemsComboBox(ComboBox<T> cmb, String background_color, String text_fill_color) {
		Callback<ListView<T>, ListCell<T>> factory = lv -> new ListCell<T>() {
			@Override
			protected void updateItem(T item, boolean empty) {

				super.updateItem(item, empty);
				if (empty || item == null)
					setStyle("-fx-text-fill:" + text_fill_color + "; -fx-background-color:" + background_color);
				else {
					setStyle("-fx-text-fill:" + text_fill_color + "; -fx-background-color:" + background_color);
					setText(item.toString());
				}
			}
		};
		cmb.setCellFactory(factory);
		cmb.setButtonCell(factory.call(null));

	}

	/**
	 * retorna o item selcionado de uma Combo
	 * 
	 * @param <T>
	 * @param cmb
	 * @return
	 */
	public <T> T getItemComboBox(ComboBox<T> cmb) {
		return cmb.getSelectionModel().getSelectedItem();
	}

}
