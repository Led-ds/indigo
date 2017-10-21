package br.bemobi.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.bemobi.task.entity.Shorten;
import br.bemobi.task.service.ShortenService;

@RestController
@RequestMapping(value = "/rest/shortens")
public class ShortenRestController {

	@Autowired
	private ShortenService shortenService;
	
	public void setShortenService(ShortenService prShortenService){
		this.shortenService = prShortenService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Shorten>> getshortens() {
		List<Shorten> shortens = new ArrayList<Shorten>();        
		shortens = shortenService.findAll();
		if(!shortens.isEmpty()){
			return new ResponseEntity<List<Shorten>>(shortens, HttpStatus.OK);
		}		
		
		return new ResponseEntity<List<Shorten>>(HttpStatus.NOT_FOUND);		        
    }
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<Shorten> getShortenById (@PathVariable("id") Long prId) {
		Shorten shorten = null;
    	if(prId > 0){
    		shorten = shortenService.getById(prId);    	
            if (shorten != null) {        	
                return new ResponseEntity<Shorten>(shorten, HttpStatus.OK);
            }
    	}
    	
        return new ResponseEntity<Shorten>(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Shorten> create(@RequestBody Shorten prShorten) {
    	if(prShorten != null){
    		shortenService.save(prShorten);
    		Shorten shortenAtual = shortenService.getById(prShorten.getId());    
        	if(shortenAtual != null){
        		return new ResponseEntity<Shorten>(shortenAtual, HttpStatus.OK);
        	}
    	}    	
     
    	return new ResponseEntity<Shorten>(HttpStatus.NOT_FOUND);
    }  
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Shorten> update(@PathVariable("id") Long prId, @RequestBody Shorten prShorten) {
    	if(prShorten != null){
    		shortenService.update(prId, prShorten);
        	Shorten shortenAtual = shortenService.getById(prShorten.getId());    
        	if(shortenAtual != null){
        		return new ResponseEntity<Shorten>(shortenAtual, HttpStatus.OK);
        	}
    	}    	
     
    	return new ResponseEntity<Shorten>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long prId) {
    	Shorten shorten = null;
    	if(prId > 0){
    		shorten = shortenService.getById(prId);
    		if(shorten != null){
    			shortenService.delete(shorten);
        		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
        	}
    	}  	
    	
    	return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.NOT_FOUND);     
    }
}
