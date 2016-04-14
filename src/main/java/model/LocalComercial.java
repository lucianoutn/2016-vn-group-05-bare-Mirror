package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LocalComercial implements PuntoDeInteres{

	private String calle;
	private String altura;
	private int cuadrasDeCercania;
	private int x;
	private int y;
	@Override
	public boolean estaDisponible(LocalDate date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaCerca(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PuntoDeInteres> buscar(String textoLibre) {
		// TODO Auto-generated method stub
		return null;
	}

}
