package dbmi_server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import resources.AirportsResource;
import resources.AllAirportsResource;
import resources.CountriesResource;
import resources.MetInfResource;
import resources.StoreResource;

public class ServerApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/airports", AllAirportsResource.class);
        router.attach("/store", StoreResource.class);
        router.attach("/countries", CountriesResource.class);
        router.attach("/countries/{country}/airports", AirportsResource.class);
        router.attach("/countries/{country}/airports/{airport}",
        		MetInfResource.class);
        
        return router;
    }
}