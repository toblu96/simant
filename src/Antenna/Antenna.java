package Antenna;

import java.net.URL;
import java.util.ResourceBundle;

import _MenuView.MenuView;
import _Model.SimantInputData;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;

public class Antenna implements Initializable {
	
	MenuView view;
	
	// Local Elements declaration
	@FXML 
	Polygon pg_SelectedAntenna;
	
	@FXML 
	AnchorPane apn_AntennaInfo;
	
	@FXML
	//ImageView iv_Antenna; file:src/resources/
	
//	Image AbstrahlYagi = new Image(getClass().getResourceAsStream("/resources/AbstrahlYagi.png"));
	//Image Icon = new Image("resources/Icon.png");
	
	private ObjectProperty<javafx.scene.image.Image> imageProperty = new SimpleObjectProperty<>();
//	@FXML
//	ImageView iv_Antenna = new ImageView(AbstrahlYagi);
	//Bindings.bindBidirectional(this.iv_Antenna.imageProperty(), GlobalModel.getInstance().getProject().getImageProperty());
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (pg_SelectedAntenna != null) {
			//iv_Antenna.setImage(AbstrahlYagi);
			GridPane.setRowIndex(pg_SelectedAntenna, 0);
		}
	} 
	
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	public void updateInputs(SimantInputData data) {
		switch (data.getAnt()) {
		case 0: manageButton1(null);	break;
		case 1: manageButton2(null);	break;
		case 2: manageButton3(null);	break;
		case 3: manageButton4(null);	break;
		}
	}
	
	// Local Calls from Elements
	public void manageButton1(ActionEvent e) {
//		System.out.println(AbstrahlYagi);
		//iv_Antenna.imageProperty();
//		iv_Antenna.setImage(AbstrahlYagi);
//		iv_Antenna.setCache(true);
		GridPane.setRowIndex(pg_SelectedAntenna, 0);
		this.view.setAnt(0);
    } 
	
	public void manageButton2(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 1);
		//iv_Antenna.setImage(Icon);
		this.view.setAnt(1);
    } 
	
	public void manageButton3(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 2);
		this.view.setAnt(2);
    } 
	
	public void manageButton4(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 3);
		this.view.setAnt(3);
    }
	
}
