package musicmgr.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.ComposerDAO;
import musicmgr.model.Composer;
import musicmgr.service.ComposerService;

public class ComposerServiceImpl implements ComposerService {
	@Autowired
	private ComposerDAO composerDAO;
	private final static Logger logger = Logger.getLogger(ComposerServiceImpl.class);

	@Override
	public List<Composer> getAllComposer() throws Exception {
		logger.debug("Call method getAllComposer in ComposerDao from ComposerService");
		try {
			return composerDAO.getAllComposer();
		} catch (Exception e) {
			logger.error("Failed to get all Composer ", e);
			throw e;
		}
	}

	@Override
	public Composer getComposer(Long composerID) throws Exception {
		logger.debug("Call method getComposer in ComposerDao from ComposerService");
		try {
			return composerDAO.getComposer(composerID);
		} catch (Exception e) {
			logger.error("Failed to get Composer by ID: " + composerID, e);
			throw e;
		}
	}

	@Override
	public Long add(LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method addComposer on ComposerDao from ComposerSerVice");
		try {
			Composer composer = new Composer();
			Set<String> composerKey = obj.keySet();
			for (String key : composerKey) {
				setComposerValue(composer, key, obj.get(key));
			}
			composerDAO.addComposer(composer);
			return composer.getComposerID();
		} catch (Exception e) {
			logger.error("Failed to add Composer: ", e);
			throw e;
		}
	}

	@Override
	public void update(Long composerID, LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method updateComposer in ComposerDao from ComposerService");
		try {
			logger.debug("Edit Composer with composerID: " + composerID);
			Composer composer = composerDAO.getComposer(composerID);
			if (composer == null) {
				logger.error("composerID: " + composerID + "not exists");
				throw new Exception("Composer not exists");
			}
			Set<String> composerKey = obj.keySet();
			for (String key : composerKey) {
				setComposerValue(composer, key, obj.get(key));
			}
			composerDAO.updateComposer(composer);
		} catch (Exception e) {
			logger.error("Failed to edit composer: " + composerID, e);
			throw e;
		}
	}

	@Override
	public void remove(Long composerID) throws Exception {
		logger.debug("Call method removeComposer in ComposerDao from ComposerService");
		try {
			Composer obj = composerDAO.getComposer(composerID);
			if (obj == null) {
				logger.error("Composer Id=" + composerID + " doesn't exist!");
				throw new Exception("Composer " + composerID + " doesn't exist!");
			}
			composerDAO.removeComposer(obj);
		} catch (Exception e) {
			logger.error("Failed to remove Composer by Id = " + composerID, e);
			throw e;
		}
	}

	private void setComposerValue(Composer composer, String keyParam, Object composerValue) throws Exception {
		if (composerValue != null) {
			String value = composerValue.toString().trim();
			if ("composerName".equals(keyParam) && value != null) {
				composer.setComposerName(value);
			} else if ("composerDesc".equals(keyParam) && value != null) {
				composer.setComposerDesc(value);
			}
		}
	}

	@Override
	public List<Composer> searchComposer(String composerName) throws Exception {
		logger.debug("Call method searchcomposer in composerDao from composerService");
		try {
			return composerDAO.searchComposer(composerName);
		} catch (Exception e) {
			logger.error("Failed to search composer: " + composerName, e);
			throw e;
		}
	}
}
