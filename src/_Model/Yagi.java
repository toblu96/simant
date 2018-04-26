package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

public class Yagi extends Topology {
	public Yagi(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
		super(an_number, an_lambda, an_direction, an_amplitude, points);
	}
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> dipolr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = an_lambda;
		int n = an_number;
		double dd_ppd = 2.0*Math.PI*d_L;
		
		ArrayList<Double> pk = new ArrayList<Double>();
		for (int i = 1; i <= n; i++) {
			pk.add(i*dd_ppd);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
					dipolr.add(c);
					res.add(dipolr.get(i).abs());
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
					dipolr.set(i, c.add(dipolr.get(i))); 
					res.set(i, dipolr.get(i).abs());
				}
			}
		}
		double maxVal = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k, res.get(k)/maxVal);
		}
		return res;
	}
}