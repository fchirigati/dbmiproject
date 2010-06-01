package resources;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AllAirportsResource extends ServerResource {
	
	@Get
	public Representation getAllAirports() throws JSONException {
		JSONObject airportsObject = new JSONObject();
		JSONArray airportsArray = new JSONArray();
        
        try {
        	addAirports(airportsArray);
        	airportsObject.put("airports", airportsArray);
        } catch (JSONException e) {
        	airportsObject.put("error", e.getCause());
        }
        
        JsonRepresentation jr = new JsonRepresentation(airportsObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
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
}
