package _Controller;
import _Model.DataExport;
import _Model.Model;
import _Model.SimantInputData;

import java.io.File;
import java.io.IOException;

import _MenuView.MenuView;

public class Controller {

	Model model;
	MenuView menuView;
	
	DataExport xml = new DataExport();

	public Controller(Model model, MenuView view) {
		this.model = model;
		this.menuView = view;
	}
	
	public void setInputData(SimantInputData data) {
		model.updateInputData(data);
	}
	
	public void exportData(File file, SimantInputData data) {
		try {
			xml.saveXML(file, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importData(File file) {
		try {
			menuView.updateInputs(xml.openXML(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
