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

import musicmgr.model.Singer;
import musicmgr.service.SingerService;

@RestController(value = "/singer")
public class SingerController {
	@Autowired
	private SingerService singerService;
	private HttpHeaders headers;
	private final static Logger logger = Logger.getLogger(SingerController.class);

	@RequestMapping(value = "/getsinger", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Singer>> getAllSingers() {
		logger.info("Starting get all singer!");
		headers = new HttpHeaders();
		try {
			List<Singer> list = singerService.getAllSinger();
			if (list == null) {
				logger.warn("Not instance in list !!!!!!!!");
				return new ResponseEntity<List<Singer>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get all singer successfully");
			headers.add("Number of Recodes Found", String.valueOf(list.size()));
			return new ResponseEntity<List<Singer>>(list, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error!!", e);
			return new ResponseEntity<List<Singer>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getsinger/{singerID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Singer> getSinger(@PathVariable("singerID") int singerID) {
		logger.info("Starting get singer by ID!");
		try {
			Singer singer = singerService.getSinger(singerID);
			if (singer == null) {
				logger.warn("Not singer in list");
				return new ResponseEntity<Singer>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get singer successfully");
			return new ResponseEntity<Singer>(singer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error!!", e);
			return new ResponseEntity<Singer>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/addsinger", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<JSONObject> addSinger(@RequestBody Object obj) {
		logger.info("Starting add singer!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			singerService.add((LinkedHashMap<String, Object>) obj);
			logger.info("Add singer successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error!!", e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updatesinger/{singerID}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<JSONObject> updateSinger(@PathVariable("singerID") int singerID, @RequestBody Object obj) {
		logger.info("Starting to update singer!");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			singerService.update(singerID, (LinkedHashMap<String, Object>) obj);
			logger.info("Update singer successfully");
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error!!", e);
			return new ResponseEntity<JSONObject>(new JSONObject(result), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/removesinger/{singerID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> removeSinger(@PathVariable("singerID") int singerID) {
		logger.info("Starting to remove singer request!");
		try {
			singerService.remove(singerID);
			logger.info("Delete singer successfully!");
			return new ResponseEntity<String>("Remove singer successfully!", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error!!", e);
			return new ResponseEntity<String>("Failed to remove singer: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/searchsinger/{singerName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Singer>> searchsinger(@PathVariable("singerName") String singerName) {
		logger.info("Starting search singer!");
		try {
			List<Singer> singer = singerService.searchSinger(singerName);
			if (singer == null) {
				logger.warn("Not singer in list");
				return new ResponseEntity<List<Singer>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Search singer successfully");
			return new ResponseEntity<List<Singer>>(singer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to search singer", e);
			return new ResponseEntity<List<Singer>>(HttpStatus.BAD_REQUEST);
		}
	}
}
