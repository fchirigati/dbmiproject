package resources;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class StoreResource extends ServerResource {
	
	@Post
    public Representation store() throws JSONException {
		String text = getRequest().getEntityAsText();
    	String[] information = text.split("&");
    	
    	JSONObject response = new JSONObject();
    	
		try {
	    	storeInformation(information);
	    	
	    	response.put("success", true);
	    	response.put("error", "");
	    	
	    	return getRepresentation(response);
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getMessage());
			
			return getRepresentation(response);
		}
	}
	
	private void storeInformation(String[] information) {
		// TODO code: pass the metar string to the decoder
		
		System.out.println(information);
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
