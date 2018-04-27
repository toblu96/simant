package _Model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math3.complex.Complex;

import Antenna.Antenna;

public abstract class Topology {
	
	protected int an_number, an_direction, points;
	protected double an_lambda, an_amplitude;
	
	public Topology(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
		this.an_amplitude = an_amplitude;
		this.an_number = an_number;
		this.an_lambda = an_lambda;
		this.an_direction = an_direction;
		this.points = points;
	}
	
	public abstract ArrayList<Double> calculate();
}



class Matrix extends Topology {
	public Matrix(int an_number, double An_Lambda, int an_direction, double an_amplitude, int points) {
		super(an_number, An_Lambda, an_direction, an_amplitude, points);
	}
	
	public ArrayList<Double> calculate() {
		
		return null;
	}
	
	public void updateData(int an_number, double an_lambda, int an_direction, double an_amplitude, int points) {
		this.an_number = an_number;
		this.an_lambda = an_lambda;
		this.an_direction = an_direction;
		this.an_amplitude = an_amplitude;
		this.points = points;
	}
}