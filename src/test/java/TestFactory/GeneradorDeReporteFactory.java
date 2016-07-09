package TestFactory;

import java.time.LocalTime;
import java.util.ArrayList;

import org.uqbar.geodds.Point;


import F5.Busqueda;


public final class GeneradorDeReporteFactory {

	private static Busqueda busquedaSegunUsuario;
	private static Busqueda busquedaSegunTerminal;
	
	public static Busqueda agregarBusquedaDeUsuarioPepe(){
		busquedaSegunUsuario.setUsuario("pepe");
		return busquedaSegunUsuario;
	}
	
	public static Busqueda agregarBusquedaDeTerminalFlores(){
		busquedaSegunTerminal.setTerminal("Flores");
		return busquedaSegunTerminal;
	}
	
	
}
