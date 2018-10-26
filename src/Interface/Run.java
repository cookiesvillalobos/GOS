package Interface;

import java.util.ArrayList;
import java.util.Iterator;

import DataStructure.LinkList;
import Objects.Sprite;
import Utils.Rearranger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Run extends Application{
	
	private Pane root = new Pane();
	
	String fireballP = "Objects/fireballP (1).png";
	String fireballE = "Objects/fireball.png";
	String player = "Objects/player (1).png";
	String enemy = "Objects/enemy (1).png";
	Image back = new Image( "Objects/backgroundImage.png");
	
	Canvas canvas = new Canvas(1500,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	int indicatorP = 0;
	int indicatorE = 0;
	int enemys = 0;
	
	private double width = 1500;
	private double height = 600;
	private double t = 0;
	
	LinkList<Sprite> enemyList = new LinkList<>();
	LinkList<Sprite> bulletPList = new LinkList<>();
	LinkList<Sprite> bulletEList = new LinkList<>();
	LinkList<Sprite> spriteList = new LinkList<>();
		
	private Sprite Player = new Sprite(player, 300.0, 100.0, "player", width, height, Color.BLUE);
	private Sprite hola = new Sprite(player, 500.0, 200.0, "player", width, height, Color.BLUE);
	
	private Parent createContent() {
		root.setPrefSize(width, height);
		
		root.getChildren().add(canvas);
		root.getChildren().add(Player);
		root.getChildren().add(hola);
		nextlevel();
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gc.drawImage(back, 0, 0);
				Player.render(gc);
				hola.render(gc);
				
				for (int i= 0; i<6; i++) {
					if (!enemyList.see(i).getDead()) {
					enemyList.see(i).render(gc);
					
					}
					else {
						root.getChildren().remove(enemyList.see(i));
					}
				}
				
				for (int a= 0; a<indicatorE; a++) {
					Sprite bullet = bulletPList.see(a);
					if (!bullet.getDead()) {
						double xtemp = bullet.getPositionX();
						if (xtemp >width-425) {
							bullet.setPosition(50000, 10000);
						}
						else {
							bullet.moveRight();
							//bullet.setPosition(bullet.getPositionX()+10, bullet.getPositionY());
						
						}
						bullet.render(gc);
					}
					else {
						root.getChildren().remove(bullet);
					}
					
					
				}
				update2();
				//update();
			}
			
		};
		
		
		timer.start();
		return root;
	}
	
	
	private void nextlevel() {
		spriteList.addPrev(Player);
		/*
		for (int i= 0; i<6; i++) {
			Sprite Enemy = new Sprite (enemy, 1000, i*100, "enemy", width, height, Color.RED);
			enemyList.addPrev(Enemy);
			spriteList.addPrev(Enemy);
			root.getChildren().add(Enemy);
			enemys++;
		}*/
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < Math.pow(2, (double) i); j++){
				Sprite Enemy = new Sprite (enemy, 800 + 100*i, 100*(1+j), "enemy", width, height, Color.RED);
				System.out.println("Crean dragon en x: " + (800 + 100*i) + " y en y: " + 100*(1+j) );
				enemyList.addPrev(Enemy);
				spriteList.addPrev(Enemy);
				root.getChildren().add(Enemy);
				enemys++;
			}
		}
	}
	

	private void update2() {
		int indicatorT = indicatorE + enemys +1;
		for (int a = 0; a<indicatorT; a++) {
			Sprite s = spriteList.see(a);
			if (s.getDead()) {
				break;
			}
			else if (s.getType().equals("enemybullet")) {
				s.moveLeft();
				break;
			}
			else if (s.getType().equals("playerbullet")) {
				s.moveRight();
				for (int e = 0; e<enemys; e++) {
					Sprite enemy = enemyList.see(e);
					if (s.intersects(enemy)) {
						enemy.setDead(true);
						s.setDead(true);
						
					}
				}
				break;
			}
			else if (s.getType().equals("enemy")) {
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

	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Scene scene = new Scene(createContent());
		
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case LEFT:
				Player.moveLeft();
				break;
			case RIGHT:
				Player.moveRight();
				break;
			case DOWN:
				Player.moveDown();
				break;
			case UP:
				Player.moveUp();
				break;
			case SPACE:
				shoot(Player);
				break;
		}
			});
		
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public static void main(String[] args ) {
		launch(args);
	}

}
