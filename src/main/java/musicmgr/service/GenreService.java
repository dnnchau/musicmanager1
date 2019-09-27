package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Genre;

public interface GenreService {
	public List<Genre> getAllGenre() throws Exception;

	public int countAllGenre() throws Exception;

	public Genre getGenre(Integer genreID) throws Exception;

	public void add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(int genreID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(int gerneID) throws Exception;

	public List<Genre> searchGenre(String genreName) throws Exception;
}
