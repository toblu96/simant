package _Model;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

import javafx.scene.image.Image;

public abstract class Characteristic {
	
	protected int an_direction, points;
	
	public Characteristic(int an_direction, int points) {
		this.an_direction = an_direction;
		this.points = points;
	}
	
	public abstract ArrayList<Double> calculate();
	public abstract void updateData(int an_direction, int points);
	public abstract String getText();
	public abstract Image getImageOrientation(boolean direction);
	public abstract Image getImageCharacterictic();
}


class Isotrop extends Characteristic {
	public Isotrop(int an_direction, int points) {
		super(an_direction, points);
	}
	
	public void updateData(int an_direction, int points) {
		this.an_direction = an_direction;
		this.points = points;
	}
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = 0.5;
		int n = 1;
		double dd_ppd = 2.0*Math.PI*d_L;
		
		ArrayList<Double> pk = new ArrayList<Double>();
		for (int i = 1; i <= n; i++) {
			pk.add(i*dd_ppd);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
					sumr.add(c);
					res.add(sumr.get(i).abs());
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
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
	public String getText() {
		return "Die isotrope Abstrahlung ist eine theoretische Charakteristik. "
				+ "Hierbei strahlt die Antenne in alle Richtungen identisch ab. "
				+ "Die Abstrahlung entspricht einer Kugel. Die isotrope Abstrahlung dient der Vorstellung "
				+ "und dem Vergleich mit den realen Abstrahlcharakteristiken respektive Antennen.";
	}
	
	@Override
	public Image getImageOrientation(boolean direction) {
		if (direction) {
			return new Image("/resources/Java_Iso_Horiz.png", true);
		} else {
			return new Image("/resources/Java_Iso_Vert.png", true);
		}
	}

	@Override
	public Image getImageCharacterictic() {
		return new Image("/resources/Java_Iso.png", true);
	}
}



class Dipol extends Characteristic {
	public Dipol(int an_direction, int points) {
		super(an_direction, points);
	}
	
	public void updateData(int an_direction, int points) {
		this.an_direction = an_direction;
		this.points = points;
	}
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = 0.5;
		int n = 2;
		double dd_ppd = 2.0*Math.PI*d_L;
		
		ArrayList<Double> pk = new ArrayList<Double>();
		for (int i = 1; i <= n; i++) {
			pk.add(i*dd_ppd);
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
					sumr.add(c);
					res.add(sumr.get(i).abs());
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
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
	public String getText() {
		return "Die Abstrahlung nach Dipol ist als reale Dipolantenne zu verstehen. "
				+ "Die Abstrahlcharakteristik Dipol ist als reale Form der Isotopen Abstrahlung zu verstehen. "
				+ "Die Dipolantenne hat die Hauptstrahlrichtung entgegengesetzt der Antennenausrichtung. "
				+ "Wenn die Antenne vertikal ausgerichtet ist, ist die Hautstrahlrichtung horizontal.";
	}

	@Override
	public Image getImageOrientation(boolean direction) {
		if (direction) {
			return new Image("/resources/Java_Dipol_Horiz.png", true);
		} else {
			return new Image("/resources/Java_Dipol_Vert.png", true);
		}
	}

	@Override
	public Image getImageCharacterictic() {
		return new Image("/resources/Java_Dipol.png", true);
	}
}


class Lambert extends Characteristic {
	public Lambert(int an_direction, int points) {
		super(an_direction, points);
	}
	
	public void updateData(int an_direction,int points) {;
		this.an_direction = an_direction;
		this.points = points;
	}
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
		ArrayList<Double> res = new ArrayList<Double>();
		double richtd = Matlab.deg2rad((double)(an_direction));
		double d_L = 0.1;
		int v_F = 6;
		int n = 13;
		double dd_ppd = 2.0*Math.PI*d_L;
		
		ArrayList<Double> x = Matlab.linspace(0.0, Math.PI, n);
		ArrayList<Double> pk = new ArrayList<Double>();
		ArrayList<Double> ak = new ArrayList<Double>();
		for (int i = 1; i <= n; i++) {
			pk.add(i*dd_ppd);
			ak.add(1+(v_F-1.0)*Math.pow(Math.cos(-Math.PI/2+x.get(i-1)), 2));
		}
		
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i < points; i++) {
				if (k==1) {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
					c = c.multiply(ak.get(k-1));
					sumr.add(c);
					res.add(sumr.get(i).abs());
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)), 
											 Math.sin(-(k-1)*d_L*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk.get(k-1)), Math.sin(pk.get(k-1)));
					Complex c = c1.multiply(c2);
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
	public String getText() {
		return "Die Abstrahlung nach Lambert ist eine theoretische Charakteristik. "
				+ "Hierbei strahlt die Antenne in eine Richtung ab. "
				+ "Die Abstrahlung wird mit einem idealen Richtstrahl verglichen. "
				+ "Diese Charakteristik dient der Vorstellung und dem Vergleich mit "
				+ "den realen Abstrahlcharakteristiken respektive Antennen.";
	}

	@Override
	public Image getImageOrientation(boolean direction) {
		if (direction) {
			return new Image("/resources/Java_Lampert_Horiz.png", true);
		} else {
			return new Image("/resources/Java_Lampert_Vert.png", true);
		}
	}

	@Override
	public Image getImageCharacterictic() {
		return new Image("/resources/Java_Lampert_Final.png", true);
	}
}


class Yagi extends Characteristic {
	public Yagi(int an_direction, int points) {
		super(an_direction, points);
	}
	
	public void updateData(int an_direction, int points) {

		this.an_direction = an_direction;
		this.points = points;
	}
	
	public ArrayList<Double> calculate() {
		ArrayList<Double> psi_r = Matlab.linspace(0.0, 2*Math.PI, points);
		ArrayList<Complex> sumr = new ArrayList<Complex>();
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
					sumr.add(c);
					res1.add(sumr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)), 
							 				 -Math.sin((k-1)*d_L1*2.0*Math.PI*Math.cos(psi_r.get(i)+richty-richtd)));
					Complex c2 = new Complex(Math.cos(pk1.get(k-1)), Math.sin(pk1.get(k-1)));
					Complex c= c1.multiply(c2);
					sumr.set(i, c.add(sumr.get(i))); 
					res1.set(i, sumr.get(i).abs());
				}
			}
		}
		
		sumr.clear();
		
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
					sumr.add(c);
					res2.add(sumr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
							 				 -Math.sin((k-1)*d_L2*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk2.get(k-1)), Math.sin(pk2.get(k-1)));
					Complex c= c1.multiply(c2);
					sumr.set(i, c.add(sumr.get(i))); 
					res2.set(i, sumr.get(i).abs());
				}
			}
		}
		
		sumr.clear();
		
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
					sumr.add(c);
					res3.add(sumr.get(i).abs());	
				} else {
					Complex c1 = new Complex(Math.cos((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)),
							 				 -Math.sin((k-1)*d_L3*2.0*Math.PI*Math.cos(psi_r.get(i)-richtd)));
					Complex c2 = new Complex(Math.cos(pk3.get(k-1)), Math.sin(pk3.get(k-1)));
					Complex c= c1.multiply(c2);
					sumr.set(i, c.add(sumr.get(i))); 
					res3.set(i, sumr.get(i).abs());
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

	@Override
	public String getText() {
		return "Die Yagi-Uda-Antenne besteht aus einer Linearen Anordnung von Dipolantennen und einem Reflektor. "
				+ "Dabei ist dient die am nächsten zum Reflektor gelegene Antenne als Strahler. "
				+ "Die weiteren Antennen dienen als Direktor, verstärken somit das Signal in eine Richtung "
				+ "(Abb. Z Richtung). Wird diese Charakteristik gewählt, werden Yagi-Uda Antennen in "
				+ "die jeweiligen Array Formen (Kreis, Linie, Array) gebracht.";
	}

	@Override
	public Image getImageOrientation(boolean direction) {
		if (direction) {
			return new Image("/resources/Java_Yagi_Horiz.png", true); 
		} else {
			return new Image("/resources/Java_Yagi_Vert.png", true);
		}
	}

	@Override
	public Image getImageCharacterictic() {
		return new Image("/resources/Java_Yagi.png", true);
	}
}