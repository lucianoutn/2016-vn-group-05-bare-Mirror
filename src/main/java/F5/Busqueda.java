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
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}


	public void setTiempoDemora(LocalTime tiempoDemora) {
		// debe ser parametrizable
		this.tiempoDemora = tiempoDemora; // en segundos
	}
	
	public void unaBusqueda(String user, String ter, String frase){
		fecha=LocalTime.now();
		usuario=user;
		terminal=ter;
		fraseBuscada=frase;
		RepositorioImpostor.setBusquedas(this);
	}
	
	public boolean realizadaPor(String unUsuario){
		if (unUsuario == null || usuario == null)
			return true;
		return usuario.equals(unUsuario);
	}
	
	public boolean realizadaEn(String unaTerminal){
		if (unaTerminal == null || terminal == null)
			return true;
		return terminal.equals(unaTerminal);
	}
	
	public boolean realizadaEnLaFecha(LocalTime unaFecha){
		//TODO ver como validar que hayan sido el mismo dia
		// hacer varios test
		if (unaFecha == null )
			return true;
		return false;
	}

	public boolean buscoFrase(String unaFrase) {
		if(unaFrase == null || fraseBuscada == null)
			return true;
		return  fraseBuscada.contains(unaFrase);
	}

}
