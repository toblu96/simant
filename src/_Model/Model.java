package _Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.SubmissionPublisher;

import _MenuView.MenuView;

public class Model {
	
	MenuView view;
	
	SimantData sData = new SimantData();
	
	private Form[] form = new Form[3];
	private Characteristic[] charact = new Characteristic[4];
	
	private int points = 1000;
	
	ArrayList<Double> winkel = Matlab.linspace(0.0, 2*Math.PI, points);
	
	ArrayList<Double> betragNorm = new ArrayList<Double>();
	ArrayList<Double> characteristic = new ArrayList<Double>();
	ArrayList<Double> layout = new ArrayList<Double>();
	
	public Model(MenuView view) {
		this.view = view;
		
		// Antenna
		charact[0] = new Lambert(0,points);
		charact[1] = new Isotrop(0,points);
		charact[2] = new Yagi(0,points);
		charact[3] = new Dipol(0,points);
		
		// Layout
		form[0] = new Linear();
		form[1] = new Circle();
		form[2] = new Matrix();
	}
	
	public void updateInputData(SimantInputData data) {

		// get Images
		sData.setImgForm(form[data.getForm()].getImage(true));
		sData.setImgOrient(charact[data.getAnt()].getImageOrientation(true));
		
		sData.setAmp(calculateTopology(data));
		sData.setWinkel(this.winkel);
		sData.setImgCharac(charact[data.getAnt()].getImageCharacterictic());
		sData.setTxCharac(charact[data.getAnt()].getText());
		
		updateView(this.sData);
		
	}
	
	public void updateView(SimantData data) {
		SubmissionPublisher<SimantData> publisher = new SubmissionPublisher<>();
		publisher.subscribe(view);
		publisher.submit(data);
		publisher.close();
	}
	
	private ArrayList<Double> calculateTopology(SimantInputData data) {
		
		// Antenna
		characteristic.clear();
		characteristic.addAll(charact[data.getAnt()].calculate());
		
		// Layout
		form[data.getForm()].updateData(data.getQuant(),data.getDLambda(),data.getDir(),data.getAmpArray(), data.getDist(),points);
		layout.clear();
		layout.addAll(form[data.getForm()].calculate());
		
		// calculate Betrag
		betragNorm.clear();
		for (int i = 0; i < points; i++) {
			betragNorm.add(characteristic.get(i)*layout.get(i));
		}
		
		double maxVal = Collections.max(betragNorm);
		for (int k = 0; k < points; k++) {
			betragNorm.set(k, betragNorm.get(k)/maxVal);
		}
		return betragNorm;
		
		/*for (int i = 0; i < points; i++) {
			double b = test1.get(i)*test2.get(i);
			betragNorm.add(20*Math.log10(b));
			System.out.println(Math.abs(20*Math.log10(b)));
		}*/
	}
}


