package com.callcenter.factory;

import java.util.ArrayList;
import java.util.List;

import com.callcenter.bean.Trabajador;
import com.callcenter.dispatcher.Dispatcher;
import com.callcenter.enums.Configuracion;
import com.callcenter.enums.TipoCargo;

public class TrabajdoresFactory {

	public static void generateTrabajadores(Dispatcher dispatcher) {
		System.out.println("Generando Trabajadores...");
		List<Trabajador> trabajadores = new ArrayList<Trabajador>(); 
		generateTrabajadorTipo(trabajadores, TipoCargo.OPE, Configuracion.operadores);
		generateTrabajadorTipo(trabajadores, TipoCargo.SUP, Configuracion.supervisores);
		generateTrabajadorTipo(trabajadores, TipoCargo.DIR, Configuracion.directores);
		dispatcher.setTrabajadores(trabajadores);
	}
	
	private static void generateTrabajadorTipo(List<Trabajador> trabajadores, TipoCargo tipoCargo, int cantTrabadores) {
		for (int i = 0; i < cantTrabadores; i++) {
			Trabajador operador = new Trabajador(tipoCargo);
			operador.setNombre(tipoCargo.getCargo()+" "+(i+1));
			System.out.println("Creado -> "+operador.toString());
			trabajadores.add(operador);			
		}		
	}
}
