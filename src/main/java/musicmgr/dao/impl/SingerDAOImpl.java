package musicmgr.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.SingerDAO;
import musicmgr.model.Singer;

@Transactional
public class SingerDAOImpl implements SingerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Singer> getAllSinger() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Singer").list();
	}

	@Override
	public Singer getSinger(Long singerID) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Singer.class, singerID);
	}

	@Override
	public Long addSinger(Singer singer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(singer);
	}

	@Override
	public void updateSinger(Singer singer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(singer);

	}

	@Override
	public void removeSinger(Singer singer) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(singer);

	}

	@Override
	public List<Singer> searchSinger(String singerName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Singer> query = session.createQuery("FROM Singer WHERE singerName LIKE :singerName");
		query.setParameter("singerName", '%' + singerName + '%');
		return query.list();
	}

}
