package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Singer;

public interface SingerService {
	public List<Singer> getAllSinger() throws Exception;

	public Singer getSinger(Long singerID) throws Exception;

	public Long add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(Long singerID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(Long singerID) throws Exception;

	public List<Singer> searchSinger(String singerName) throws Exception;

}
