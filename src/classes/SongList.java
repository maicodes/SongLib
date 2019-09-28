package classes;

import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SongList {
	
	static private TreeMap<String, TreeMap<String, Song> > songListTM = new TreeMap<String, TreeMap<String, Song> >();
	private static ObservableList<Song> obsList = FXCollections.observableArrayList();
	
	private String getTitle() {
		return"";
	}
	
	private String getArtist() {
		return"";
	}
	
	private String getAlbum() {
		return "";
	}
	
	private int getYear() {
		return 0;
	}
	
	public static ObservableList<Song> getSongs() {
		
		return obsList;
	}
	
	public static boolean editSong(Song song) {
		String title = song.getTitle();
		String artist = song.getArtist();
		if ( songListTM.containsKey(title) && songListTM.get(title).containsKey(artist) ) {
			
			return true;
		}
		return false;
	}
	
	public static boolean addNewSong(Song newSong) {
		String title = newSong.getTitle();
		String artist = newSong.getArtist();
		if ( songListTM.containsKey(title) ) {
			if (songListTM.get(title).containsKey(artist) ) {
				return false;
			}
			songListTM.get(title).put(artist, newSong);
		} else {
			TreeMap<String, Song> tm = new TreeMap<String, Song>();
			tm.put(artist, newSong);
			songListTM.put(title, tm );
		}
		return true;
	}
	
	private boolean deleteSong() {
		return true;
	}
	
}
