package Interface;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TempMainWithFXML extends Application {
	//Attributes
	
	//Methods
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Interface/GameScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("Interface.css").toExternalForm());
			
			primaryStage.setTitle("Game Of Sorts");
			primaryStage.setMinWidth(1440);
			primaryStage.setMinHeight(810);
			primaryStage.setMaxWidth(1440);
			primaryStage.setMaxHeight(810);
			//primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
