package Layout;


import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;

import _Model.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Layout implements Initializable{
	private Utility util = new Utility();
	
	
	
	
	// Local Elements declaration	
	@FXML
	ColumnConstraints cc_MenuSettings;
	
	@FXML 
	FontAwesomeIconView ToggleMenuIcon;
	
	@FXML
	Text tf_TitleSettings;
	
	@FXML
	JFXComboBox<String> cb_Form;
	
	@FXML
	JFXTextField tf_Anzahl, tf_Lambda, tf_Richtung, tf_Amplitude;
	
	@FXML
	JFXButton bt_tabAnt, bt_tab3D, bt_unity;
	
	ObservableList<String> formOptions = FXCollections.observableArrayList(
		        "Reihe",
		        "Kreis",
		        "Matrix"	);
	
	ObservableList<String> unityOptions = FXCollections.observableArrayList(
	        "uV",
	        "mV",
	        "V"	);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Settings Menu
		if (cc_MenuSettings != null) {
			cc_MenuSettings.setPrefWidth(300);
			tf_TitleSettings.setText("Antenna Array");

			bt_tabAnt.setStyle("-fx-background-color: white");
		}
		// init ComboBox
		if (cb_Form != null) {
			cb_Form.setItems(formOptions);
			cb_Form.getStyleClass().add("optionsComboBox");
			cb_Form.setValue("Reihe");
		}
	    
	} 
	
	
	@FXML
	private void toggleMenuSettings(ActionEvent e) {
		
		// Side Menu (Settings)
	    if (cc_MenuSettings.getMaxWidth() > 0) {
	    	cc_MenuSettings.setMaxWidth(0);
	    	ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_LEFT);
		} else {
			cc_MenuSettings.setMaxWidth(300);
			ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_RIGHT);
		}
	}
	
	
	
	// Local Calls from Elements
	public void manageButton(ActionEvent e) { 
		
		if (e.getSource().equals(cb_Form)) {
			System.out.println(cb_Form.getValue());
		}
		if (e.getSource().equals(bt_unity)) {
			unitsPopup(e);
		}
    }
	
	@FXML
	protected void FXSetAnzahl() {
		// only Numbers validation
		util.onlyNumber(tf_Anzahl, 1.0, 1.0);
	}
	
	@FXML
	protected void manageTab(ActionEvent e) {
		JFXButton btn = (JFXButton)e.getTarget();
		// reset all backgrounds, bring panel to front
		bt_tabAnt.setStyle("");
		bt_tab3D.setStyle("");
		// set activated button background
		btn.setStyle("-fx-background-color: white");	
	}

	
	
	
	
	// Local Calls
	private void unitsPopup(ActionEvent e) {
		JFXButton actionButton = (JFXButton)e.getTarget();
		JFXButton bt1 = new JFXButton("mV");
		JFXButton bt2 = new JFXButton("V");
		JFXButton bt3 = new JFXButton("kV");
		bt1.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 35px;-fx-font-weight: bold;");
		bt2.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 35px;-fx-font-weight: bold;");
		bt3.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 35px;-fx-font-weight: bold;");
		bt2.setMinWidth(60);
		bt1.setPadding(new Insets(10));
		bt2.setPadding(new Insets(10));
		bt3.setPadding(new Insets(10));
		
		HBox hBox = new HBox(bt1, bt2, bt3);
		HBox.setMargin(bt1, new Insets(10,10,10,10));
		HBox.setMargin(bt2, new Insets(10,10,10,10));
		HBox.setMargin(bt3, new Insets(10,10,10,10));
	    PopOver popOver = new PopOver(hBox);
	    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
	    popOver.setTitle("Unity");
	    bt1.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("V");    });
	    bt2.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("V");    });
	    bt3.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("V");    });
	    popOver.show(actionButton);
	}
	
	
}
