package view;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import classes.Song;

public class EditController {
	
	
	@FXML
	void editSong() {};
	
		@FXML
	void backToList(ActionEvent event) {
		try {
			AnchorPane editView = FXMLLoader.load(getClass().getResource("/view/SongListView.fxml"));
			Scene newScene = new Scene (editView);
			Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();
			
			window.setScene(newScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
}
