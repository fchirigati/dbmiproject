package dbmi_server.resources;

import java.net.URLDecoder;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.CharacterSet;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.appengine.api.datastore.Key;

import dbmi_server.database.Airport;
import dbmi_server.database.MetarInformation;
import dbmi_server.database.PMF;
import dbmi_server.decoder.Decoder;
import dbmi_server.decoder.MetarInformationObject;

public class StoreResource extends ServerResource {
	
	@Post
    public Representation store() throws JSONException {
		String text = getRequest().getEntityAsText();
    	String[] information = text.split("&");
    	
    	JSONObject response = new JSONObject();
    	
		try {
	    	storeInformation(information);
	    	
	    	response.put("error", "");
	    	response.put("success", true);
	    	
	    	return getRepresentation(response);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			response.put("success", false);
			
			return getRepresentation(response);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void storeInformation(String[] information) throws Exception {
		for (int i = 0; i < information.length; i++) {
			String icao = information[i].substring(0, 4);
			String metar = information[i].substring(5);
			metar = URLDecoder.decode(metar, "ASCII");
			
			if (metar != "") {
				Decoder decoder;
				try {
					decoder = new Decoder(metar);
				} catch (Exception e) {
					continue;
				}
				MetarInformationObject metarInformationObject = decoder.getInformation();
				
				PersistenceManager pm = PMF.get().getPersistenceManager();
				Query query = pm.newQuery(Airport.class);
				query.setFilter("icaoCode == icao");
				query.declareParameters("String icao");
				
				try {
					List<Airport> airports = (List<Airport>) query.execute(icao);
					MetarInformation metarInformation;
					Key airportKey;
					
					if ((airports.iterator().hasNext()) && (airports.size() == 1)) {
						airportKey = airports.get(0).getKey();
					} else {
						throw new Exception("No airports with icao " + icao + ".");
					}
					
					metarInformation = pm.getObjectById(Airport.class, airportKey).getMetarInformation();
					metarInformation.setDay(metarInformationObject.getDay());
					metarInformation.setTime(metarInformationObject.getTime());
					metarInformation.setTemperature(metarInformationObject.getTemperature());
					metarInformation.setDewPoint(metarInformationObject.getDewPoint());
					metarInformation.setWeatherCondition(metarInformationObject.getWeatherCondition());
					metarInformation.setPressure(metarInformationObject.getPressure());
					metarInformation.setCloudLayers(metarInformationObject.getCloudLayers());
					metarInformation.setWindData(metarInformationObject.getWindData());
				} finally {
					query.closeAll();
					pm.close();
				}
			}
		}
	}
	
	private Representation getRepresentation(JSONObject jsonObject) {
		JsonRepresentation jr = new JsonRepresentation(jsonObject);
        jr.setCharacterSet(CharacterSet.UTF_8);

        return jr;
	}
}
