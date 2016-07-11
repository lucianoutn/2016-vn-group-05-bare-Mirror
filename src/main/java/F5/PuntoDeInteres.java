package F5;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.uqbar.geodds.Point;

public abstract class  PuntoDeInteres {

	public PuntoDeInteres(){
		Id = UUID.randomUUID().toString();
	}
	
	public String Id;

	
	public void agregarPalabrasClaves(List<String> palabras){
		
	}


	protected String calle;
	public String getCalle() {
		return calle;
	}

	protected String altura;
	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	protected Point posicion;
	protected List<DiaAtencion> atencionAlPublico;
	
	public void setToleranciaEnCuadras(int toleranciaEnCuadras) {
		this.toleranciaEnCuadras = toleranciaEnCuadras;
	}

	protected int toleranciaEnCuadras;
	public boolean estaDisponible(Dias unDia, int hora , Servicio unServicio){
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(hora));
	}

	public boolean estaCerca(Point point){
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}

	public abstract boolean encuentra(String textoLibre);

	
	protected int cuadrasDeDistancia(Point point) {
		return (int) Math.abs(posicion.distance(point)/100);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		return this.Id.equals(((PuntoDeInteres)obj).Id);
	}
	
}