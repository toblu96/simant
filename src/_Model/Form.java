package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

import javafx.scene.image.Image;

public abstract class Form {
	
	protected int an_number, an_direction, points;
	protected double an_lambda, an_amplitude, rf_distance;
	protected ArrayList<Double> an_amp;
	
	public void updateData(int an_number, double an_lambda, int an_direction, ArrayList<Double> an_amplitude, double rf_distance, int points) {
		this.an_amp = an_amplitude;
		this.an_number = an_number;
		this.an_lambda = an_lambda;
		this.an_direction = an_direction;
		this.rf_distance = rf_distance;
		this.points = points;
	}
	
	public abstract ArrayList<Double> calculate();
	public abstract Image getImage(boolean reflector);
}



class Linear extends Form {
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad(an_direction);
		double d_L = an_lambda;
		int n = an_number;
		ArrayList<Double> ak = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			ak.add(an_amp.get(i));
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					c = c.multiply(ak.get(k-1));
					sumr.add(c);
					res.add(sumr.get(i).abs());
				} else {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					c = c.multiply(ak.get(k-1));
					sumr.set(i, c.add(sumr.get(i))); 
					res.set(i, sumr.get(i).abs());
				}
			}
		}
		double maxVal = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k, res.get(k)/maxVal);
		}
		return res;
	}

	@Override
	public Image getImage(boolean reflector) {
		if (reflector) {
			return new Image("/resources/Java_Linie_Reflektor.png", true);
		} else {
			return new Image("/resources/Java_Linie.png", true);
		}
	}
}



class Circle extends Form {

	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double phase = Matlab.deg2rad((double)(an_direction));
		double d_L = an_lambda;
		int n = an_number;

		ArrayList<Double> ak = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			ak.add(an_amp.get(i+1));
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c = new Complex(Math.cos(d_L*2.0*Math.PI*(Math.cos(2.0*Math.PI*k/n)*Math.sin(phase)*Math.cos(psi_r.get(i)) + Math.sin(2.0*Math.PI*k/n)*Math.sin(phase)*Math.sin(psi_r.get(i)))),
											Math.sin(d_L*2.0*Math.PI* (Math.cos(2.0*Math.PI*k/n)*Math.sin(phase)*Math.cos(psi_r.get(i)) + Math.sin(2.0*Math.PI*k/n)*Math.sin(phase)*Math.sin(psi_r.get(i)))));
					c = c.multiply(ak.get(k-1));
					sumr.add(c);
					res.add(sumr.get(i).abs());
				} else {
					Complex c = new Complex(Math.cos(d_L*2.0*Math.PI* (Math.cos(2.0*Math.PI*k/n)*Math.sin(phase)*Math.cos(psi_r.get(i)) + Math.sin(2.0*Math.PI*k/n)*Math.sin(phase)*Math.sin(psi_r.get(i)))), 
											Math.sin(d_L*2.0*Math.PI* (Math.cos(2.0*Math.PI*k/n)*Math.sin(phase)*Math.cos(psi_r.get(i)) + Math.sin(2.0*Math.PI*k/n)*Math.sin(phase)*Math.sin(psi_r.get(i)))));
					c = c.multiply(ak.get(k-1));
					sumr.set(i, c.add(sumr.get(i))); 
					res.set(i, sumr.get(i).abs());
				}
			}
		}
		double maxVal = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k, res.get(k)/maxVal);
		}
		return res;
	}

	@Override
	public Image getImage(boolean reflector) {
		if (reflector) {
			return new Image("/resources/Java_Kreis_Reflektor.png", true);
		} else {
			return new Image("/resources/Java_Kreis.png", true);
		}
	}
}



class Matrix extends Form {
	
	public ArrayList<Double> calculate() {
		
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		ArrayList<Double> res1 = new ArrayList<Double>();
		ArrayList<Double> res2 = new ArrayList<Double>();
		ArrayList<Double> ak = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = an_lambda;
		int n = 3; // Antenne x richtung
		int m = 6; // Antenne y richtung
		
		for (int i = 0; i < n; i++) {
			ak.add(an_amp.get(i+1));
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==0) {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					sumr.add(c);
					res1.add(sumr.get(i).abs());	
				} else {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					sumr.set(i, c.add(sumr.get(i))); 
					res1.set(i, sumr.get(i).abs());
				}
			}
		}
		
		sumr.clear();
		
		for (int i = 1; i <= m; i++) {
			ak.add(an_amplitude);
		}
		
		for (int k = 0; k < m; k++) {
			for (int i = 0; i < points; i++) {
				if (k==0) {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
							 				Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					sumr.add(c);
					res2.add(sumr.get(i).abs());	
				} else {
					Complex c = new Complex(Math.cos(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))), 
			 								Math.sin(2.0*Math.PI*(k*d_L*Math.cos(psi_r.get(i)-richtd))));
					c = c.multiply(ak.get(k));
					sumr.set(i, c.add(sumr.get(i))); 
					res2.set(i, sumr.get(i).abs());
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

	@Override
	public Image getImage(boolean reflector) {
		if (reflector) {
			return new Image("/resources/Java_Matrix_Reflektor.png", true);
		} else {
			return new Image("/resources/Java_Matrix.png", true);
		}
	}
}

class Reflector extends Form {
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Double> res = new ArrayList<Double>();
		ArrayList<Complex> res1 = new ArrayList<Complex>();
		ArrayList<Complex> res2 = new ArrayList<Complex>();
		double h = rf_distance;
		double d_L = an_lambda;
		int n = an_number;
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c = new Complex(Math.cos(d_L*2.0*Math.PI*Math.cos(psi_r.get(i))*(k-1)), 
											Math.sin(d_L*2.0*Math.PI*Math.cos(psi_r.get(i))*(k-1)));
					res1.add(c);
					//res1.add(sumr.get(i).abs());
				} else {
					Complex c = new Complex(Math.cos(d_L*2.0*Math.PI*Math.cos(psi_r.get(i))*(k-1)), 
											Math.sin(d_L*2.0*Math.PI*Math.cos(psi_r.get(i))*(k-1)));
					res1.set(i, c.add(res1.get(i))); 
					//res1.set(i, sumr.get(i).abs());
				}
			}
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*(Math.cos(psi_r.get(i))+2.0*h*Math.sin(psi_r.get(i)))), 
											Math.sin((k-1)*d_L*2.0*Math.PI*(Math.cos(psi_r.get(i))+2.0*h*Math.sin(psi_r.get(i)))));
					res2.add(c);
					//res2.add(sumr.get(i).abs());
				} else {
					Complex c = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*(Math.cos(psi_r.get(i))+2.0*h*Math.sin(psi_r.get(i)))), 
											Math.sin((k-1)*d_L*2.0*Math.PI*(Math.cos(psi_r.get(i))+2.0*h*Math.sin(psi_r.get(i)))));
					res2.set(i, c.add(res2.get(i))); 
					//res2.set(i, sumr.get(i).abs());
				}
			}
		}
		
		for (int i = 0; i < points; i++) {
			res.add(res1.get(i).subtract(res2.get(i)).abs());
		}
		
		double maxVal = Collections.max(res);
		for (int k = 0; k < points; k++) {
			res.set(k, res.get(k)/maxVal);
		}
		return res;
	}

	@Override
	public Image getImage(boolean reflector) {
		/*if (reflector) {
			return new Image("/resources/.png", true);
		} else {
			return new Image("/resources/.png", true);
		}*/
		return null;
	}
}