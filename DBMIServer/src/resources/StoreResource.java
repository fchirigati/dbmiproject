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
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getCause());
		}
    	
		JsonRepresentation jr = new JsonRepresentation(response);
        jr.setCharacterSet(CharacterSet.UTF_8);
		
    	return jr;
	}
	
	private void storeInformation(String[] information) {
		// TODO code: pass the metar string to the decoder
		
		System.out.println(information);
	}
}
