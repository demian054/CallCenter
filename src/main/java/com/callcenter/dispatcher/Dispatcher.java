package com.callcenter.dispatcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.callcenter.bean.Cliente;
import com.callcenter.bean.Llamada;
import com.callcenter.bean.Trabajador;
import com.callcenter.enums.Configuracion;
import com.callcenter.factory.LlamadasFactory;
import com.callcenter.factory.TrabajdoresFactory;

public class Dispatcher {

	List<Cliente> clientes;
	List<Trabajador> trabajadores;
	List<Llamada> llamadas;
	int llamadasActivas = 0;
	
	public Dispatcher() {
		llamadas = new ArrayList<Llamada>();
		clientes = new ArrayList<Cliente>();
		TrabajdoresFactory.generateTrabajadores(this);
	}
	
	public void dispatchCall() {
		int totalLlamadas = Configuracion.cantidadLlamadasTotales;
		while (totalLlamadas>0) {
			
			while (llamadasActivas >= Configuracion.llamadasSimultaneas) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();				
				}
			}
			
			Thread call = new Thread(LlamadasFactory.generateLlamada(this));
			call.start();
			totalLlamadas--;
			
			try {
				Thread.sleep(calcularTiempoEntreLlamada());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
			
	private long calcularTiempoEntreLlamada() {
		Random aleatorio = new Random(System.currentTimeMillis());
		long tiempo = (aleatorio.nextInt(Configuracion.maximoEntreLlamada)+Configuracion.minimoEnrteLlamada)*1000;
		System.out.println("Tiempo entre llamada "+tiempo);
		// Producir nuevo int aleatorio entre min y max
		return tiempo;
	}
	
	public Trabajador getDisponible() {
		for (Iterator iterator = trabajadores.iterator(); iterator.hasNext();) {
			Trabajador trabajador = (Trabajador) iterator.next();
			if (!trabajador.getOcupado()) {
				trabajador.setOcupado(true);
				return trabajador; 
			}
		}
		return null;
	}
	
	public boolean waitForOtherCall(Llamada llamada) {
		int index = llamadas.indexOf(llamada);
		if (index > 0) {
			int cont = 0;
			for (Iterator iterator = llamadas.iterator(); iterator.hasNext();) {
				Llamada llamadaExistente = (Llamada) iterator.next();
				if (index == cont) {
					//System.out.println("****FIN DE CONTEO*****************No hay llamadas en espera");
					break;
				} else if(llamadaExistente.getCliente().isEnEspera()){
					//System.out.println("******EN ESPERA************llamada "+llamada.getCliente().getNombre()+" esperando por "+llamadaExistente.getCliente().getNombre());
					return true;
				}
				cont++;
				
			}
		}
		//System.out.println("******SIN ESPERA**************No hay llamadas en espera");
		return false;
		
	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public List<Llamada> getLlamadas() {
		return llamadas;
	}

	public void setLlamadas(List<Llamada> llamadas) {
		this.llamadas = llamadas;
	}

	public int getLlamadasActivas() {
		return llamadasActivas;
	}

	public void setLlamadasActivas(int llamadasActivas) {
		this.llamadasActivas = llamadasActivas;
	}
	
	
	
	
}
