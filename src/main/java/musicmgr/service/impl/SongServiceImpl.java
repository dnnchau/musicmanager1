package musicmgr.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.SongDAO;
import musicmgr.model.Composer;
import musicmgr.model.Genre;
import musicmgr.model.Song;
import musicmgr.model.Singer;
import musicmgr.service.ComposerService;
import musicmgr.service.GenreService;
import musicmgr.service.SingerService;
import musicmgr.service.SongService;

public class SongServiceImpl implements SongService {

	@Autowired
	private SongDAO songDAO;
	@Autowired
	private GenreService genreSerivce;
	@Autowired
	private ComposerService composerSerivce;
	@Autowired
	private SingerService singerService;

	private final static Logger logger = Logger.getLogger(SongServiceImpl.class);

	public SongDAO getSongDAO() {
		return songDAO;
	}

	public void setSongDAO(SongDAO songDAO) {
		this.songDAO = songDAO;
	}

	public SongServiceImpl(SongDAO songDAO, GenreService genreSerivce, ComposerService composerSerivce,
			SingerService singerService) {
		this.songDAO = songDAO;
		this.genreSerivce = genreSerivce;
		this.composerSerivce = composerSerivce;
		this.singerService = singerService;
	}

	public List<Song> getAllSong() throws Exception {
		logger.debug("Call method getAllSong in SongDao from SongService");
		try {
			return songDAO.getAllSong();
		} catch (Exception e) {
			logger.error("Failed to get all Song ", e);
			throw e;
		}
	}

	@Override
	public Song getSong(Integer songID) throws Exception {
		logger.debug("Call method getSong in SongDao from SongService");
		try {
			return songDAO.getSong(songID);
		} catch (Exception e) {
			logger.error("Failed to get Song by ID: " + songID, e);
			throw e;
		}
	}

	@Override
	public void add(LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method addSong in SongDao from SongService");
		try {
			Song song = new Song();
			Set<String> songKey = obj.keySet();
			for (String key : songKey) {
				setSongValue(song, key, obj.get(key));
			}
			songDAO.addSong(song);
		} catch (Exception e) {
			logger.error("Failed to add Song: ", e);
			throw e;
		}
	}

	@Override
	public void update(int songID, LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method updateSong in SongDao from SongService");
		try {
			logger.debug("Edit Song with songID: " + songID);
			Song song = songDAO.getSong(songID);
			if (song == null) {
				logger.error("songID" + songID + "not exists");
				throw new Exception("Song not exists");
			}
			Set<String> songKey = obj.keySet();
			for (String key : songKey) {
				setSongValue(song, key, obj.get(key));
			}
			songDAO.updateSong(song);
		} catch (Exception e) {
			logger.error("Failed to edit song: " + songID, e);
			throw e;
		}
	}

	@Override
	public void remove(int songID) throws Exception {
		logger.debug("Call method deleteSong in SongDao from SongService");
		try {
			Song obj = songDAO.getSong(songID);
			if (obj == null) {
				logger.error("Song Id=" + songID + " doesn't exist!");
				throw new Exception("Song " + songID + " doesn't exist!");
			}
			songDAO.removeSong(obj);
		} catch (Exception e) {
			logger.error("Failed to delete Song by Id = " + songID, e);
			throw e;
		}
	}
	
	
	
	@Override
	public List<Song> searchSongbyName(String songName) throws Exception {
		logger.debug("Call method searchSongbyName in SongDao from SongService");
		try {
			return songDAO.searchSongbyName(songName);
		} catch (Exception e) {
			logger.error("Failed to search Song: " + songName, e);
			throw e;
		}
	}
	@Override
	public List<Song> searchSongbyID(Integer songID) throws Exception {
		logger.debug("Call method searchSongbyID in SongDao from SongService");
		try {
			return songDAO.searchSongbyID(songID);
		} catch (Exception e) {
			logger.error("Failed to search Song: " + songID, e);
			throw e;
		}
	}

	private void setSongValue(Song song, String keyParam, Object songValue) throws Exception {
		if (songValue != null) {
			String value = songValue.toString().trim();
			if ("songName".equals(keyParam) && value != null) {
				song.setSongName(value);
				
			} else if ("lyrics".equals(keyParam) && value != null) {
				song.setLyrics(value);
			} else if ("genreID".equals(keyParam)) {
				Genre currentGenre = genreSerivce.getGenre(Integer.valueOf(value));
				if (currentGenre == null) {
					throw new Exception("Genre doesn't exist id = " + value);
				}
				song.setGenre(currentGenre);
			} else if ("composerID".equals(keyParam)) {
				Composer currentComposer = composerSerivce.getComposer(Integer.valueOf(value));
				if (currentComposer == null) {
					throw new Exception("Composer doesn't exist! " + value);
				}
				song.setComposer(currentComposer);
			} else if ("singerID".equals(keyParam)) {
				Singer currentSinger = singerService.getSinger(Integer.valueOf(value));
				if (currentSinger == null) {
					throw new Exception("Singer doesn't exist! " + value);
				} 
				song.setSinger(currentSinger);
			}
		}

	}



}
