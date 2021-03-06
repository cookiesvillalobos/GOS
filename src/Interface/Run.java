package Interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import DataStructure.LinkList;
import Networking.ClientConnection;
import Networking.MockClientConnection;
import Objects.Sprite;
import Server.Dragon;
import Server.List;
import Utils.Rearranger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
//Logback imports

public class Run extends Application{
	
	//Logback variables
	//private final org.slf4j.Logger log = LoggerFactory.getLogger(Run.class);
	
	private Pane root = new Pane();
	
	private double width = 1500;
	private double height = 600;
	
	private double nominalMarginX = 10;
	private double nominalMarginY = 40;
	
	private double t = 0;
	
	String ss;
	
	String name;
	String fathername;
	String position;
	
	String fireballP = "Objects/fireballP (1).png";
	String fireballE = "Objects/fireball.png";
	String player = "Objects/player (1).png";
	String enemy = "Objects/enemy (1).png";
	Image back = new Image( "Objects/backgroundImage.png", width, height, false, false);
	
	Canvas canvas = new Canvas(1500,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	int age;
	int velocity;
	int resistance;
	
	int indicatorP = 0;
	int indicatorE = 0;
	int enemys = 0;
	
	int Points = 0;
	int Lifes = 3;
	
	LinkList<Sprite> enemyList = new LinkList<>();
	LinkList<Sprite> bulletPList = new LinkList<>();
	LinkList<Sprite> bulletEList = new LinkList<>();
	LinkList<Sprite> spriteList = new LinkList<>();
	LinkList<Dragon> dragonList = new LinkList<>();
	
	
		
	private Sprite Player = new Sprite(player, 300.0, 100.0, "player", width, height, Color.BLUE);
	
    PanamaHitek_Arduino ion=new PanamaHitek_Arduino();
    SerialPortEventListener lis= new SerialPortEventListener() {
       @Override
       public void serialEvent(SerialPortEvent spe) {
            
         
           try {
                   if(ion.isMessageAvailable()){
                      ss=ion.printMessage();
                      System.out.println(ss);
                   }
               } catch (SerialPortException ex) {
                   Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ArduinoException ex) {
                   Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
               } }
   };
	
	
	public Run() {
		 try {
	            ion.arduinoRX("COM6",9600,lis);
	        } catch (ArduinoException ex) {
	            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SerialPortException ex) {
	            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
	        }
	           }
	
	
	private Parent createContent() {
		root.setPrefSize(width, height);
		
		root.getChildren().add(canvas);
		root.getChildren().add(Player);
		nextlevel(2);
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gc.drawImage(back, 0, 0);
				
			/*
				if (ss.equals("LEFTLE")) {
					Player.moveLeft(5);
				}
				if (ss.equals("RIGHTR")) {
					Player.moveLeft(5);
				}
				if (ss.equals("DOWNDO")) {
					Player.moveLeft(5);
				}
				if (ss.equals("UPUP")) {
					Player.moveLeft(5);
				}
				*/
				if (!Player.getDead()) {
					Player.render(gc);
				}
				gc.fillText("Points " + Points, 100, 36);
				gc.strokeText("Points " + Points, 100, 36);
				

				gc.fillText("Lifes " + Lifes, 300, 36);
				gc.strokeText("Lifes " + Lifes, 300, 36);
				
				for (int i= 0; i<enemys; i++) {
					if (!enemyList.see(i).getDead() && enemyList.see(i).getPositionX() != 0) {
						enemyList.see(i).render(gc);
						if (t>1000) {
							t = 0;
						}
						if (enemyList.see(i).getDragon().getVelocidadRecarga() == t) {
							//shoot(enemyList.see(i));
							
						}
						//enemyList.see(i).moveLeft(1);
						t++;
					}
					else {
						enemyList.see(i).setPosition(500, 800);
						root.getChildren().remove(enemyList.see(i));
						
						if (Lifes != 0 && enemyList.see(i).getPositionX() == 0) {
							Lifes--;
						}
						
					}
				}
				for (int a= 0; a<indicatorE; a++) {
					if(bulletPList.see(a).getType().equals("enemybullet")) {
						bulletPList.see(a).moveLeft(5);
						bulletPList.see(a).render(gc);
						
					}
					else if (!bulletPList.see(a).getDead() ) {
						bulletPList.see(a).render(gc);
						bulletPList.see(a).moveRight(5);
					}
					else {
						bulletPList.see(a).setPosition(500, 800);
						bulletPList.see(a).setDead(true);
						root.getChildren().remove(bulletPList.see(a));
					}
					
					
				}
				update2();
				
				if (Points == enemys) {
					System.out.println("YA");
					try {
						List<Dragon> list = MockClientConnection.getServerUpdate(5);
						for (int i = 0; i<5; i++) {
							Dragon dragon =list.getData(i);
							dragonList.addPrev(dragon);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Points = 0;
				}
			}
			
		};
		
		
		timer.start();
		return root;
	}
	
	private void enemyToSpriteList() {
		for(int i = 0; i < enemyList.length(); i++) {
			Sprite enemyTemp = new Sprite(enemy, 0, 0, "enemy", width, height, Color.RED);
			
			enemyList.addPrev(enemyTemp);
			enemys++;			
		}
	}
	
	private void nextlevel(int x) {
		spriteList.addPrev(Player);
		treeOrdering(x);
	}
	
	private void treeOrdering(int x) {
		double divisor = 1.00;
		double prevDivisor = divisor;
		for (int i = 0; i < x; i++) {
			prevDivisor = divisor;
			divisor = divisor/2;
			for(int j = 0; j < Math.pow(2, (double) i); j++){
				int y_pos = (int) (height*divisor + j*(height*prevDivisor));
				Sprite Enemy = new Sprite (enemy, 800 + 75*i, y_pos - 24, "enemy", width, height, Color.RED);
				enemyList.addPrev(Enemy);
				spriteList.addPrev(Enemy);
				root.getChildren().add(Enemy);
				//Logger.newDragon(Enemy.toString());
				enemys++;
			}
		}
	}
	
private void sortOrdering() {
		
		for(int i = 0; i < 6; i++) {
			double[] distPix = percentToPix(95, i * 20 - 3, true, true);
		
			Sprite Enemy = new Sprite (enemy, distPix[0], distPix[1], "enemy", width, height, Color.RED);
			enemyList.addPrev(Enemy);
			spriteList.addPrev(Enemy);
			root.getChildren().add(Enemy);
			//Logger.newDragon(Enemy.toString());
			enemys++;
		}
//		double divisor = 1.00;
//		double prevDivisor = divisor;
//		for (int i = 0; i < 4; i++) {
//			prevDivisor = divisor;
//			divisor = divisor / 2;
//			for(int j = 0; j < 6; j++){
//				int y_pos = (int) (height*divisor + j*(height*prevDivisor));
//				Sprite Enemy = new Sprite (enemy, 800 + 75*i, y_pos - 24, "enemy", width, height, Color.RED);
//				enemySpriteList.addPrev(Enemy);
//				spriteList.addPrev(Enemy);
//				root.getChildren().add(Enemy);
//				Logger.newDragon(Enemy.toString());
//				enemys++;
//			}
//		}
	}
	
	private void sortedOrdering() {
		int enemiesLeft = enemys;
		int columns = enemiesLeft / 6; //a lo sumo 6 sprites enemigas por columna
		double distFromRightPerc = 100;
		double distFromAbovePerc = 0;
		
		for (int col = 0; col < columns; col++) {
			int rows;
			if (col < columns - 1) {
				rows = 6;
			}else {
				rows = enemiesLeft;
			}
			
			for(int row = (6 - rows); row < rows; row ++) {
				double[] distPix = percentToPix(distFromRightPerc, distFromAbovePerc, true, true);
				distFromAbovePerc += 100 / 6; 
				//Sprite enemyTemp = enemySpriteList.see(--enemiesLeft);
				Sprite enemyTemp = new Sprite (enemy, distPix[0], distPix[1], "enemy", width, height, Color.RED);
				//enemyTemp.setPosition(distPix[0], distPix[1]);
				enemyList.addPrev(enemyTemp);
				spriteList.addPrev(enemyTemp);
				root.getChildren().add(enemyTemp);
				//Logger.newDragon(enemyTemp.toString());
				enemys++;
			}
			
			distFromRightPerc -= 30;
		}
		
		
		
		
		
		
		
//		double divisor = 1.00;
//		double prevDivisor = divisor;
//		for (int i = 0; i < 4; i++) {
//			prevDivisor = divisor;
//			divisor = divisor/2;
//			for(int j = 0; j < Math.pow(2, (double) i); j++){
//				int y_pos = (int) (height*divisor + j*(height*prevDivisor));
//				Sprite Enemy = new Sprite (enemy, 800 + 75*i, y_pos - 24, "enemy", width, height, Color.RED);
//				enemySpriteList.addPrev(Enemy);
//				spriteList.addPrev(Enemy);
//				root.getChildren().add(Enemy);
//				Logger.newDragon(Enemy.toString());
//				enemys++;
//			}
//		}
		
		
		
	}
	
	private double[] pixToPercent() {
		return null;
	}
	
	private double[] percentToPix(double percPosX, double percPosY, boolean xHasMargin, boolean yHasMargin) {
		double pixPosX;
		double pixPosY;
		
		double marginX = 0;
		double marginY = 0;
				
		double allowedWidthPix;
		double allowedHeightPix;
		
		if(!xHasMargin) {
			allowedWidthPix = width;
		}else {
			marginX = nominalMarginX;
			allowedWidthPix = width - 2 * marginX;

		}
		
		if(!yHasMargin) {
			allowedHeightPix = height;
		}else {
			marginY = nominalMarginY;
			allowedHeightPix = height - 2 * marginY;

		}
		
		pixPosX = percPosX / 100 * allowedWidthPix + marginX;
		pixPosY = percPosY / 100 * allowedHeightPix + marginY;
		
		return new double[] {pixPosX, pixPosY};		
	}
	
	
//	private void nextlevel(int x) {
//		spriteList.addPrev(Player);
//		double divisor = 1.00;
//		double prevDivisor = divisor;
//		for (int i = 0; i < x; i++) {
//			prevDivisor = divisor;
//			divisor = divisor/2;
//			for(int j = 0; j < Math.pow(2, (double) i); j++){
//				int y_pos = (int) (height*divisor + j*(height*prevDivisor));
//				Sprite Enemy = new Sprite (enemy, 800 + 75*i, y_pos - 24, "enemy", width, height, Color.RED);
//				enemyList.addPrev(Enemy);
//				spriteList.addPrev(Enemy);
//				root.getChildren().add(Enemy);
//				//Logger.newDragon(Enemy.toString());
//				enemys++;
//			}
//		}
//	}
	

	private void update2() {
		int indicatorT = indicatorE + enemys +1;
		for (int a = 0; a<indicatorT; a++) {
			Sprite s = spriteList.see(a);
			if (s.getDead()) {
				break;
			}
			else if (s.getType().equals("enemybullet")) {
				s.moveLeft(5);
				if (s.intersects(Player)) {
					Player.setDead(true);
					s.setDead(true);
					root.getChildren().remove(Player);
					Lifes = 0;
				}
				break;
			}
			else if (s.getType().equals("playerbullet")) {
				s.moveRight(5);
				for (int e = 0; e<enemys; e++) {
					Sprite enemy = enemyList.see(e);
					if(s.getDead()==false) {
						
						if (s.intersects(enemy)) {
							enemy.setDead(true);
							s.setDead(true);
							Points++;
						}
						
					}
				}
				break;
			}

		}
	}


	private void shoot(Sprite who) {
		indicatorE++;
		Sprite s = new Sprite (fireballE, who.getTranslateX(), who.getTranslateY()+20,who.getType()+"bullet", width, height, Color.BLACK );
		bulletPList.addPrev(s);
		spriteList.addPrev(s);
		root.getChildren().add(s);
	
	}
	
	
	public void nuevaVentana() {
		Stage stage = new Stage();
        Scene scene = new Scene(newWindow());

        stage.setScene(scene);
        stage.setTitle("Nueva Ventana");
        
        stage.show();
        

	}
	
	private Parent newWindow() {
		ScrollPane sp = new ScrollPane();
		
		Canvas canvas = new Canvas(300,500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		sp.setContent(canvas);
		
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				//gc.drawImage(back, 0, 0);
				for (int i =0; i<enemys; i++) {
					Sprite s = enemyList.see(i);
					if (!s.getDead()) {
						//name = s.getDragon().getName();
						name = "No hay todav�a";
						velocity = s.getDragon().getVelocidadRecarga();
						age = s.getDragon().getAge();
						resistance = s.getDragon().getResistencia();
						position = s.getDragon().getClas();
						//fathername = s.getDragon().getFather().getName();
						fathername = "A�n no";

						gc.fillText("Nombre " + name, 100, 10+(140*i));
						gc.fillText("Velocidad Recarga " + velocity, 100, 30+(140*i));
						gc.fillText("Edad " + age, 100, 50+(140*i));
						gc.fillText("Resistencia " + resistance, 100, 70+(140*i));
						gc.fillText("Clase " + position, 100, 90+(140*i));
						gc.fillText("Father Name " + fathername, 100, 110+(140*i));
						gc.fillText("***************************************", 100, 130+(140*i));
						
					}
				}	
			}	
		};
		
		
		timer.start();
		return sp;
	}


	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Scene scene = new Scene(createContent());
		

		

		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case LEFT:
				Player.moveLeft(5);
				break;
			case RIGHT:
				Player.moveRight(5);
				break;
			case DOWN:
				Player.moveDown(5);
				break;
			case UP:
				Player.moveUp(5);
				break;
			case SPACE:
				if (Lifes != 0) {
					shoot(Player);
				}
				break;
		}
			});
		
		
		stage.setScene(scene);
		stage.show();
		
		nuevaVentana();
		
	}
	
	
	public static void main(String[] args ) {
		launch(args);
	}

}
