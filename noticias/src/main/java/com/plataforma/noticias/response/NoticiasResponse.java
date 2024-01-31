package com.plataforma.noticias.response;

import com.plataforma.noticias.modelo.Noticias;

public class NoticiasResponse {

    //private Noticias noticia;
    private Iterable<Noticias> noticias;
    /*public Noticias getNoticia() {
        return noticia;
    }
    public void setNoticia(Noticias noticia) {
        this.noticia = noticia;
    } */
    public Iterable<Noticias> getNoticias() {
        return noticias;
    }
    public void setNoticias(Iterable<Noticias> noticias) {
        this.noticias = noticias;
    }
}