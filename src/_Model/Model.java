package _Model;

import java.util.ArrayList;
import java.util.concurrent.SubmissionPublisher;

import _MenuView.MenuView;

public class Model {
	
	MenuView view;
	
	SimantData sData = new SimantData();
	
	private Topology[] form = new Topology[3];
	private Topology[] abstrahl = new Topology[4];
	
	private int points = 1000;
	
	ArrayList<Double> winkel = Matlab.linspace(0.0, 2*Math.PI, points);
	
	ArrayList<Double> betragNorm = new ArrayList<Double>();
	ArrayList<Double> characteristic = new ArrayList<Double>();
	ArrayList<Double> layout = new ArrayList<Double>();
	
	public Model(MenuView view) {
		this.view = view;
		
		// Antenna
		abstrahl[0] = new Lambert(13,0.1,0,1.0,points);
		abstrahl[1] = new Dipol(1,0.5,0,1.0,points);		// entspricht Isotrop!
		abstrahl[2] = new Yagi(0,0,0,0,points);
		abstrahl[3] = new Dipol(2,0.5,0,1.0,points);
		
		// Layout
		form[0] = new Linear(1,1,1,1,points);
		form[1] = new Circle(1,1,1,1,points);
		form[2] = new Matrix(1,1,1,1,points);
		
	}
	
	public void updateInputData(SimantInputData data) {

		sData.setAmp(calculateTopology(data));
		sData.setWinkel(this.winkel);
		
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
		characteristic.addAll(abstrahl[data.getAnt()].calculate());
		System.out.println("ant"+data.getAnt());
		
		// Layout		
		form[data.getForm()].updateData(data.getQuant(),data.getDLambda(),data.getDir(),data.getAmp(),points);
		layout.clear();
		layout.addAll(form[data.getForm()].calculate());
		System.out.println("form"+data.getForm());
		
		// calculate Betrag
		betragNorm.clear();
		for (int i = 0; i < points; i++) {
			betragNorm.add(characteristic.get(i)*layout.get(i));
		}
		return betragNorm;
		
		/*for (int i = 0; i < points; i++) {
			double b = test1.get(i)*test2.get(i);
			betragNorm.add(20*Math.log10(b));
			System.out.println(Math.abs(20*Math.log10(b)));
		}*/
	}
}


