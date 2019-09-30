package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Song;

public interface SongService {
	public List<Song> getAllSong() throws Exception;

	public Song getSong(Long songID) throws Exception;

	public Long add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(Long songID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(Long songID) throws Exception;

	public List<Song> searchSongbyName(String songName) throws Exception;

	public List<Song> searchSongbyID(Long songID) throws Exception;

	public List<Song> getNameSong() throws Exception;

}
