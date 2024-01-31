package com.plataforma.noticias.service;
import com.plataforma.noticias.modelo.Noticias;
import com.plataforma.noticias.response.NoticiasResponseRest;
import org.springframework.http.ResponseEntity;

public interface IntNoticiasServ {

    public ResponseEntity<NoticiasResponseRest> getFavoritos();
    public ResponseEntity<NoticiasResponseRest> getFavoritosPorTitulo(String title);
    public ResponseEntity<NoticiasResponseRest> updateFavoritos(Noticias noticias, Long idFavorito);
    public ResponseEntity<NoticiasResponseRest> setFavorito(Noticias noticias);
    public ResponseEntity<NoticiasResponseRest> deleteFavoritos(Long idFavorito);

}
