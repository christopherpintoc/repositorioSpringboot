package com.sodexo.noticias.service;
import com.sodexo.noticias.modelo.Noticias;
import com.sodexo.noticias.modelo.dao.INoticiasDao;
import com.sodexo.noticias.response.NoticiasResponse;
import com.sodexo.noticias.response.NoticiasResponseRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class NoticiasServiceImpl implements INoticiasService{
	private static final Logger log = (Logger) LoggerFactory.getLogger(NoticiasServiceImpl.class);
	
	@Autowired
	private INoticiasDao noticiasDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<NoticiasResponseRest> obtenerFavoritos() {
		log.info("Inicio método obtenerFavoritos()");
		NoticiasResponseRest response = new NoticiasResponseRest();
		try {
			Iterable<Noticias> noticias = noticiasDao.findAll();
			if (noticias == null || !noticias.iterator().hasNext()) {
				response.setMetada("404", "Favoritos no encontrados");
				log.error("Favoritos no encontrados: ");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			else{
				response.getNoticiasResponse().setNoticias(noticias);
				response.setMetada("200", "OK");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.setMetada("500", "Error en consultar favoritos");
			log.error("Error en consultar favoritos: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<NoticiasResponseRest> buscarFavoritosPorTitulo(String title) {
		log.info("Inicio método buscarFavoritosPorTitulo");
		NoticiasResponseRest response = new NoticiasResponseRest();
		try {
			Iterable<Noticias> noticias = noticiasDao.findByTitleContainingIgnoreCase(title);
			if(noticias == null || !noticias.iterator().hasNext()) {
				response.setMetada("404", "Favorito no encontrado");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			else {
				response.getNoticiasResponse().setNoticias(noticias);
				response.setMetada("200", "OK");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
			}
		}catch (Exception e) {
			response.setMetada("500", "Error en consultar favorito");
			log.error("Error en consultar Favorito "+e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<NoticiasResponseRest> actualizarFavoritos(Noticias noticias, Long idFavorite) {
		log.info("Inicio método actualizarFavoritos");
		NoticiasResponseRest response = new NoticiasResponseRest();
		try {
			Optional<Noticias> optionalNoticia = noticiasDao.findById(idFavorite);
			if(optionalNoticia.isPresent()) {
				Noticias existingNoticia = optionalNoticia.get();
				existingNoticia.setId(noticias.getId()); //id desde el front
				existingNoticia.setTitle(noticias.getTitle());
				existingNoticia.setUrl(noticias.getUrl());
				existingNoticia.setImageUrl(noticias.getImageUrl());
				existingNoticia.setNewsSite(noticias.getNewsSite());
				existingNoticia.setSummary(noticias.getSummary());
				existingNoticia.setPublishedAt(noticias.getPublishedAt());
				existingNoticia.setUptatedAt(noticias.getUptatedAt());
				existingNoticia.setFeatured(noticias.isFeatured());
				
				noticiasDao.save(existingNoticia);
				response.setMetada("200", "OK");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
			}else {
				response.setMetada("404", "Favorito no entontrado");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetada("500", "Error al actualizar favorito: " +e.getMessage());
			log.error("Error al actualizar favorito: "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<NoticiasResponseRest> guardarFavorito(Noticias noticias) {
		log.info("Inicio método guardarNoticia");
		NoticiasResponseRest response = new NoticiasResponseRest();
		int validacion = validarDatosNulos(noticias);	
		if (validacion == 0) {
			try {
				noticiasDao.save(noticias);
				response.setMetada("200", "OK");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
				
			}catch(Exception e){
				response.setMetada("500", "Error al guardar favorito: "+ e.getMessage());
				log.error("Error al guardar favorito: "+e.getMessage());
				e.printStackTrace();
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			switch (validacion) {
	        case 1:
	        	log.info("No esta enviando el objeto");
				response.setMetada("500", "Request sin data");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 2:
	        	log.info("Id es NULL");
				response.setMetada("500", "Id no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 3:
	        	log.info("title es NULL");
				response.setMetada("500", "title no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 4:
	        	log.info("url es NULL");
				response.setMetada("500", "url no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 5:
	        	log.info("image_url es NULL");
				response.setMetada("500", "image_url no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 6:
	        	log.info("news_site es NULL");
				response.setMetada("500", "news_site no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 7:
	        	log.info("summary es NULL");
				response.setMetada("500", "summary no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 8:
	        	log.info("published_at es NULL");
				response.setMetada("500", "summary no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        case 9:
	        	log.info("updated_at es NULL");
				response.setMetada("500", "summary no puede ser NULL");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        default:
	        	log.info("Error en switch guardarFavoritos");
				response.setMetada("500", "Error al guardar");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	@Transactional
	public ResponseEntity<NoticiasResponseRest> borrarFavoritos(Long idFavorite) {
		log.info("Inicio método guardarNoticia");
		NoticiasResponseRest response = new NoticiasResponseRest();
		try {
			Optional<Noticias> optionalNoticia = noticiasDao.findById(idFavorite);
			if(optionalNoticia.isPresent()){
				noticiasDao.deleteById(idFavorite);
				response.setMetada("200", "OK");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
			}else {
				response.setMetada("404", "Favorito no encontrado");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			response.setMetada("500", "Error al borrar favorito: " + e.getMessage());
			log.error("Error al borrar Favorito "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//valida campos de entrada
	public int validarDatosNulos(Noticias noticias) {
		int Error;		
	    
		if (noticias == null) {
	        return Error = 1;
	    }

	    String id = noticias.getId();
	    if (id == null) {
	        return Error = 2;
	    }

	    String title = noticias.getTitle();
	    if (title == null) {
	        return Error = 3;
	    }
	    
	    String url = noticias.getUrl();
	    if (url == null) {
	        return Error = 4;
	    }
	    
	    String imageUrl = noticias.getImageUrl();
	    if (imageUrl == null) {
	        return Error = 5;
	    }
	    
	    String news_site = noticias.getNewsSite();
	    if (news_site == null) {
	        return Error = 6;
	    }
	    
	    String summary = noticias.getSummary();
	    if (summary == null) {
	        return Error = 7;
	    }
	    
	    LocalDateTime publishedAt = noticias.getPublishedAt();
	    if (publishedAt == null) {
	        return Error = 8;
	    }
	    
	    LocalDateTime updatedAt = noticias.getUptatedAt();
	    if (updatedAt == null) {
	        return Error = 9;
	    }
	    return Error =0;
	}

}
