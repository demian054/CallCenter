package com.callcenter.factory;

import java.util.Iterator;

import com.callcenter.bean.Llamada;
import com.callcenter.bean.Trabajador;
import com.callcenter.dispatcher.Dispatcher;

public class ReportFactory {
	
	public static void report(Dispatcher dispatcher) {
		
		int llamadasActivas;
		do {
			llamadasActivas = 0;
			try {
				System.out.println("Esperando para reportar");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
			for (Iterator iterator = dispatcher.getLlamadas().iterator(); iterator.hasNext();) {
				Llamada llamada = (Llamada) iterator.next();
				if (llamada.getTrabajador()==null || llamada.getTrabajador().getOcupado()) {
					llamadasActivas++;
				} 
			}
			
		} while (llamadasActivas > 0);

		
		for (Iterator iterator = dispatcher.getTrabajadores().iterator(); iterator.hasNext();) {
			Trabajador trabajador = (Trabajador) iterator.next();
			System.out.println("Trabajor :"+trabajador.toString()+" atendidas "+trabajador.getCantidadLlamadas());	
		}
		
		int count = 0;
		long totalduraciones = 0;
		long totalEspera = 0;
		for (Iterator iterator = dispatcher.getLlamadas().iterator(); iterator.hasNext();) {
			Llamada llamada = (Llamada) iterator.next();
			totalduraciones = totalduraciones + llamada.getDuracion();
			totalEspera = totalEspera + llamada.getTiempoEspera();
			System.out.println("Llamada "+(++count)+" duracion "+llamada.getDuracion()+" espera "+llamada.getTiempoEspera()+" atendida por "+llamada.getTrabajador().toString());
			
		}
		
		System.out.println("Total Llamadas = "+dispatcher.getLlamadas().size());
		System.out.println("Total Clientes = "+dispatcher.getClientes().size());
		
		System.out.println("Tiempo Total Llamadas = "+totalduraciones);
		System.out.println("Tiempo Total Espera = "+totalEspera);
		
		
	}
	

}
