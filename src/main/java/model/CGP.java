package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class CGP implements PuntoDeInteres {

	private String calle;
	private String altura;
	private int x; // Asumimos que las coordenadas son cuadras
	private int y;
	private int rangoComuna;// supongo que el rango de la zona de el CGP será
	private Collection<Servicio> servicios;

	// private DisponibilidadHoraria disponibilidadHoraria;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRangoComuna() {
		return rangoComuna;
	}

	public void setRangoComuna(int rangoComuna) {
		this.rangoComuna = rangoComuna;
	}

	public CGP(int rango, int coordenadaX, int coordenadaY) {

		this.setX(coordenadaX);
		this.setY(coordenadaY);
		this.setRangoComuna(rango);

	}

	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		if (servicios.contains(valorX) && valorX.estaAbierto(date))
			return true;

		return false;
	}

	@Override
	public boolean estaCerca(int corX, int corY) {
		if ((x - rangoComuna) < corX && corX < (x + rangoComuna) && (y - rangoComuna) < corY
				&& corY < (y + rangoComuna))
			return true;
		else
			return false;
	}

	@Override
	public boolean encuentra(String textoLibre) {

		return false;
	}

}


