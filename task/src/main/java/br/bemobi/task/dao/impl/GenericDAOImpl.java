package br.bemobi.task.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.bemobi.task.dao.GenericDAO;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class GenericDAOImpl<PK, T> implements GenericDAO<PK, T> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory prSessionFactory){
		this.sessionFactory = prSessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAlias(String alias) throws HibernateException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction ts = session.beginTransaction();
			String queryHQL = " FROM " + getTypeClass().getName() + " WHERE customeAlias like :customeAlias";
			Query query = session.createQuery(queryHQL).setParameter("customeAlias", alias);

			List<T> list = (List<T>) query.list();        
			ts.commit();

			return list;

		} catch (HibernateException hex) {
			hex.getStackTrace();
			throw hex;
		}
	}

	@SuppressWarnings("unchecked")
	public T getById(PK id) throws HibernateException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction ts = session.beginTransaction();
			String queryHQL = " FROM " + getTypeClass().getName() + " WHERE ID =" + id;
			Query query = session.createQuery(queryHQL);

			T t = (T) query.uniqueResult();
			ts.commit();

			if(t != null){
				return t;
			}

			return null;
		} catch (HibernateException hex) {
			hex.getStackTrace();
			throw hex;
		}
	}

	public Long save(T entity) throws HibernateException {
		Long id = 0L;
		try{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			id = (Long) session.save(entity);
			tx.commit();

		}catch(HibernateException he){
			he.getStackTrace();
			throw he;    		
		}   	

		return id;
	}

	public void update(T entity) throws HibernateException {
		try{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.update(entity);
			tx.commit();

		}catch(HibernateException he){
			he.getStackTrace();
			throw he;    		
		} 

	}

	public void delete(T entity) throws HibernateException {
		try{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		}catch(HibernateException he){
			he.getStackTrace();
			throw he;    		
		} 

	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() throws HibernateException {
		try{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			String hql = "from " + getTypeClass().getName();
			Query query = session.createQuery(hql);       
			List<T> list = (List<T>) query.list();        
			tx.commit();

			if (list != null && !list.isEmpty()) {
				return list;
			}

			return null;
		}catch(HibernateException he){
			he.getStackTrace();
			throw he;    		
		}
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}

}
