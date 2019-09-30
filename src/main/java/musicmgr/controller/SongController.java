package musicmgr.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import musicmgr.model.Song;
import musicmgr.service.SongService;

@RestController(value = "/song")
public class SongController {
	@Autowired
	private SongService songService;

	private HttpHeaders headers;
	private final static Logger logger = Logger.getLogger(SongController.class);

	@RequestMapping(value = "/getallsong", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Song>> getAllSongs() {
		logger.info("Starting get all song!");
		headers = new HttpHeaders();
		try {
			List<Song> list = songService.getAllSong();
			if (list == null) {
				logger.warn("Not instance in list !!!!!!!!");
				return new ResponseEntity<List<Song>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get all song successfully");
			headers.add("Number of Recodes Found", String.valueOf(list.size()));
			return new ResponseEntity<List<Song>>(list, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);
			return new ResponseEntity<List<Song>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getsong/{songID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Song> getSong(@PathVariable("songID") int songID) {
		logger.info("Starting get song by ID!");
		try {
			Song song = songService.getSong(songID);
			if (song == null) {
				logger.warn("Not song in list");
				return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get song successfully");
			return new ResponseEntity<Song>(song, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);
			return new ResponseEntity<Song>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/addsong", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<JSONObject> addSong(@RequestBody Object obj) {
		logger.info("Starting add song!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			songService.add((LinkedHashMap<String, Object>) obj);
			logger.info("Add song successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updatesong/{songID}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<JSONObject> updateSong(@PathVariable("songID") int songID, @RequestBody Object obj) {
		logger.info("Starting to update song!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			songService.update(songID, (LinkedHashMap<String, Object>) obj);
			logger.info("Update song successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/removesong/{songID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> removeSong(@PathVariable("songID") int songID) {
		logger.info("Starting to delete song request!");
		try {
			songService.remove(songID);
			logger.info("Delete song successfully!");
			return new ResponseEntity<String>("Delete song successfully!", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error", e);
			return new ResponseEntity<String>("Failed to delete song: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/searchsongbyname/{songName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Song>> searchSongbyName(@PathVariable("songName") String songName) {
		logger.info("Starting search song!");
		try {
			List<Song> song = songService.searchSongbyName(songName);
			if (song == null) {
				logger.warn("Not song in list");
				return new ResponseEntity<List<Song>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Search song successfully");
			return new ResponseEntity<List<Song>>(song, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to search Song", e);
			return new ResponseEntity<List<Song>>(HttpStatus.BAD_REQUEST);
		}
	}
}