package classes;

import java.util.TreeMap;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class CompareTwoStringArrays implements Comparator<String[]>{
	 
    @Override
    public int compare(String[] e1, String[] e2) {
    	int r = e1[0].compareToIgnoreCase(e2[0]); 	
    	if (r != 0 ) return r;
    	return e1[1].compareToIgnoreCase(e2[1]);
    }
}
public class SongList {
	
	public static TreeMap<String[], Song > songListTM = new TreeMap<String[], Song >( new CompareTwoStringArrays() );
	
	private static ObservableList<Song> obsList = FXCollections.observableArrayList();
	
	
	public static ObservableList<Song> getSongs() {
		obsList.clear();
		songListTM.forEach((k,v) -> {
			obsList.add(v);	
		});
		return obsList;
	}
	
	
	public static boolean editSong(Song oldSong, Song newSong) {
//		Inputed Song
		String title = newSong.getTitle();
		String artist = newSong.getArtist();
		String[] key = {title, artist};
		if ( songListTM.containsKey( key ) ) {
			return false;
		}
//		Removes song from objList to re-add later
		deleteSongObj(oldSong);
		obsList.remove(oldSong);
		songListTM.put(key, newSong);
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
	
	public static ObservableList<Song> getObsList(){
		return obsList;
	}

	
}
