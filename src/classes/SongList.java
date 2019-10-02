package classes;

import java.util.TreeMap;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.SongListController;

class CompareTwoStringArrays implements Comparator<String[]>{
	 
    @Override
    public int compare(String[] e1, String[] e2) {
    	int r = e1[0].compareTo(e2[0]); 	
    	if (r != 0 ) return r;
    	return e1[1].compareTo(e2[1]);
    }
}
public class SongList {
	
	private static TreeMap<String[], Song > songListTM = new TreeMap<String[], Song >( new CompareTwoStringArrays() );
	
	private static ObservableList<Song> obsList = FXCollections.observableArrayList();
	
	private static ObservableList<String> obsListTitle = FXCollections.observableArrayList();  
	
	public static ObservableList<Song> getSongs() {
		obsList.clear();
		songListTM.forEach((k,v) -> {
			obsList.add(v);	
		});
		return obsList;
	}
	
	public static ObservableList<String> getTitlesAndArtists() {
//		Clearing the VIEW
		obsListTitle.clear();
//		Adds everything from Treemap back into view
		songListTM.forEach((k,v) -> {
			obsListTitle.add("Title: " + k[0] + "\nArtist: " + k[1]);	
		});
		return obsListTitle;
	}
	
	public static boolean editSong(Song song) {
//		Removes song from objList to re-add later
		deleteSongObj(song);

//		Inputed Song
		String title = song.getTitle();
		String artist = song.getArtist();
		String album = song.getAlbum();
		String year = song.getYear();
		
		String[] key = {title, artist};
		if ( songListTM.containsKey( key ) ) {
			songListTM.put(key, song);
			return false;
		}
		Song edit = new Song(title, artist, album, year);
		songListTM.put(key, edit);
		return true;
	}
	
	public static boolean addNewSong(Song newSong) {
		String title = newSong.getTitle();
		String artist = newSong.getArtist();
		String[] key = {title, artist};
		if ( songListTM.containsKey( key ) ) {
			return false;
		} 
//		Adds to treemap - sorts on its own
		songListTM.put(key, newSong);
		return true;
	}
	
//	Removes from objList
	public static void deleteSongObj(Song selected) {
		String title = selected.getTitle();
		String artist = selected.getArtist();
		String[] key = {title, artist};
		songListTM.remove(key);
	}

	public static TreeMap<String[], Song > getSongListTM() {
		return songListTM;
	}

	
}
