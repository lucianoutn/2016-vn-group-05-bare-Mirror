package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LocalComercial implements PuntoDeInteres{

	private String calle;
	private int altura;
	private int cuadrasDeCercania;
	private int x; //Asumimos que las coordenadas son cuadras
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
	
	public boolean encuentra(String textoLibre) {
		// TODO Auto-generated method stub
		return false;
	}
	//A partir de un texto libre, busco en todas los atributos si el objeto aplica al texto de busqueda
	//Si alguno aplica, devuelvo true

}
