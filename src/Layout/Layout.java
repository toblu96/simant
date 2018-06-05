package Layout;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import _MenuView.MenuView;
import _Model.AmplitudePlot;
import _Model.FormPlot;
import _Model.SimantData;
import _Model.SimantInputData;
import _Model.Utility;
import _Model.tblCharts;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Layout implements Initializable{
	
	private MenuView view;
		
	private Utility util = new Utility();
	
	FormPlot fplot = new FormPlot();
	AmplitudePlot ampPlot = new AmplitudePlot();
	tblCharts chart = new tblCharts();
	
	
	
	// Local Elements declaration	
	@FXML
	GridPane gp_root;
	
	@FXML
	Pane pn_form, pn_amplitude, pn_vorschau;
	
	@FXML
	ColumnConstraints cc_MenuSettings;
	
	@FXML 
	FontAwesomeIconView ToggleMenuIcon;
	
	@FXML
	Text txt_amp, txt_dlambda, txt_richtAnt, txt_EinhRef;
	
	@FXML
	HBox hb_richtAnt, hb_Reflector;
	
	@FXML
	VBox vb_ScrollPane, vb_Array;
	
	@FXML
	JFXComboBox<String> cb_Form;
	
	@FXML
	JFXTextField tf_AnzahlRow, tf_AnzahlCol, tf_Lambda, tf_Richtung, tf_RichtHauptkaeule, tf_Amplitude, tf_Reflector;
	
	@FXML
	JFXButton bt_tabAnt, bt_tab3D, bt_unity;
	
	@FXML
	ImageView img_form, img_ant;
	
	@FXML
	JFXSlider sl_percent;
	
	@FXML
	JFXCheckBox cb_advanced, cb_reflektor, cb_AntVert;
	
	@FXML
	ScrollPane sp_Einst;

	ObservableList<String> formOptions = FXCollections.observableArrayList(
		        "Reihe",
		        "Kreis",
		        "Matrix"	);
	
	/**
	 * - erzeugt Plots nach Initilisierung
	 * - erstellt Combo-Box
	 * - setzt cos^2 Plot
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
		cc_MenuSettings.setMaxWidth(317);
		bt_tabAnt.setStyle("-fx-background-color: #F0F0F0");
		
		// init ComboBox
		cb_Form.setItems(formOptions);
		cb_Form.getStyleClass().add("optionsComboBox");
		cb_Form.setValue("Reihe");   
		
		chart.createLineChart(pn_vorschau);
		fplot.createForm(pn_form);
		ampPlot.initPane(pn_amplitude);
		sl_percent.valueProperty().addListener((obs, oldVal, newVal) -> {		FXSetPercentage();	});
		sl_percent.setOnMouseReleased(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	FXCalculatePercentage();
		    }
		});
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
	private void toggleMenuSettings(ActionEvent e) {
		
		// Side Menu (Settings)
	    if (cc_MenuSettings.getMaxWidth() > 0) {
	    	cc_MenuSettings.setMaxWidth(0);
	    	ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_LEFT);
		} else {
			cc_MenuSettings.setMaxWidth(317);
			ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_RIGHT);
		}
	}
	
	/**
	 * - aktualisiert Bilder der Antennenausrichtungen
	 * 
	 * @param imgOrient	-> Bild der Antennenausrichtung
	 * @param imgForm	-> Bild der Array-Form
	 */
	public void updatePicture(Image imgOrient, Image imgForm) {
		img_ant.setImage(imgOrient);
		img_form.setImage(imgForm);
	}
	
	@FXML
	private void manageButton(ActionEvent e) { 
		
		if (e.getSource().equals(cb_Form)) {
			switch (cb_Form.getValue()) {
			case "Reihe": view.setForm(0); fplot.setForm(0); tf_AnzahlCol.setDisable(true); break;
			case "Kreis": view.setForm(1); fplot.setForm(1); tf_AnzahlCol.setDisable(true); break;
			case "Matrix": view.setForm(2); fplot.setForm(2); tf_AnzahlCol.setDisable(false); break;
			}
		}
    }
	
	@FXML
	private void FXSetAnzahl() {
		Integer x = util.getInt(tf_AnzahlRow, 1, 8);
		Integer y = util.getInt(tf_AnzahlCol, 1, 8);
		if (x != null && y != null) {
			view.setAmpArray(ampPlot.setAntQuant(x,y), sl_percent.getValue());
			fplot.setAntCount(x, y);
		}
				
	}
	
	@FXML
	private void FXSetDirection() {
		Integer data = util.getInt(tf_Richtung, -360, 360);
		if (data != null) {
			view.setDir(data);
			fplot.setAngle(data);
		}
	}
	
	@FXML
	private void FXSetDirHauptk() {
		Integer data = util.getInt(tf_RichtHauptkaeule, -360, 360);
		if (data != null) {
			view.setDirHauptk(data);
			fplot.setArrayDir(data);
		}
	}
	
	@FXML
	private void FXSetAmplitude() {
		view.setAmp(util.getDouble(tf_Amplitude, 0.0, 100.0));	
	}
	
	@FXML
	private void FXSetDLambda() {
		view.setDLambda(util.getDouble(tf_Lambda, 0.0, 100.0));	
	}
	
	@FXML
	private void FXSetDist() {
		view.setDist(util.getDouble(tf_Reflector, 0.0, 10.0));	
	}
	
	@FXML
	private void manageTab(ActionEvent e) {
		JFXButton btn = (JFXButton)e.getTarget();
		// reset all backgrounds, bring panel to front
		bt_tabAnt.setStyle("");
		bt_tab3D.setStyle("");
		// set activated button background
		btn.setStyle("-fx-background-color: #F0F0F0");
		
		if (btn.equals(bt_tabAnt)) {
			vb_Array.toFront();
		} else {
			pn_vorschau.toFront();
		}
	}
	
	@FXML
	private void FXSetReflektor() {
		boolean visible = cb_reflektor.isSelected();
		view.setReflektor(cb_reflektor.isSelected());
		tf_Reflector.setVisible(visible);
		txt_EinhRef.setVisible(visible);
	}
	
	@FXML
	private void FXSetAntVertikal() {
		view.setAntVertikal(cb_AntVert.isSelected());
	}
	
	@FXML
	private void FXSetAdvanced() {
		boolean visible = cb_advanced.isSelected();
		txt_richtAnt.setVisible(visible);
		hb_richtAnt.setVisible(visible);
		txt_dlambda.setVisible(visible);
		tf_Lambda.setVisible(visible);
		txt_amp.setVisible(visible);
		cb_reflektor.setVisible(visible);
		tf_Amplitude.setVisible(visible);
		hb_Reflector.setVisible(visible);
	}
	
	// nur Plot aktualisieren
	@FXML
	private void FXSetPercentage() {
		ampPlot.setPercentage((int) sl_percent.getValue());
	}
	
	// Berechnungen aktualiseren
	private void FXCalculatePercentage() {
		view.setAmpArray(ampPlot.setPercentage(sl_percent.getValue()), sl_percent.getValue());
	}

	/**
	 * - aktualisiert alle Eingabefelder
	 * 
	 * @param data	-> Datentyp Eingabefelder
	 */
	public void updateInputs(SimantInputData data) {
		// Form
		switch (data.getForm()) {
		case 0: cb_Form.setValue("Reihe"); tf_AnzahlCol.setDisable(true); break;
		case 1: cb_Form.setValue("Kreis"); tf_AnzahlCol.setDisable(true); break;
		case 2: cb_Form.setValue("Matrix"); tf_AnzahlCol.setDisable(false); break;
		}
		view.setForm(data.getForm());
		
		sl_percent.setValue(data.getAmpPercent());
		// quantity
		tf_AnzahlRow.setText(""+data.getAmpArray().get(0).size());
		tf_AnzahlCol.setText(""+data.getAmpArray().size());
		view.setAmpArray(ampPlot.setAntQuant(data.getAmpArray().get(0).size(), data.getAmpArray().size()), sl_percent.getValue());
		// dLambda
		tf_Lambda.setText(""+data.getDLambda());
		view.setDLambda(data.getDLambda());
		// direction
		tf_Richtung.setText(""+data.getDir());
		view.setDir(data.getDir());
		tf_RichtHauptkaeule.setText(""+data.getDirHauptk());
		view.setDirHauptk(data.getDirHauptk());
		// amplitude
		tf_Amplitude.setText(""+data.getAmp());
		view.setAmp(data.getAmp());
		
		// reflector
		tf_Reflector.setText(""+data.getDist());
		view.setDist(data.getDist());
		
		fplot.setForm(data.getForm());
		fplot.setAntCount(data.getAmpArray().get(0).size(), data.getAmpArray().size());
		fplot.setAngle(data.getDir());
		fplot.setArrayDir(data.getDirHauptk());
		
		ampPlot.setAntQuant(data.getAmpArray().get(0).size(), data.getAmpArray().size());
		
		cb_reflektor.setSelected(data.getReflektor());
		cb_AntVert.setSelected(data.getAntVertikal());
		
		// hide advanced mode if programm has started..
		FXSetAdvanced();
		FXSetReflektor();
	}
	
	
	/**
	 * - aktualisiert Daten für Plots
	 * 
	 * @param sData	-> gerechnete Daten aus Model
	 */
	public void drawCharts(SimantData sData) {
		chart.setDataSet(sData.getWinkel(), sData.getAmp(), sData.getAmpLogReal());
	}
	
}
