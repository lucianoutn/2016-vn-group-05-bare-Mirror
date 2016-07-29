package InterfacesExternas;

import java.util.ArrayList;
import java.util.List;

import F5.Pois.PuntoDeInteres;
import F5.Terminal.RepositorioDePOIs;


public class BajaPoisRestMock implements IBajaPoi {
	
	private RepositorioDePOIs repoDePOIs;
	
	public BajaPoisRestMock(RepositorioDePOIs unRepoDePOIs){
		repoDePOIs = unRepoDePOIs;
	}
	
	@Override
	public List<PuntoDeInteres> poisADarDeBaja() {
		// TODO Auto-generated method stub
		return repoDePOIs.getPOIs();
	}

}
