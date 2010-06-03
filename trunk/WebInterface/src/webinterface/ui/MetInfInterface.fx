/*
 * InformationInterface.fx
 *
 * Created on 04/05/2010, 19:50:29
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;

/**
 * This class implements the interface for showing the meteorological
 * information.
 */
public class MetInfInterface extends CustomNode {

    /**
    * Returns the root of the hierarchy that defines MetInfInterface.
    */
    protected override function create(): Node {
        Group {
            content: [
            ]
        }
    }
}
