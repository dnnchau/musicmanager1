package musicmgr.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.SongDAO;
import musicmgr.model.Song;

@Transactional
public class SongDAOImpl implements SongDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	public SessionFactory getFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Song> getAllSong() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Song").list();
	}

	public Song getSong(Integer songID) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Song.class, songID);
	}

	public void addSong(Song song) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(song);
	}

	public void updateSong(Song song) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(song);
	}

	public void removeSong(Song obj) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
	}

	public List<Song> searchSongbyName(String songName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Song> query = session.createQuery("FROM Song WHERE songName LIKE :songName");
		query.setParameter("songName", '%' + songName + '%');
		return query.list();
	}

	public List<Song> searchSongbyID(Integer songID) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Song> query = session.createQuery("FROM Song WHERE songID LIKE :songID");
		query.setParameter("songID", songID);
		return query.list();
	}

	public Song getSongByName(String songName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Song> query = session.createQuery("From Song WHERE songName Like :songName");
		query.setParameter("songName", '%' + songName + '%');
		return query.uniqueResult();
	}

}
