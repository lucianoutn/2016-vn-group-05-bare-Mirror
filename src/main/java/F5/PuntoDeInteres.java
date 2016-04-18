package F5;

import java.time.LocalDate;
import java.util.ArrayList;

public interface PuntoDeInteres {

	public boolean estaDisponible(LocalDate date, Servicio valorX);

	public boolean estaCerca(int x, int y);

	public boolean encuentra(String textoLibre);
	// A partir de un texto libre, busco en todas los atributos si el objeto
	// aplica al texto de busqueda
	// Si alguno aplica, devuelvo true
}