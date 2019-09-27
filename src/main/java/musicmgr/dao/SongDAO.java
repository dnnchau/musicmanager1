package musicmgr.dao;

import java.util.List;

import musicmgr.model.Song;

public interface SongDAO {

	List<Song> getAllSong() throws Exception;

	public Song getSong(Integer songID) throws Exception;

	public void addSong(Song song) throws Exception;

	public void updateSong(Song song) throws Exception;

	public void removeSong(Song obj) throws Exception;

	public List<Song> searchSongbyName(String songName) throws Exception;

	public List<Song> searchSongbyID(Integer songID) throws Exception;

	public Song getSongByName(String songName) throws Exception;
}
