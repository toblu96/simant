package Settings;

import java.net.URL;
import java.util.ResourceBundle;

import _MenuView.MenuView;
import _Model.Utility; 
import javafx.fxml.Initializable;

public class Settings implements Initializable {
	
	MenuView view;
	
	// Local Elements declaration
	private Utility util = new Utility();	
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setParentView(MenuView view) {
		this.view = view;
	}
	
	// Local Calls from Elements
	
	
	
	// Local Calls
	
	
	
}
