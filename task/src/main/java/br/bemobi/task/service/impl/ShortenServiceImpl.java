package br.bemobi.task.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bemobi.task.dao.ShortenDAO;
import br.bemobi.task.entity.Shorten;
import br.bemobi.task.service.ShortenService;
import br.bemobi.task.util.ToConvertUrlIn;

@Service("shortenService")
public class ShortenServiceImpl implements ShortenService{
	
	private static final String DOMINIO = "http://shortener/";
	private static final String ERR_ALIAS = "CUSTOM ALIAS ALREADY EXISTS";
	private HashMap<String, Object> message;
	private JSONObject jsonObj;
	
	@Autowired
	private ShortenDAO shortenDAO;
	
	public void setShortenDAO(ShortenDAO shortenDAO){
		this.shortenDAO = shortenDAO;
	}
	
	@SuppressWarnings({ "static-access" })
	public HashMap<String, Object> save(Shorten prShorten) {
		this.jsonObj = new JSONObject();
		this.message = new HashMap<String, Object>();
		
		try {
			if(new UrlValidator(new String[]{"http", "https"}).isValid(prShorten.getOriginalUrl())) {
				message.put("ERRO", "Erro: Url invalid...");
			}else{
				//verificar se existe "alias" cadastrado
				if(prShorten.getCustomeAlias() != null){					
					List<Shorten> lcoShorten = shortenDAO.findAlias(prShorten.getCustomeAlias());
					
					if(lcoShorten != null && lcoShorten.size()>0){
						Shorten lShorten =  lcoShorten.get(0);
						jsonObj.put("alias", lShorten.getCustomeAlias());
						jsonObj.put("err_code", "001");
						jsonObj.put("description", this.ERR_ALIAS);
						
						message.clear();
						message.put("ERRO", jsonObj);
					}else{
						prShorten.setShortUrl(DOMINIO+prShorten.getCustomeAlias());
						jsonObj.put("alias", prShorten.getCustomeAlias());
					}
					
				}else{					
					
					String alias = ToConvertUrlIn.convertedToString(prShorten.getCustomeAlias());
					prShorten.setCustomeAlias(alias);
					prShorten.setShortUrl(DOMINIO+alias);
					
					jsonObj.put("alias", alias);
				}
				
				if(!message.containsKey("ERRO")){					
					long timeExecute = System.currentTimeMillis();
					String time = (new SimpleDateFormat("mm:ss").format(new Date(timeExecute)));
					prShorten.setCreated(new Date());
					
					Long id = shortenDAO.save(prShorten);					
					Shorten shorten = shortenDAO.getById(id);
					
					jsonObj.put("url", shorten.getShortUrl());
					jsonObj.put("statistics", message);
					
					message.clear();
					message.put("time_taken", time);
					message.put("SUCCESS", jsonObj);					
				}
			}			
		} catch (Exception e) {
			message.put("ERRO", e.getMessage().toString());
		}
		
		return message;
	}

	public Shorten update(Long prId, Shorten prShorten) {
		try {
			shortenDAO.update(prShorten);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prShorten;
	}

	public void delete(Shorten prShorten) {
		shortenDAO.delete(prShorten);
	}

	public List<Shorten> findAll() {
		return shortenDAO.findAll();
	}

	public Shorten getById(Long prId) {
		return shortenDAO.getById(prId);
	}
	
	public Shorten findByShortUrl(String prShortUrl) {
		return shortenDAO.getByShortUrl(prShortUrl);
	}

}