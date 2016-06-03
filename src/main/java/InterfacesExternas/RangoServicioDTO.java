package InterfacesExternas;

public class RangoServicioDTO {

	private int dia;
	private int horarioDesde;
	private int minutoDesde;
	private int horarioHasta;
	private int minutoHasta;
	
	
	public int getDia() {
		return dia;
	}

	public int getHorarioDesde() {
		return horarioDesde;
	}

	public int getMinutoDesde() {
		return minutoDesde;
	}

	public int getHorarioHasta() {
		return horarioHasta;
	}

	public int getMinutoHasta() {
		return minutoHasta;
	}
	

	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public void setHorarioDesde(int horarioDesde) {
		this.horarioDesde = horarioDesde;
	}

	public void setMinutoDesde(int minutoDesde) {
		this.minutoDesde = minutoDesde;
	}

	public void setHorarioHasta(int horarioHasta) {
		this.horarioHasta = horarioHasta;
	}

	public void setMinutoHasta(int minutoHasta) {
		this.minutoHasta = minutoHasta;
	}

	public RangoServicioDTO() {}
	
}
