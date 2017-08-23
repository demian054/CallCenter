package com.callcenter.bean;

public class Cliente extends Persona {
	
	Boolean enEspera = false;
	
	public Cliente(String nombre) {
		this.setNombre(nombre);
	}

	public Boolean isEnEspera() {
		return enEspera;
	}

	public void setEnEspera(Boolean enEspera) {
		this.enEspera = enEspera;
	}

	

}
