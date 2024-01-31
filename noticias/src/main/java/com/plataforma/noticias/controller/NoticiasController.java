package com.plataforma.noticias.controller;

import com.plataforma.noticias.response.NoticiasResponseRest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.plataforma.noticias.modelo.Noticias;
import com.plataforma.noticias.service.IntNoticiasServ;

@CrossOrigin (origins = {"*"})
@RestController
@RequestMapping("/apiNoticias")
public class NoticiasController {

    @Autowired
    private IntNoticiasServ service;

    @PostMapping("/setFavorito")
    public ResponseEntity<NoticiasResponseRest> setFavorito(@RequestBody Noticias request){
        ResponseEntity<NoticiasResponseRest> response = service.setFavorito(request);
        return response;
    }

    @PutMapping("/updateFavoritos/{idFavorito}")
    public ResponseEntity<NoticiasResponseRest> updateFavoritos(@RequestBody Noticias request, @PathVariable Long idFavorito){
        ResponseEntity<NoticiasResponseRest> response = service.updateFavoritos(request, idFavorito);
        return response;
    }

    @GetMapping("/getFavoritos")
    public ResponseEntity<NoticiasResponseRest> getFavoritos(){
        ResponseEntity<NoticiasResponseRest> response = service.getFavoritos();
        return response;
    }

    @GetMapping("/getFavoritosPorTitulo/{title}")
    public ResponseEntity<NoticiasResponseRest> getFavoritosPorTitulo(@PathVariable String title){
        ResponseEntity<NoticiasResponseRest> response = service.getFavoritosPorTitulo(title);
        return response;
    }

    @DeleteMapping("/deleteFavoritos/{idFavorito}")
    public ResponseEntity<NoticiasResponseRest> deleteFavoritos(@PathVariable Long idFavorito){
        ResponseEntity<NoticiasResponseRest> response = service.deleteFavoritos(idFavorito);
        return response;
    }

}