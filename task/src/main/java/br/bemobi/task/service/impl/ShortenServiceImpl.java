package br.bemobi.task.service.impl;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import br.bemobi.task.dao.ShortenDAO;
import br.bemobi.task.entity.Shorten;
import br.bemobi.task.service.ShortenService;

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
					int identificador = Integer.parseInt(Hashing.murmur3_32().hashString(prShorten.getOriginalUrl(), StandardCharsets.UTF_8).toString().trim(), 16);
					String alias = this.convertTo(identificador);
					
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
					message.clear();
					message.put("time_taken", time);
					
					jsonObj.put("url", shorten.getShortUrl());
					jsonObj.put("statistics", message);
					
					message.clear();
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
	
	public String convertTo(int prToBeConverted) {
		String[] elements = {
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
                "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
                "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
                "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                "Y","Z"
                };
		
        String convertedString = "";
        int numOfDiffChars= elements.length;
        
        if(prToBeConverted < numOfDiffChars+1 && prToBeConverted>0){
            convertedString = elements[(int) (prToBeConverted-1)];
        }
        else
        	if(prToBeConverted>numOfDiffChars){
            long mod = 0;
            long multiplier = 0;
            boolean determinedTheLength = false;
           
            for(int j = 6; j >= 0; j--){
                multiplier = (long) (prToBeConverted/Math.pow(numOfDiffChars, j));
                
                if(multiplier > 0 && prToBeConverted >= numOfDiffChars){
                    convertedString += elements[(int) multiplier];
                    
                    determinedTheLength = true;
                }
                
                else
                	if(determinedTheLength && multiplier == 0){
                		convertedString += elements[0];
                }
                else
                	if(prToBeConverted < numOfDiffChars){                    
                		convertedString += elements[(int) mod];
                }
                
                mod= (int) (prToBeConverted % Math.pow(numOfDiffChars, j));
                
                prToBeConverted = (int) mod;                
            }
            
        }
        return convertedString;
	}

}