package _Model;

import java.util.concurrent.SubmissionPublisher;

import _MenuView.MenuView;

public class Model {
	
	MenuView view;
	
	public Model(MenuView view) {
		this.view = view;
	}
	
	public void updateInputData(SimantInputData data) {
		// Werte auseinandernehmen und Berechnung auslösen!!!!!!!!
		SimantData sD = new SimantData();
		sD.setAmp(Matlab.linspace(0, 10, 10));
		sD.setWinkel(Matlab.linspace(0, 10, 10));
		updateView(sD);
		
	}
	
	public void updateView(SimantData data) {
		SubmissionPublisher<SimantData> publisher = new SubmissionPublisher<>();
		publisher.subscribe(view);
		publisher.submit(data);
		publisher.close();
	}
}


