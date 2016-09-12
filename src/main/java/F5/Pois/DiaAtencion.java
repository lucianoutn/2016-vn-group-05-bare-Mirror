package F5.Pois;

public class DiaAtencion {
	
	private double id_diaAtencion;
	
	
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


