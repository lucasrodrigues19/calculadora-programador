package application;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();

			scrollPane.setFitToHeight(true); // para que o scrollPane acompanhe o conteudo
			scrollPane.setFitToWidth(true);

			Scene scene = new Scene(scrollPane);
			mainScene = scene;
			primaryStage.setScene(scene);
			// primaryStage.setTitle("C - P");
			primaryStage.show();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
