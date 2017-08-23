package com.callcenter.bean;

import com.callcenter.dispatcher.Dispatcher;
import com.callcenter.enums.Configuracion;

public class Llamada implements Runnable {
	Cliente cliente;
	Trabajador trabajador;
	long duracion;
	long tiempoEspera;
	Dispatcher dispatcher;
	
	public Llamada(long duracion, Dispatcher dispatcher) {
		this.duracion = duracion;
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void run() {
		atender();
		try {
			Thread.sleep(duracion);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		colgar();		
	}
	

	public Llamada(Cliente cliente, long duracion, Dispatcher dispatcher) {
		super();
		this.duracion = duracion;
		this.dispatcher = dispatcher;
		this.cliente = cliente;
		this.tiempoEspera = 0;
		dispatcher.getLlamadas().add(this);
	}
	
	public void enEspera() {
		//System.out.println("Llamada en espera cliente "+getCliente().getNombre());
		incEspera(Configuracion.esperaEntreReintentos);
		try {
			Thread.sleep(Configuracion.esperaEntreReintentos*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void atender() {
		dispatcher.setLlamadasActivas(dispatcher.getLlamadasActivas()+1);
		
		Trabajador trabajador = dispatcher.getDisponible();
		while (trabajador == null) {
			cliente.setEnEspera(true);
			System.out.println("cliente "+getCliente().getNombre()+" Esperando por trabajador");
			enEspera();
			if (!dispatcher.waitForOtherCall(this)) {
				trabajador = dispatcher.getDisponible();
			}			
		}	
		cliente.setEnEspera(false);
		setTrabajador(trabajador);
		System.out.println(new StringBuilder("inicia llamada ")
				.append(" cliente: ").append(cliente.getNombre())
				.append(" atendio: ").append(trabajador.getNombre())
				.append(" Tipo: ").append(trabajador.getCargo().name()).toString());
		cliente.setOcupado(true);
		trabajador.incCantidadLlamadas();
	}
	
	public void colgar() {
		dispatcher.setLlamadasActivas(dispatcher.getLlamadasActivas()-1);
		System.out.println(new StringBuilder("Termina llamada ")
				.append(" cliente: ").append(cliente.getNombre())
				.append(" atendio: ").append(trabajador.getNombre())
				.append(" Tipo: ").append(trabajador.getCargo().name())
				.append(" Duracion: ").append(duracion).toString());
		cliente.setOcupado(false);
		trabajador.setOcupado(false);
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Trabajador getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}
	public long getDuracion() {
		return duracion;
	}
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public long getTiempoEspera() {
		return tiempoEspera;
	}


	public void setTiempoEspera(long tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}
	
	public void incEspera(long tiempoEspera) {
		this.tiempoEspera =  this.tiempoEspera + tiempoEspera;
	}
	
	
	
	


	
	
	
	
	
}
