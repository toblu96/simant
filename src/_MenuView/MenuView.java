package _MenuView;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import Layout.Layout;
import XML.XML;
import _Controller.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import com.jfoenix.controls.*;

import Antenna.Antenna;
import Diagram.Diagram;
import Help.Help;
import _Model.Model;
import _Model.SimantData;
import _Model.SimantInputData;
import __MVCFramework.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

public class MenuView implements Initializable, Subscriber<SimantData> {
	// MVC
	Model model = new Model(this);
	Controller controller = new Controller(model, this);
	
	// Subscriber
	private Subscription subscription;
	
	// Data Structures
	public SimantInputData inpData;
	private SimantData sData = new SimantData();
	
	// Layouts & Screens
	FXMLLoader antennaLoader = new FXMLLoader(Main.class.getResource("/Antenna/Antenna.fxml"));
	FXMLLoader layoutLoader = new FXMLLoader(Main.class.getResource("/Layout/Layout.fxml"));
	FXMLLoader diagramLoader = new FXMLLoader(Main.class.getResource("/Diagram/Diagram.fxml"));
	FXMLLoader xmlLoader = new FXMLLoader(Main.class.getResource("/XML/XML.fxml"));
	FXMLLoader helpLoader = new FXMLLoader(Main.class.getResource("/Help/Help.fxml"));
	private Antenna antenna;
	private Layout layout;
	private Diagram diagram;
	private XML xml;
	private Help help;
	
	
	// Local Elements declaration
	@FXML 
	JFXButton btn_antenna, btn_layout, btn_diagram, btn_xml, btn_help;
	private JFXButton[] arrButton = new JFXButton[5];
	
	@FXML
	AnchorPane apn_Content;
	private AnchorPane[] arrAnchPane = new AnchorPane[5];
	
	@FXML
	ColumnConstraints menuPaneWidth;
	
	private AnchorPane createStageSection(FXMLLoader viewLoader) {
        try {
            // Load Layout
        	AnchorPane pane = (AnchorPane) viewLoader.load();
        	apn_Content.getChildren().add(pane);
        	
        	AnchorPane.setTopAnchor(pane, 0.0);
        	AnchorPane.setBottomAnchor(pane, 0.0);
        	AnchorPane.setLeftAnchor(pane, 0.0);
        	AnchorPane.setRightAnchor(pane, 0.0);
        	return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		inpData = new SimantInputData();
		inpData.setAmp(1.0);
		inpData.setAnt(0);
		inpData.setDir(0);
		inpData.setDirHauptk(0);
		inpData.setDLambda(1.0);
		inpData.setForm(0);
		inpData.setDist(0.0);
		List<List<Double>> temp = new ArrayList<>(); temp.add(new ArrayList<Double>()); temp.get(0).add(1.0);
		inpData.setAmpArray(temp);
		inpData.setAmpPercent(0.0);
		
		arrButton[0] = btn_antenna;		arrAnchPane[0] = createStageSection(this.antennaLoader);
		arrButton[1] = btn_layout;		arrAnchPane[1] = createStageSection(this.layoutLoader);
		arrButton[2] = btn_diagram;		arrAnchPane[2] = createStageSection(this.diagramLoader);
		arrButton[3] = btn_xml;			arrAnchPane[3] = createStageSection(this.xmlLoader);
		arrButton[4] = btn_help;		arrAnchPane[4] = createStageSection(this.helpLoader);
		
		arrAnchPane[1].toFront();
		btn_layout.getStyleClass().add("menuButtonActive");
		
		// get references from fxml		
		this.antenna = this.antennaLoader.getController();
		this.layout = this.layoutLoader.getController();
		this.diagram = this.diagramLoader.getController();
		this.xml = this.xmlLoader.getController();
		this.help = this.helpLoader.getController();
		
		this.antenna.setParentView(this);
		this.layout.setParentView(this);
		this.diagram.setParentView(this);
		this.xml.setParentView(this);
		this.help.setParentView(this);
		
		updateInputs(inpData);
		
	}
	
	
	// Local Calls from Elements
	public void manageButton(ActionEvent e) { 
		this.setBtnPanel((JFXButton)e.getTarget());		
    } 
	
	
	
	// Local Calls
	public void setAnt(Integer data) {
		this.inpData.setAnt(data);
		controller.setInputData(inpData);
	}
	
	public void setForm(Integer data) {
		this.inpData.setForm(data);
		controller.setInputData(inpData);
	}
	
	public void setDLambda(Double data) {
		this.inpData.setDLambda(data);
		controller.setInputData(inpData);
	}
	
	public void setDir(Integer data) {
		this.inpData.setDir(data);
		controller.setInputData(inpData);
	}
	
	public void setDirHauptk(Integer data) {
		this.inpData.setDirHauptk(data);
		controller.setInputData(inpData);
	}
	
	public void setAmp(Double data) {
		this.inpData.setAmp(data);
		controller.setInputData(inpData);
	}
	
	public void setAmpArray(List<List<Double>> data, Double sliderPercent) {
		this.inpData.setAmpArray(data);
		this.inpData.setAmpPercent(sliderPercent);
		controller.setInputData(inpData);
	}
	
	public void setReflektor(boolean data) {
		this.inpData.setReflektor(data);
		controller.setInputData(inpData);
	}
	
	public void setAntVertikal(boolean data) {
		this.inpData.setAntVertikal(data);
		controller.setInputData(inpData);
	}
	
	public void saveXML(File file) {
		controller.exportData(file, this.inpData);
	}
	
	public void loadXML(File file) {
		controller.importData(file);
	}
	
	public void updateInputs(SimantInputData data) {
		layout.updateInputs(data);
		antenna.updateInputs(data);
	}
	
	
	
	public void setBtnPanel(JFXButton btn) {
		
		// reset all backgrounds, bring panel to front
		for (int i = 0; i < arrButton.length; i++) {
			arrButton[i].getStyleClass().removeIf(s -> s.contains("menuButtonActive"));
			if (btn.getId() == arrButton[i].getId()) {
				arrAnchPane[i].toFront();
			}
		}
		
		// set activated button background
		btn.getStyleClass().add("menuButtonActive");		
	}
	

	@Override
	public void onComplete() {
		Platform.runLater( () -> {  layout.drawCharts(sData); 
									diagram.drawCharts(this.sData); 
									diagram.updatePicture(this.sData.getImgOrient(), this.sData.getImgForm());
									layout.updatePicture(this.sData.getImgOrient(), this.sData.getImgForm()); 
									antenna.updateView(this.sData);
									});
	}

	@Override
	public void onError(Throwable error) {
		System.out.println(error);
	}

	@Override
	public void onNext(SimantData item) {
		this.sData = item;
		
	    subscription.request(1);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
        subscription.request(1);
	}
	
	
}
