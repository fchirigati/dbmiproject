package restlettest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class HelloWorldApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/users/{user}", HelloWorldResource.class);
        router.attach("/store", OtherResource.class);
        router.attach("/airports", AirportsResource.class);

        return router;
    }
}