package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import Server.Dragon;
import Server.List;
import Server.Server;

import Utils.ListSerializer;

public class ClientConnection {
	private static String urlDefault = "http://localhost:9080/GameOfSortsServer/resources";
	
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
	
	public static List<Dragon> getServerUpdate(int quantity) throws IOException {
		 try {
		        URL url = new URL(urlDefault + "/WaveGenerator/Generate");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setDoOutput(true);
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "text/plain");
		        String input = String.valueOf(quantity);
		        OutputStream os = conn.getOutputStream();
		        os.write(input.getBytes());
		        os.flush();
		        BufferedReader br = new BufferedReader(new InputStreamReader(
		                (conn.getInputStream())));
		        String output = br.readLine();
		        conn.disconnect();
		        return ListSerializer.fromJsonString(output);
		    } catch (MalformedURLException e) {
		    } catch (IOException e) {
		    }
		 return null;
		}
	
	public static List<Dragon> postServerUpdate(List<Dragon> json) throws IOException {
		 try {
		        URL url = new URL(urlDefault + "/WaveGenerator/Sort");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setDoOutput(true);
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/json");
		        String input = ListSerializer.toJsonString(json);
		        OutputStream os = conn.getOutputStream();
		        os.write(input.getBytes());
		        os.flush();
		        BufferedReader br = new BufferedReader(new InputStreamReader(
		                (conn.getInputStream())));
		        String output = br.readLine();
		        conn.disconnect();
		        return ListSerializer.fromJsonString(output);
		    } catch (MalformedURLException e) {
		    	System.out.println("Error de URL malformado");
		    } catch (IOException e) {
		    	System.out.println("Error de IO");
		    }
		 return null;
		}
		
	}
