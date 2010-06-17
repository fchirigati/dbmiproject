package dbmi_server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import dbmi_server.database.Country;
import dbmi_server.database.Database;
import dbmi_server.database.PMF;
import dbmi_server.resources.AirportsResource;
import dbmi_server.resources.AllAirportsResource;
import dbmi_server.resources.CountriesResource;
import dbmi_server.resources.MetInfResource;
import dbmi_server.resources.StoreResource;

public class ServerApplication extends Application {
	
	@SuppressWarnings("unchecked")
	public ServerApplication() {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query query = pm.newQuery(Country.class);
		try {
			List<Country> countries = (List<Country>) query.execute();
			if (countries.size()==0) {
				Database database = new Database();
		        database.init();
			}
		} finally {
			query.closeAll();
		}
	}

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/airports", AllAirportsResource.class);
        router.attach("/store", StoreResource.class);
        router.attach("/countries", CountriesResource.class);
        router.attach("/countries/{countryCode}/airports", AirportsResource.class);
        router.attach("/countries/{countryCode}/airports/{airportICAO}",
        		MetInfResource.class);
        
        return router;
    }
}