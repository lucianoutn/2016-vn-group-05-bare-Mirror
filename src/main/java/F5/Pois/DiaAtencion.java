package F5.Pois;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DiaAtencion {
	
	@Id
	@GeneratedValue
	private double id_diaAtencion;
	
	@OneToOne
	private Dias dia;
	
	public Dias getDia() {
		return dia;
	}

	private int horaApertura;
	private int horaCierre;
	
	public DiaAtencion(Dias unDia, int unaHoraApertura, int unaHoraCierre)
	{
		dia = unDia;
		horaApertura = unaHoraApertura;
		horaCierre = unaHoraCierre;
	}
	
	public boolean estaAbierto(int hora)
	{
		return hora >= horaApertura && hora <= horaCierre;
	}	
}


