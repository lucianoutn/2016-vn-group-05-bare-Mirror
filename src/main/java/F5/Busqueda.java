package F5;

import java.time.LocalTime;

public class Busqueda {
	public static int tiempoParaNotificar = 10;
	
	// atributos
	private String fraseBuscada;
	public String getFraseBuscada() {
		return fraseBuscada;
	}


	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	private String terminal;
	private String usuario;
	private int cantResultados;
	private LocalTime fecha;
	private int tiempoBusqueda;
	

		
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}


	public void setTiempoBusqueda(int unTiempo) {
		// debe ser parametrizable
		this.tiempoBusqueda = unTiempo; // en segundos
	}
	
	public void unaBusqueda(String user, String ter, String frase){
		fecha=LocalTime.now();
		usuario=user;
		terminal=ter;
		fraseBuscada=frase;
		RepositorioImpostor.agregarBusqueda(this);
	}
	
	public boolean realizadaPor(String unUsuario){
		if (unUsuario == null || usuario == null)
			return true;
		return usuario.equals(unUsuario);
	}
	
	public boolean realizadaEn(String unaTerminal){
		if (unaTerminal == null || terminal == null)
			return true;
		return terminal.equals(unaTerminal) || unaTerminal == null;
	}
	
	public boolean realizadaEnLaFecha(LocalTime unaFecha){
		
		if (unaFecha == null )
			return true;
		return unaFecha.equals(fecha);
	}

	public boolean buscoFrase(String unaFrase) {
		if(unaFrase == null || fraseBuscada == null)
			return true;

		return fraseBuscada.contains(unaFrase);
	}
	
	public boolean excedioDemora(){
		return tiempoBusqueda >= tiempoParaNotificar;		
	}
	

}
