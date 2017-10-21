package br.bemobi.task.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHORTEN")
public class Shorten implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Shorten(){
		super();
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "originalUrl")
	private String originalUrl;

	@Column(name = "shortUrl")
	private String shortUrl;
	
	@Column(name = "customeAlias")
	private String customeAlias;
	
	@Column(name = "created")
	private Date created;
	
	public Shorten(Long prId, String prOriginalUrl, String prShortUrl, String prCustomeAlias, Date prCreated){
		super();
		this.id = prId;
		this.originalUrl = prOriginalUrl;
		this.shortUrl = prShortUrl;
		this.customeAlias = prCustomeAlias;
		this.created = prCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getCustomeAlias() {
		return customeAlias;
	}

	public void setCustomeAlias(String customeAlias) {
		this.customeAlias = customeAlias;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
