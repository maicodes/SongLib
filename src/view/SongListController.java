package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import classes.Song;
import classes.SongList;


public class SongListController  {
	
	@FXML         
	ListView<Song> listView;  
	@FXML
	TextField title;
	@FXML
	TextField artist;
	@FXML
	TextField album;
	@FXML
	TextField year;
	boolean isStart = true; 
	int index = 0;

	private ObservableList<Song> obsList;  
//	Currently selected item
	public static Song currItem;
	
	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList 
		// System.out.println(isStart + "");
				if (isStart) { // only pull songs from songs.txt when open the app
					obsList = FXCollections.observableArrayList(readFromFile("src/songs.txt"));
					// add songs to treemap
					if ( obsList != null && obsList.size() > 0 ) {
						for(Song s : obsList) {
							String title = s.getTitle();
							String artist = s.getArtist();
							String[] key = {title, artist};
							SongList.songListTM.put(key, s);
						}
					}
				}
				else {
					// get songs from treemap when move from add/edit view to main view
					obsList = SongList.getSongs();
				}
				
				 listView.setItems(obsList); 
				 
				 /*
				  * Setup Cells
				  */
				 listView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
				        @Override
			            public ListCell<Song> call(ListView<Song> p) {
			                 
			                ListCell<Song> cell = new ListCell<Song>(){
			 
			                    @Override
			                    protected void updateItem(Song song, boolean empty) {
			                        super.updateItem(song,empty);
			                        if (song != null) {
			                            setText("Title: " + song.getTitle() +"\nArtist: " + song.getArtist());
			                        }
			                        else if (song == null) setText(null);
			                    }
			 
			                };
			                 
			                return cell;
			            }
			       });
			      
			    // select the first item
				//System.out.print(obsList.size() + "");
			   if ( obsList != null && obsList.size() > 0 ) 
				{
					 listView.getSelectionModel().select(index);
				     title.setText(obsList.get(index).getTitle());  
				     artist.setText(obsList.get(index).getArtist());
					 album.setText(obsList.get(index).getAlbum());
					 year.setText(obsList.get(index).getYear());
					 currItem = obsList.get(index);
				}
			   // set listener for the items
			   listView
			        .getSelectionModel()
			        .selectedIndexProperty()
			        .addListener(
			           (obs, oldVal, newVal) -> 
			               showItem());
			      
			    //save data to songs.txt before the application is terminated
			    mainStage.setOnCloseRequest(event -> {  
			    	  PrintWriter writer;
			    	  	try {
			    	  			File file = new File ("src/songs.txt");
			    	  			file.createNewFile();
			    	  			writer = new PrintWriter(file);
								for(Song s: obsList)
						    	  {
						    		  writer.println(s.getTitle());
						    		  writer.println(s.getArtist());
						    		  writer.println(s.getAlbum());
						    		  writer.println(s.getYear());
						    		  
						    	  }
						    	 writer.close(); 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    	 
			      }); 

	   }

	
	private ObservableList<Song> readFromFile(String filePathName)
	   {
		 ObservableList<Song> songs = FXCollections.observableArrayList();
				 
		   BufferedReader br;
		   Path filePath = Paths.get(filePathName);
		   try {

				if (!new File(filePathName).exists())
				{
				   return songs;
				}
			   br = Files.newBufferedReader(filePath);
			   String line = br.readLine();
				
			   while (line != null) { 
		              
				   String title = line;
		               
				   line = br.readLine();
				   String artist = line;
		               
				   line = br.readLine();
				   String album = line;
		               
				   line = br.readLine();
				   String year = line;
		               
				   Song temp = new Song(title, artist, album, year);
				   songs.add(temp);
		               
				   line = br.readLine(); //to the next song name, if not null
			   }
			  
				
		   } catch (IOException e) {
			   e.printStackTrace();
		   }	   
		   return songs;
	   }


	private void showItem() {   
		int idx = listView.getSelectionModel().getSelectedIndex();
		if (idx < 0 ) return;
		//System.out.println("index: " + idx);
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
