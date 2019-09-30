package musicmgr.dao;

import java.util.List;

import musicmgr.model.Composer;

public interface ComposerDAO {
	List<Composer> getAllComposer() throws Exception;

	public Composer getComposer(Long somposerID) throws Exception;

	public Long addComposer(Composer composer) throws Exception;

	public void updateComposer(Composer composer) throws Exception;

	public void removeComposer(Composer composer) throws Exception;

	public List<Composer> searchComposer(String composerName) throws Exception;
}
