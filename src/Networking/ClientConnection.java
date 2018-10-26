package Networking;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class ClientConnection {
	private static String urlDefault;
	
	public static String requestServerUpdate(String path) throws IOException {
		URL url = new URL(urlDefault + path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int status = connection.getResponseCode();
		InputStreamReader reader = new InputStreamReader((connection.getInputStream()));
		char[] buffer = new char[1024];
		int readChars = 0;
		StringBuilder builder = new StringBuilder();
		while((readChars = reader.read(buffer))>0) {
			builder.append(new String(buffer, 0, readChars));
		}
		
		return builder.toString();
		
	}
	
	public static void postServerUpdate(String path) throws IOException {
		URL url = new URL(urlDefault + path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		Socket socket = new Socket(urlDefault + path, 65356);
		connection.setRequestMethod("POST");
		
	}
	
}
