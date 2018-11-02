package Utils;

import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

public class Logger {
	
	private final static org.slf4j.Logger loggerDragon = LoggerFactory.getLogger(Interface.Run.class);
	private final static org.slf4j.Logger loggerPlayer = LoggerFactory.getLogger(Interface.Run.class);
	private final static org.slf4j.Logger loggerSort = LoggerFactory.getLogger(Interface.Run.class);
	
	public static void newDragon(String type){
		loggerDragon.info("A new dragon has been created: {}", type);
    }
	
	public static void eliminateDragon(String enemy) {
		loggerDragon.info("A dragon has been slayed: {}", enemy);
	}
	
	public static void statusPlayer(Object player){
		loggerPlayer.info("Player died with the following attributes: {}", new Gson().toJson(player));
    }
	
	public static void logSort(String type) {
		loggerSort.info("A list has been sorted using {} sorting algoritm", type);
	}

}
