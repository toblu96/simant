package _MenuView;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import _Model.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

public class MenuView implements Initializable, Observer {
	
	private Utility util = new Utility();
	
	// Local Elements declaration
	@FXML 
	JFXButton btn_antenna, btn_layout, btn_diagram, btn_xml, btn_help, btn_settings;
	private JFXButton[] arrButton = new JFXButton[6];
	
	@FXML
	AnchorPane apn_Antenna, apn_Layout, apn_Diagram, apn_xml, apn_Help, apn_Settings;
	private AnchorPane[] arrAnchPane = new AnchorPane[6];
	
	@FXML
	ColumnConstraints menuPaneWidth;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Achtung!: wird mehrmals initialisert da die einzelnen importierten Panels zuerst geladen werden müssen!
		// _MenuView wird als letztes initialisiert!
		if (menuPaneWidth != null) {
			menuPaneWidth.setPrefWidth(util.getScreenWidthPercentage(15.5));
			apn_Antenna.toFront();
			
			arrButton[0] = btn_antenna;		arrAnchPane[0] = apn_Antenna;
			arrButton[1] = btn_layout;		arrAnchPane[1] = apn_Layout;
			arrButton[2] = btn_diagram;		arrAnchPane[2] = apn_Diagram;
			arrButton[3] = btn_xml;			arrAnchPane[3] = apn_xml;
			arrButton[4] = btn_help;		arrAnchPane[4] = apn_Help;
			arrButton[5] = btn_settings;	arrAnchPane[5] = apn_Settings;
			
		}
		
	}
	
	
	// Local Calls from Elements
	public void manageButton(ActionEvent e) { 

		setBtnPanel((JFXButton)e.getTarget());
    } 
	
	
	
	// Local Calls
	public void setBtnPanel(JFXButton btn) {
		
		// reset all backgrounds, bring panel to front
		for (int i = 0; i < arrButton.length; i++) {
			arrButton[i].getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
			if (btn.getId() == arrButton[i].getId()) {
				arrAnchPane[i].toFront();
			}
		}
		
		// set activated button background
		btn.getStyleClass().add("menuButtonActive");		
	}
	
	public void update(Observable obs, Object obj) {
		
	}
	
}
