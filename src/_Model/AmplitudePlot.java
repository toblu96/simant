package _Model;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class AmplitudePlot {

	private Group ampGroup = new Group();
	private Pane ampPane;
	
	private int antQuant;
	private double percent;
	private ArrayList<Double> amp;
	
	
	public void initPane(Pane pane) {
		this.ampPane = pane;
		this.ampPane.widthProperty().addListener((obs, oldVal, newVal) -> {		redraw();	});
		this.ampPane.heightProperty().addListener((obs, oldVal, newVal) -> {   	redraw();	});
	}
	
	public void setAntQuant(int quant) {
		this.antQuant = quant;
		redraw();
	}

	public void setPercentage(int percent) {
		this.percent = (100.0 - percent) / 100.0;
		redraw();
	}
	
	public ArrayList<Double> getAmp() {
		return amp;
	}
	
	
	private void redraw() {
		
		// clear all existing elements
		ampGroup.getChildren().clear();
		ampPane.getChildren().clear();
		
		// draw nothing if pane minimized
		if (ampPane.getHeight() < 1) {
			return;
		}
		
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
		
		for (int i = 0; i < this.antQuant; i++) {
			Line ampline = new Line();
			if (this.antQuant > 1) {
				ampline.setLayoutX((ampPane.getWidth() - 80) / (this.antQuant-1) * i + 40);
			} else {
				ampline.setLayoutX(40);
			}

			ampline.setLayoutY(ampPane.getHeight() - 20);
			ampline.setEndX(0);
			ampline.setEndY(-amp.get(i)*50 + 20);
			ampline.setStroke(Color.RED);
			ampline.setStrokeWidth(2);
			
			ampGroup.getChildren().add(ampline);
		}		
	}
	
	private ArrayList<Double> calculateAmp() {
		ArrayList<Double> x = Matlab.linspace(0, Math.PI, this.antQuant);
		ArrayList<Double> res = new ArrayList<Double>();
		double a = 1.0;	//offset
		double t = this.percent;
		for (int i = 0; i < x.size(); i++) {
			res.add(a+t+(1.0-t)*Math.pow(Math.cos(x.get(i)-Math.PI/2.0),2.0));
		}
		return res;
		
	}
	
}
