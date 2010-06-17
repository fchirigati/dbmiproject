package dbmi_server.resources;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import dbmi_server.database.Airport;
import dbmi_server.database.MetarInformation;
import dbmi_server.database.PMF;

public class MetInfResource extends ServerResource {
	
	private String countryCode;
	private String airportICAO;
	
	@Override
	public void doInit() {
		this.countryCode = (String) getRequestAttributes().get("countryCode");
		this.airportICAO = (String) getRequestAttributes().get("airportICAO");
	}
	
	@Get
	public Representation getMeteorologicalInformation() throws JSONException {
		JSONObject informationObject = new JSONObject();
        
        try {
        	addInformation(informationObject, airportICAO);
        	
        	return getRepresentation(informationObject);
        } catch (JSONException e) {
        	informationObject = new JSONObject();
        	informationObject.put("error", e.getMessage());
        	
        	return getRepresentation(informationObject);
        }
	}
	
	@SuppressWarnings("unchecked")
	private void addInformation(JSONObject jsonObject,
			String airportICAO) throws JSONException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Airport.class);
		query.setFilter("icaoCode == airportICAO");
		query.declareParameters("String airportICAO");
		try {
			List<Airport> airports = (List<Airport>) query.execute(airportICAO);
			if ((airports.iterator().hasNext()) && (airports.size() == 1)) {
				MetarInformation metarInformation = airports.get(0).getMetarInformation();
				jsonObject.put("day", metarInformation.getDay());
				jsonObject.put("time", metarInformation.getTime());
				jsonObject.put("temperature", metarInformation.getTemperature());
				jsonObject.put("dew_point", metarInformation.getDewPoint());
				jsonObject.put("weather_condition", metarInformation.getWeatherCondition());
				jsonObject.put("pressure", metarInformation.getPressure());
				
				JSONArray cloudLayers = new JSONArray();
				for (int i = 0; i < metarInformation.getCloudLayers().size(); i++) {
					cloudLayers.put(metarInformation.getCloudLayers().get(i));
				}
				jsonObject.put("cloud_layers", cloudLayers);
				
				JSONArray windData = new JSONArray();
				for (int i = 0; i < metarInformation.getWindData().size(); i++) {
					windData.put(metarInformation.getWindData().get(i));
				}
				jsonObject.put("wind_data", windData);
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
