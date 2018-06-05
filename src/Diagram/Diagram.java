package Diagram;

import java.net.URL;
import java.util.ResourceBundle;

import _MenuView.MenuView;
import _Model.SimantData;
import _Model.tblCharts;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Diagram implements Initializable {
	
	MenuView view;
	
	// Local Elements declaration	
	private tblCharts chart = new tblCharts();
	
	@FXML
	GridPane backGrid;
	
	@FXML
	Pane pn_LineChart, pn_PolarChart;
	
	@FXML 
	FontAwesomeIconView resizePolIcon, resizeLinIcon;
	
	@FXML
	ImageView img_form, img_ant;
		
	
	/**
	 * - erzeugt Plots nach Initilisierung
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chart.createLineChart(pn_LineChart);
		chart.createPolarChart(pn_PolarChart);
	} 
	
	/**
	 * - setzt Referenz der Hauptview in Attribut
	 * 
	 * @param view	-> Referenz auf HauptView
	 */
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	@FXML
	private void resizePolar() {
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
	private void resizeLinear() {
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
	
	/**
	 * - aktualisiert Daten für Plots
	 * 
	 * @param sData	-> gerechnete Daten aus Model
	 */
	public void drawCharts(SimantData sData) {
		chart.setDataSet(sData.getWinkel(), sData.getAmp(), sData.getAmpLogReal());
	}
	
	/**
	 * - aktualisiert Bilder der Antennenausrichtungen
	 * 
	 * @param imgOrient	-> Bild der Antennenausrichtung
	 * @param imgForm	-> Bild der Array-Form
	 */
	public void updatePicture(Image imgOrient, Image imgForm) {
		img_ant.setImage(imgOrient);
		img_form.setImage(imgForm);
	}
}
