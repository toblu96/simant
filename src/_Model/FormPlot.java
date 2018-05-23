package _Model;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class FormPlot {

	private Group formGroup = new Group();
	private Pane formPane;
	
	// reference and scale points
	private double radius;
	private double middleX;
	private double middleY;
	
	private int form;
	private int antennaCount;
	private int angle;
	
	
	public void createForm(Pane pane) {
		this.formPane = pane;
		this.formPane.widthProperty().addListener((obs, oldVal, newVal) -> {	redraw();	});
		this.formPane.heightProperty().addListener((obs, oldVal, newVal) -> {   redraw();	});
	}
	
	public void setForm(int form) {
		this.form = form;
		redraw();
	}
	
	public void setAntCount(int antCount) {
		this.antennaCount = antCount;
		redraw();
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
		redraw();
	}
	
	private void redraw() {
		
		// clear all existing elements
		formGroup.getChildren().clear();
		formPane.getChildren().clear();
		
		// draw nothing if pane minimized
		if (formPane.getHeight() < 1) {
			return;
		}
		
		// get Positions
		this.radius = Math.min(this.formPane.getHeight()/2.5, this.formPane.getWidth()/2.5);
		this.middleX = this.formPane.getWidth()/2;
		this.middleY = this.formPane.getHeight()/2;
		
		switch (form) {
		case 0:	drawLine();		break;
		case 1:	drawCircle();	break;
		case 2:	drawMatrix();	break;
		}
		
		// add group to pane
    	this.formPane.getChildren().add(formGroup);
		
				
	}
	
	private void drawLine() {
		Line line = new Line();
		
		line.setLayoutX(60);
		line.setLayoutY(middleY);
		line.setEndX(formPane.getWidth() - 80);
		line.setEndY(0);
		line.setStroke(Color.BLUE);
		line.setFill(null);
		line.setStrokeWidth(5);
		formGroup.getChildren().add(line);
		
		for (int i = 0; i < this.antennaCount; i++) {
			Image imgPfeil = new Image("/resources/Pfeil.png", 50, 50, false, false); 
			double offsetX = imgPfeil.getWidth() / 2;
			double offsetY = imgPfeil.getHeight() / 2;
			ImageView imgView = new ImageView();
			if (this.antennaCount > 1) {
				imgView.setX((formPane.getWidth() - 80) / (this.antennaCount-1) * i - offsetX + 60);
			} else {
				imgView.setX(- offsetX + 60);
			}
			imgView.setY(middleY - offsetY);
			imgView.setRotate(-this.angle);
			imgView.setImage(imgPfeil);
			
			formGroup.getChildren().add(imgView);
		}
		
	}
	
	private void drawCircle() {
		Circle circ = new Circle(middleX,middleY,radius);
		circ.setStroke(Color.BLUE);
		circ.setFill(null);
		circ.setStrokeWidth(5);
		formGroup.getChildren().add(circ);
		
		for (int i = 0; i < this.antennaCount; i++) {
			Image imgPfeil = new Image("/resources/Pfeil.png", 50, 50, false, false);
			double angle = 2* Math.PI / this.antennaCount * i; 
			double offsetX = imgPfeil.getWidth() / 2;
			double offsetY = imgPfeil.getHeight() / 2;
			ImageView imgView = new ImageView();
			imgView.setX(middleX + (radius)*Math.cos(angle) - offsetX);
			imgView.setY(middleY -(radius)*Math.sin(angle) - offsetY);
			imgView.setRotate(-this.angle);
			imgView.setImage(imgPfeil);
			
			formGroup.getChildren().add(imgView);
		}
		
	}
	
	private void drawMatrix() {
		
	}
	
}
