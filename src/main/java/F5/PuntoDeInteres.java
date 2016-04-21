package F5;


import org.uqbar.geodds.Point;

public interface PuntoDeInteres {

	
	
	public boolean estaDisponible(Dias unDia, int hora , Servicio unServicio);

	public boolean estaCerca(Point point);

	public boolean encuentra(String textoLibre);
	// A partir de un texto libre, busco en todas los atributos si el objeto
	// aplica al texto de busqueda
	// Si alguno aplica, devuelvo true
	
}