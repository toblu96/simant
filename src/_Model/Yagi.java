package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

public class Yagi extends Topology {
	public Yagi(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
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
		ArrayList<Double> res1 = new ArrayList<Double>();
		ArrayList<Double> res2 = new ArrayList<Double>();
		ArrayList<Double> res12 = new ArrayList<Double>();
		ArrayList<Double> res3 = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L1 = 0.1;
		double d_L2 = 0.5;
		double d_L3= 0.2;
		int n1 = 9;
		int n2 = 7;
		int n3 = 4;
		double dd_ppd1 = 2.0*Math.PI*d_L1*Math.cos(Matlab.deg2rad(90));
		double dd_ppd2 = 2.0*Math.PI*d_L2*Math.cos(Matlab.deg2rad(100));
		double dd_ppd3 = 2.0*Math.PI*d_L3;
		double richty = Matlab.deg2rad(-90);
		
		ArrayList<Double> pk1 = new ArrayList<Double>();
		for (int i = 1; i <= n1; i++) {
			pk1.add(i*dd_ppd1);
		}
		
		for (int k = 1; k <= n1; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)), 
											 -Math.sin((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)));
					Complex c2 = new Complex(Math.cos(pk1.get(k-1)), Math.sin(pk1.get(k-1)));
					Complex c = c1.multiply(c2);
					dipolr.add(c);
					res1.add(dipolr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)), 
							 				 -Math.sin((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)));
					Complex c2 = new Complex(Math.cos(pk1.get(k-1)), Math.sin(pk1.get(k-1)));
					Complex c= c1.multiply(c2);
					dipolr.set(i, c.add(dipolr.get(i))); 
					res1.set(i, dipolr.get(i).abs());
				}
			}
		}
		
		dipolr.clear();
		
		ArrayList<Double> pk2 = new ArrayList<Double>();
		for (int i = 1; i <= n2; i++) {
			pk2.add(i*dd_ppd2);
		}
		
		for (int k = 1; k <= n2; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
											 -Math.sin((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk2.get(k-1)), Math.sin(pk2.get(k-1)));
					Complex c = c1.multiply(c2);
					dipolr.add(c);
					res2.add(dipolr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
							 				 -Math.sin((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk2.get(k-1)), Math.sin(pk2.get(k-1)));
					Complex c= c1.multiply(c2);
					dipolr.set(i, c.add(dipolr.get(i))); 
					res2.set(i, dipolr.get(i).abs());
				}
			}
		}
		
		dipolr.clear();
		
		ArrayList<Double> pk3 = new ArrayList<Double>();
		for (int i = 1; i <= n3; i++) {
			pk3.add(i*dd_ppd3);
		}
		
		for (int k = 1; k <= n3; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
							 				 -Math.sin((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk3.get(k-1)), Math.sin(pk3.get(k-1)));
					Complex c = c1.multiply(c2);
					dipolr.add(c);
					res3.add(dipolr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
							 				 -Math.sin((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk3.get(k-1)), Math.sin(pk3.get(k-1)));
					Complex c= c1.multiply(c2);
					dipolr.set(i, c.add(dipolr.get(i))); 
					res3.set(i, dipolr.get(i).abs());
				}
			}
		}
		
		double maxVal1 = Collections.max(res1);
		double maxVal2 = Collections.max(res2);
		double maxVal3 = Collections.max(res3);
		for (int k = 0; k < points; k++) {
			res1.set(k, res1.get(k)/maxVal1);
			res2.set(k, res2.get(k)/maxVal2);
			res3.set(k, res3.get(k)/maxVal3);
			res12.add(res1.get(k)+res2.get(k));
		}
		
		double maxVal12 = Collections.max(res12);
		for (int k = 0; k < points; k++) {
			res.add(res12.get(k)/maxVal12);
			res.set(k, res.get(k)*res3.get(k));
		}
		
		double maxVal4 = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k,res.get(k)/maxVal4);
		}

		return res;
	}
}
