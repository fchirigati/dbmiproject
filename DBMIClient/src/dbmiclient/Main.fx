/*
 * Main.fx
 *
 * Created on 15/06/2010, 08:23:55
 */

package dbmiclient;

import com.sun.javafx.runtime.sequence.Sequence;
import dbmiclient.http.CountryHttpRequester;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import com.sun.javafx.runtime.sequence.Sequences;
import dbmiclient.http.AirportHttpRequester;
import dbmiclient.http.MetInfHttpRequester;
import dbmiclient.http.MeteorologicalInformation;

/**
 * @author Fernando Seabra
 */
public class Main {

    /**
    * List of available countries.
    */
    var countries: Sequence = null;

    /**
    * List of countries' codes.
    */
    var countriesCodes: Sequence;

    /**
    * The current selected country.
    */
    var selectedCountry: Object = "";

    /**
    * The code of the current selected country.
    */
    var selectedCountryCode: Object;

    /**
    * List of available airports.
    */
    var airports: Sequence;

    /**
    * List of airports' codes.
    */
    var airportsCodes: Sequence;

    /**
    * The current selected airport.
    */
    var selectedAirport: Object = "";

    /**
    * The current meteorological information.
    */
    var meteorologicalInformation: Object = null;

    def airportsListHandler: Timeline =
        Timeline {
            repeatCount: Timeline.INDEFINITE
            interpolate: false
            keyFrames: [
                KeyFrame {
                    time: 1s
                    action: function() {
                        getAirportsList()
                    }
                }
            ]
        }

    public-read var rectangle2: javafx.scene.shape.Rectangle;//GEN-BEGIN:main
    public-read var imageView: javafx.scene.image.ImageView;
    public-read var label: javafx.scene.control.Label;
    public-read var textbox: javafx.scene.control.TextBox;
    public-read var button: javafx.scene.control.Button;
    public-read var hbox: javafx.scene.layout.HBox;
    public-read var label2: javafx.scene.control.Label;
    public-read var listView: javafx.scene.control.ListView;
    public-read var progressIndicator: javafx.scene.control.ProgressIndicator;
    public-read var stack: javafx.scene.layout.Stack;
    public-read var vbox2: javafx.scene.layout.VBox;
    public-read var label3: javafx.scene.control.Label;
    public-read var listView2: javafx.scene.control.ListView;
    public-read var progressIndicator2: javafx.scene.control.ProgressIndicator;
    public-read var stack2: javafx.scene.layout.Stack;
    public-read var vbox3: javafx.scene.layout.VBox;
    public-read var button2: javafx.scene.control.Button;
    public-read var hbox2: javafx.scene.layout.HBox;
    public-read var rectangle: javafx.scene.shape.Rectangle;
    public-read var label4: javafx.scene.control.Label;
    public-read var textbox2: javafx.scene.control.TextBox;
    public-read var vbox4: javafx.scene.layout.VBox;
    public-read var label5: javafx.scene.control.Label;
    public-read var textbox3: javafx.scene.control.TextBox;
    public-read var vbox5: javafx.scene.layout.VBox;
    public-read var label6: javafx.scene.control.Label;
    public-read var textbox4: javafx.scene.control.TextBox;
    public-read var vbox6: javafx.scene.layout.VBox;
    public-read var hbox3: javafx.scene.layout.HBox;
    public-read var label7: javafx.scene.control.Label;
    public-read var textbox5: javafx.scene.control.TextBox;
    public-read var vbox7: javafx.scene.layout.VBox;
    public-read var label8: javafx.scene.control.Label;
    public-read var textbox6: javafx.scene.control.TextBox;
    public-read var vbox8: javafx.scene.layout.VBox;
    public-read var label9: javafx.scene.control.Label;
    public-read var textbox7: javafx.scene.control.TextBox;
    public-read var vbox9: javafx.scene.layout.VBox;
    public-read var hbox4: javafx.scene.layout.HBox;
    public-read var label10: javafx.scene.control.Label;
    public-read var listView3: javafx.scene.control.ListView;
    public-read var vbox10: javafx.scene.layout.VBox;
    public-read var label11: javafx.scene.control.Label;
    public-read var listView4: javafx.scene.control.ListView;
    public-read var vbox11: javafx.scene.layout.VBox;
    public-read var hbox5: javafx.scene.layout.HBox;
    public-read var vbox: javafx.scene.layout.VBox;
    public-read var scene: javafx.scene.Scene;
    public-read var image: javafx.scene.image.Image;
    public-read var font: javafx.scene.text.Font;
    public-read var font2: javafx.scene.text.Font;
    public-read var color: javafx.scene.paint.Color;
    public-read var linearGradient: javafx.scene.paint.LinearGradient;
    
    public-read var currentState: org.netbeans.javafx.design.DesignState;
    
    // <editor-fold defaultstate="collapsed" desc="Generated Init Block">
    init {
        textbox = javafx.scene.control.TextBox {
            layoutX: 229.0
            layoutY: 200.0
            width: 301.0
            height: 24.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox.width
                height: bind textbox.height
            }
            effect: null
            selectOnFocus: true
            text: "http://dbmiserver.appspot.com"
            action: textboxAction
        };
        listView = javafx.scene.control.ListView {
            cursor: javafx.scene.Cursor.DEFAULT
            width: 280.0
            height: 128.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind listView.width
                height: bind listView.height
            }
            items: bind countries
        };
        progressIndicator = javafx.scene.control.ProgressIndicator {
            opacity: 0.0
            cursor: javafx.scene.Cursor.WAIT
            effect: null
        };
        stack = javafx.scene.layout.Stack {
            content: [ listView, progressIndicator, ]
        };
        listView2 = javafx.scene.control.ListView {
            cursor: javafx.scene.Cursor.DEFAULT
            width: 280.0
            height: 128.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind listView2.width
                height: bind listView2.height
            }
            onKeyPressed: listView2OnKeyPressed
            items: bind airports
        };
        progressIndicator2 = javafx.scene.control.ProgressIndicator {
            opacity: 0.0
            cursor: javafx.scene.Cursor.WAIT
        };
        stack2 = javafx.scene.layout.Stack {
            content: [ listView2, progressIndicator2, ]
        };
        textbox2 = javafx.scene.control.TextBox {
            layoutX: 0.0
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox2.width
                height: bind textbox2.height
            }
            editable: false
        };
        textbox3 = javafx.scene.control.TextBox {
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox3.width
                height: bind textbox3.height
            }
            editable: false
        };
        textbox4 = javafx.scene.control.TextBox {
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox4.width
                height: bind textbox4.height
            }
            editable: false
        };
        textbox5 = javafx.scene.control.TextBox {
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox5.width
                height: bind textbox5.height
            }
            editable: false
        };
        textbox6 = javafx.scene.control.TextBox {
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox6.width
                height: bind textbox6.height
            }
            editable: false
        };
        textbox7 = javafx.scene.control.TextBox {
            width: 200.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind textbox7.width
                height: bind textbox7.height
            }
            editable: false
        };
        listView3 = javafx.scene.control.ListView {
            width: 260.0
            height: 110.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind listView3.width
                height: bind listView3.height
            }
        };
        listView4 = javafx.scene.control.ListView {
            width: 260.0
            height: 110.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind listView4.width
                height: bind listView4.height
            }
        };
        image = javafx.scene.image.Image {
            url: "{__DIR__}resources/DBMI.bmp"
        };
        imageView = javafx.scene.image.ImageView {
            image: image
            fitWidth: 0.0
            fitHeight: 0.0
            preserveRatio: false
        };
        font = javafx.scene.text.Font {
            name: "Trebuchet MS bold"
            size: 14.0
        };
        label11 = javafx.scene.control.Label {
            text: "Vento"
            font: font
        };
        vbox11 = javafx.scene.layout.VBox {
            content: [ label11, listView4, ]
            spacing: 6.0
        };
        label10 = javafx.scene.control.Label {
            text: "Nuvens"
            font: font
        };
        vbox10 = javafx.scene.layout.VBox {
            content: [ label10, listView3, ]
            spacing: 6.0
        };
        hbox5 = javafx.scene.layout.HBox {
            layoutX: 0.0
            content: [ vbox10, vbox11, ]
            spacing: 10.0
        };
        label9 = javafx.scene.control.Label {
            text: "Press\u00E3o Barom\u00E9trica"
            font: font
        };
        vbox9 = javafx.scene.layout.VBox {
            content: [ label9, textbox7, ]
            spacing: 6.0
        };
        label8 = javafx.scene.control.Label {
            text: "Condi\u00E7\u00E3o do Tempo"
            font: font
        };
        vbox8 = javafx.scene.layout.VBox {
            content: [ label8, textbox6, ]
            spacing: 6.0
        };
        label7 = javafx.scene.control.Label {
            text: "Ponto de Orvalho"
            font: font
        };
        vbox7 = javafx.scene.layout.VBox {
            content: [ label7, textbox5, ]
            spacing: 6.0
        };
        hbox4 = javafx.scene.layout.HBox {
            layoutX: 0.0
            content: [ vbox7, vbox8, vbox9, ]
            spacing: 10.0
        };
        label6 = javafx.scene.control.Label {
            text: "Temperatura"
            font: font
        };
        vbox6 = javafx.scene.layout.VBox {
            content: [ label6, textbox4, ]
            spacing: 6.0
        };
        label5 = javafx.scene.control.Label {
            text: "Hora"
            font: font
        };
        vbox5 = javafx.scene.layout.VBox {
            content: [ label5, textbox3, ]
            spacing: 6.0
        };
        label4 = javafx.scene.control.Label {
            text: "Data"
            font: font
        };
        vbox4 = javafx.scene.layout.VBox {
            content: [ label4, textbox2, ]
            spacing: 6.0
        };
        hbox3 = javafx.scene.layout.HBox {
            layoutX: 0.0
            content: [ vbox4, vbox5, vbox6, ]
            spacing: 10.0
        };
        label3 = javafx.scene.control.Label {
            text: "Aeroportos"
            font: font
        };
        vbox3 = javafx.scene.layout.VBox {
            layoutX: 0.0
            content: [ label3, stack2, ]
            spacing: 6.0
        };
        label2 = javafx.scene.control.Label {
            text: "Pa\u00EDses"
            font: font
        };
        vbox2 = javafx.scene.layout.VBox {
            layoutX: 0.0
            content: [ label2, stack, ]
            spacing: 6.0
        };
        label = javafx.scene.control.Label {
            effect: null
            text: "URI"
            font: font
        };
        font2 = javafx.scene.text.Font {
            name: "Trebuchet MS"
            size: 13.0
        };
        button2 = javafx.scene.control.Button {
            width: 78.0
            height: 29.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind button2.width
                height: bind button2.height
            }
            effect: null
            text: "Pesquisar"
            font: font2
            action: getMeteorologicalInformation
        };
        hbox2 = javafx.scene.layout.HBox {
            layoutX: 0.0
            layoutY: 0.0
            content: [ vbox2, vbox3, button2, ]
            hpos: javafx.geometry.HPos.CENTER
            vpos: javafx.geometry.VPos.CENTER
            nodeVPos: javafx.geometry.VPos.CENTER
            spacing: 20.0
        };
        button = javafx.scene.control.Button {
            width: 45.0
            height: 27.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind button.width
                height: bind button.height
            }
            effect: null
            text: "Go!"
            font: font2
            action: buttonAction
        };
        hbox = javafx.scene.layout.HBox {
            width: 800.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind hbox.width
                height: bind hbox.height
            }
            content: [ label, textbox, button, ]
            hpos: javafx.geometry.HPos.CENTER
            nodeVPos: javafx.geometry.VPos.CENTER
            spacing: 15.0
        };
        color = javafx.scene.paint.Color {
            green: 0.57
            blue: 1.0
        };
        rectangle = javafx.scene.shape.Rectangle {
            fill: color
            width: 700.0
            height: 2.0
        };
        vbox = javafx.scene.layout.VBox {
            layoutX: 0.0
            layoutY: 0.0
            width: 800.0
            height: 550.0
            layoutInfo: javafx.scene.layout.LayoutInfo {
                width: bind vbox.width
                height: bind vbox.height
            }
            translateY: 20.0
            content: [ imageView, hbox, hbox2, rectangle, hbox3, hbox4, hbox5, ]
            nodeHPos: javafx.geometry.HPos.CENTER
            spacing: 20.0
        };
        linearGradient = javafx.scene.paint.LinearGradient {
            endX: 0.0
            endY: 1.0
            stops: [ javafx.scene.paint.Stop { offset: 0.0, color: javafx.scene.paint.Color.web ("#FFFFFF") }, javafx.scene.paint.Stop { offset: 0.2, color: javafx.scene.paint.Color.web ("#FFFFFF") }, javafx.scene.paint.Stop { offset: 1.0, color: javafx.scene.paint.Color.web ("#A4B8D0") }, ]
        };
        rectangle2 = javafx.scene.shape.Rectangle {
            fill: linearGradient
            width: 800.0
            height: 700.0
        };
        scene = javafx.scene.Scene {
            width: 800.0
            height: 700.0
            content: javafx.scene.layout.Panel {
                content: getDesignRootNodes ()
            }
        };
        
        currentState = org.netbeans.javafx.design.DesignState {
            names: [ ]
            stateChangeType: org.netbeans.javafx.design.DesignStateChangeType.PAUSE_AND_PLAY_FROM_START
            createTimeline: function (actual) {
                null
            }
        }
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Generated Design Functions">
    public function getDesignRootNodes () : javafx.scene.Node[] {
        [ rectangle2, vbox, ]
    }
    
    public function getDesignScene (): javafx.scene.Scene {
        scene
    }// </editor-fold>//GEN-END:main

    function listView2OnKeyPressed(event: javafx.scene.input.KeyEvent): Void {
        if (event.text == "Enter") {
            button2.fire()
        }
    }

    function textboxAction(): Void {
        button.fire()
    }

    init {
        airportsListHandler.playFromStart()
    }

    function buttonAction(): Void {
        delete countries;
        delete countriesCodes;
        delete airports;
        delete airportsCodes;
        progressIndicator.opacity = 1.0;
        var result: Sequence;
        var countryHttpRequester: CountryHttpRequester =
            CountryHttpRequester {
                location: "{textbox.text}/countries"
                onDone: function() {
                    progressIndicator.opacity = 0;
                    result = countryHttpRequester.getResult();
                    var i: Integer = -1;
                    if (sizeof result == 0) {
                        progressIndicator2.opacity = 0.0;
                        insert "<Lista de Países Vazia>" into countries
                    }
                    var newResult: Sequence = Sequences.sort(result, new NameComparator());
                    while (++i < sizeof newResult) {
                        var country: String[] = (newResult[i] as String).split("/");
                        insert country[1] into countriesCodes;
                        insert country[0] into countries
                    }
                }
            }
        countryHttpRequester.start()
    }

    function getAirportsList(): Void {
        var selected: Object = listView.selectedItem;
        if (selected != null) {
            if (selectedCountry != selected) {
                progressIndicator2.opacity = 1.0;
                selectedCountry = selected;
                delete airports;
                delete airportsCodes;

                selectedCountryCode = countriesCodes[
                    Sequences.indexOf(countries, selectedCountry)];
                var result: Sequence;
                var airportHttpRequester: AirportHttpRequester =
                    AirportHttpRequester {
                        location: "{textbox.text}/countries/{selectedCountryCode}/airports"
                        onDone: function() {
                            progressIndicator2.opacity = 0;
                            result = airportHttpRequester.getResult();
                            var i: Integer = -1;
                            var newResult: Sequence = Sequences.sort(result, new NameComparator());
                            while (++i < sizeof newResult) {
                                var airport: String[] = (newResult[i] as String).split("/");
                                insert airport[1] into airportsCodes;
                                insert airport[0] into airports
                            }
                        }
                    }
                airportHttpRequester.start()
            }
        }
    }

    function getMeteorologicalInformation(): Void {
        var selected: Object = listView2.selectedItem;
        if (selected != null) {
            if (selectedAirport != selected) {
                selectedAirport = selected;

                var airportCode = airportsCodes[
                    Sequences.indexOf(airports, selectedAirport)];
                var result: Sequence;
                var metInfHttpRequester: MetInfHttpRequester =
                    MetInfHttpRequester {
                        location: "{textbox.text}/countries/{selectedCountryCode}/airports/{airportCode}"
                        onDone: function() {
                            result = metInfHttpRequester.getResult();
                            meteorologicalInformation = result[0];
                            setInformation();
                        }
                    }
                metInfHttpRequester.start()
            }
        }
    }

    function setInformation(): Void {
        var metInf: MeteorologicalInformation = (meteorologicalInformation as MeteorologicalInformation);

        if (metInf.getDay() != "") {
            textbox2.text = metInf.getDay();
        } else {
            textbox2.text = "Informação Indisponível"
        }

        if (metInf.getHour() != "") {
            textbox3.text = metInf.getHour();
        } else {
            textbox3.text = "Informação Indisponível"
        }

        if (metInf.getTemperature() != "") {
            textbox4.text = metInf.getTemperature();
        } else {
            textbox4.text = "Informação Indisponível"
        }

        if (metInf.getDay() != "") {
            textbox5.text = metInf.getDewPoint();
        } else {
            textbox5.text = "Informação Indisponível"
        }

        if (metInf.getWeatherCondition() != "") {
            textbox6.text = metInf.getWeatherCondition();
        } else {
            textbox6.text= "Informação Indisponível"
        }

        if (metInf.getPressure() != "") {
            textbox7.text = metInf.getPressure();
        } else {
            textbox7.text = "Informação Indisponível"
        }

        if (metInf.getCloudLayers().size() != 0) {
            listView3.items = metInf.getCloudLayers()
        } else {
            listView3.items = ["Informação Indisponível"]
        }

        if (metInf.getWindData().size() != 0) {
            listView4.items = metInf.getWindData()
        } else {
            listView4.items = ["Informação Indisponível"]
        }
    }
}
