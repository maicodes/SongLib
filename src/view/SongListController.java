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
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import classes.Song;


public class SongListController {
	
	@FXML         
	ListView<String> listView;  
	@FXML
	TextField title;
	@FXML
	TextField artist;
	@FXML
	TextField album;
	@FXML
	TextField year;
	
//	Stage window;
//	Scene addScene;
//	Scene editScene;

	private ObservableList<String> obsList;              

	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList 
		//window = mainStage;
		obsList = FXCollections.observableArrayList(                                                              
				"Patriots",
				"49ers",
				"Rams",
				"Packers",
				"Colts",
				"Cowboys",
				"Broncos",
				"Vikings",
				"Dolphins",
				"Titans",
				"Seahawks",
				"Steelers",
				"Jaguars"); 

		listView.setItems(obsList); 
		
		// select the first item
	      listView.getSelectionModel().select(0);
	      title.setText(obsList.get(0));  
	      // set listener for the items
	      listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> 
	               showItem(mainStage,  listView.getSelectionModel().getSelectedIndex() ));
	     

	}
	
	private void showItem(Stage mainStage, int idx) {                
		 title.setText(obsList.get(idx));   
	}
	
	@FXML
	private void moveToEditSong(ActionEvent event) {
		try {
			GridPane editView = FXMLLoader.load(getClass().getResource("/view/EditView.fxml"));
			Scene newScene = new Scene (editView);
			Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();
			
			window.setScene(newScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	private void moveToAddSong (ActionEvent event) {
		try {
			GridPane editView = FXMLLoader.load(getClass().getResource("/view/AddView.fxml"));
			Scene newScene = new Scene (editView);
			Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();
			
			window.setScene(newScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@FXML
	private void addSong(ObservableList<Song> obsList) {
		String strTitle = title.getText();
		String strArtist = artist.getText();
		String strAlbum = album.getText();
		String strYear = year.getText();
		Song song = new Song(strTitle, strArtist, strAlbum, strYear);
		
		/*
		 * Sorting algo here using obsList
		 */	
		
		return obsList;		
	}
	
	@FXML
	private void deleteSong() {}

}
