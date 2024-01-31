package com.plataforma.noticias.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plataforma.noticias.modelo.Noticias;

@Repository
public interface INoticiasDao extends CrudRepository<Noticias, Long> {

    Iterable<Noticias> findByTitleContainingIgnoreCase(String title);

}