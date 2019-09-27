package musicmgr.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.SingerDAO;
import musicmgr.model.Singer;
import musicmgr.service.SingerService;

public class SingerServiceImpl implements SingerService {
	@Autowired
	private SingerDAO singerDAO;
	private final static Logger logger = Logger.getLogger(SingerServiceImpl.class);

	public SingerServiceImpl(SingerDAO singerDAO) {
		this.singerDAO = singerDAO;
	}

	@Override
	public List<Singer> getAllSinger() throws Exception {
		logger.debug("Call method getAllSinger in SingerDao from SingerService");
		try {
			return singerDAO.getAllSinger();
		} catch (Exception e) {
			logger.error("Failed to get all Singer ", e);
			throw e;
		}
	}

	@Override
	public int countAllSinger() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Singer getSinger(Integer singerID) throws Exception {
		logger.debug("Call method getSinger in SingerDao from SingerService");
		try {
			return singerDAO.getSinger(singerID);
		} catch (Exception e) {
			logger.error("Failed to get Singer by ID: " + singerID, e);
			throw e;
		}
	}

	@Override
	public void add(LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method addSing in SingerDao from SingerService");
		try {
			Singer singer = new Singer();
			Set<String> singerKey = obj.keySet();
			for (String key : singerKey) {
				setSingerValue(singer, key, obj.get(key));
			}
			singerDAO.addSinger(singer);
		} catch (Exception e) {
			logger.error("Failed to add Singer: ", e);
			throw e;
		}
	}

	@Override
	public void update(int singerID, LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method updateSinger in SingerDao from SingerService");
		try {
			logger.debug("Edit Singer with singerID: " + singerID);
			Singer singer = singerDAO.getSinger(singerID);
			if (singer == null) {
				logger.error("singerID: " + singerID + "not exists");
				throw new Exception("Singer not exists");
			}
			Set<String> singerKey = obj.keySet();
			for (String key : singerKey) {
				setSingerValue(singer, key, obj.get(key));
			}
			singerDAO.updateSinger(singer);
		} catch (Exception e) {
			logger.error("Failed to edit singer: " + singerID, e);
			throw e;
		}
	}

	@Override
	public void remove(int singerID) throws Exception {
		logger.debug("Call method removeSinger in SingerDao from SingerService");
		try {
			Singer obj = singerDAO.getSinger(singerID);
			if (obj == null) {
				logger.error("Singer Id=" + singerID + " doesn't exist!");
				throw new Exception("Singer " + singerID + " doesn't exist!");
			}
			singerDAO.removeSinger(obj);
		} catch (Exception e) {
			logger.error("Failed to remove Singer by Id = " + singerID, e);
			throw e;
		}
	}

	private void setSingerValue(Singer singer, String keyParam, Object singerValue) throws Exception {
		if (singerValue != null) {
			String value = singerValue.toString().trim();
			if ("singerName".equals(keyParam) && value != null) {
				singer.setSingerName(value);
			} else if ("singerDesc".equals(keyParam) && value != null) {
				singer.setSingerDesc(value);
			}
		}
	}

	@Override
	public List<Singer> searchSinger(String singerName) throws Exception {
		logger.debug("Call method searchSinger in SingerDao from SingerService");
		try {
			return singerDAO.searchSinger(singerName);
		} catch (Exception e) {
			logger.error("Failed to search Singer: " + singerName, e);
			throw e;
		}
	}
}
