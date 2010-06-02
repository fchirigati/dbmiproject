package resources;

import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AirportsResource extends ServerResource {
	
	private String country;
	
	@Override
	public void doInit() {
		this.country = (String) getRequestAttributes().get("country");
	}
	
	@Get
	public Representation getAirports() throws Exception {
		JSONObject airportsObject = new JSONObject();
        
        try {
        	addAirports(airportsObject, country);
        	
        	return getRepresentation(airportsObject);
        } catch (Exception e) {
        	airportsObject.put("error", e.getMessage());
        	
        	return getRepresentation(airportsObject);
        }
	}
	
	private void addAirports(JSONObject jsonObject, String country) 
			throws Exception {
		
		// TODO code: get airports from the database
		String[] airports = new String[3];
		String[] codes = new String[3];
		
		if (country.equals("1")) {
			codes[0] = "SBGL";
			airports[0] = "Aeroporto A";
			codes[1] = "SBGR";
			airports[1] = "Aeroporto B";
			codes[2] = "SBBR";
			airports[2] = "Aeroporto C";
		} else if (country.equals("2")) {
			codes[0] = "SBGL";
			airports[0] = "Aeroporto D";
			codes[1] = "SBGR";
			airports[1] = "Aeroporto E";
			codes[2] = "SBBR";
			airports[2] = "Aeroporto F";
		} else if (country.equals("3")) {
			codes[0] = "SBGL";
			airports[0] = "Aeroporto G";
			codes[1] = "SBGR";
			airports[1] = "Aeroporto H";
			codes[2] = "SBBR";
			airports[2] = "Aeroporto I";
		} else {
			System.out.println(country);
			throw new Exception("Invalid country code.");
		}
		
		for (int i = 0; i < airports.length; i++) {
			jsonObject.put(codes[i], airports[i]);
		}
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
