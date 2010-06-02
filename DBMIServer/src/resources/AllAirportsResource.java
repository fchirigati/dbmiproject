package resources;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AllAirportsResource extends ServerResource {
	
	@Get
	public Representation getAllAirports() throws Exception {
		JSONObject airportsObject = new JSONObject();
		JSONArray airportsArray = new JSONArray();
        
        try {
        	addAirports(airportsArray);
        	airportsObject.put("airports", airportsArray);
        	
        	return getRepresentation(airportsObject);
        } catch (Exception e) {
        	airportsObject.put("error", e.getMessage());
        	
        	return getRepresentation(airportsObject);
        }
	}
	
	private void addAirports(JSONArray jsonArray) {
		// TODO code: get airports from the database
		String[] airports = new String[5];
		airports[0] = "SBGL";
		airports[1] = "SBGR";
		airports[2] = "SBBR";
		airports[3] = "SBSV";
		airports[4] = "SBPA";
		
		for (int i = 0; i < airports.length; i++) {
			jsonArray.put(airports[i]);
		}
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
