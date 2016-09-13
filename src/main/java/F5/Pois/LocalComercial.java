package F5.Pois;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.Point;

@Entity
public class LocalComercial extends PuntoDeInteres {

	@Id
	@GeneratedValue
	private Long id_Local;
	private String rubro;
	private String nombre;
	private List<String> palabrasClaves = new ArrayList<>();

	public void agregarPalabrasClaves(List<String> palabras) {
		palabrasClaves = palabras;
	}

	public LocalComercial() {// constructor vacio para persistencia

	}

	public LocalComercial(String unNombre, String unaCalle, String unaAltura, String unRubro,
			List<DiaAtencion> diasDeAtencion, Point unaPosicion) {
		nombre = unNombre;
		calle = unaCalle;
		altura = unaAltura;
		posicion = unaPosicion;
		this.cargarCoordenadasDePosicion(unaPosicion);
		rubro = unRubro;
		atencionAlPublico = diasDeAtencion;
	}

	public Long getId() {
		return id_Local;
	}

	public boolean encuentra(String textoLibre) {
		return encuentraCalle(textoLibre) || encuentraPalabraClave(textoLibre) || encuentraNombre(textoLibre)
				|| encuentraRubro(textoLibre);

	}

	private boolean encuentraPalabraClave(String palabra) {
		return palabrasClaves.stream().anyMatch(pal -> pal.equals(palabra));

	}

	private boolean encuentraRubro(String textoLibre) {
		return rubro.equals(textoLibre);
	}

	private boolean encuentraCalle(String textoLibre) {
		return calle.equals(textoLibre);
	}

	private boolean encuentraNombre(String textoLibre) {
		return nombre.equals(textoLibre);

	}

	public void setCuadrasDeCercania(int cuadrasDeCercania) {
		this.toleranciaEnCuadras = cuadrasDeCercania;
	}

}
