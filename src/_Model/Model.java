package _Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

import _MenuView.MenuView;

public class Model {
	
	MenuView view;
	
	SimantData sData = new SimantData();
	
	private Form[] form = new Form[3];
	private Form[] reflector = new Form[1];
	private Characteristic[] charact = new Characteristic[4];
	
	private int points = 1000;
	
	ArrayList<Double> winkel = Matlab.linspace(0.0, 2*Math.PI, points);
	
	ArrayList<Double> betragNorm = new ArrayList<Double>();
	ArrayList<Double> betragNormLog = new ArrayList<Double>();
	ArrayList<ArrayList<Double> > res = new ArrayList<ArrayList<Double> >();
	ArrayList<Double> characteristic = new ArrayList<Double>();
	ArrayList<Double> layout = new ArrayList<Double>();
	ArrayList<Double> refl = new ArrayList<Double>();
	
	/**
	 * - erzeugt Charakteristiken und Array-Formen
	 * 
	 * @param view	-> Referenz auf View für Publisher (MVC olé)
	 */
	public Model(MenuView view) {
		this.view = view;
		
		// Antenna
		charact[0] = new Lambert();
		charact[1] = new Isotrop();
		charact[2] = new Yagi();
		charact[3] = new Dipol();
		
		// Layout
		form[0] = new Linear();
		form[1] = new Circle();
		form[2] = new Matrix();
		
		// Reflector
		reflector[0] = new Reflector();
		
	}
	
	/**
	 * - Errechnet Amplituden mit allen Eingabeparameter
	 * - Sendet gerechnete Werte an die View
	 * 
	 * @param data	-> Eingabeparameter (SimantInputData)
	 */
	public void updateInputData(SimantInputData data) {

		// get Images
		sData.setImgForm(form[data.getForm()].getImage(data.getReflektor()));	// set true if reflector!
		sData.setImgOrient(charact[data.getAnt()].getImageOrientation(!data.getAntVertikal()));	// set false if antenna orientation vertical
		sData.setAmp(calculateTopology(data).get(0));
		sData.setAmpLog(calculateTopology(data).get(1));
		sData.setWinkel(this.winkel);
		sData.setImgCharac(charact[data.getAnt()].getImageCharacterictic());
		sData.setTxCharac(charact[data.getAnt()].getText());
		
		updateView(this.sData);
	}
	
	private void updateView(SimantData data) {
		SubmissionPublisher<SimantData> publisher = new SubmissionPublisher<>();
		publisher.subscribe(view);
		publisher.submit(data);
		publisher.close();
	}
	
	private ArrayList<ArrayList<Double>> calculateTopology(SimantInputData data) {
		
		// Antenna
		charact[data.getAnt()].updateData(data.getDir(), points);
		characteristic.clear();
		characteristic.addAll(charact[data.getAnt()].calculate());
		
		// Layout
		form[data.getForm()].updateData(data.getDLambda(),data.getDirHauptk(),data.getAmpArray(), data.getDist(),points);
		layout.clear();
		layout.addAll(form[data.getForm()].calculate());
		
		//Reflector
		if (data.getReflektor()) {
			reflector[0].updateData(data.getDLambda(),data.getDirHauptk(),data.getAmpArray(), data.getDist(),points);
			refl.clear();
			refl.addAll(reflector[0].calculate());
		}
		
		// calculate Betrag
		betragNorm.clear();
		for (int i = 0; i < points; i++) {
			betragNorm.add(characteristic.get(i)*layout.get(i));
			if (data.getReflektor()) {
				betragNorm.set(i, betragNorm.get(i) * refl.get(i));
			}
		}
		
		betragNormLog.clear();
		for (int i = 0; i < points; i++) {
			double b = betragNorm.get(i);
			betragNormLog.add((20*Math.log10(b)));
		}
		
		double maxVal = Collections.max(betragNorm);
		double minValLog = Collections.min(betragNormLog);
		sData.setAmpLogReal(Math.abs(minValLog));
		for (int k = 0; k < points; k++) {
			betragNorm.set(k, betragNorm.get(k)/maxVal);
			betragNormLog.set(k, betragNormLog.get(k)/minValLog);
		}
		
		res.add(0, betragNorm);
		res.add(1, betragNormLog);
		
		return res;
	}
}