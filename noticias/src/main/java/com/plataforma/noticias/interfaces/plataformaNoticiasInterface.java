package com.plataforma.noticias.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plataforma.noticias.modelo.Noticias;

public interface plataformaNoticiasInterface extends JpaRepository<Noticias, Long>{

}