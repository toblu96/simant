package _Model;

import javafx.scene.Group;
import javafx.scene.control.Label;
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
	private int antennaCountX, antennaCountY;
	private int angle, arrayDirection;
	
	
	public void createForm(Pane pane) {
		this.formPane = pane;
		this.formPane.widthProperty().addListener((obs, oldVal, newVal) -> {	if(Math.abs(oldVal.doubleValue() - newVal.doubleValue()) > 1) redraw();	});
		this.formPane.heightProperty().addListener((obs, oldVal, newVal) -> {   if(Math.abs(oldVal.doubleValue() - newVal.doubleValue()) > 1) redraw();	});
	}
	
	public void setForm(int form) {
		this.form = form;
		redraw();
	}
	
	public void setAntCount(int antCountX, int antCountY) {
		this.antennaCountX = antCountX;
		this.antennaCountY = antCountY;
		redraw();
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
		redraw();
	}
	
	public void setArrayDir(int angle) {
		this.arrayDirection = angle;
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
				
		// draw Arrow for Matrix direciton
		Image imgPfeil = new Image("/resources/right-arrow.png", middleX, middleY, false, false); 
		ImageView imgView = new ImageView();
		imgView.setX(middleX/2);
		imgView.setY(middleY/2);
		imgView.setRotate(-this.arrayDirection);
		imgView.setImage(imgPfeil);
		imgView.setOpacity(0.2);
		formGroup.getChildren().add(imgView);
		
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
		
		line.setLayoutX(40);
		line.setLayoutY(middleY);
		line.setEndX(formPane.getWidth() - 80);
		line.setEndY(0);
		line.setStroke(Color.BLUE);
		line.setFill(null);
		line.setStrokeWidth(5);
		formGroup.getChildren().add(line);
		
		for (int i = 0; i < this.antennaCountX; i++) {
			Image imgPfeil = new Image("/resources/Pfeil.png", 50, 50, false, false); 
			double offsetX = imgPfeil.getWidth() / 2;
			double offsetY = imgPfeil.getHeight() / 2;
			ImageView imgView = new ImageView();
			if (this.antennaCountX > 1) {
				imgView.setX((formPane.getWidth() - 80) / (this.antennaCountX-1) * i - offsetX + 40);
			} else {
				imgView.setX(- offsetX + 40);
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
		
		for (int i = 0; i < this.antennaCountX; i++) {
			Image imgPfeil = new Image("/resources/Pfeil.png", 50, 50, false, false);
			double angle = 2* Math.PI / this.antennaCountX * i; 
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
		
		if (antennaCountY > 6) antennaCountY = 6;
		// Antennen in Y-Richtung
		for (int j = 1; j <= antennaCountY; j++) {
			Line line = new Line();
			double lineCoordY = formPane.getHeight()/(antennaCountY+1)*j;
			
			line.setLayoutX(40);
			line.setLayoutY(lineCoordY);
			line.setEndX(formPane.getWidth() - 80);
			line.setEndY(0);
			line.setStroke(Color.BLUE);
			line.setFill(null);
			line.setStrokeWidth(5);
			formGroup.getChildren().add(line);
			
			// Antennen in X-Richtung
			for (int i = 0; i < this.antennaCountX; i++) {
				Image imgPfeil = new Image("/resources/Pfeil.png", 50, 50, false, false); 
				double offsetX = imgPfeil.getWidth() / 2;
				double offsetY = imgPfeil.getHeight() / 2;
				ImageView imgView = new ImageView();
				if (this.antennaCountX > 1) {
					imgView.setX((formPane.getWidth() - 80) / (this.antennaCountX-1) * i - offsetX + 40);
				} else {
					imgView.setX(- offsetX + 40);
				}
				imgView.setY(lineCoordY - offsetY);
				imgView.setRotate(-this.angle);
				imgView.setImage(imgPfeil);
				
				formGroup.getChildren().add(imgView);
			}
		}
		
	}
	
}
