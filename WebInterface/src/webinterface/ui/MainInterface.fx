/*
 * MainInterface.fx
 *
 * Created on 04/05/2010, 19:30:10
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.layout.Flow;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;

/**
 * This class defines the main interface of DBMIWebInterface.
 * Throughout this interface, it is possible to search for meteorological
 * information and, so, connect to the web service.
 * MainInterface consists in two interfaces: one for searching the airport,
 * and other to show the meteorological information.
 */
public class MainInterface extends CustomNode {

    /**
    * Init method for MainInterface.
    */
    init {
        meteorologicalInformationHandler.playFromStart()
    }

    /**
    * The position of the interface in the x axis.
    */
    public var x: Number;

    /**
    * The position of the interface in the y axis.
    */
    public var y: Number;

    /**
    * The meteorological information coming from searchInterface.
    */
    var meteorologicalInformation: Object;

    /**
    * The current meteorological information.
    */
    var currentMeteorologicalInformation: Object = null;

    /**
    * The logo image.
    */
    def logoImage: Image =
        Image {
            url: "{__DIR__}resources/DBMI.bmp"
        }

    /**
    * The ImageView for logo image.
    */
    def logoImageView: ImageView =
        ImageView {
            image: logoImage
            fitHeight: 110
            preserveRatio: true
            cache: true
        }

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
    * A separator.
    */
    def separator: Rectangle =
        Rectangle {
            width: 700
            height: 2
            fill: Color.web("#10459B")
        }

    /**
    * The flow of interfaces.
    */
    def flow: VBox =
        VBox {
            translateX: x
            translateY: y
            spacing: 15
            nodeHPos: HPos.CENTER
            content: [
                logoImageView,
                searchInterface,
                separator,
                metInfInterface
            ]
        }

    /**
    * Handler for meteorological information.
    */
    def meteorologicalInformationHandler: Timeline =
        Timeline {
            repeatCount: Timeline.INDEFINITE
            interpolate: false
            keyFrames: [
                KeyFrame {
                    time: 0.5s
                    action: function() {
                        prepareMetInfInterface()
                    }
                }
            ]
        }

    /**
    * Prepares the meteorological information interface.
    */
    function prepareMetInfInterface(): Void {
        if (searchInterface.getSearchStatus()) {
            meteorologicalInformation = searchInterface.getMeteorologicalObject();
            if (currentMeteorologicalInformation != meteorologicalInformation) {
                searchInterface.setSearchStatus(false);
                currentMeteorologicalInformation = meteorologicalInformation;
                metInfInterface.meteorologicalInformation =
                    currentMeteorologicalInformation;
                metInfInterface.prepareInterface()
            }
        }
    }

    /**
    * Returns the root of the hierarchy that defines MainInterface.
    */
    protected override function create(): Node {
        flow
    }
}