package musicmgr.dao;

import java.util.List;

import musicmgr.model.Song;

public interface SongDAO {

	List<Song> getAllSong() throws Exception;

	public Song getSong(Long songID) throws Exception;

	public Long addSong(Song song) throws Exception;

	public void updateSong(Song song) throws Exception;

	public void removeSong(Song obj) throws Exception;

	public List<Song> searchSongbyName(String songName) throws Exception;

	public List<Song> searchSongbyID(Long songID) throws Exception;

	public List<Song> getNameSongDAO() throws Exception;
}
