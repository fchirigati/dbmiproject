package restlettest;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class OtherResource extends ServerResource {
	
	@Post
    public String store() {
		System.out.println("POST");
    	String text = getRequest().getEntityAsText();
    	System.out.println(text);
    	
    	return "Post";
	}
}
