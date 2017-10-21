package br.bemobi.task.dao;

import java.util.List;

import org.hibernate.HibernateException;

public interface GenericDAO<PK, T> {

	public T getById(PK id) throws HibernateException;
	
	public Long save(T entity) throws HibernateException;
	
	public void update(T entity) throws HibernateException;
	
	public void delete(T entity) throws HibernateException;
	
	public List<T> findAll() throws HibernateException;	
}
