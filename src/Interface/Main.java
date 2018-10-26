package Interface;


//Object imports
import Objects.Player;
import Objects.Enemy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	
	//Virtual Screen Resolution
	private final double Width =600;
	private final double Height = 400;
	
	//Variables for player
	private final String playerPathImage = "Objects/player (1).png";
	private Image playerImage;
	private Node player;
	private Player Player = new Player(Width,Height);
	private boolean goNorthP, goSouthP, goEastP, goWestP, FireP;
	
	//Variables for enemy
	private final String enemyPathImage = "Objects/enemy.png";
	private Image enemyImage;
	private Node enemy;
	private Enemy Enemy = new Enemy(Width, Height);
	private boolean goNorthE, goSouthE, goEastE, goWestE, FireE;
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		playerImage = new Image (playerPathImage);
		player = new ImageView(playerImage);
		
		Group dungeon = new Group(player);
		
		Player.Moveto(Width/2,Height/2, player.getBoundsInLocal().getWidth()/2,player.getBoundsInLocal().getHeight()/2);
		
		player.relocate(Player.getxPosition(), Player.getyPosition());
		
		Scene scene = new Scene(dungeon,Width,Height);

		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				switch (event.getCode()) {
				case UP: goNorthP =true;
					break;
				case DOWN: goSouthP =true;
					break;
				case LEFT: goWestP =true;
					break;
				case RIGHT: goEastP =true;
					break;
				case SHIFT: FireP =true;
					break;

		
				}
				
			}
		});
		
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorthP = false;
                    	break;
                    case DOWN:  goSouthP = false; 
                    	break;
                    case LEFT:  goWestP  = false; 
                    	break;
                    case RIGHT: goEastP  = false;
                    	break;
                    case SHIFT: FireP = false;
                    	break;
                }
            }
        });
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game of Sorts");
		primaryStage.show();
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				int dx = 0, dy = 0;
				
                if (goNorthP) {
                	dy -= 1;
                }
                if (goSouthP) {
                	dy += 1;
                }
                if (goEastP)  {
                	dx += 1;
                }
                if (goWestP)  {
                	dx -= 1;
                }
                if (FireP) {
                	dx *= 3; dy *= 3;
                }
                
                Player.MoveBy(dx, dy, player.getBoundsInLocal().getWidth(), player.getBoundsInLocal().getHeight(), player.getLayoutX(), player.getLayoutY());
                //System.out.println("Posición en X "+ Player.getxPosition() + " Posición en Y "+Player.getyPosition());
                player.relocate(Player.getxPosition(), Player.getyPosition());
			}
		};
		
		timer.start();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		}

}


