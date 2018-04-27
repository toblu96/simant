package _Model;

public class SimantInputData {

	// all Data
	private int antenna;
	private int form;
	private int quantity;
	private Double dLambda;
	private Integer direction;
	private Double amplitude;
	
	
	
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
	
	public void setQuant(Integer data) {
		this.quantity = data;
	}
	
	public Integer getQuant() {
		return this.quantity;
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
	
	public void setAmp(Double data) {
		this.amplitude = data;
	}
	
	public Double getAmp() {
		return this.amplitude;
	}
	
	
	
}
