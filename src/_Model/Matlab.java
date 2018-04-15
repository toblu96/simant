package _Model;

import java.util.ArrayList;

public class Matlab {

	public static ArrayList<Double> linspace(double start, double end, int n) {
		ArrayList<Double> arl = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			arl.add(start + (end-start)/(n-1)*i);
		}
		return arl;
		
	}
}
