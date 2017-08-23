package com.callcenter.bean;

import com.callcenter.enums.TipoCargo;

public class Trabajador extends Persona {
	TipoCargo cargo;
	int cantidadLlamadas = 0;

	public Trabajador(TipoCargo cargo) {
		super();
		this.cargo = cargo;
	}

	public TipoCargo getCargo() {
		return cargo;
	}

	public void setCargo(TipoCargo cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(" Nombre: ").append(getNombre())
				.append(" Cargo: ").append(cargo.getCargo())
				.toString();
	}
	
	@Override
	public void setOcupado(Boolean ocupado) {
		super.setOcupado(ocupado);		
	}

	public int incCantidadLlamadas() {
		return cantidadLlamadas++;
	}

	public int getCantidadLlamadas() {
		return cantidadLlamadas;
	}

	
}
