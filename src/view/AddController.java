package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import classes.Song;
import classes.SongList;

/* 
 * Phi Hoang
 * Mai Le
 * Software Methodology
*/

public class AddController {
	@FXML
	Button addBtn;
	
	@FXML
	TextField songname;
	@FXML
	TextField artist;
	@FXML
	TextField album;
	@FXML
	TextField year;

	Song newSong;
	@FXML
	void addSong(ActionEvent event) {
		String title = songname.getText();
		String artistName = artist.getText();
		String albumName = album.getText();
		String yearPublic = year.getText();
		Stage stage = (Stage) addBtn.getScene().getWindow();
		System.out.println(title + "\n" + artistName);
		if ( songname == null || artist == null || title.trim().isEmpty()  || artistName.trim().isEmpty()  ) {
			showAlert2(stage);
			return;
		}
		newSong = new Song(title, artistName, albumName, yearPublic);
		boolean isAdded = SongList.addNewSong(newSong);
		//System.out.println(isAdded + "");
		if ( !isAdded ) {
			showAlert(stage, false);
		}
		else {
			showAlert(stage, true);
		}
	}
	
	private void showAlert(Stage mainStage, boolean isAdded) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("Add New Song");
	      String content ="";
	      if ( !isAdded ) {
	    	   alert.setHeaderText(
	    	           "Cannot add new song");
	    	            content = "This song has already existed";
	      } else {
	    	   alert.setHeaderText(
	    	           "Song is added");
	    	   content = "OK!";
	      }
	          alert.setContentText(content);
	          alert.showAndWait();
	          
	   }
	
	private void showAlert2(Stage mainStage) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("Add New Song");
	      alert.setHeaderText(
	    	           "Cannot add new song");
	      String  content = "Title and Artist are required!";
	   
	      alert.setContentText(content);
	      alert.showAndWait();
	          
	   }
	
	@FXML
	void backToList(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(
					getClass().getResource("/view/SongListView.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();
			SongListController listController = loader.getController();
			if ( newSong != null ) {
				listController.index = SongList.getSongs().indexOf(newSong);
			}
			listController.isStart = false;
			listController.start(window);
//			AnchorPane editView = FXMLLoader.load(getClass().getResource("/view/SongListView.fxml"));
			Scene newScene = new Scene (root);
			
			
			window.setScene(newScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}