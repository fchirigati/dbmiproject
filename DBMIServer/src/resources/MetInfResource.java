package resources;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class MetInfResource extends ServerResource {
	
	private String country;
	private String airport;
	
	@Override
	public void doInit() {
		this.country = (String) getRequestAttributes().get("country");
		this.airport = (String) getRequestAttributes().get("airport");
	}
	
	@Get
	public Representation getMeteorologicalInformation() throws JSONException {
		JSONObject informationObject = new JSONObject();
        
        try {
        	addInformation(informationObject, country, airport);
        } catch (JSONException e) {
        	informationObject.put("error", e.getCause());
        }
        
        JsonRepresentation jr = new JsonRepresentation(informationObject);
        jr.setCharacterSet(CharacterSet.UTF_8);
        
        return jr;
	}
	
	private void addInformation(JSONObject jsonObject, String country,
			String airport) throws JSONException {
		
		// TODO code: get information from the database
		jsonObject.put("weather", "great");
		jsonObject.put("temperature", "awesome");
	}
}
