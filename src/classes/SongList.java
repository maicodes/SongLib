package classes;

import java.util.TreeMap;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
	
//	private String getTitle() {
//		return"";
//	}
//	
//	private String getArtist() {
//		return"";
//	}
//	
//	private String getAlbum() {
//		return "";
//	}
//	
//	private String getYear() {
//		return "";
//	}
	
	public static ObservableList<Song> getSongs() {
		obsList.clear();
		songListTM.forEach((k,v) -> {
			obsList.add(v);	
		});
		return obsList;
	}
	
	public static ObservableList<String> getTitlesAndArtists() {
		obsListTitle.clear();
		songListTM.forEach((k,v) -> {
			obsListTitle.add("Title: " + k[0] + "\nArtist: " + k[1]);	
		});
		return obsListTitle;
	}
	
	public static boolean editSong(Song song) {
		String title = song.getTitle();
		String artist = song.getArtist();
		String[] key = {title, artist};
		if ( songListTM.containsKey( key ) ) {
			
			return true;
		}
		return false;
	}
	
	public static boolean addNewSong(Song newSong) {
		String title = newSong.getTitle();
		String artist = newSong.getArtist();
		String[] key = {title, artist};
		if ( songListTM.containsKey( key ) ) {
			return false;
		} 
		songListTM.put(key, newSong);
		return true;
	}
	
	public static boolean deleteSong(String title, String artist) {
		boolean isRemoved = false;
		String[] key = {title, artist};
		if ( songListTM.containsKey(key) ) {
				songListTM.remove(key);
				isRemoved = true;
			
		}
		return isRemoved;
	}

	public static TreeMap<String[], Song > getSongListTM() {
		return songListTM;
	}

	
}
