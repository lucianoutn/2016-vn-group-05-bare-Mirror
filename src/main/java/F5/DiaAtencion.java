package F5;

public class DiaAtencion {

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

enum Dias {Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo}
