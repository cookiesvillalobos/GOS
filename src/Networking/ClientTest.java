package Networking;

import java.io.IOException;

import Server.Dragon;
import Server.List;
import Server.Server;
import Utils.ListSerializer;
import ch.qos.logback.core.net.server.Client;

import com.google.gson.*;

public class ClientTest {
	
	
	public static void main(String[] args) {
		try {
			List<Dragon> output = ClientConnection.getServerUpdate(20);
			String test = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(output);
			System.out.println(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
