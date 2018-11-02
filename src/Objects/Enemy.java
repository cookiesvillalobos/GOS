package Objects;

import Objects.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;

public class Enemy{
	private double xPosition, yPosition;
	private boolean blowFire;
	private double screenWidth, screenHeight;
	
	
	public Enemy(double screenWidth, double screenHeight) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public void Fire() {
		
	}
	
	
	public void MoveBy(int xPosition, int yPosition, double cx, double cy, double xLayout, double yLayout) {
		if (xPosition == 0 && yPosition == 0) {
			return;
		}
		
		double x = cx + xLayout + xPosition;
		double y = cy + yLayout + yPosition;
		
		Moveto(x, y, cx, cy);
		
	}
	
	public void Moveto (double x, double y, double cx, double cy ) {
		//System.out.println("cx " + cx + " cy " + cy + " x " + x + " y " + y);
		if (x-cx >= 0 && x+cx <=screenWidth && y-cy >= 0 && y+cy <=screenHeight) {
			
			this.xPosition = x-cx;
			this.yPosition = y-cy;
			
		}
	}

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	
	
	
	
	
	

}