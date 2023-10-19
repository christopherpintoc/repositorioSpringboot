package com.sodexo.noticias.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sodexo.noticias.modelo.Noticias;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class NoticiasControllerRestTest{
	
	private final static String BASE_URL = "/apiSodexo";
	
	MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void testGuardarFavorito200() throws Exception {
		//recibe parametro noticias desde servicio buildFavorito()
		Noticias noticias = buildFavorito();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/guardarFavorito")
						.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapToJson(noticias)))
			.andReturn();
		assertEquals(200, result.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testGuardarFavoritoWithError500() throws Exception {
		//recibe parametro con error
		Noticias noticias = new Noticias();
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/guardarFavorito")
						.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapToJson(noticias)))
			.andReturn();
		assertEquals(500, result.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testObtenerFavoritos200() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/obtenerFavoritos")
					.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(200, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testObtenerFavoritosWithError404() throws Exception {
		//se envia url con error para probar error 404
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/obtenerFavoritos_test")
					.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		assertEquals(404, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}

	@Test
	void testBuscarFavoritosPorTitulo200() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/buscarFavoritosPorTitulo/SpaceNews")
				//.queryParam("title", "SpaceNews")
			.accept(MediaType.APPLICATION_JSON_VALUE))
		.andReturn();
		assertEquals(200, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testBuscarFavoritosPorTituloWithError500() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/buscarFavoritosPorTitulo/SpaceNews")
				//.queryParam("title", "SpaceNews")
			.accept(MediaType.APPLICATION_JSON_VALUE))
		.andReturn();
		assertEquals(500, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}

	@Test
	void testBuscarFavoritosPorTituloWithError404() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/buscarFavoritosPorTitulo/")
				//.queryParam("title", "SpaceNews")
			.accept(MediaType.APPLICATION_JSON_VALUE))
		.andReturn();
		assertEquals(404, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testActualizarFavoritos200() throws JsonProcessingException, Exception {
		//recibe parametro noticias desde servicio buildFavorito()
				Noticias noticias = buildFavorito();
				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL+"/actualizarFavoritos/1")
								//.queryParam("idFavorite", "1")
							.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mapToJson(noticias)))
				.andReturn();
				assertEquals(200, result.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testActualizarFavoritosWithError500() throws JsonProcessingException, Exception {
		//recibe parametro noticias vacío
				Noticias noticias = new Noticias();
				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL+"/actualizarFavoritos/1")
								//.queryParam("idFavorite", "1")
							.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mapToJson(noticias)))
				.andReturn();
				assertEquals(500, result.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testActualizarFavoritosWithError404() throws JsonProcessingException, Exception {
		//recibe url con error 
				Noticias noticias = buildFavorito();
				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(BASE_URL+"/actualizarFavoritos/")
								//.queryParam("idFavorite", "1")
							.accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(mapToJson(noticias)))
				.andReturn();
				assertEquals(404, result.getResponse().getStatus());
		//fail("Not yet implemented");
	}

	@Test
	void testBorrarFavoritos200() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL+"/borrarFavoritos/1")
					//.queryParam("idFavorite", "1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		assertEquals(200, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testBorrarFavoritosWitError500() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL+"/borrarFavoritos/1")
					//.queryParam("idFavorite", "1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		assertEquals(500, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	@Test
	void testBorrarFavoritosWitError404() throws Exception {
		MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL+"/borrarFavoritos")
					//.queryParam("idFavorite", "1")
				.accept(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		assertEquals(404, mockMvcResult.getResponse().getStatus());
		//fail("Not yet implemented");
	}
	
	//permite mapear como json un objeto enviado por parametro
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	//Se crea objeto para pruebas en metodo GuardarFavorito
	private Noticias buildFavorito() {
		Noticias noticias = new Noticias();
		noticias.setId("1");
		noticias.setTitle("SpaceNews Announces Promotion of Kamal Flucker to Global Sales Director");
		noticias.setUrl("https://spacenews.com/spacenews-announces-promotion-of-kamal-flucker-to-global-sales-director/");
		noticias.setImage_url("https://spacenews.com/wp-content/uploads/2023/01/kamal_f-300x300.jpg");
		noticias.setNews_site("SpaceNews");
		noticias.setSummary("Tysons Corner, VA – October 2, 2023 –  SpaceNews, the most trusted and comprehensive source of news and analysis of the global space industry, is proud to announce the promotion […]");
		noticias.setPublished_at("2023-10-02T20:03:44Z");
		noticias.setUpdated_at("2023-10-02T20:07:49.906000Z");
		noticias.isFeatured();
		return noticias;
	}
}
