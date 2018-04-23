package Diagram;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import _Model.Complex;
import _Model.Matlab;
import _Model.tblCharts;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Diagram implements Initializable {
	
	// Local Elements declaration	
	private tblCharts chart = new tblCharts();
	
	int points = 800;
	double dLambda = 0.5;
	double phase = 0.3;
	int elements = 5;
	
	ArrayList<Double> winkel = Matlab.linspace(0, 2*Math.PI, points);
	
	ArrayList<Double> betrag = new ArrayList<Double>();
	ArrayList<Double> betragNorm = new ArrayList<Double>();
	
	@FXML
	GridPane backGrid;
	
	@FXML
	Pane pn_LineChart, pn_PolarChart;
	
	@FXML 
	FontAwesomeIconView resizePolIcon, resizeLinIcon;
		
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (pn_LineChart != null) {
			drawCharts();
		}
		
	} 
	
	// Local Calls from Elements
	@FXML
	protected void resizePolar() {
		if (backGrid.getColumnConstraints().get(0).getPercentWidth() < 100) {
			backGrid.getRowConstraints().get(2).setMaxHeight(0);
			backGrid.getColumnConstraints().get(0).setPercentWidth(100);
			resizePolIcon.setIcon(FontAwesomeIcon.TIMES);
		} else {
			backGrid.getRowConstraints().get(2).setMaxHeight(Control.USE_COMPUTED_SIZE);
			backGrid.getColumnConstraints().get(0).setPercentWidth(-1);
			resizePolIcon.setIcon(FontAwesomeIcon.EXPAND);
		}
		
	}
	
	@FXML
	protected void resizeLinear() {
		if (backGrid.getColumnConstraints().get(0).getPercentWidth() < 100) {
			backGrid.getRowConstraints().get(1).setMaxHeight(0);
			backGrid.getColumnConstraints().get(0).setPercentWidth(100);
			resizeLinIcon.setIcon(FontAwesomeIcon.TIMES);
		} else {
			backGrid.getRowConstraints().get(1).setMaxHeight(Control.USE_COMPUTED_SIZE);
			backGrid.getColumnConstraints().get(0).setPercentWidth(-1);
			resizeLinIcon.setIcon(FontAwesomeIcon.EXPAND);
		}
	}
	
	// Local Calls	
	private void drawCharts() {
		for (int i = 0; i < winkel.size(); i++) {
			Complex c = new Complex(0, 0);
			for (int j = 1; j < elements; j++) {
				double real = Math.cos(	(j-1)*dLambda*2*Math.PI* (Math.cos(winkel.get(i)) ) );
				double imag = Math.sin(	(j-1)*dLambda*2*Math.PI* (Math.cos(winkel.get(i)) ) );
				Complex cmp = new Complex(real, imag);
				c = c.plus(cmp.exp());
			}
			
			betrag.add(c.abs());
		}
		
		System.out.println(betrag.get(1));
		System.out.println(betrag.get(2));
		System.out.println(betrag.get(3));
		System.out.println(betrag.get(4));
		System.out.println(betrag.get(5));
		System.out.println(betrag.get(6));
		System.out.println(betrag.size());
		
		for (int i = 0; i < winkel.size(); i++) {
			double res = betrag.get(i) / Collections.max(betrag);
			betragNorm.add(res);
		}
		
		System.out.println(betragNorm.get(1));
		System.out.println(betragNorm.get(2));
		System.out.println(betragNorm.get(3));
		System.out.println(betragNorm.get(4));
		System.out.println(betragNorm.get(5));
		System.out.println(betragNorm.get(6));
		System.out.println(betragNorm.size());
		
		
		
		// create polar chart
		chart.createLineChart(pn_LineChart);
		chart.createPolarChart(pn_PolarChart);

		chart.setDataSet(winkel, betragNorm);
	}
}
