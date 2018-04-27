package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

public class Linear extends Topology {
	public Linear(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
		super(an_number, an_lambda, an_direction, an_amplitude, points);
	}
	
	public void updateData(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
		this.an_number = an_number;
		this.an_lambda = an_lambda;
		this.an_direction = an_direction;
		this.an_amplitude = an_amplitude;
		this.points = points;
	}
	
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> dipolr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad(an_direction);
		double d_L = an_lambda;
		int n = an_number;

		ArrayList<Double> ak = new ArrayList<Double>();
		for (int i = 1; i <= n; i++) {
			ak.add(an_amplitude);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					c = c.multiply(ak.get(k-1));
					dipolr.add(c);
					res.add(dipolr.get(i).abs());
				} else {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					c = c.multiply(ak.get(k-1));
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
