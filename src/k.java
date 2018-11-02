import DataStructure.LinkList;
import Objects.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class k extends Application{
	
	//Logback variables
	//private final org.slf4j.Logger log = LoggerFactory.getLogger(Run.class);
	
	private Pane root = new Pane();
	
	private double width = 1500;
	private double height = 600;
	private double t = 0;
	
	String fireballP = "Objects/fireballP (1).png";
	String fireballE = "Objects/fireball.png";
	String player = "Objects/player (1).png";
	String enemy = "Objects/enemy (1).png";
	Image back = new Image( "Objects/backgroundImage.png", width, height, false, false);
	
	Canvas canvas = new Canvas(1500,600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	int indicatorP = 0;
	int indicatorE = 0;
	int enemys = 0;
	
	int Points = 0;
	
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
						//System.out.println(enemyList.see(i).getDragon().getVelocidadRecarga());
						if (t>100) {
							t = 0;
						}
						if (enemyList.see(i).getDragon().getVelocidadRecarga() == t) {
							shoot(enemyList.see(i));
							
						}
						enemyList.see(i).moveLeft(1);
						t++;
					}
					else {
						enemyList.see(i).setPosition(500, 800);
						root.getChildren().remove(enemyList.see(i));
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
			}
			
		};
		
		
		timer.start();
		return root;
	}
	
	
	private void nextlevel() {
		spriteList.addPrev(Player);
		double divisor = 1.00;
		double prevDivisor = divisor;
		for (int i = 0; i < 4; i++) {
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
	

	private void update2() {
		int indicatorT = indicatorE + enemys +1;
		for (int a = 0; a<indicatorT; a++) {
			Sprite s = spriteList.see(a);
			if (s.getDead()) {
				break;
			}
			else if (s.getType().equals("enemybullet")) {
				s.moveLeft(5);
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
