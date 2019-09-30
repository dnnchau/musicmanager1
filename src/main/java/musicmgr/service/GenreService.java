package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Genre;

public interface GenreService {
	public List<Genre> getAllGenre() throws Exception;

	public Genre getGenre(Long genreID) throws Exception;

	public Long add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(Long genreID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(Long gerneID) throws Exception;

	public List<Genre> searchGenre(String genreName) throws Exception;
}
