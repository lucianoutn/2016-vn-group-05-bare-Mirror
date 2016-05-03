package F5;

import java.time.LocalTime;

public class Busqueda {

	// atributos
	private String fraseBuscada;
	private int cantResutlados;
	private LocalTime tiempoConsulta;
	private LocalTime tiempoDemora; // en segundos

	// metodos

	// public Busqueda(String fraseBuscada); //todo

	public void setTiempoDemora(LocalTime tiempoDemora) {
		// debe ser parametrizable
		this.tiempoDemora = tiempoDemora; // en segundos
	}

	public void notificarDemora() {
		// todo
	}

	public void almacenarBusqueda() {
		// todo
	}
}
