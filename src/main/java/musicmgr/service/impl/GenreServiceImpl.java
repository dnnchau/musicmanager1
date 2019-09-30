package musicmgr.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.GenreDAO;
import musicmgr.model.Genre;
import musicmgr.service.GenreService;

public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreDAO genreDAO;
	private final static Logger logger = Logger.getLogger(GenreServiceImpl.class);

	@Override
	public List<Genre> getAllGenre() throws Exception {
		logger.debug("Call method getAllGenre in GenreDao from GenreService");
		try {
			return genreDAO.getAllGenre();
		} catch (Exception e) {
			logger.error("Failed to get all Genre ", e);
			throw e;
		}
	}

	@Override
	public Genre getGenre(Long genreID) throws Exception {
		logger.debug("Call method getGenre in GenreDao from GenreService");
		try {
			return genreDAO.getGenre(genreID);
		} catch (Exception e) {
			logger.error("Failed to getGenre by ID: " + genreID, e);
			throw e;
		}
	}

	private void setGenreName(Genre genre, String name) throws Exception {
		if (name != null && !name.isEmpty()) {
			Genre curentGenres = genreDAO.getGenreByName(name);
			if (curentGenres != null) {
				logger.error("Genres" + name + " existed!");
				throw new Exception("Genres " + name + " existed!");
			}
			genre.setGenreName(name);
		}
	}

	@Override
	public Long add(LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method addGenres on GenresDao from GenresSerVice");
		try {
			Genre genre = new Genre();
			Set<String> genreKey = obj.keySet();
			for (String key : genreKey) {
				setGenreValue(genre, key, obj.get(key));
			}
			genreDAO.addGenre(genre);
			return genre.getGenreId();
		} catch (Exception e) {
			logger.error("Failed to add Genre: ", e);
			throw e;
		}
	}

	@Override
	public void update(Long genreID, LinkedHashMap<String, Object> obj) throws Exception {
		logger.debug("Call method updateGenre in GenreDao from GenreService");
		try {
			logger.debug("Edit Genre with genreID: " + genreID);
			Genre genre = genreDAO.getGenre(genreID);
			if (genre == null) {
				logger.error("genreID: " + genreID + "not exists");
				throw new Exception("Genre not exists");
			}
			Set<String> genreKey = obj.keySet();
			for (String key : genreKey) {
				setGenreValue(genre, key, obj.get(key));
			}
			genreDAO.updateGenre(genre);
		} catch (Exception e) {
			logger.error("Failed to edit genre: " + genreID, e);
			throw e;
		}
	}

	@Override
	public void remove(Long genreID) throws Exception {
		logger.debug("Call method removeGenre in GenreDao from GenreService");
		try {
			Genre obj = genreDAO.getGenre(genreID);
			if (obj == null) {
				logger.error("Genre Id=" + genreID + " doesn't exist!");
				throw new Exception("Genre " + genreID + " doesn't exist!");
			}
			genreDAO.removeGenre(obj);
		} catch (Exception e) {
			logger.error("Failed to remove Genre by Id = " + genreID, e);
			throw e;
		}
	}

	private void setGenreValue(Genre genre, String keyParam, Object genreValue) throws Exception {
		if (genreValue != null) {
			String value = genreValue.toString().trim();
			if ("genreName".equals(keyParam) && value != null) {
				genre.setGenreName(value);
			} else if ("genreDesc".equals(keyParam) && value != null) {
				genre.setGenreDesc(value);
			}
		}
	}

	@Override
	public List<Genre> searchGenre(String genreName) throws Exception {
		logger.debug("Call method searchGenre in GenreDAO from GenreService");
		try {
			return genreDAO.searchGenre(genreName);
		} catch (Exception e) {
			logger.error("Failed to search Genre: " + genreName, e);
			throw e;
		}
	}
}
