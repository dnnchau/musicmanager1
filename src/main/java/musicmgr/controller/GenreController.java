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

import musicmgr.model.Genre;
import musicmgr.service.GenreService;

@RestController(value = "/genre")
public class GenreController {
	@Autowired
	private GenreService genreService;
	private HttpHeaders headers;
	private final static Logger logger = Logger.getLogger(GenreController.class);

	@RequestMapping(value = "/getallgenre", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Genre>> getAllSongs() {
		logger.info("Starting get all genre!");
		headers = new HttpHeaders();
		try {
			List<Genre> list = genreService.getAllGenre();
			if (list == null) {
				logger.warn("Not instance in list !!!!!!!!");
				return new ResponseEntity<List<Genre>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get all genre successfully");
			headers.add("Number of Recodes Found", String.valueOf(list.size()));
			return new ResponseEntity<List<Genre>>(list, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when get all genre", e);
			return new ResponseEntity<List<Genre>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getgenre/{genreID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Genre> getgenre(@PathVariable("genreID") Long genreID) {
		logger.info("Starting get genre by ID!");
		try {
			Genre genre = genreService.getGenre(genreID);
			if (genre == null) {
				logger.warn("Not genre in list");
				return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get genre successfully");
			return new ResponseEntity<Genre>(genre, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when get genre by genreID: " + genreID, e);
			return new ResponseEntity<Genre>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/addgenre", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<JSONObject> addGenre(@RequestBody Object obj) {
		logger.info("Starting to add genre!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			genreService.add((LinkedHashMap<String, Object>) obj);
			logger.debug("Add genre successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Failed when add genre", e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updategenre/{genreID}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<JSONObject> updateGenre(@PathVariable("genreID") Long genreID, @RequestBody Object obj) {
		logger.info("Starting to update genre!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			genreService.update(genreID, (LinkedHashMap<String, Object>) obj);
			logger.info("Update genre successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when update genre by genreID: " + genreID, e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/removegenre/{genreID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> removeGenre(@PathVariable("genreID") Long genreID) {
		logger.info("Starting to delete genre request!");
		try {
			genreService.remove(genreID);
			logger.info("Delete genre successfully!");
			return new ResponseEntity<String>("Remove genre successfully!", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when delete genre by genreID: " + genreID, e);
			return new ResponseEntity<String>("Failed to remove genre: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/searchgenre/{genreName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Genre>> searchGenre(@PathVariable("genreName") String genreName) {
		logger.info("Starting search genre");
		try {
			List<Genre> genre = genreService.searchGenre(genreName);
			if (genre == null) {
				logger.warn("Not genre in list");
				return new ResponseEntity<List<Genre>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Search genre successfully");
			return new ResponseEntity<List<Genre>>(genre, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to search Genre by genreName: " + genreName, e);
			return new ResponseEntity<List<Genre>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
