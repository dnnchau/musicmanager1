package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Singer;

public interface SingerService {
	public List<Singer> getAllSinger() throws Exception;

	public int countAllSinger() throws Exception;

	public Singer getSinger(Integer singerID) throws Exception;

	public void add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(int singerID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(int singerID) throws Exception;

	public List<Singer> searchSinger(String singerName) throws Exception;

}
