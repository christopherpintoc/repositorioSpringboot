package com.sodexo.noticias.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabla_favoritos")
public class Noticias {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorite", nullable = false, unique = true)
    private Long idFavorite;
	
	@Column(name = "id", length = 55)
	private String id;
	
	@Column(name = "title", length = 255)
	private String title;
	
	@Column(name = "url", length = 255)
	private String url;

	@Column(name = "image_url", length = 255)
	private String imageUrl;
	
	@Column(name = "news_site", length = 255)
	private String newsSite;
	
	@Column(name = "summary", length = 512)
	private String summary;
	
	@Column(name = "published_at")
	private LocalDateTime publishedAt;
	
	@Column(name = "uptated_at")
	private LocalDateTime uptatedAt;
	
	@Column(name = "featured", length  = 15)
	private boolean featured;

	public Long getIdFavorite() {
		return idFavorite;
	}

	public void setIdFavorite(Long idFavorite) {
		this.idFavorite = idFavorite;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNewsSite() {
		return newsSite;
	}

	public void setNewsSite(String newsSite) {
		this.newsSite = newsSite;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public LocalDateTime getUptatedAt() {
		return uptatedAt;
	}

	public void setUptatedAt(LocalDateTime uptatedAt) {
		this.uptatedAt = uptatedAt;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
	
}
