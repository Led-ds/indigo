package br.bemobi.task.dao.impl;

import org.springframework.stereotype.Repository;

import br.bemobi.task.dao.ShortenDAO;
import br.bemobi.task.entity.Shorten;

@Repository("shortenDAO")
public class ShortenDAOImpl extends GenericDAOImpl<Long, Shorten> implements ShortenDAO{

	public ShortenDAOImpl(){
		super();
	}
}
