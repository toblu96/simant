package XML;

import java.io.File;
import java.io.IOException;

import com.jfoenix.controls.*;

import _MenuView.MenuView;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class XML {
	
	MenuView view;
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter extFilter;
	File file;
	
	// Local Elements declaration
	@FXML JFXButton bt_export, bt_import;
	
	
	public XML() {
		
		extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
		file = new File(".");
	}
	
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	
	// Local Calls from Elements
	@FXML protected void importFileChooser() {
		
	    fileChooser.setTitle("Open Resource File");
	    
    	File existDirectory = file.getParentFile();
    	fileChooser.setInitialDirectory(existDirectory);
	    file = fileChooser.showOpenDialog(null);
	    if (file != null) {
	    	view.loadXML(file);
		}
	    
	}
	
	@FXML protected void exportFileChooser() throws IOException {
		
	    fileChooser.setTitle("Save Resource File"); 

    	File existDirectory = file.getParentFile();
    	fileChooser.setInitialDirectory(existDirectory);
    	// only if sucessful!
	    file = fileChooser.showSaveDialog(null);
	    if (file != null) {
	    	view.saveXML(file);
		}
	    
	}
	
	
	// Local Calls
	
	
}
