package F5;
import java.io.Serializable;

public class Busqueda_CompositeKey implements Serializable{
	
	private int terminal;
	private int busqueda;
		
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	
	public void setBusqueda(int busqueda) {
		this.busqueda = busqueda;
	}

	public int getTerminal() {
		return terminal;
	}

	public int getBusqueda() {
		return busqueda;
	}
	
	public Busqueda_CompositeKey getPK(){
		return this;
	}
}
