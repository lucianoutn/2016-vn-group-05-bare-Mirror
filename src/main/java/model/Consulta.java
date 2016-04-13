package model;

import java.util.Date;

public class Consulta {

	
	
	public boolean estaCerca(PuntoDeInteres poi, int x, int y)
	{
		return poi.estaCerca(x, y);
	}
	
	public boolean estaDisponible(PuntoDeInteres poi, Date momento)
	{
		return poi.estaDisponible(momento);
	}
	
	public PuntoDeInteres buscar(String busqueda)
	{
		return null;
	}
	
	
}
