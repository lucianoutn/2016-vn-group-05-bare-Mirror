package F5.Pois;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.IndexColumn;
import org.uqbar.geodds.Point;
//los import de persistencia:
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import static javax.persistence.InheritanceType.JOINED;
import javax.persistence.InheritanceType;

//@Entity
@Table(name = "Pois")
@Inheritance(strategy = JOINED)
public abstract class PuntoDeInteres {

	protected Point posicion;
	// @Column(name="coordenadaX")
	private double coordenadaX;
	// @Column(name="coordenadaY")
	private double coordenadaY;

	@Id
	@GeneratedValue
	private Long id_Poi;

	public Long getId() {
		return id_Poi;
	}

	public PuntoDeInteres() {

	}

	public void setCoordenadaX(double coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public void setCoordenadaY(double coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public void cargarCoordenadasDePosicion(Point unaPosicion) {
		if (unaPosicion != null) {
			coordenadaX = unaPosicion.latitude();
			coordenadaY = unaPosicion.longitude();
		}
	}

	public void agregarPalabrasClaves(List<String> palabras) {

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

	@OneToMany
	@JoinColumn(name = "id_diaAtencion")
	@IndexColumn(name = "num_dia")
	protected List<DiaAtencion> atencionAlPublico;

	public void setAtencionAlPublico(List<DiaAtencion> atencionAlPublico) {
		this.atencionAlPublico = atencionAlPublico;
	}

	public List<DiaAtencion> getAtencionAlPublico() {
		return atencionAlPublico;
	}

	public void setToleranciaEnCuadras(int toleranciaEnCuadras) {
		this.toleranciaEnCuadras = toleranciaEnCuadras;
	}

	protected int toleranciaEnCuadras;

	public boolean estaDisponible(Dias unDia, int hora, Servicio unServicio) {
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(hora));
	}

	public boolean estaCerca(Point point) {
		return cuadrasDeDistancia(point) <= toleranciaEnCuadras;
	}

	public abstract boolean encuentra(String textoLibre);

	public int cuadrasDeDistancia(Point point) {
		return (int) Math.abs(posicion.distance(point) / 100);
	}

}