package app;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXMLLoader;



import view.SongListController;


public class SongLib extends Application {
	@Override
	public void start(Stage primaryStage) 
	throws IOException {
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/view/SongListView.fxml"));
		AnchorPane root = (AnchorPane)loader.load();


		SongListController listController = loader.getController();
		listController.start(primaryStage);
		primaryStage.setResizable(false);
		Scene scene = new Scene(root, 600.0, 450.0);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}