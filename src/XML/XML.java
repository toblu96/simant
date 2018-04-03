package XML;

import java.io.File;

import com.jfoenix.controls.*;

import _Model.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class XML {
	
	// Local Elements declaration
	private Utility util = new Utility();
		
	@FXML JFXButton bt_export, bt_import;
	
	public void initialize() {
		
	}
	
	
	// Local Calls from Elements
	@FXML protected void importFileChooser() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
	    fileChooser.setTitle("Open Resource File"); 
	    File file = fileChooser.showOpenDialog(null);
	    System.out.println(file);
	}
	
	@FXML protected void exportFileChooser() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
	    fileChooser.setTitle("Save Resource File"); 
	    File file = fileChooser.showSaveDialog(null);
	    System.out.println(file);
	}
	
	
	// Local Calls
	
	
}
