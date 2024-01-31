package com.plataforma.noticias.response;

public class NoticiasResponseRest extends ResponseRest{

    private NoticiasResponse  noticiasResponse = new NoticiasResponse();

    public NoticiasResponse getNoticiasResponse() {
        return noticiasResponse;
    }

    public void setNoticiasResponse(NoticiasResponse noticiasResponse) {
        this.noticiasResponse = noticiasResponse;
    }

}