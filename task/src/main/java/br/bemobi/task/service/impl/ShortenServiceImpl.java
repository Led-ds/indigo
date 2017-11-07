package br.bemobi.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.bemobi.task.dao.ShortenDAO;
import br.bemobi.task.entity.Shorten;
import br.bemobi.task.service.ShortenService;

@Service("shortenService")
public class ShortenServiceImpl implements ShortenService{
	
	@Autowired
	private ShortenDAO shortenDAO;
	
	public void setShortenDAO(ShortenDAO prShortenDAO){
		this.shortenDAO = prShortenDAO;
	}
	
	public String save(Shorten prShorten) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Long id = shortenDAO.save(prShorten);
			//newShorten = shortenDAO.getById(id);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map.toString();
	}

	public Shorten update(Long prId, Shorten prShorten) {
		// TODO Auto-generated method stub
		try {
			shortenDAO.update(prShorten);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prShorten;
	}

	public void delete(Shorten prShorten) {
		// TODO Auto-generated method stub
		shortenDAO.delete(prShorten);
	}

	public List<Shorten> findAll() {
		// TODO Auto-generated method stub
		return shortenDAO.findAll();
	}

	public Shorten getById(Long prId) {
		// TODO Auto-generated method stub
		return shortenDAO.getById(prId);
	}

}
