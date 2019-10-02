package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import classes.Song;
import classes.SongList;

public class EditController implements Initializable{
	
	@FXML
	Button editBtn;
	
	@FXML
	private TextField songname;
	@FXML
	private TextField artist;
	@FXML
	private TextField album;
	@FXML
	private TextField year;
		
	@Override
//	Initializes textfields to selected song
    public void initialize(URL url, ResourceBundle rb) {
    	Song song = SongListController.currItem;

    	String title = song.getTitle();
		String artistName = song.getArtist();
		String albumName = song.getAlbum();
		String yearPublic = song.getYear();

        songname.setText(title);
        artist.setText(artistName);
        album.setText(albumName);
        year.setText(yearPublic);
    } 
	
	
	@FXML
	void editSong() {
		String title = songname.getText();
		String artistName = artist.getText();
		String albumName = album.getText();
		String yearPublic = year.getText();
		Song newSong = new Song(title, artistName, albumName, yearPublic);
		boolean isAdded = SongList.editSong(newSong);
		Stage stage = (Stage) editBtn.getScene().getWindow();
		//System.out.println(isAdded + "");
		if ( !isAdded ) {
			showAlert(stage, false);
		}
		else {
			showAlert(stage, true);
			//System.out.println();
		}
	};
	
	private void showAlert(Stage mainStage, boolean isAdded) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("Add New Song");
	      String content ="";
	      if ( !isAdded ) {
	    	   alert.setHeaderText(
	    	           "Cannot edit song");
	    	            content = "This song already exists";
	      } else {
	    	   alert.setHeaderText(
	    	           "Song is edited");
	    	   content = "OK!";
	      }
	          alert.setContentText(content);
	          alert.showAndWait();
	          
	   }
	
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
