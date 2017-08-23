package com.callcenter.bean;

public class Persona {
	String nombre;
	Boolean ocupado = false;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getOcupado() {
		return ocupado;
	}

	public void setOcupado(Boolean ocupado) {
		System.out.println(nombre+" esta "+(ocupado?"Ocupado":"desocupado"));
		this.ocupado = ocupado;
	}
	
	
	
		

}
