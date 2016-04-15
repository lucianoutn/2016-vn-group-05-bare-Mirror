package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SucursalDeBanco implements PuntoDeInteres {

	private String calle;
	private int altura;
	private int cuadrasDeCercania;
	private int x;//Asumimos que las coordenadas son cuadras
	private int y;
	
	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaCerca(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean encuentra(String textoLibre) {
		// TODO Auto-generated method stub
		return false;
	}

}
