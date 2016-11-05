package F5.Pois;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.uqbar.geodds.Point;
//los import de persistencia:
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import static javax.persistence.InheritanceType.JOINED;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "Pois")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoDeInteres {

	@OneToOne
	protected Punto posicion;
	private double coordenadaX;
	private double coordenadaY;

	@Id
	@GeneratedValue
	@Column(name = "id_Poi", unique = true, nullable = false)
	private Long id_Poi;
	public Long getId_Poi() {
		return id_Poi;
	}

	public void setId_Poi(Long id_Poi) {
		this.id_Poi = id_Poi;
	}

	private String nombrePoi;

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

	public void cargarCoordenadasDePosicion(Punto unaPosicion) {
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
	protected List<DiaAtencion> atencionAlPublico;

	public void setToleranciaEnCuadras(int toleranciaEnCuadras) {
		this.toleranciaEnCuadras = toleranciaEnCuadras;
	}

	protected int toleranciaEnCuadras;

	public boolean estaDisponible(Dias unDia, int hora, Servicio unServicio) {
		return atencionAlPublico.stream().anyMatch(d -> d.getDia().equals(unDia) && d.estaAbierto(hora));
	}

	public boolean estaCerca(Punto Punto) {
		return cuadrasDeDistancia(Punto) <= toleranciaEnCuadras;
	}

	public abstract boolean encuentra(String textoLibre);

	public int cuadrasDeDistancia(Punto Punto) {
		return (int) Math.abs(posicion.distance(Punto) / 100);
	}

	public String getNombrePoi() {
		return nombrePoi;
	}

	public void setNombrePoi(String nombrePoi) {
		this.nombrePoi = nombrePoi;
	}

}