package Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Server.Dragon;
import Server.List;
import Server.Server;
import Utils.ListSerializer;

public class MockClientConnection {
	
	public static List<Dragon> getServerUpdate(int quantity) throws IOException {
		 return new Server().getListaDragones(quantity);
		}
	
	public static List<Dragon> postServerUpdate(List<Dragon> list){
		
		if(list.getSortValue() == 1) {
			list = new Server().insertionSort(list);
		}else if(list.getSortValue() == 2) {
			list = new Server().quickSort(list);
		}else if(list.getSortValue() == 3) {
			list = new Server().selectionSort(list);
		}
		return list;
		
	}

}
