package dbmi_server.resources;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import dbmi_server.database.Airport;
import dbmi_server.database.Country;
import dbmi_server.database.PMF;

public class AirportsResource extends ServerResource {
	
	private String countryCode;
	
	@Override
	public void doInit() {
		this.countryCode = (String) getRequestAttributes().get("countryCode");
	}
	
	@Get
	public Representation getAirports() throws Exception {
		JSONObject airportsObject = new JSONObject();
        
        try {
        	addAirports(airportsObject, countryCode);
        	
        	return getRepresentation(airportsObject);
        } catch (Exception e) {
        	airportsObject = new JSONObject();
        	airportsObject.put("error", e.getMessage());
        	
        	return getRepresentation(airportsObject);
        }
	}
	
	@SuppressWarnings("unchecked")
	private void addAirports(JSONObject jsonObject, String countryCode) 
			throws Exception {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Country.class);
		query.setFilter("code == countryCode");
		query.setOrdering("name desc");
		query.declareParameters("String countryCode");
		try {
			List<Country> countries = (List<Country>) query.execute(countryCode);
			if ((countries.iterator().hasNext()) && (countries.size() == 1)) {
				List<Airport> airports = countries.get(0).getAirports();
				for (Airport airport: airports) {
					jsonObject.put(airport.getIcaoCode(), airport.getName());
				}
			} else {
				jsonObject.put("","");
			}
		} finally {
			query.closeAll();
		}
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
