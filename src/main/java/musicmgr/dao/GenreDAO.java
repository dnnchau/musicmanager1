package musicmgr.dao;

import java.util.List;

import musicmgr.model.Genre;

public interface GenreDAO {
	List<Genre> getAllGenre() throws Exception;

	public int countAllGenre() throws Exception;

	public Genre getGenre(Integer genreID) throws Exception;

	public Genre getGenreByName(String name) throws Exception;

	public void addGenre(Genre genre) throws Exception;

	public void updateGenre(Genre genre) throws Exception;

	public void removeGenre(Genre genre) throws Exception;
	
	public List<Genre> searchGenre(String genreName) throws Exception;
}
