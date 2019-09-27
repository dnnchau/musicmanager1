package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Composer;
import musicmgr.model.Song;

public interface ComposerService {
	public List<Composer> getAllComposer() throws Exception;

	public int countAllComposer() throws Exception;

	Composer getComposer(Integer composerID) throws Exception;

	public void add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(int composerID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(int composerID) throws Exception;

	public List<Composer> searchComposer(String composerName) throws Exception;
}
