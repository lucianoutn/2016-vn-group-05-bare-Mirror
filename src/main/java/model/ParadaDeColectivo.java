package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ParadaDeColectivo implements PuntoDeInteres{
	
	//Declaraciones -- Inicio
	private String calle;
	private int altura;
	private int toleranciaEnCuadras;
	private int y; //Asumimos que las coordenadas son cuadras
	private int x;
	private String numeroDeLinea;
	//Declaraciones -- Fin
	
	
	//Getters y Setters -- Inicio
	public int getToleranciaEnCuadras() {
		return toleranciaEnCuadras;
	}

	public void setToleranciaEnCuadras(int toleranciaEnCuadras) {
		this.toleranciaEnCuadras = toleranciaEnCuadras;
	}
	//Getters y Setters -- Fin
	
	//Metodos -- Inicio
	public ParadaDeColectivo (String calleDeParada, int alturaDeParada, int ejeY, int ejeX, String lineaDeColectivo){
		calle = calleDeParada;
		altura = alturaDeParada;
		y = ejeY;
		x = ejeX;
		numeroDeLinea = lineaDeColectivo;
	}
	
	@Override
	public boolean estaDisponible(LocalDate date, Servicio valorX) {
		return true;
	}
	@Override
	public boolean estaCerca(int x, int y) {
		int distancia = Math.abs(x-this.x)+Math.abs(y-this.y);
		return (distancia < toleranciaEnCuadras);
	}



	@Override
	public boolean encuentra(String textoLibre) {
		return (numeroDeLinea == textoLibre);
			}
	//Metodos -- Fin
}
