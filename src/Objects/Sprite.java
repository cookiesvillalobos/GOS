	package Objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import Server.Dragon;
import javafx.geometry.Rectangle2D;

public class Sprite extends Rectangle {
	
    private Image image;
    private double positionX;
    private double positionY;    
    private double width;
    private double height;
    private String type;
    private boolean dead = false;
    private double widthS;
    private double heightS;
    private Dragon dragon;
    
    
    
    public Sprite(String filename, double positionX, double positionY, String type, double widthS, double heightS, Color color) {
    	super (5,5, color);
    	setTranslateX(positionX);
    	setTranslateY(positionY);
    	this.positionX = positionX;
    	this.positionY = positionY;
    	setImage(filename);
    	this.widthS = widthS;
    	this.heightS = heightS;
    	this.type = type;
    	dragon = new Dragon();
    }
    
    public void setImage(Image image) {
    	this.image = image;
    	width = image.getWidth();
    	height = image.getHeight();
    }
    
    public void setImage (String filename) {
    	Image image = new Image(filename);
    	setImage(image);
    }
    
    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
        
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public void moveLeft(int amount) {
    	positionX = positionX-amount;
    	setTranslateX(getTranslateX()-amount);
    	
    	if (positionX < 0 &&!this.type.equals("enemybullet")) {
			positionX = positionX + amount;
			setTranslateX(getTranslateX()+amount);
		}
    }
    
    public void moveRight(int amount) {
    	positionX = positionX+amount;
    	setTranslateX(getTranslateX()+amount);
    	if (positionX > widthS-425 && !this.type.equals("playerbullet")) {
			positionX = positionX - amount;
			setTranslateX(getTranslateX()-amount);
		}
    }
    
    public void moveUp(int amount) {
    	positionY = positionY -amount;
    	setTranslateY(getTranslateY()-amount);
		if (positionY < 0) {
			positionY = positionY + amount;
			setTranslateY(getTranslateY()+amount);
		}
    }
    
    public void moveDown(int amount) {
    	positionY = positionY +amount;
    	setTranslateY(getTranslateY()+amount);

    	if (positionY > heightS-50) {
			positionY = positionY - amount;
			setTranslateY(getTranslateY()-amount);
		}
    }
    
    public String getType() {
    	return this.type;
    }
    
    public double getPositionX() {
    	return this.positionX;
    }  
    public double getPositionY() {
    	return this.positionY;
    }
    
    public boolean getDead () {
    	return this.dead;
    }
    
    public void setDead (boolean dead) {
    	this.dead = dead;
    }
    
    public Image getImage () {
    	return this.image;
    }
    
    public Dragon getDragon () {
    	return this.dragon;
    }
    
    
}
