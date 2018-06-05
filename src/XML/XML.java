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

	/**
	 * - setzt Filter vom FileChooser
	 * - erstellt lokales File
	 */
	public XML() {	
		extFilter = new FileChooser.ExtensionFilter("SimAnt files (*.simant)", "*.simant");
		fileChooser.getExtensionFilters().add(extFilter);
		file = new File(".");
	}
	
	/**
	 * - setzt Referenz der Hauptview in Attribut
	 * 
	 * @param view	-> Referenz auf HauptView
	 */
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	@FXML 
	private void importFileChooser() {
		
	    fileChooser.setTitle("Öffne Einstellungs Datei");
	    
	    file = fileChooser.showOpenDialog(null);
	    if (file != null) {
	    	view.loadXML(file);
	    	fileChooser.setInitialDirectory(file.getParentFile());
		}
	    
	}
	
	@FXML 
	private void exportFileChooser() throws IOException {
		
	    fileChooser.setTitle("Speichere Einstellungs Datei"); 

    	// nur wenn gültiger Pfad vorhanden ist..!
	    file = fileChooser.showSaveDialog(null);
	    if (file != null) {
	    	view.saveXML(file);
	    	fileChooser.setInitialDirectory(file.getParentFile());
		}
	    
	}
}
