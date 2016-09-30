package TestFactory;

import java.time.LocalTime;
import java.util.ArrayList;

import org.uqbar.geodds.Point;


import F5.Busqueda;
import F5.Terminal.Terminal;
import F5.Terminal.Usuario;


public final class GeneradorDeReporteFactory {

	private static Busqueda busquedaSegunUsuario;
	private static Busqueda busquedaSegunTerminal;
	private static Terminal unaTerminal;
	
	public static Busqueda agregarBusquedaDeUsuarioPepe(){
		busquedaSegunUsuario.setUsuario(new Usuario("pepe", null));
		return busquedaSegunUsuario;
	}
	
	public static Busqueda agregarBusquedaDeTerminalFlores(){
		unaTerminal = new Terminal("Flores",null);
		busquedaSegunTerminal.setTerminal(unaTerminal);
		return busquedaSegunTerminal;
	}
	
	
}
