package restlettest;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AirportsResource extends ServerResource {
	
	@Get
	public String getAirports() {
		return "SBGL\nSBGR";
	}
}
