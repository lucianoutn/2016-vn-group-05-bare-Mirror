package model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface PuntoDeInteres {
	
	
	public boolean estaDisponible(LocalDate date);
	
	public boolean estaCerca(int x, int y) ;
	
	public ArrayList<PuntoDeInteres> buscar(String textoLibre);
}
