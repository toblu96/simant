package XML;

import com.jfoenix.controls.*;

import _Model.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

public class XML {
	
	// Local Elements declaration
	@FXML 
	JFXButton btn_antenna, btn_layout, btn_diagram, btn_xml, btn_help, btn_settings;
	
	@FXML
	ColumnConstraints menuPaneWidth;
	
	private Utility util = new Utility();
		
	
	public void initialize() {
		// set width of menu panel
		menuPaneWidth.setPrefWidth(util.getScreenWidthPercentage(15.5));
	}
	
	
	// Local Calls from Elements
	public void manageButton(ActionEvent e) { 

        setBtnBackground((JFXButton)e.getTarget());
    } 
	
	
	
	// Local Calls
	public void setBtnBackground(JFXButton btn) {
		
		// reset all backgrounds
		btn_antenna.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		btn_layout.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		btn_diagram.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		btn_xml.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		btn_help.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		btn_settings.getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
		
		
		// set activated button background
		btn.getStyleClass().add("menuButtonActive");
		
	}
	
	
}
