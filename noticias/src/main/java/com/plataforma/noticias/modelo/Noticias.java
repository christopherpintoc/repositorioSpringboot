package com.plataforma.noticias.modelo;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
public class Noticias {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito", nullable = false, unique = true)
    @Id
    private Long idFavorito;

    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "news_site")
    private String newsSite;

    @Column(name = "summary")
    private String summary;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "uptated_at")
    private LocalDateTime uptatedAt;

    @Column(name = "featured")
    private boolean featured;

    //get y set
    public Long getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Long idFavorito) {
        this.idFavorito = idFavorito;
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
