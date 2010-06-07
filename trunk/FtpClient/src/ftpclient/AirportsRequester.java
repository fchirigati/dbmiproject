package ftpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONTokener;

/**
 * AirportsRequester gets the list of airports from the server, through a GET
 * method.
 * @author Fernando Seabra
 */
public class AirportsRequester {

	/**
	 * List of airports.
	 */
	private List<String> airports = new ArrayList<String>();

	/**
	 * Constructor of AirportsRequester.
	 */
	public AirportsRequester() throws IOException, JSONException {
		URL url = new URL("http://localhost:8888/airports");
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String input;
		JSONTokener jsonT = new JSONTokener(in.readLine());
		//while ((input = in.readLine()) != null) {
		//	airports.add(input);
		//}
		in.close();
		while (jsonT.more()) {
			System.out.println(jsonT.nextValue());
		}
	}
	
	/**
	 * Getter for airports.
	 */
	public List<String> getAirports() {
		return airports;
	}
}
