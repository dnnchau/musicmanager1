package musicmgr.service;

import java.util.LinkedHashMap;
import java.util.List;

import musicmgr.model.Composer;
import musicmgr.model.Song;

public interface ComposerService {
	public List<Composer> getAllComposer() throws Exception;

	Composer getComposer(Long composerID) throws Exception;

	public Long add(LinkedHashMap<String, Object> obj) throws Exception;

	public void update(Long composerID, LinkedHashMap<String, Object> obj) throws Exception;

	public void remove(Long composerID) throws Exception;

	public List<Composer> searchComposer(String composerName) throws Exception;
}
