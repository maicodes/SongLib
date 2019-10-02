package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
	
	Song newSong;
	Song oldSong;
	@Override
//	Initializes textfields to selected song
    public void initialize(URL url, ResourceBundle rb) {
    	oldSong = SongListController.currItem;
        songname.setText(oldSong.getTitle());
        artist.setText(oldSong.getArtist());
        album.setText(oldSong.getAlbum());
        year.setText(oldSong.getYear());
    } 
	
	
	@FXML
	void editSong() {
		String title = songname.getText();
		String artistName = artist.getText();
		String albumName = album.getText();
		String yearPublic = year.getText();
		newSong = new Song(title, artistName, albumName, yearPublic);
		boolean isEdited = SongList.editSong(oldSong, newSong);
		Stage stage = (Stage) editBtn.getScene().getWindow();

		if ( !isEdited ) {
			showAlert(stage, false);
		}
		else {
			showAlert(stage, true);
		}
	};
	
	private void showAlert(Stage mainStage, boolean isAdded) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("Edit Song");
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
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(
				getClass().getResource("/view/SongListView.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();

			SongListController listController = loader.getController();
			listController.index = SongList.getSongs().indexOf(newSong);
			listController.isStart = false;
			System.out.print(listController.index + "");
			listController.start(window);
//			AnchorPane editView = FXMLLoader.load(getClass().getResource("/view/SongListView.fxml"));
			Scene newScene = new Scene (root);
			window.setScene(newScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
}
