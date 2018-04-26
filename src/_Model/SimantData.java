package _Model;

import java.util.ArrayList;

public class SimantData {
	// all Data
	private ArrayList<Double> winkel = new ArrayList<>();
	private ArrayList<Double> amplitude = new ArrayList<>();
	
	public void setWinkel(ArrayList<Double> data) {
		this.winkel = data;
	}
	
	public ArrayList<Double> getWinkel() {
		return this.winkel;
	}
	
	public void setAmp(ArrayList<Double> data) {
		this.amplitude = data;
	}
	
	public ArrayList<Double> getAmp() {
		return this.amplitude;
	}
	
	
}
