package com.callcenter.factory;

import java.util.Random;

import com.callcenter.bean.Trabajador;
import com.callcenter.bean.Llamada;
import com.callcenter.dispatcher.Dispatcher;
import com.callcenter.enums.Configuracion;

public class LlamadasFactory {
	
	public static Llamada generateLlamada(Dispatcher dispatcher) {
		
		Llamada llamada = new Llamada(
				ClientesFactory.generateCliente(dispatcher),
				calcularTiempoLLamada(), 
				dispatcher
				);
		return llamada; 
	}

	
	private static long calcularTiempoLLamada() {
		Random aleatorio = new Random(System.currentTimeMillis());
		// Producir nuevo int aleatorio entre min y max
		return (aleatorio.nextInt(Configuracion.maximoLlamada)+Configuracion.minimoLlamada)*1000;
	}

}
