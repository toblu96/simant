package Layout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import com.jfoenix.controls.*;

import _MenuView.MenuView;
import _Model.AmplitudePlot;
import _Model.FormPlot;
import _Model.SimantInputData;
import _Model.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class Layout implements Initializable{
	
	private MenuView view;
	
	// Layouts & Screens
	FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/Layout/Settings1.fxml"));
		
	private Utility util = new Utility();
	
	FormPlot fplot = new FormPlot();
	AmplitudePlot ampPlot = new AmplitudePlot();
	
	
	
	// Local Elements declaration	
	@FXML
	GridPane gp_root;
	
	@FXML
	Pane pn_form, pn_amplitude;
	
	@FXML
	ColumnConstraints cc_MenuSettings;
	
	@FXML 
	FontAwesomeIconView ToggleMenuIcon;
	
	@FXML
	Text txt_amp, txt_dlambda, txt_richtAnt;
	
	@FXML
	HBox hb_amp, hb_richtAnt;
	
	@FXML
	VBox vb_ScrollPane;
	
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
		        "Row",
		        "Circle",
		        "Matrix"	);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		try {
            // Load Layout
			settingsLoader.setController(this);
        	AnchorPane pane = (AnchorPane) settingsLoader.load();
        	gp_root.add(pane, 2, 1, 1, 1);
        	
        	AnchorPane.setTopAnchor(pane, 0.0);
        	AnchorPane.setBottomAnchor(pane, 0.0);
        	AnchorPane.setLeftAnchor(pane, 0.0);
        	AnchorPane.setRightAnchor(pane, 0.0);
        	
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		// Settings Menu
		if (cc_MenuSettings != null) {
			cc_MenuSettings.setMinWidth(317);

			bt_tabAnt.setStyle("-fx-background-color: white");
		}
		// init ComboBox
		if (cb_Form != null) {
			cb_Form.setItems(formOptions);
			cb_Form.getStyleClass().add("optionsComboBox");
			cb_Form.setValue("Reihe");
		}    
	} 
	
	public void setParentView(MenuView view) {
		this.view = view;
		
		// hier weil initialize 2x ausgef�hrt wird.. wird noch ge�ndert!!
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
	
	
	@FXML
	private void toggleMenuSettings(ActionEvent e) {
		
		// Side Menu (Settings)
	    if (cc_MenuSettings.getMaxWidth() > 0) {
	    	cc_MenuSettings.setMaxWidth(0);
	    	ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_LEFT);
		} else {
			cc_MenuSettings.setMaxWidth(300);
			ToggleMenuIcon.setIcon(FontAwesomeIcon.CARET_RIGHT);
		}
	}
	
	public void updatePicture(Image imgOrient, Image imgForm) {
		img_ant.setImage(imgOrient);
		img_form.setImage(imgForm);
	}
	
	// Local Calls from Elements
	@FXML
	private void manageButton(ActionEvent e) { 
		
		if (e.getSource().equals(cb_Form)) {
			switch (cb_Form.getValue()) {
			case "Row": view.setForm(0); fplot.setForm(0); tf_AnzahlCol.setDisable(true); break;
			case "Circle": view.setForm(1); fplot.setForm(1); tf_AnzahlCol.setDisable(true); break;
			case "Matrix": view.setForm(2); fplot.setForm(2); tf_AnzahlCol.setDisable(false); break;
			}
		}
		if (e.getSource().equals(bt_unity)) {
			unitsPopup(e);
		}
    }
	
	@FXML
	protected void FXSetAnzahl() {
		Integer x = util.getInt(tf_AnzahlRow, 1, 10);
		Integer y = util.getInt(tf_AnzahlCol, 1, 10);
		if (x != null && y != null) {
			view.setAmpArray(ampPlot.setAntQuant(x,y), sl_percent.getValue());
			fplot.setAntCount(x);
		}
				
	}
	
	@FXML
	protected void FXSetDirection() {
		Integer data = util.getInt(tf_Richtung, -360, 360);
		if (data != null) {
			view.setDir(data);
			fplot.setAngle(data);
		}
	}
	
	@FXML
	protected void FXSetDirHauptk() {
		Integer data = util.getInt(tf_RichtHauptkaeule, -360, 360);
		if (data != null) {
			view.setDirHauptk(data);
		}
	}
	
	@FXML
	protected void FXSetAmplitude() {
		view.setAmp(util.getDouble(tf_Amplitude, 0.0, 10.0));	
	}
	
	@FXML
	protected void FXSetDLambda() {
		view.setDLambda(util.getDouble(tf_Lambda, 0.0, 10.0));	
	}
	
	@FXML
	protected void FXSetDist() {
		view.setDist(util.getDouble(tf_Reflector, 0.0, 10.0));	
	}
	
	@FXML
	protected void manageTab(ActionEvent e) {
		JFXButton btn = (JFXButton)e.getTarget();
		// reset all backgrounds, bring panel to front
		bt_tabAnt.setStyle("");
		bt_tab3D.setStyle("");
		// set activated button background
		btn.setStyle("-fx-background-color: white");	
	}
	
	@FXML
	protected void FXSetReflektor() {
		boolean visible = cb_reflektor.isSelected();
		view.setReflektor(cb_reflektor.isSelected());
		tf_Reflector.setVisible(visible);
	}
	
	@FXML
	protected void FXSetAntVertikal() {
		view.setAntVertikal(cb_AntVert.isSelected());
	}
	
	@FXML
	protected void FXSetAdvanced() {
		boolean visible = cb_advanced.isSelected();
		txt_richtAnt.setVisible(visible);
		hb_richtAnt.setVisible(visible);
		txt_dlambda.setVisible(visible);
		tf_Lambda.setVisible(visible);
		txt_amp.setVisible(visible);
		hb_amp.setVisible(visible);
		cb_reflektor.setVisible(visible);
		cb_AntVert.setVisible(visible);
	}
	
	// nur Plot aktualisieren
	@FXML
	protected void FXSetPercentage() {
		ampPlot.setPercentage((int) sl_percent.getValue());
	}
	
	// Berechnungen aktualiseren
	protected void FXCalculatePercentage() {
		view.setAmpArray(ampPlot.setPercentage(sl_percent.getValue()), sl_percent.getValue());
	}

	
	public void updateInputs(SimantInputData data) {
		// Form
		switch (data.getForm()) {
		case 0: cb_Form.setValue("Row"); tf_AnzahlCol.setDisable(true); break;
		case 1: cb_Form.setValue("Circle"); tf_AnzahlCol.setDisable(true); break;
		case 2: cb_Form.setValue("Matrix"); tf_AnzahlCol.setDisable(false); break;
		}
		view.setForm(data.getForm());
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
		fplot.setAntCount(data.getAmpArray().size());
		fplot.setAngle(data.getDir());
		
		sl_percent.setValue(data.getAmpPercent());
		ampPlot.setAntQuant(data.getAmpArray().get(0).size(), data.getAmpArray().size());
		
		cb_reflektor.setSelected(data.getReflektor());
		cb_AntVert.setSelected(data.getAntVertikal());
		
		// hide advanced mode if programm has started..
		FXSetAdvanced();
		FXSetReflektor();
	}
	
	
	// Local Calls
	private void unitsPopup(ActionEvent e) {
		JFXButton actionButton = (JFXButton)e.getTarget();
		JFXButton bt1 = new JFXButton("mV");
		JFXButton bt2 = new JFXButton("V");
		JFXButton bt3 = new JFXButton("kV");
		bt1.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 25px;-fx-font-weight: bold;");
		bt2.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 25px;-fx-font-weight: bold;");
		bt3.setStyle("-fx-background-color: #E4E4E4; -fx-font-size: 25px;-fx-font-weight: bold;");
		bt2.setMinWidth(60);
		bt1.setPadding(new Insets(10));
		bt2.setPadding(new Insets(10));
		bt3.setPadding(new Insets(10));
		
		HBox hBox = new HBox(bt1, bt2, bt3);
		HBox.setMargin(bt1, new Insets(10,10,10,10));
		HBox.setMargin(bt2, new Insets(10,10,10,10));
		HBox.setMargin(bt3, new Insets(10,10,10,10));
	    PopOver popOver = new PopOver(hBox);
	    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
	    popOver.setTitle("Unity");
	    bt1.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("mV");    });
	    bt2.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("V");    });
	    bt3.setOnAction(event -> { 	popOver.hide();   	actionButton.setText("kV");    });
	    popOver.show(actionButton);
	}
	
	
}
