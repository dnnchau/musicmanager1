package musicmgr.dao;

import java.util.List;

import musicmgr.model.Singer;

public interface SingerDAO {
	List<Singer> getAllSinger() throws Exception;

	public Singer getSinger(Long singerID) throws Exception;

	public Long addSinger(Singer singer) throws Exception;

	public void updateSinger(Singer singer) throws Exception;

	public void removeSinger(Singer singer) throws Exception;

	public List<Singer> searchSinger(String singerName) throws Exception;
}
