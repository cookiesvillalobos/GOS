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
	
	int Points = 0;
	
	private double width = 1500;
	private double height = 600;
	private double t = 0;
	
	LinkList<Sprite> enemyList = new LinkList<>();
	LinkList<Sprite> bulletPList = new LinkList<>();
	LinkList<Sprite> bulletEList = new LinkList<>();
	LinkList<Sprite> spriteList = new LinkList<>();
	
	
		
	private Sprite Player = new Sprite(player, 300.0, 100.0, "player", width, height, Color.BLUE);
	
	private Parent createContent() {
		root.setPrefSize(width, height);
		
		root.getChildren().add(canvas);
		root.getChildren().add(Player);
		nextlevel();
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gc.drawImage(back, 0, 0);
				Player.render(gc);
				gc.fillText("Points " + Points, 100, 36);
				gc.strokeText("Points " + Points, 100, 36);
				
				for (int i= 0; i<enemys; i++) {
					if (!enemyList.see(i).getDead() && enemyList.see(i).getPositionX() != 0) {
						enemyList.see(i).render(gc);
						System.out.println(enemyList.see(i).getDragon().getVelocidadRecarga());
						if (t>100) {
							t = 0;
						}
						if (enemyList.see(i).getDragon().getVelocidadRecarga() == t) {
							shoot(enemyList.see(i));
							
						}
						enemyList.see(i).moveLeft();
						t++;
					}
					else {
						enemyList.see(i).setPosition(500, 800);
						root.getChildren().remove(enemyList.see(i));
					}
				}
				for (int a= 0; a<indicatorE; a++) {
					if(bulletPList.see(a).getType().equals("enemybullet")) {
						bulletPList.see(a).moveLeft();
						bulletPList.see(a).render(gc);
						
					}
					else if (!bulletPList.see(a).getDead() ) {
						bulletPList.see(a).render(gc);
						bulletPList.see(a).moveRight();
					}
					else {
						bulletPList.see(a).setPosition(500, 800);
						bulletPList.see(a).setDead(true);
						root.getChildren().remove(bulletPList.see(a));
					}
					
					
				}
				update2();
			}
			
		};
		
		
		timer.start();
		return root;
	}
	
	
	private void nextlevel() {
		spriteList.addPrev(Player);
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < Math.pow(2, (double) i); j++){
				Sprite Enemy = new Sprite (enemy, 800 + 100*i, 100*(1+j), "enemy", width, height, Color.RED);
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
