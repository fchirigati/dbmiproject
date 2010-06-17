/*
 * InformationInterface.fx
 *
 * Created on 04/05/2010, 19:50:29
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Interpolator;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Flow;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.control.TextBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import webinterface.http.MeteorologicalInformation;
import javafx.scene.layout.Stack;
import javafx.scene.layout.LayoutInfo;
import javafx.scene.layout.VBox;

/**
 * This class implements the interface for showing the meteorological
 * information.
 */
public class MetInfInterface extends CustomNode {

    /**
    * Init for MetInfInterface.
    */
    init {
        //meteorologicalInformationHandler.playFromStart()
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
            translateY: -140
            image: projectImage
            fitHeight: 160
            preserveRatio: true
            cache: true
        }

    def projectLabel: Label =
        Label {
            text: "COS470 - Sistemas Distribuídos\nProfessor José Ferreira de Rezende\n\nAlunos:\tAna Luiza Dallora Moraes\n\tFernando Seabra Chirigati\n\tLuís Cesar dos Santos Guedes"
            font: Font {
                name: "Trebuchet MS bold"
                size: 18
            }
            textFill: Color.web("#10459B")
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
                        projectImageView.opacity => 1.0 tween Interpolator.LINEAR
                    ]
                }
                KeyFrame {
                    time: 0.3s
                    values: [
                        projectImageView.opacity => 0.0 tween Interpolator.LINEAR
                    ]
                }
            ]
        }

    /**
    * The progress indicator of this interface.
    */
    public var progressIndicator: ProgressIndicator =
        ProgressIndicator {
            scaleX: 1.5
            scaleY: 1.5
            translateY: -100
            progress: -1
            opacity: 0.0
        }

    /**
    * The flow that shows the meteorological information.
    */
    def metInfFlow: MetInfFlow =
        MetInfFlow {
            opacity: 0.0
            translateY: -94
        }
        
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
                        metInfFlow.opacity => 0.0
                            tween Interpolator.LINEAR
                    ]
                }
                KeyFrame {
                    time: 1s
                    values: [
                        metInfFlow.opacity => 1.0
                            tween Interpolator.LINEAR
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
            meteorologicalInformationFlowAnimation.rate = - 1.0;
            meteorologicalInformationFlowAnimation.play()
        }
        getInformation()
    }

    /**
    * Gets the meteorological information.
    */
    function getInformation(): Void {
        var metInf: MeteorologicalInformation = (meteorologicalInformation as MeteorologicalInformation);

        if (metInf.getDay() != "") {
            metInfFlow.dateText = metInf.getDay();
        } else {
            metInfFlow.dateText = "Informação Indisponível"
        }

        if (metInf.getHour() != "") {
            metInfFlow.timeText = metInf.getHour();
        } else {
            metInfFlow.timeText = "Informação Indisponível"
        }

        if (metInf.getTemperature() != "") {
            metInfFlow.temperatureText = metInf.getTemperature();
        } else {
            metInfFlow.temperatureText = "Informação Indisponível"
        }

        if (metInf.getDay() != "") {
            metInfFlow.dewPointText = metInf.getDewPoint();
        } else {
            metInfFlow.dewPointText = "Informação Indisponível"
        }

        if (metInf.getWeatherCondition() != "") {
            metInfFlow.weatherConditionText = metInf.getWeatherCondition();
        } else {
            metInfFlow.weatherConditionText = "Informação Indisponível"
        }

        if (metInf.getPressure() != "") {
            metInfFlow.pressureText = metInf.getPressure();
        } else {
            metInfFlow.pressureText = "Informação Indisponível"
        }

        metInfFlow.cloudItems = metInf.getCloudLayers();
        metInfFlow.windItems = metInf.getWindData();

        progressIndicator.opacity = 0.0;
        meteorologicalInformationFlowAnimation.rate = 1.0;
        meteorologicalInformationFlowAnimation.play();
    }

    /**
    * Returns the root of the hierarchy that defines MetInfInterface.
    */
    protected override function create(): Node {
        Stack {
            content: [
                //progressIndicator,
                projectImageView,
                //projectLabel,
                metInfFlow
            ]
        }
    }
}


/**
 * This class implements the flow of items showing meteorological information.
 */
protected class MetInfFlow extends CustomNode {

    var dateText: String;

    var timeText: String;

    var temperatureText: String;

    var dewPointText: String;

    var weatherConditionText: String;

    var pressureText: String;

    var cloudItems: Object[];

    var windItems: Object[];

    /**
    * Date.
    */
    def date: InformationText =
        InformationText {
            name: "Data"
            text: bind dateText
        }

    /**
    * Time.
    */
    def time: InformationText =
        InformationText {
            name: "Hora"
            text: bind timeText
        }

    /**
    * Temperature.
    */
    def temperature: InformationText =
        InformationText {
            name: "Temperatura"
            text: bind temperatureText
        }

    /**
    * Dew point.
    */
    def dewPoint: InformationText =
        InformationText {
            name: "Ponto de Orvalho"
            text: bind dewPointText
        }

    /**
    * Weather condition.
    */
    def weatherCondition: InformationText =
        InformationText {
            name: "Condição do Tempo"
            text: bind weatherConditionText
        }

    /**
    * Pressure.
    */
    def pressure: InformationText =
        InformationText {
            name: "Pressão do Ar"
            text: bind pressureText
        }

    /**
    * Cloud.
    */
    def cloud: InformationList =
        InformationList {
            name: "Nuvens"
            items: bind cloudItems
        }

    /**
    * Wind.
    */
    def wind: InformationList =
        InformationList {
            name: "Vento"
            items: bind windItems
        }

    def hBox_1: HBox =
        HBox {
            spacing: 15
            hpos: HPos.CENTER
            vpos: VPos.CENTER
            nodeVPos: VPos.CENTER
            content: [
                date,
                time,
                temperature
            ]
        }

    def hBox_2: HBox =
        HBox {
            spacing: 15
            hpos: HPos.CENTER
            vpos: VPos.CENTER
            nodeVPos: VPos.CENTER
            content: [
                dewPoint,
                weatherCondition,
                pressure
            ]
        }

    def hBox_3: HBox =
        HBox {
            spacing: 15
            hpos: HPos.CENTER
            vpos: VPos.CENTER
            nodeVPos: VPos.CENTER
            content: [
                cloud,
                wind
            ]
        }

    /**
    * The main flow.
    */
    def flow: VBox =
        VBox {
            spacing: 15
            nodeHPos: HPos.CENTER
            content: [
                hBox_1,
                hBox_2,
                hBox_3
            ]
        }

    /**
    * Returns the root of the hierarchy that defines MetInfFlow.
    */
    protected override function create(): Node {
        flow
    }
}


/**
 * One single information.
 */
protected class InformationText extends CustomNode {

    protected var name: String;

    protected var text: String;

    /**
    * The Label of the information.
    */
    def label: Label =
        Label {
            text: bind name
            font: Font {
                name: "Trebuchet MS bold"
                size: 14
            }
            textFill: Color.web("#10459B")
        }

    def textBox: TextBox =
        TextBox {
            editable: false
            font: Font {
                name: "Trebuchet MS bold"
                size: 12
            }
            text: bind text
            layoutInfo: LayoutInfo {
                width: 200
            }
        }

    /**
    * A meteorological single information.
    */
    def metSingleInf: Flow =
        Flow {
            vertical: true
            vgap: 5
            nodeHPos: HPos.LEFT
            nodeVPos: VPos.CENTER
            content: [
                label,
                textBox
            ]
        }

    /**
    * Returns the root of the hierarchy that defines MetInfFlow.
    */
    protected override function create(): Node {
        metSingleInf
    }
}


/**
 * One single information.
 */
protected class InformationList extends CustomNode {

    protected var name: String;

    protected var items: Object[];

    /**
    * The Label of the information.
    */
    def label: Label =
        Label {
            text: bind name
            font: Font {
                name: "Trebuchet MS bold"
                size: 14
            }
            textFill: Color.web("#10459B")
        }

    def listView: ListView =
        ListView {
            items: bind items
            layoutInfo: LayoutInfo {
                width: 300
                height: 94
            }
        }

    /**
    * A meteorological single information.
    */
    def metSingleInf: Flow =
        Flow {
            vertical: true
            vgap: 5
            nodeHPos: HPos.LEFT
            nodeVPos: VPos.CENTER
            content: [
                label,
                listView
            ]
        }

    /**
    * Returns the root of the hierarchy that defines MetInfFlow.
    */
    protected override function create(): Node {
        metSingleInf
    }
}