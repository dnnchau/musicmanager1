package musicmgr.dao;

import java.util.List;

import musicmgr.model.Singer;
import musicmgr.model.Song;

public interface SingerDAO {
	List<Singer> getAllSinger() throws Exception;

	public int countAllSinger() throws Exception;

	public Singer getSinger(Integer singerID) throws Exception;

	public void addSinger(Singer singer) throws Exception;

	public void updateSinger(Singer singer) throws Exception;

	public void removeSinger(Singer singer) throws Exception;

	public List<Singer> searchSinger(String singerName) throws Exception;
}
