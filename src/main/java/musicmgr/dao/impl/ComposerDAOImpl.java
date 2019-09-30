package musicmgr.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.ComposerDAO;
import musicmgr.model.Composer;

@Transactional
public class ComposerDAOImpl implements ComposerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ComposerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Composer> getAllComposer() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Composer").list();
	}

	@Override
	public Composer getComposer(Long composerID) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Composer.class, composerID);
	}

	@Override
	public Long addComposer(Composer composer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(composer);

	}

	@Override
	public void updateComposer(Composer composer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(composer);

	}

	@Override
	public void removeComposer(Composer composer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(composer);

	}

	@Override
	public List<Composer> searchComposer(String composerName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Composer> query = session.createQuery("FROM Composer WHERE composerName LIKE :composerName");
		query.setParameter("composerName", '%' + composerName + '%');
		return query.list();
	}

}
