package br.bemobi.task.dao;

import java.util.List;

import org.hibernate.HibernateException;

public interface GenericDAO<PK, T> {

	public T getById(PK prId) throws HibernateException;
	
	public T getByShortUrl(String prShortUrl) throws HibernateException;
	
	public Long save(T prEntity) throws HibernateException;
	
	public void update(T prEntity) throws HibernateException;
	
	public void delete(T prEntity) throws HibernateException;
	
	public List<T> findAll() throws HibernateException;
	
	public List<T> findAlias(String prAlias) throws HibernateException;
}
