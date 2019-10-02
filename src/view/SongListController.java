package view;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import java.util.TreeMap;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import classes.SongList;


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

	private ObservableList<Song> obsList;  
	private ObservableList<String> obsListTitle;
//	Currently selected item
	public static Song currItem;
	
	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList 
		obsListTitle = SongList.getTitlesAndArtists();
		obsList = SongList.getSongs();

		listView.setItems(obsListTitle); 
		
		// select the first item
		if ( obsList.size() > 0 ) {
			 listView.getSelectionModel().select(0);
		     title.setText(obsList.get(0).getTitle());  
		     artist.setText(obsList.get(0).getArtist());
			 album.setText(obsList.get(0).getAlbum());
			 year.setText(obsList.get(0).getYear());
			 currItem = obsList.get(0);
		}
	      // set listener for the items
	      listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener(
	           (obs, oldVal, newVal) -> 
	               showItem(mainStage,  listView.getSelectionModel().getSelectedIndex() ));
	}	
	
	private void showItem(Stage mainStage, int idx) {                
		 title.setText(obsList.get(idx).getTitle());  
		 artist.setText(obsList.get(idx).getArtist());
		 album.setText(obsList.get(idx).getAlbum());
		 year.setText(obsList.get(idx).getYear());
		 currItem = obsList.get(idx);
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
	private void deleteSong(ActionEvent event) {
		SongList.deleteSongObj(currItem);
//		Removes from view
		final int selectedIdx = listView.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) { 
        	final int newSelectedIdx =
        			(selectedIdx == listView.getItems().size() - 1)
        			? selectedIdx - 1
        			: selectedIdx;
 
        	listView.getItems().remove(selectedIdx);
        	listView.getSelectionModel().select(newSelectedIdx);
        }
	}

}
