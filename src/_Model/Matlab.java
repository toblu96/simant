package _Model;

import java.util.ArrayList;

public class Matlab {

	/**
	 * - erzeugt eine ArrayList mit linearen Abständen
	 * @param start	-> Startwert der Liste
	 * @param end	-> Endwert der Liste
	 * @param n		-> Anzahl Punkte
	 * @return		-> ArrayListe mit linearisierten Werten 
	 */
	public static ArrayList<Double> linspace(double start, double end, int n) {
		ArrayList<Double> arl = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			if (n > 1) {
				arl.add(start + (i*(end-start))/(n-1));
			} else {
				arl.add(start + (end-start));
			}
			
		}
		return arl;
		
	}
}
