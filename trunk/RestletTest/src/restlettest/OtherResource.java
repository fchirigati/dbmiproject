package restlettest;

import java.util.Map;

import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class OtherResource extends ServerResource {
	
	@Post
    public String store() {
		System.out.println("POST");
    	Map<String,Object> map = getRequest().getAttributes();
    	System.out.println(map);
    	System.out.println(map.values());
    	System.out.println(map.keySet());
    	
    	return "Post";
	}
}
