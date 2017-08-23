package com.callcenter.enums;

public enum TipoCargo {
	
	OPE("Operador", 0),
	SUP("Supervisor", 1),
	DIR("Director", 2);
	
	String cargo;
	int nivel;
	
	private TipoCargo(String cargo, int nivel) {
		this.cargo = cargo;
		this.nivel = nivel;		
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
	

}
