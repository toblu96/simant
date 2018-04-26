package Help;

import com.jfoenix.controls.*;

import _MenuView.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class Help {
	
	MenuView view;
	
	// Local Elements declaration
	@FXML 
	JFXButton btn_1;
	
	@FXML
	GridPane testPane;
		
	
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	
	
	// Local Calls from Elements
	public void manageButton(ActionEvent e) { 
		
    } 
	
	
	
	// Local Calls
	
	
	
}
