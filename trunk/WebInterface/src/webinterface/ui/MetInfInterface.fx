/*
 * InformationInterface.fx
 *
 * Created on 04/05/2010, 19:50:29
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Interpolator;
import javafx.scene.control.ProgressIndicator;

/**
 * This class implements the interface for showing the meteorological
 * information.
 */
public class MetInfInterface extends CustomNode {

    /**
    * Init for MetInfInterface.
    */
    init {
        meteorologicalInformationHandler.playFromStart()
    }

    /**
    * The meteorological information.
    */
    public var meteorologicalInformation: Object;

    /**
    * A boolean that indicates whether the interface is currently showing
    * project information or not.
    */
    var projectInformationStatus: Boolean = true;

    /**
    * Handler for meteorological information.
    */
    def meteorologicalInformationHandler: Timeline =
        Timeline {
            repeatCount: Timeline.INDEFINITE
            interpolate: false
            keyFrames: [
                KeyFrame {
                    time: 1s
                    action: function() {
                        getInformation()
                    }
                }
            ]
        }

    /**
    * The project information.
    */
    def projectImage: Image =
        Image {
            url: "{__DIR__}resources/information.bmp"
        }

    /**
    * The ImageView for project information.
    */
    def projectImageView: ImageView =
        ImageView {
            image: projectImage
            fitHeight: 200
            preserveRatio: true
            cache: true
        }

    /**
    * The animation of 'projectImageView'.
    */
    def projectImageViewAnimation: Timeline =
        Timeline {
            framerate: 30
            keyFrames: [
                KeyFrame {
                    time: 0s
                    values: [
                        projectImageView.opacity => 1.0 tween Interpolator.EASEOUT
                    ]
                }
                KeyFrame {
                    time: 0.3s
                    values: [
                        projectImageView.opacity => 0.0 tween Interpolator.EASEOUT
                    ]
                }
            ]
        }

    /**
    * The progress indicator of this interface.
    */
    def progressIndicator: ProgressIndicator =
        ProgressIndicator {
            translateY: 100
            translateX: 120
            progress: -1
            opacity: 0.0
        }

    /**
    * The flow that shows the meteorological information.
    */
    def metInfFlow: MetInfFlow =
        MetInfFlow {}
        
    /**
    * The animation of 'meteorologicalInformationFlow'.
    */
    def meteorologicalInformationFlowAnimation: Timeline =
        Timeline {
            framerate: 30
            keyFrames: [
                KeyFrame {
                    time: 0s
                    values: [
                        metInfFlow.opacity => 1.0
                            tween Interpolator.EASEOUT
                    ]
                }
                KeyFrame {
                    time: 0.3s
                    values: [
                        metInfFlow.opacity => 0.0
                            tween Interpolator.EASEOUT
                    ]
                }
            ]
        }

    /**
    * Prepares the interface for the meteorological information.
    */
    public function prepareInterface(): Void {
        if (projectInformationStatus) {
            projectInformationStatus = false;
            projectImageViewAnimation.play();
            progressIndicator.opacity = 1.0
        } else {
            meteorologicalInformationFlowAnimation.rate = 1.0;
            meteorologicalInformationFlowAnimation.play()
        }
    }

    /**
    * Gets the meteorological information.
    */
    function getInformation(): Void {
        
    }

    /**
    * Returns the root of the hierarchy that defines MetInfInterface.
    */
    protected override function create(): Node {
        Group {
            content: [
                metInfFlow,
                projectImageView,
                progressIndicator
            ]
        }
    }
}


/**
 * This class implements the flow of items showing meteorological information.
 */
protected class MetInfFlow extends CustomNode {

     /**
    * Returns the root of the hierarchy that defines MetInfFlow.
    */
    protected override function create(): Node {
        Group {}
    }
}