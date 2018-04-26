package Diagram;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import org.apache.commons.math3.complex.Complex;

import _MenuView.MenuView;
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
	
	MenuView view;
	
	// Local Elements declaration	
	private tblCharts chart = new tblCharts();
	
	ArrayList<Double> winkel = Matlab.linspace(0, 2*Math.PI, 800);
	
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
//			drawCharts();
		}
	} 
	
	public void setParentView(MenuView view) {
		this.view = view;
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
		
		// create polar chart
		chart.createLineChart(pn_LineChart);
		chart.createPolarChart(pn_PolarChart);

		chart.setDataSet(winkel, betragNorm);
	}
}
