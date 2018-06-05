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

	/**
	 * - lädt parameter in Attribute
	 * @param model	-> Referenz auf Model
	 * @param view	-> Referenz auf MenuView
	 */
	public Controller(Model model, MenuView view) {
		this.model = model;
		this.menuView = view;
	}
	
	/**
	 * - aktualisiert gesamten Datentyp "SimantInputData" im Model
	 * @param data -> Datentyp SimantInputData für Model
	 */
	public void setInputData(SimantInputData data) {
		model.updateInputData(data);
	}
	
	/**
	 * - löst XML-Speichervorgang für Eingabeparameter aus
	 * @param file	-> Speicherpfad
	 * @param data	-> SimantInputData (enthält alle Eingabeparameter)
	 */
	public void exportData(File file, SimantInputData data) {
		try {
			xml.saveXML(file, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * - löst XML-Einlesevorgang für Eingabeparameter aus
	 * - übergibt eingelesene Daten der MenuView zur Aktualisierung der Eingabefelder
	 * @param file	-> zu öffnender Dateipfad
	 */
	public void importData(File file) {
		try {
			menuView.updateInputs(xml.openXML(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
