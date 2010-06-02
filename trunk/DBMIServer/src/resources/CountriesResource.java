package resources;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class CountriesResource extends ServerResource {
	
	@Get
	public Representation getCountries() throws Exception {
		JSONObject countriesObject = new JSONObject();
        
        try {
        	addCountries(countriesObject);
        	
        	return getRepresentation(countriesObject);
        } catch (Exception e) {
        	countriesObject.put("error", e.getMessage());
        	
        	return getRepresentation(countriesObject);
        }
	}
	
	private void addCountries(JSONObject jsonObject) throws JSONException {
		
		// TODO code: get countries from the database
		String[] countries = new String[3];
		String[] codes = new String[3];
		
		// Each country must have a code identifying it
		countries[0] = "Brasil";
		codes[0] = "1";
		countries[1] = "Estados Unidos";
		codes[1] = "2";
		countries[2] = "Canadá";
		codes[2] = "3";
		
		for (int i = 0; i < countries.length; i++) {
			jsonObject.put(codes[i], countries[i]);
		}
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
