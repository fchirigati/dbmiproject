package restlettest;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

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
    public String represent() {
        return "User: " + this.user;
    }

}