package musicmgr.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import musicmgr.dao.GenreDAO;
import musicmgr.model.Genre;

@Transactional
public class GenreDAOImpl implements GenreDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getAllGenre() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Genre").list();
	}

	@SuppressWarnings("unchecked")
	public Genre getGenreByName(String name) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		Query<Genre> query = session.createQuery("From Genre Where Name Like :name");
		query.setParameter("name", name);
		return query.uniqueResult();
	}

	@Override
	public Genre getGenre(Long genreID) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Genre.class, genreID);
	}

	@Override
	public Long addGenre(Genre genre) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		return (Long)session.save(genre);

	}

	@Override
	public void updateGenre(Genre genre) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.update(genre);

	}

	@Override
	public void removeGenre(Genre genre) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(genre);

	}

	@Override
	public List<Genre> searchGenre(String genreName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Query<Genre> query = session.createQuery("FROM Genre WHERE genreName LIKE :genreName");
		query.setParameter("genreName", '%' + genreName + '%');
		return query.list();
	}

}
