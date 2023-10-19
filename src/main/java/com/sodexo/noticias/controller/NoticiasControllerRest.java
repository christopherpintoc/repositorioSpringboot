package com.sodexo.noticias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sodexo.noticias.modelo.Noticias;
import com.sodexo.noticias.response.NoticiasResponseRest;
import com.sodexo.noticias.service.INoticiasService;

@CrossOrigin
@RestController
@RequestMapping("/apiSodexo")
public class NoticiasControllerRest {
	
	@Autowired
	private INoticiasService service;
	
	@GetMapping("/obtenerFavoritos")
	public ResponseEntity<NoticiasResponseRest> obtenerFavoritos(){	
		ResponseEntity<NoticiasResponseRest> response = service.obtenerFavoritos();
		return response;
	}
	
	@GetMapping("/buscarFavoritosPorTitulo/{title}")
	public ResponseEntity<NoticiasResponseRest> buscarFavoritosPorTitulo(@PathVariable String title){
		ResponseEntity<NoticiasResponseRest> response = service.buscarFavoritosPorTitulo(title);
		return response;
	}
	
	@PutMapping("/actualizarFavoritos/{idFavorite}")
	public ResponseEntity<NoticiasResponseRest> actualizarFavoritos(@RequestBody Noticias request, @PathVariable Long idFavorite){
		ResponseEntity<NoticiasResponseRest> response = service.actualizarFavoritos(request, idFavorite);
		return response;
	}
	
	@PostMapping("/guardarFavorito")
	public ResponseEntity<NoticiasResponseRest> guardarFavorito(@RequestBody Noticias request){
		ResponseEntity<NoticiasResponseRest> response = service.guardarFavorito(request);
		return response;
	}
	
	@DeleteMapping("/borrarFavoritos/{idFavorite}")
	public ResponseEntity<NoticiasResponseRest> borrarFavoritos(@PathVariable Long idFavorite){
		ResponseEntity<NoticiasResponseRest> response = service.borrarFavoritos(idFavorite);
		return response;
	}
	
}
