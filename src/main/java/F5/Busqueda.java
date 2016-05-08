package F5;

import java.time.LocalTime;

public class Busqueda {

	// atributos
	private String fraseBuscada;
	private String terminal;
	private String usuario;
	private int cantResultados;
	private LocalTime fecha;
	private LocalTime tiempoConsulta;
	private LocalTime tiempoDemora; // en segundos

	// metodos

	// public Busqueda(String fraseBuscada); //todo

	public void setTiempoDemora(LocalTime tiempoDemora) {
		// debe ser parametrizable
		this.tiempoDemora = tiempoDemora; // en segundos
	}
	
	
	//eze dice: no es responsabilidad de la busqueda notificar la demora. sacar metodo
	public void notificarDemora() {
		// todo
	}
	//eze dice: no es responsabbilidad de la busqueda saber almacenarse. sacar metodo
	public void almacenarBusqueda() {
		// todo
	}
	
	
	public boolean realizadaPor(String unUsuario){
		return usuario.equals(unUsuario);
	}
	
	public boolean realizadaEn(String unaTerminal){
		return usuario.equals(unaTerminal);
	}
	
	public boolean realizadaEnLaFecha(LocalTime unaFecha){
		//TODO ver como validar que hayan sido el mismo dia
		// hacer varios test
		return false;
	}

	public boolean buscoFrase(String unaFrase) {
		return fraseBuscada.contains(unaFrase);
	}

}
