package XML;

import java.io.File;
import java.io.IOException;

import com.jfoenix.controls.*;

import _MenuView.MenuView;
import _Model.Utility;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class XML {
	
	MenuView view;
	
	// Local Elements declaration
	private Utility util = new Utility();
		
	@FXML JFXButton bt_export, bt_import;
	
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	
	// Local Calls from Elements
	@FXML protected void importFileChooser() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
	    fileChooser.setTitle("Open Resource File"); 
	    File file = fileChooser.showOpenDialog(null);

	    view.loadXML(file);
	}
	
	@FXML protected void exportFileChooser() throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
	    fileChooser.setTitle("Save Resource File"); 
	    File file = fileChooser.showSaveDialog(null);
	    
	    view.saveXML(file);
	}
	
	
	// Local Calls
	
	
}
