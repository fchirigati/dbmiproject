package restlettest;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.ext.json.JsonRepresentation;

/**
 * Resource which has only one representation.
 * 
 */
public class HelloWorldResource extends ServerResource {
	
	String user;

	@Override
	public void doInit() {
		this.user = (String) getRequestAttributes().get("user");
	}
	
    @Get
    public Representation represent() throws ResourceException {
    	System.out.println("GET");
        JSONObject json = new JSONObject();
        
        try {
        	json.put("user", this.user);
        } catch (JSONException e) {
        	throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
        }
        
        JsonRepresentation jr = new JsonRepresentation(json);
        jr.setCharacterSet(CharacterSet.UTF_8);
        
        return jr;
    }
   /* public String represent() {
    	return "User: " + this.user;
    }*/
    
	/*@Post
    public void store() {
		System.out.println("POST");
    	Map<String,Object> map = getRequest().getAttributes();
    	System.out.println(map.values());
    	System.out.println(map.get("SGBL"));
    	System.out.println(map.keySet());
    }*/
}