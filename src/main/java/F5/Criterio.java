package F5;

import java.time.LocalTime;

public class Criterio {

	private LocalTime fecha;
	private String terminal;
	private String usuario;
	
	public String getTerminal(){
		return this.terminal;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public LocalTime getFecha(){
		return this.fecha;
	}
	
	public Criterio(String unUsuario, String unaTerminal, LocalTime unaFecha){
		this.usuario = unUsuario;
		this.terminal = unaTerminal;
		this.fecha = unaFecha;
	}
	
}
