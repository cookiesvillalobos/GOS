package Interface;

import java.util.ArrayList;
import java.util.Iterator;

import DataStructure.LinkList;
import Objects.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Run extends Application{
	
	
	String fireballP = "Objects/fireballP (1).png";
	String fireballE = "Objects/fireball.png";
	String player = "Objects/player (1).png";
	String enemy = "Objects/enemy (1).png";
	Image back = new Image( "Objects/backgroundImage.png");
	
	ArrayList<Sprite> EnemyList = new ArrayList<Sprite>();
    Group root = new Group();
	
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
	
	private void shoot(Sprite who) {
		indicatorE++;
		Sprite s = new Sprite (fireballE, who.getTranslateX(), who.getTranslateY()+20,who.getType()+"bullet", width, height, Color.BLACK );
		bulletPList.addPrev(s);
		root.getChildren().add(s);
	
	}

	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Scene scene = new Scene(root);
		root.getChildren().add(canvas);
		for (int i= 0; i<6; i++) {
			Sprite Enemy = new Sprite (enemy, 1000, i*100, "enemy", width, height, Color.RED);
			enemyList.addPrev(Enemy);
			spriteList.addPrev(Player);
			spriteList.addPrev(Enemy);
			EnemyList.add(Enemy);
			root.getChildren().add(Enemy);
			enemys++;
		}
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
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gc.drawImage(back, 0, 0);
				Player.render(gc);
				
				for (int i= 0; i<6; i++) {
					enemyList.see(i).render(gc);
				}
				
				for (int a= 0; a<indicatorE; a++) {
					Sprite bullet = bulletPList.see(a);
					double xtemp = bullet.getPositionX();
					if (xtemp >width-425) {
						bullet.setPosition(50000, 10000);
					}
					else {
						//bullet.moveRight();
						bullet.setPosition(bullet.getPositionX()+10, bullet.getPositionY());
						
					}
					bullet.render(gc);
				}
				
				Iterator<Sprite> enemyIter = EnemyList.iterator();
				
				while (enemyIter.hasNext()) {
					Sprite enemy = enemyIter.next();
					System.out.println(enemy.getType());
					if (Player.intersects(enemy)) {
						enemyIter.remove();
					}
				}
				

				
				for (Sprite enemy: EnemyList)
					enemy.render(gc);
			
			
			}
			
		};
		
		
		timer.start();
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public static void main(String[] args ) {
		launch(args);
	}

}