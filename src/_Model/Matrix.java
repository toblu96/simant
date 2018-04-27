package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

public class Matrix extends Topology {
	public Matrix(int an_number, double An_Lambda, int an_direction, double an_amplitude, int points) {
		super(an_number, An_Lambda, an_direction, an_amplitude, points);
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
		ArrayList<Double> res1 = new ArrayList<Double>();
		ArrayList<Double> res2 = new ArrayList<Double>();
		ArrayList<Double> ak = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = an_lambda;
		int n = 3; // Antenne x richtung
		int m = 6; // Antenne y richtung
		
		for (int i = 1; i <= n; i++) {
			ak.add(an_amplitude);
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==0) {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					dipolr.add(c);
					res1.add(dipolr.get(i).abs());	
				} else {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					dipolr.set(i, c.add(dipolr.get(i))); 
					res1.set(i, dipolr.get(i).abs());
				}
			}
		}
		
		dipolr.clear();
		
		for (int i = 1; i <= m; i++) {
			ak.add(an_amplitude);
		}
		
		for (int k = 0; k < m; k++) {
			for (int i = 0; i < points; i++) {
				if (k==0) {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
							 				Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					dipolr.add(c);
					res2.add(dipolr.get(i).abs());	
				} else {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					dipolr.set(i, c.add(dipolr.get(i))); 
					res2.set(i, dipolr.get(i).abs());
				}
			}
		}
		
		for (int k = 0; k < points; k++) {
			res.add(res1.get(k)*res2.get(k));
		}
		
		double maxVal = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k, res.get(k)/maxVal);
		}

		return res;
	}
}