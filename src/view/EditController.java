/* 
 * Phi Hoang
 * Mai Le
 * Software Methodology
*/

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
	boolean isEdited;
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
	void editSong(ActionEvent event) {
		String title = songname.getText();
		String artistName = artist.getText();
		String albumName = album.getText();
		String yearPublic = year.getText();
		Stage stage = (Stage) editBtn.getScene().getWindow();
		if ( songname == null || artist == null || title.trim().isEmpty()  || artistName.trim().isEmpty()  ) {
			showAlert2(stage);
			return;
		}
		newSong = new Song(title, artistName, albumName, yearPublic);
		isEdited = SongList.editSong(oldSong, newSong);

		if ( !isEdited ) {
			showAlert(stage, false);
		}
		else {
			showAlert(stage, true);
			try {
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(
					getClass().getResource("/view/SongListView.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Stage window = (Stage) ( (Node) event.getSource()).getScene().getWindow();

				SongListController listController = loader.getController();
				if ( newSong != null && isEdited) {
					listController.index = SongList.getSongs().indexOf(newSong);
				}
				listController.isStart = false;
				System.out.println(listController.index + "");
				listController.start(window);
//				AnchorPane editView = FXMLLoader.load(getClass().getResource("/view/SongListView.fxml"));
				Scene newScene = new Scene (root);
				window.setScene(newScene);
				window.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	private void showAlert2(Stage mainStage) {                
	      Alert alert = 
	         new Alert(AlertType.INFORMATION);
	      //alert.initModality(Modality.NONE);
	      alert.initOwner(mainStage);
	      alert.setTitle("Edit Song");
	      alert.setHeaderText(
	    	           "Cannot edit song");
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
			if ( newSong != null && isEdited) {
				listController.index = SongList.getSongs().indexOf(newSong);
			}
			listController.isStart = false;
			System.out.println(listController.index + "");
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