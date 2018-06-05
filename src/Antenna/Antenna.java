package Antenna;

import java.net.URL;
import java.util.ResourceBundle;

import _MenuView.MenuView;
import _Model.SimantData;
import _Model.SimantInputData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;

public class Antenna implements Initializable {
	
	MenuView view;
	
	@FXML 
	Polygon pg_SelectedAntenna;
	
	@FXML
	ImageView img_ant;
	
	@FXML
	Label txt_ant;
	
	/**
	 * - setzt Pfeil der Ausgewählten Antenne nach initialisierung
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GridPane.setRowIndex(pg_SelectedAntenna, 0);
	} 
	
	/**
	 * - setzt Attribut der Hauptview
	 * 
	 * @param view	-> Referenz auf Hauptview
	 */
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	/**
	 * - aktualisiert alle Eingabefelder
	 * 
	 * @param data	-> Datentyp Eingabeparameter
	 */	
	public void updateInputs(SimantInputData data) {
		switch (data.getAnt()) {
		case 0: manageButton1(null);	break;
		case 1: manageButton2(null);	break;
		case 2: manageButton3(null);	break;
		case 3: manageButton4(null);	break;
		}
	}
	
	/**
	 * - aktualisiert Bild und Text in Informationspanel
	 * 
	 * @param data	-> Daten von Model
	 */
	public void updateView(SimantData data) {
		img_ant.setImage(data.getImgCharac());
		txt_ant.setText(data.getTxCharac());
	}
	
	@FXML
	private void manageButton1(ActionEvent e) {
		GridPane.setRowIndex(pg_SelectedAntenna, 0);
		this.view.setAnt(0);
    } 
	
	@FXML
	private void manageButton2(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 1);
		this.view.setAnt(1);
    } 
	
	@FXML
	private void manageButton3(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 2);
		this.view.setAnt(2);
    } 
	
	@FXML
	private void manageButton4(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 3);
		this.view.setAnt(3);
    }
	
}
