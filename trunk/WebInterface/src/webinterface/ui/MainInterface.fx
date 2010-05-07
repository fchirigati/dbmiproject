/*
 * MainInterface.fx
 *
 * Created on 04/05/2010, 19:30:10
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;

/**
 * This class defines the main interface of DBMIWebInterface.
 * Throughout this interface, it is possible to search for meteorological
 * information and, so, connect to the web service.
 * MainInterface consists in two interfaces: one for searching the airport,
 * and other to show the meteorological information.
 */
public class MainInterface extends CustomNode {

    /**
    * Interface for searching the airport.
    */
    def searchInterface: SearchInterface =
        SearchInterface {}

    /**
    * Interface for showing the meteorological information.
    */
    def metInfInterface: MetInfInterface =
        MetInfInterface {}

    /**
    * Returns the root of the hierarchy that defines MainInterface.
    */
    protected override function create(): Node {
        Group {
            content: [
                searchInterface,
                metInfInterface
            ]
        }
    }
}