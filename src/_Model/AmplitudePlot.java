package _Model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AmplitudePlot {

	private Group ampGroup = new Group();
	private Pane ampPane;
	
	private int antQuantX = 1;
	private int antQuantY = 1;
	private double percent = 0.0;
	private List<List<Double>> amp;
	
	/**
	 * - Übergibt Referenz auf Panel, um AmpPlot darauf zu zeichnen
	 * - erstellt Listener, welche Grössenänderungen des Panels detektieren
	 * 
	 * @param pane	-> Referenz des darauf zu zeichnenden Panels
	 */
	public void initPane(Pane pane) {
		this.ampPane = pane;
		this.ampPane.widthProperty().addListener((obs, oldVal, newVal) -> {		if(Math.abs(oldVal.doubleValue() - newVal.doubleValue()) > 5) redraw();	});
		this.ampPane.heightProperty().addListener((obs, oldVal, newVal) -> {   	if(Math.abs(oldVal.doubleValue() - newVal.doubleValue()) > 5) redraw();	});
	}
	
	/**
	 * - übergibt Array-Grösse von Eingabefeldern
	 * - setzt Attribute
	 * - berechnet und zeichnet neu
	 * 
	 * @param x	-> Anzahl Antennen in X-Richtung
	 * @param y	-> Anzahl Antennen in Y-Richtung
	 * @return	-> gerechnetes cos^2 Array
	 */
	public List<List<Double>> setAntQuant(int x, int y) {
		this.antQuantX = x;
		this.antQuantY = y;
		redraw();
		return amp;
	}

	/**
	 * - liest Prozentwert des Sliders
	 * - setzt Attribute
	 * - berechnet und zeichnet neu
	 * 
	 * @param percent	-> Sliderwert von Eingabefelder
	 * @return	-> gerechnetes cos^2 Array
	 */
	public List<List<Double>> setPercentage(double percent) {
		this.percent = (100.0 - (int)(percent)) / 100.0;
		redraw();
		return amp;
	}
	
	private void redraw() {
		
		// clear all existing elements
		ampGroup.getChildren().clear();
		ampPane.getChildren().clear();
		
		// draw content
		drawShape();
		
		
		// add group to pane
    	this.ampPane.getChildren().add(ampGroup);
	}
	
	private void drawShape() {
		Line line = new Line();
		
		line.setLayoutX(40);
		line.setLayoutY(ampPane.getHeight() - 20);
		line.setEndX(ampPane.getWidth() - 80);
		line.setEndY(0);
		line.setStroke(Color.BLACK);
		line.setFill(null);
		line.setStrokeWidth(4);
		ampGroup.getChildren().add(line);
		
		amp = calculateAmp();
		
		for (int i = 0; i < this.antQuantX; i++) {
			Line ampline = new Line();
			if (this.antQuantX > 1) {
				ampline.setLayoutX((ampPane.getWidth() - 80) / (this.antQuantX-1) * i + 40);
			} else {
				ampline.setLayoutX(40);
			}

			ampline.setLayoutY(ampPane.getHeight() - 20);
			ampline.setEndX(0);
			ampline.setEndY(-amp.get(0).get(i)*50 + 20);
			ampline.setStroke(Color.RED);
			ampline.setStrokeWidth(2);
			
			ampGroup.getChildren().add(ampline);
		}		
	}
	
	private List<List<Double>> calculateAmp() {
		ArrayList<Double> x = Matlab.linspace(0, Math.PI, this.antQuantX);
		List<List<Double>> res = new ArrayList<>();
		double a = 1.0;	//offset
		double t = this.percent;
		for (int j = 0; j < antQuantY; j++) {
			res.add(new ArrayList<>());
			for (int i = 0; i < x.size(); i++) {
				res.get(j).add(a+t+(1.0-t)*Math.pow(Math.cos(x.get(i)-Math.PI/2.0),2.0));
			}
		}
		
		return res;
		
	}
	
}
