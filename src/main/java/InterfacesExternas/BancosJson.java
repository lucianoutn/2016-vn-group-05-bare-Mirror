package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BancosJson {

	@JsonProperty("banco")
	private String nombre;

	@JsonProperty("x")
	private String x;

	@JsonProperty("y")
	private String y;

	@JsonProperty("sucursal")
	private String sucursal;

	@JsonProperty("gerente")
	private String gerente;

	@JsonProperty("servicios")
	private List<String> servicios;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> lista) {

		this.servicios = lista;

	}

	
	//public void setServicios()

}