package br.bemobi.task.service;

import java.util.HashMap;
import java.util.List;

import br.bemobi.task.entity.Shorten;

public interface ShortenService {

	public HashMap<String, Object> save(Shorten prShorten);
	
	public Shorten update(Long id, Shorten prShorten);
	
	public void delete(Shorten prShorten);
	
	public List<Shorten> findAll();
	
	public Shorten getById(Long id);
	
	public String convertTo(int prToBeConverted);
}