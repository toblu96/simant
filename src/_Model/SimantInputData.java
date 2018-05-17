package _Model;

import java.util.ArrayList;
import java.util.List;

public class SimantInputData {

	// all Data
	private int antenna;
	private int form;
	private Double dLambda;
	private Integer direction, dirHauptkaeule;
	private Double amplitude;
	List<List<Double>> ampArray = new ArrayList<>();
	private Double ampPercent;
	private Double distance;
	private boolean reflektor, antVertikal;
	
	
	
	public void setAnt(int data) {
		this.antenna = data;
	}
	
	public Integer getAnt() {
		return this.antenna;
	}
	
	public void setForm(Integer data) {
		this.form = data;
	}
	
	public Integer getForm() {
		return this.form;
	}
	
	public void setDLambda(Double data) {
		this.dLambda = data;
	}
	
	public Double getDLambda() {
		return this.dLambda;
	}

	public void setDir(Integer data) {
		this.direction = data;
	}
	
	public Integer getDir() {
		return this.direction;
	}
	
	public void setDirHauptk(Integer data) {
		this.dirHauptkaeule = data;
	}
	
	public Integer getDirHauptk() {
		return this.dirHauptkaeule;
	}
	
	public void setAmp(Double data) {
		this.amplitude = data;
	}
	
	public Double getAmp() {
		return this.amplitude;
	}
	
	public void setAmpArray(List<List<Double>> data) {
		this.ampArray = data;
	}
	
	public List<List<Double>> getAmpArray() {
		return this.ampArray;
	}
	
	public void setAmpPercent(Double data) {
		this.ampPercent = data;
	}
	
	public Double getAmpPercent() {
		return this.ampPercent;
	}
	
	public void setDist(Double data) {
		this.distance = data;
	}
	
	public Double getDist() {
		return this.distance;
	}
	
	public void setReflektor(boolean data) {
		this.reflektor = data;
	}
	
	public boolean getReflektor() {
		return this.reflektor;
	}
	
	public void setAntVertikal(boolean data) {
		this.antVertikal = data;
	}
	
	public boolean getAntVertikal() {
		return this.antVertikal;
	}
	
}
