package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BancosJson {

	@JsonProperty("banco")
	private String nombre;

	@JsonProperty("x")
	private Double x;

	@JsonProperty("y")
	private Double y;

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

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
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