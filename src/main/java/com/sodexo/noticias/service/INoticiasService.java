package com.sodexo.noticias.service;

import org.springframework.http.ResponseEntity;
import com.sodexo.noticias.modelo.Noticias;
import com.sodexo.noticias.response.NoticiasResponseRest;

public interface INoticiasService {

	public ResponseEntity<NoticiasResponseRest> obtenerFavoritos();
	public ResponseEntity<NoticiasResponseRest> buscarFavoritosPorTitulo(String title);
	public ResponseEntity<NoticiasResponseRest> actualizarFavoritos(Noticias noticias, Long idFavorite);
	public ResponseEntity<NoticiasResponseRest> guardarFavorito(Noticias noticias);
	public ResponseEntity<NoticiasResponseRest> borrarFavoritos(Long idFavorite);

}
