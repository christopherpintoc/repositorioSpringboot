package com.plataforma.noticias.service;
import com.plataforma.noticias.modelo.Noticias;
import com.plataforma.noticias.dao.INoticiasDao;
import com.plataforma.noticias.response.NoticiasResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.*;

@Service
public class NoticiasServImpl implements IntNoticiasServ {
    private static final Logger log = (Logger) LoggerFactory.getLogger(NoticiasServImpl.class);

    @Autowired
    private INoticiasDao noticiasDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<NoticiasResponseRest> getFavoritos() {
        NoticiasResponseRest response = new NoticiasResponseRest();
        log.info("Inicio método getFavoritos()");
        try {
            Iterable<Noticias> noticias = noticiasDao.findAll();
            if (noticias == null || !noticias.iterator().hasNext()) {
                //Error controlado en caso de no encontrar favoritos
                response.setMetadata("404", "Favoritos no encontrados");
                log.error("Favoritos no encontrados - 404");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
            }
            else{
                //Caso correcto
                response.setMetadata("200", "OK");
                response.getNoticiasResponse().setNoticias(noticias);
                log.info("Favoritos encontrados - 200");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            //Error de plataforma
            response.setMetadata("500", "Error en consultar favoritos");
            log.error("Error en consultar favoritos: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<NoticiasResponseRest> getFavoritosPorTitulo(String title) {
        log.info("Inicio método getFavoritosPorTitulo");
        NoticiasResponseRest response = new NoticiasResponseRest();
        try {
            Iterable<Noticias> noticias = noticiasDao.findByTitleContainingIgnoreCase(title); //uso de metodo de intefaz Dao
            if(noticias == null || !noticias.iterator().hasNext()) {
                //Error controlado en caso de no encontrar el favorito
                response.setMetadata("404", "Favorito no encontrado");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
            }
            else {
                //Caso correcto
                response.getNoticiasResponse().setNoticias(noticias);
                response.setMetadata("200", "OK");
                log.info("Favorito encontrado - 200");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            //Error de plataforma
            response.setMetadata("500", "Error en consultar favorito");
            log.error("Error en consultar Favorito "+e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<NoticiasResponseRest> updateFavoritos(Noticias noticias, Long idFavorito) {
        log.info("Inicio método updateFavoritos");
        NoticiasResponseRest response = new NoticiasResponseRest();
        int validacion = validarDatos(noticias);
        if (validacion == 0) {
            try {
                Optional<Noticias> optionalNoticia = noticiasDao.findById(idFavorito);
                if (optionalNoticia.isPresent()) {
                    //Caso existoso, se implementa funcion getNoticias para actualizar
                    Noticias existingNoticia = getNoticias(noticias, optionalNoticia);
                    noticiasDao.save(existingNoticia);
                    response.setMetadata("200", "OK");
                    return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
                } else {
                    //Error controlado en caso de no encontrar el favorito para actualizar
                    response.setMetadata("404", "Favorito no encontrado para actualizar");
                    return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                //Error de plataforma
                response.setMetadata("500", "Error al actualizar favorito: " + e.getMessage());
                log.error("Error al actualizar favorito: " + e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            //Casos de errores controlados
            switch (validacion) {
                case 1:
                    log.info("No esta enviando el request de entrada");
                    response.setMetadata("500", "Request sin data");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 2:
                    log.info("Id es NULL");
                    response.setMetadata("500", "Id no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 3:
                    log.info("title es NULL");
                    response.setMetadata("500", "title no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 4:
                    log.info("url es NULL");
                    response.setMetadata("500", "url no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                    log.info("Error en switch setFavorito");
                    response.setMetadata("500", "Error al guardar favoritos");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    //Funcion extra para ser utilizada al actualizar favorito en el metodo updateFavoritos
    private static Noticias getNoticias(Noticias noticias, Optional<Noticias> optionalNoticia) {
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
        return existingNoticia;
    }

    @Override
    @Transactional
    public ResponseEntity<NoticiasResponseRest> setFavorito(Noticias noticias) {
        log.info("Inicio método setFavoritos");
        NoticiasResponseRest response = new NoticiasResponseRest();
        int validacion = validarDatos(noticias);
        if (validacion == 0) {
            try {
                //Caso correcto
                noticiasDao.save(noticias);
                response.setMetadata("200", "OK");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
            }catch(Exception e){
                //Error de plataforma
                response.setMetadata("500", "Error al guardar favorito: "+ e.getMessage());
                log.error("Error al guardar favorito: "+e.getMessage());
                e.printStackTrace();
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            //Casos de errores controlados
            switch (validacion) {
                case 1:
                    log.info("No esta enviando el request de entrada");
                    response.setMetadata("500", "Request sin data");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 2:
                    log.info("Id es NULL");
                    response.setMetadata("500", "Id no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 3:
                    log.info("title es NULL");
                    response.setMetadata("500", "title no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                case 4:
                    log.info("url es NULL");
                    response.setMetadata("500", "url no puede ir vacío");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                default:
                  /*  log.info("Error en switch setFavorito"); */
                    response.setMetadata("500", "Error al guardar favoritos");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<NoticiasResponseRest> deleteFavoritos(Long idFavorito) {
        log.info("Inicio método deleteFavoritos");
        NoticiasResponseRest response = new NoticiasResponseRest();
        try {
            Optional<Noticias> optionalNoticia = noticiasDao.findById(idFavorito);
            if(optionalNoticia.isPresent()){
                //Caso exitoso
                noticiasDao.deleteById(idFavorito);
                response.setMetadata("200", "OK");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
            }else {
                //Caso no exitoso
                response.setMetadata("404", "Favorito no encontrado");
                return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch(Exception e) {
            //Error de plataforma
            response.setMetadata("500", "Error al borrar favorito: " + e.getMessage());
            log.error("Error al borrar Favorito "+e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Funcion para validar data de entrada
    public int validarDatos(Noticias noticias) {
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
        return Error =0;
    }

}
