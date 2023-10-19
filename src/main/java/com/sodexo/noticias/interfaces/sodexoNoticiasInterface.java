package com.sodexo.noticias.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sodexo.noticias.modelo.Noticias;

public interface sodexoNoticiasInterface extends JpaRepository<Noticias, Long>{

}