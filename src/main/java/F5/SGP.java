package F5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import InterfacesExternas.IConsultorBancos;
import InterfacesExternas.IConsultorCGP;

//Sistema de Gestion de Pois
//TODO ver si le cambiamos el nombre
public class SGP {  

	public List<IConsultorCGP> consultoresCgp = new ArrayList<IConsultorCGP>();
	public List<IConsultorBancos> consultoresBancos = new ArrayList<IConsultorBancos>();
	
	public List<PuntoDeInteres> CGPsUbicadasEn(String unLugar){
		return consultoresCgp.stream()
				 			 .map(cgp-> cgp.cgpUbicadasEn(unLugar))
				 			 .flatMap(l -> l.stream())
				 			 .collect(Collectors.toList());
							 
	}
	
	public List<SucursalDeBanco> bancosConServicio(String nombreBanco, String unServicio){
		return consultoresBancos.stream()
	 			 .map(banco-> banco.bancosQueCumplenCon(nombreBanco, unServicio)) 
	 			 .flatMap(l -> l.stream())
	 			 .collect(Collectors.toList());
	}

	public void agregarConsultorCgp(IConsultorCGP consultor){
		consultoresCgp.add(consultor);
	}
	
	public void agregarConsultorBanco(IConsultorBancos consultor){
		consultoresBancos.add(consultor);
	}
	
	
	public List<PuntoDeInteres> dameNuevasCgp(){
		return CGPsUbicadasEn(null);
	}
	
	public List<SucursalDeBanco> dameNuevasSucursalesDeBanco(){
		return bancosConServicio(null, null);
	}
}
