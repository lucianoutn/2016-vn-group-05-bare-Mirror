package TestFactory;

import F5.Mapa;

public final class MapaFactory {

	public static Mapa sinPOIs(){
		return new Mapa();
	}
	
	public static Mapa soloBancos(){
		Mapa mapa = new Mapa();
		mapa.anadirPOI(BancoFactory.BancoHSBC());
		mapa.anadirPOI(BancoFactory.BancoSantanderEnOrigenYMiercolesDe9A18());
		mapa.anadirPOI(BancoFactory.BancoSabadoDe10a13());
		return mapa;
	}
	
	public static Mapa soloLocalesComerciales(){
		Mapa mapa = new Mapa();
		mapa.anadirPOI(LocalComercialFactory.mimoEsLibreriaPunto100_0DisponibleMiercoles10a20());
		return mapa;
	}
}
