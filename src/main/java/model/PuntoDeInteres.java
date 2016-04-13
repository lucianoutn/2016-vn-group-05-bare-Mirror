package model;

public class PuntoDeInteres {

	private String calle;

	private String altura;
	private int cuadrasDeCercania;
	private DisponibilidadHoraria disponibilidadHoraria;

	public boolean estaDisponible(Object date) {
		return disponibilidadHoraria.incluye(date);
	}

	public boolean estaCerca(int x, int y)
	{
		return true;
	}
	public boolean estaCerca(PuntoDeInteres poi) {
		return DistanciaEnCuadras(calle, altura, poi.getCalle(), poi.getAltura()) < cuadrasDeCercania;
	}

	private int DistanciaEnCuadras(String calle1, String altura1, String calle2, String altura2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean estaDelimitadoPor(Object radio) {
		return true;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public int getCallesDeCercania() {
		return cuadrasDeCercania;
	}

}
