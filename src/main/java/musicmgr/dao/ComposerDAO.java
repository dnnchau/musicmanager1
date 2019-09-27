package musicmgr.dao;

import java.util.List;

import musicmgr.model.Composer;

public interface ComposerDAO {
	List<Composer> getAllComposer() throws Exception;

	public int countAllComposer() throws Exception;

	public Composer getComposer(Integer somposerID) throws Exception;

	public void addComposer(Composer composer) throws Exception;

	public void updateComposer(Composer composer) throws Exception;

	public void removeComposer(Composer composer) throws Exception;

	public List<Composer> searchComposer(String composerName) throws Exception;
}
