package _Model;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class SimantData {
	// all Data
	private ArrayList<Double> winkel = new ArrayList<>();
	private ArrayList<Double> amplitude = new ArrayList<>();
	private Image imgOrientation = null;
	private Image imgForm = null;
	
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
	
	public void setImgOrient(Image data) {
		this.imgOrientation = data;
	}
	
	public Image getImgOrient() {
		return this.imgOrientation;
	}
	
	public void setImgForm(Image data) {
		this.imgForm = data;
	}
	
	public Image getImgForm() {
		return this.imgForm;
	}
	
	
}
