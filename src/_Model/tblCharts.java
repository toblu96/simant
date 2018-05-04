package _Model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class tblCharts {

	// Color maps (from Matlab) >> parula(20), jet(20)
	private double[][] jet = {	{0, 0, 0, 0, 0, 0, 0, 0, 0.2000, 0.4000, 0.6000, 0.8000, 1, 1, 1, 1, 1, 1, 0.8000, 0.6000 } , 
								{0, 0, 0, 0.2000, 0.4000, 0.6000, 0.8000, 1, 1, 1, 1, 1, 1, 0.8000, 0.6000, 0.4000, 0.2000, 0, 0, 0 } , 
								{0.6000, 0.8000, 1, 1, 1, 1, 1, 1, 0.8000, 0.6000, 0.4000, 0.2000, 0, 0, 0, 0, 0, 0, 0, 0 }	};
	
	@SuppressWarnings("unused")
	private double[][] parula = {	{0.2422, 0.2667, 0.2797, 0.2796, 0.2567, 0.1867, 0.1700, 0.1356, 0.0831, 0.0040, 
									0.1466, 0.2291, 0.3802, 0.5675, 0.7455, 0.8934, 0.9961, 0.9857, 0.9595, 0.9769 } , 
									{0.1504, 0.2028, 0.2699, 0.3435, 0.4185, 0.4981, 0.5681, 0.6315, 0.6879, 0.7296,
									0.7597, 0.7880, 0.8026, 0.7936, 0.7657, 0.7348, 0.7445, 0.8228, 0.9058, 0.9839 } , 
									{0.6603, 0.8087, 0.9141, 0.9710, 0.9962, 0.9841, 0.9374, 0.8954, 0.8494, 0.7701,
									0.6797, 0.5757, 0.4448, 0.2998, 0.1789, 0.1712, 0.2362, 0.1841, 0.1463, 0.0805 }	};
	
	// reference pane
	private Group polGroup = new Group();
	private Group linGroup = new Group();
	private Pane polPane;
	private Pane linPane;
	
	// chart data
	private ArrayList<Double> x;
	private ArrayList<Double> y;
	
	// reference and scale points
	private double radius;
	private double maxRadius;
	private double maxWinkel;
	private double middleX;
	private double middleY;
	
	
	
	private void drawPolar() {
		// clear all existing elements
		polGroup.getChildren().clear();
		
		// draw nothing if pane minimized
		if (polPane.getHeight() < 1 || this.x == null) {
			return;
		}
		
		// get Positions
		this.radius = Math.min(this.polPane.getHeight()/2.5, this.polPane.getWidth()/2.5);
		this.middleX = this.polPane.getWidth()/2;
		this.middleY = this.polPane.getHeight()/2;
		
		// Circles with label
		int circles = 6;
		for (int i = 0; i < circles; i++) {
			double amp = this.maxRadius / (circles-1) * (circles -1 -i); // replace with maxAmp from ChartInput!
			
			Circle circ = new Circle(middleX,middleY,radius/(circles-1)*i);
			circ.setStroke(Color.BLACK);
			circ.setFill(null);
			circ.setStrokeWidth(2);
			polGroup.getChildren().add(circ);
			
			Label label = new Label("-"+(int)amp);
			label.setLayoutX(middleX + 10);
			label.setLayoutY(middleY + 5 - radius/(circles-1)*i);
			polGroup.getChildren().add(label);
		}
		// Lines with Label (Degrees)
		int lines = 12;
		for (int i = 0; i < lines; i++) {
			double angle = 2* Math.PI / 12 * i; 
			Line line = new Line();
			line.setLayoutX(middleX);
			line.setLayoutY(middleY);
			line.setEndX((radius)*Math.cos(angle));
			line.setEndY(-(radius)*Math.sin(angle));
			polGroup.getChildren().add(line);
			
			Label label = new Label(""+(int)Math.ceil(Math.toDegrees(angle)));
			label.setLayoutX(middleX - 5 + (radius+30)*Math.cos(angle));
			label.setLayoutY(middleY - 5 - (radius+20)*Math.sin(angle));
			polGroup.getChildren().add(label);
		}
		
		// Shape from dataSet
		for (int i = 0; i < this.x.size()-1; i++) {
			double scaleFactor = this.radius/this.maxRadius;	// Scale Factor
			double xStart = this.middleX + scaleFactor*(this.y.get(i))*Math.cos(this.x.get(i));
			double yStart = this.middleY - scaleFactor*(this.y.get(i))*Math.sin(this.x.get(i));
			double xEnd = this.middleX + scaleFactor*(this.y.get(i+1))*Math.cos(this.x.get(i+1));
			double yEnd = this.middleY - scaleFactor*(this.y.get(i+1))*Math.sin(this.x.get(i+1));
			
			Line line = new Line(xStart, yStart, xEnd, yEnd);
			line.setStroke(getColorMap(jet, this.y.get(i)));
			line.setStrokeWidth(3);
			polGroup.getChildren().add(line);
		}
	}
	
	private void drawLin() {
		// clear all existing elements
		linGroup.getChildren().clear();
		
		// draw nothing if pane minimized
		if (linPane.getHeight() < 1 || this.x == null) {
			return;
		}
		
		// draw horizontal lines
		int horLines = 9;
		for (int i = 0; i < horLines; i++) {
			double amp = this.maxRadius / (horLines-1) * (horLines -1 -i);
			double calcHeight = linPane.getHeight()/(horLines-1)*i*0.9 + linPane.getHeight()*0.05;
			
			Line line = new Line(linPane.getWidth()*0.05,calcHeight,linPane.getWidth()*0.95,calcHeight);
			linGroup.getChildren().add(line);
			
			Label label = new Label("-"+(int)amp);
			label.setLayoutX(10);
			label.setLayoutY(calcHeight-10);
			linGroup.getChildren().add(label);
		}
		
		// draw vertical lines
		int vertLines = 10;
		for (int i = 0; i < vertLines; i++) {
//			double amp = this.maxRadius / (vertLines-1) * (vertLines -1 -i);
			double calcHeight = linPane.getWidth()/(vertLines-1)*i*0.9 + linPane.getWidth()*0.05;
			
			Line line = new Line(calcHeight, linPane.getHeight()*0.05,calcHeight,linPane.getHeight()*0.95);
			linGroup.getChildren().add(line);
			
			Label label = new Label("" + 360 / (vertLines-1) * i);
			label.setLayoutX(calcHeight - 10);
			label.setLayoutY(linPane.getHeight()-20);
			linGroup.getChildren().add(label);
		}	
		
		// Shape from dataSet
		for (int i = 0; i < this.x.size()-1; i++) {
			double scaleFactor = linPane.getHeight()*0.9/this.maxRadius;	// Scale Factor
			double offset = linPane.getHeight()*0.05/this.maxRadius;
			double xStart = linPane.getWidth()*0.9 / this.maxWinkel*this.x.get(i) + linPane.getWidth()*0.05;
			double yStart = linPane.getHeight() - offset - scaleFactor*(this.y.get(i));
			double xEnd = linPane.getWidth()*0.9 / this.maxWinkel*this.x.get(i+1) + linPane.getWidth()*0.05;
			double yEnd = linPane.getHeight() - offset - scaleFactor*(this.y.get(i+1));
			
			Line line = new Line(xStart, yStart, xEnd, yEnd);
			line.setStroke(getColorMap(jet, this.y.get(i)));
			line.setStrokeWidth(3);
			linGroup.getChildren().add(line);
		}
	}
	
	private Paint getColorMap(double[][] map, double radius) {
		int index = (int) (map[0].length/this.maxRadius*radius - 1);		// calculate index of ColorMap
		return Color.rgb((int)(255 * map[0][index]), (int)(255 * map[1][index]), (int)(255 * map[2][index]));
	}
	
	@SuppressWarnings("unused")
	private int linColor(double val, double inMin, double inMax, double outMin, double outMax) {
		int res = (int) ((outMax - outMin) * (val - inMin) / (inMax - inMin) + outMin);
		if (res > Math.max(outMax, outMin)) { return (int) Math.max(outMax, outMin); }
		if (res < Math.min(outMax, outMin)) { return (int) Math.min(outMax, outMin); }
		return res;
	}
	
	/**
	 * Places the PolarChart to a specific pane
	 * 
	 * @param pane -> Panel, on which polarChart will be placed
	 */
    public void createPolarChart(Pane pane) {
    	this.polPane = pane;
    	// detect resizing from Pane
		this.polPane.widthProperty().addListener((obs, oldVal, newVal) -> {		drawPolar();	});
		this.polPane.heightProperty().addListener((obs, oldVal, newVal) -> {   	drawPolar();	});
    	
		// add group to pane
    	this.polPane.getChildren().add(polGroup);
    }
    
    /**
     * Places the LineChart to a specific pane
     * 
     * @param pane -> Panel, on which lineChart will be placed
     */
    public void createLineChart(Pane pane) {
    	this.linPane = pane;
    	// detect resizing from Pane
		this.linPane.widthProperty().addListener((obs, oldVal, newVal) -> {		drawLin();	});
		this.linPane.heightProperty().addListener((obs, oldVal, newVal) -> {   	drawLin();	});

		// add group to pane
    	this.linPane.getChildren().add(linGroup);
    }
    
    
    /**
     * Set Parameters for all Charts, draws/redraws Chart
     * 
     * @param angle -> ArrayList of angle
     * @param norm 	-> ArrayList of norm value
     */
    public void setDataSet(ArrayList<Double> angle, ArrayList<Double> norm) {
    	this.x = angle;
    	this.y = norm;
    	this.maxRadius = Collections.max(y);
    	this.maxWinkel = Collections.max(x);
    	// redraw with new values
    	if (polPane != null) {
    		drawPolar();
		}
    	if (linPane != null) {
    		drawLin();
		}
    	
    }
	
}
