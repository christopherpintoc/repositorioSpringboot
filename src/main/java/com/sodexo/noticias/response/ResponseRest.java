package com.sodexo.noticias.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
	
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;
	}
	
	public void setMetada(String codigo, String descripcion) {
		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("codigo", codigo);
		mapa.put("descripcion", descripcion);
		
		metadata.add(mapa);
	}

}
