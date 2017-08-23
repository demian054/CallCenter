package com.callcenter.factory;

import com.callcenter.bean.Cliente;
import com.callcenter.dispatcher.Dispatcher;

public class ClientesFactory {
	
	public static int nroCliente = 0;
	
	public static Cliente generateCliente(Dispatcher dispatcher) {
		Cliente cliente = new Cliente("Cliente "+(++nroCliente));
		dispatcher.getClientes().add(cliente);
		return cliente;
	}

}
