package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Song;

public interface SongService {
	public List<Song> getAllSong() throws Exception;

	public Song getSong(Integer songID) throws Exception;

	public void add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(int songID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(int songID) throws Exception;

	public List<Song> searchSongbyName(String songName) throws Exception;
	
	public List<Song> searchSongbyID(Integer songID) throws Exception;
	
	
	public List<Song> getNameSong() throws Exception;
	
}
