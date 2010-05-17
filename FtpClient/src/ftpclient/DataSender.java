package ftpclient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

/**
 * DataSender sends the meteorological information to the server through
 * Http POST.
 * @author Fernando Seabra
 */
public class DataSender {
	
	/**
	 * Data to be sent.
	 */
	private String data = "";
	
	/**
	 * Constructor of DataSender.
	 */
	public DataSender(List<String> airports, String dir) throws IOException {
		String metarString;
		
		System.out.println("Getting the Metar string...");
		for (int i = 0; i < airports.size(); i++) {
			try {
				metarString = getMetarString(dir + airports.get(i) + ".TXT");
				System.out.println(airports.get(i) + " -> ");
				System.out.println(metarString + "\n");
				
				data += URLEncoder.encode(airports.get(i), "UTF-8") + "=" +
					URLEncoder.encode(metarString, "UTF-8");
				
				if (i < airports.size() - 1) {
					data += "&";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Sends the data to the server. 
	 */
	public void sendData() throws IOException {
		System.out.println("Sending data...");
		System.out.println("Data: " + data);
		try {
			URL url = new URL("http://localhost:8888/store");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			
			OutputStreamWriter wr = new OutputStreamWriter(
					connection.getOutputStream());
			wr.write(data);
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        System.out.println("Response: " + line + "\n");
		    }
		    rd.close();
		    
		    wr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the Metar string from a file.
	 */
	private String getMetarString(String file) throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
	        StringBuilder content = new StringBuilder();
	        String line;
	        Integer count = 1;
	        while ((line = in.readLine()) != null) {
	        	if (count == 2) {
	        		content.append(line);
	        		break;
	        	}
	        	count++;
	        }
	        return content.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
