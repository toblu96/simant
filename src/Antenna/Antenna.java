package Antenna;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import _Controller.Publish;
import __MVCFramework.Main;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Antenna implements Initializable{
	
	// Local Elements declaration
	@FXML 
	Polygon pg_SelectedAntenna;
	
	@FXML 
	AnchorPane apn_AntennaInfo;
	
	@FXML
	//ImageView iv_Antenna; file:src/resources/
	
	Image AbstrahlYagi = new Image(getClass().getResourceAsStream("/resources/AbstrahlYagi.png"));
	//Image Icon = new Image("resources/Icon.png");
	
	private ObjectProperty<javafx.scene.image.Image> imageProperty = new SimpleObjectProperty<>();
	@FXML
	ImageView iv_Antenna = new ImageView(AbstrahlYagi);
	//Bindings.bindBidirectional(this.iv_Antenna.imageProperty(), GlobalModel.getInstance().getProject().getImageProperty());
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (pg_SelectedAntenna != null) {
			//iv_Antenna.setImage(AbstrahlYagi);
			GridPane.setRowIndex(pg_SelectedAntenna, 0);
			Publish.Antenne(1);
		}
	} 
	
	// Local Calls from Elements
	public void manageButton1(ActionEvent e) {
		System.out.println(AbstrahlYagi);
		//iv_Antenna.imageProperty();
		iv_Antenna.setImage(AbstrahlYagi);
		iv_Antenna.setCache(true);
		GridPane.setRowIndex(pg_SelectedAntenna, 0);
		Publish.Antenne(1);
    } 
	
	public void manageButton2(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 1);
		//iv_Antenna.setImage(Icon);
		Publish.Antenne(2);
    } 
	
	public void manageButton3(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 2);
		Publish.Antenne(3);
    } 
	
	public void manageButton4(ActionEvent e) { 
		GridPane.setRowIndex(pg_SelectedAntenna, 3);
		Publish.Antenne(4);
    }
	
}
