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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
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
	Text tf_TitleSettings;
	
	@FXML
	JFXComboBox<String> cb_Form;
	
	@FXML
	JFXTextField tf_Anzahl, tf_Lambda, tf_Richtung, tf_Amplitude;
	
	@FXML
	JFXButton bt_tabAnt, bt_tab3D, bt_unity;
	
	@FXML
	ImageView img_form, img_ant;
	
	@FXML
	JFXSlider sl_percent;
	
	static Image imgDipolHoriz = new Image("/resources/Java_Dipol_Horiz.png", true);
	static Image imgDipolVert = new Image("/resources/Java_Dipol_Vert.png", true);
	static Image imgCircle = new Image("/resources/Java_Kreis.png", true);
	static Image imgRow = new Image("/resources/Java_Linie.png", true);
	static Image imgMatrix = new Image("/resources/Java_Matrix.png", true);
	static Image imgYagiHoriz = new Image("/resources/Java_Yagi_Horiz.png", true);
	static Image imgYagiVert = new Image("/resources/Java_Yagi_Vert.png", true);
	
	
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
        	
        	img_ant.setImage(imgDipolHoriz);
        	img_form.setImage(imgRow);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		// Settings Menu
		if (cc_MenuSettings != null) {
			cc_MenuSettings.setPrefWidth(300);
			tf_TitleSettings.setText("Group Settings");

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
		
		// hier weil initialize 2x ausgeführt wird.. wird noch geändert!!
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
	
	public void updatePicture(Image img) {
		img_ant.setImage(img);		
	}
	
	// Local Calls from Elements
	@FXML
	private void manageButton(ActionEvent e) { 
		
		if (e.getSource().equals(cb_Form)) {
			switch (cb_Form.getValue()) {
			case "Row": view.setForm(0); img_form.setImage(imgRow); fplot.setForm(0); break;
			case "Circle": view.setForm(1); img_form.setImage(imgCircle); fplot.setForm(1); break;
			case "Matrix": view.setForm(2); img_form.setImage(imgMatrix); fplot.setForm(2); break;
			}
		}
//		if (e.getSource().equals(tf_Anzahl)) {
//			view.setQuant(Integer.parseInt(tf_Anzahl.getText()));
//		}
//		if (e.getSource().equals(tf_Lambda)) {
//			view.setDLambda(Double.parseDouble(tf_Lambda.getText()));
//		}
		if (e.getSource().equals(tf_Richtung)) {
			int data = Integer.parseInt(tf_Richtung.getText());
			view.setDir(data);
			fplot.setAngle(data);
		}
		if (e.getSource().equals(tf_Amplitude)) {
			view.setAmp(Double.parseDouble(tf_Amplitude.getText()));
		}
		if (e.getSource().equals(bt_unity)) {
			unitsPopup(e);
		}
    }
	
	@FXML
	protected void FXSetAnzahl() {
		int data = util.getInt(tf_Anzahl, 0, 10);
		ampPlot.setAntQuant(data);
		FXSetPercentage();
		FXCalculatePercentage();
		view.setQuant(data);
		fplot.setAntCount(data);
		
		
	}
	
	@FXML
	protected void FXSetDLambda() {
		view.setDLambda(util.getDouble(tf_Lambda, 0.0, 10.0));	
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
	protected void FXSetPercentage() {
		ampPlot.setPercentage((int) sl_percent.getValue());
	}
	
	protected void FXCalculatePercentage() {
		view.setAmpArray(ampPlot.getAmp(), sl_percent.getValue());
	}

	
	public void updateInputs(SimantInputData data) {
		// Form
		switch (data.getForm()) {
		case 0: cb_Form.setValue("Row"); break;
		case 1: cb_Form.setValue("Circle"); break;
		case 2: cb_Form.setValue("Matrix"); break;
		}
		view.setForm(data.getForm());
		// quantity
		tf_Anzahl.setText(""+data.getQuant());
		view.setQuant(data.getQuant());
		// dLambda
		tf_Lambda.setText(""+data.getDLambda());
		view.setDLambda(data.getDLambda());
		// direction
		tf_Richtung.setText(""+data.getDir());
		view.setDir(data.getDir());
		// amplitude
		tf_Amplitude.setText(String.format("%.0E", data.getAmp()));
		view.setAmp(data.getAmp());
		
		fplot.setForm(data.getForm());
		fplot.setAntCount(data.getQuant());
		fplot.setAngle(data.getDir());
		
		sl_percent.setValue(data.getAmpPercent());
		ampPlot.setAntQuant(data.getQuant());
		
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
