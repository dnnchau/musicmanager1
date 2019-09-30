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

import musicmgr.model.Composer;
import musicmgr.service.ComposerService;

@RestController(value = "/composer")
public class ComposerController {
	@Autowired
	private ComposerService composerService;
	private HttpHeaders headers;
	private final static Logger logger = Logger.getLogger(ComposerController.class);

	@RequestMapping(value = "/getallcomposer", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Composer>> getAllComposers() {
		logger.info("Starting get all composer!");
		headers = new HttpHeaders();
		try {
			List<Composer> list = composerService.getAllComposer();
			if (list == null) {
				logger.warn("Not instance in list !");
				return new ResponseEntity<List<Composer>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get all composer successfully");
			headers.add("Number of Recodes Found", String.valueOf(list.size()));
			return new ResponseEntity<List<Composer>>(list, headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when get all composer", e);
			return new ResponseEntity<List<Composer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getcomposer/{composerID}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Composer> getcomposer(@PathVariable("composerID") Long composerID) {
		logger.info("Starting get composer by ID!");
		try {
			Composer composer = composerService.getComposer(composerID);
			if (composer == null) {
				logger.warn("Not composer in list");
				return new ResponseEntity<Composer>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Get composer successfully");
			return new ResponseEntity<Composer>(composer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when get composer by composerID: " + composerID, e);
			return new ResponseEntity<Composer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/addcomposer", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> addcomposer(@RequestBody Object obj) {
		logger.info("Starting to add composer!");
		try {
			composerService.add((LinkedHashMap<String, Object>) obj);
			logger.debug("Add composer successfully");
			return new ResponseEntity<String>("Add composer successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Failed when add composer", e);
			return new ResponseEntity<String>("Failed when add composer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/updatecomposer/{composerID}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> updateComposer(@PathVariable("composerID") Long composerID, @RequestBody Object obj) {
		logger.info("Starting to update composer!");
		try {
			composerService.update(composerID, (LinkedHashMap<String, Object>) obj);
			logger.info("Update composer successfully");
			return new ResponseEntity<String>("Update composer successfully", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when update composer by composerID:" + composerID, e);
			return new ResponseEntity<String>("Failed to update composer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/removecomposer/{composerID}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> removecomposer(@PathVariable("composerID") Long composerID) {
		logger.info("Starting to delete composer request!");
		try {
			composerService.remove(composerID);
			logger.info("Delete composer successfully!");
			return new ResponseEntity<String>("Remove composer successfully!", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed when delete composer by composerID: " + composerID, e);
			return new ResponseEntity<String>("Failed to remove composer: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/searchcomposer/{composerName}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Composer>> searchcomposer(@PathVariable("composerName") String composerName) {
		logger.info("Starting search composer!");
		try {
			List<Composer> composer = composerService.searchComposer(composerName);
			if (composer == null) {
				logger.warn("Not composer in list");
				return new ResponseEntity<List<Composer>>(HttpStatus.NOT_FOUND);
			}
			logger.debug("Search composer successfully");
			return new ResponseEntity<List<Composer>>(composer, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Failed to search composer by composerName: " + composerName, e);
			return new ResponseEntity<List<Composer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
