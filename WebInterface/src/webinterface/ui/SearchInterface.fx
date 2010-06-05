/*
 * SearchInterface.fx
 *
 * Created on 04/05/2010, 19:50:08
 */

package webinterface.ui;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.layout.Flow;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.LayoutInfo;
import javafx.scene.layout.HBox;
import com.sun.javafx.runtime.sequence.Sequence;
import webinterface.http.CountryHttpRequester;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import webinterface.http.AirportHttpRequester;
import javafx.util.Sequences;
import webinterface.http.MetInfHttpRequester;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Stack;
import javafx.scene.paint.Color;

/**
 * This class implements the interface for searching the airport.
 */
public class SearchInterface extends CustomNode {

    /**
    * Init for SearchInterface.
    */
    init {
        countriesListHandler.playFromStart();
        airportsListHandler.playFromStart()
    }

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

    /**
    * A boolean to indicate whether the search button was clicked or not.
    */
    var searchStatus: Boolean = false;

    /**
    * The ComboBox for the list of countries.
    */
    def countriesComboBox: ComboBox =
        ComboBox {
            width: 300
            height: 116
            text: "Países"
            items: bind countries
        }

    /**
    * The ComboBox for the list of airports.
    */
    def airportsComboBox: ComboBox =
        ComboBox {
            width: 300
            height: 116
            text: "Aeroportos"
            items: bind airports
            progressOpacity: 0
        }

    /**
    * The button of the interface.
    */
    def button: Button =
        Button {
            text: "Pesquisar"
            font: Font {
                name: "Trebuchet MS"
            }
            action: function() {
                searchStatus = true;
                getMeteorologicalInformation()
            }
        }

    /**
    * The set of items inside SearchInterface.
    */
    def hBox: HBox =
        HBox {
            spacing: 10
            hpos: HPos.CENTER
            vpos: VPos.CENTER
            nodeVPos: VPos.CENTER
            content: [
                countriesComboBox,
                airportsComboBox,
                button
            ]
        }

    /**
    * Handler for the countries list.
    */
    def countriesListHandler: Timeline =
        Timeline {
            repeatCount: 1
            interpolate: false
            keyFrames: [
                KeyFrame {
                    time: 1s
                    action: function() {
                        getCountriesList()
                    }
                }
            ]
        }

    /**
    * Returns 'meteorologicalInformation'.
    */
    public function getMeteorologicalObject(): Object {
        return meteorologicalInformation
    }

    /**
    * Get method for 'searchStatus'.
    */
    public function getSearchStatus(): Boolean {
        return searchStatus
    }

    /**
    * Set method for 'searchStatus'.
    */
    public function setSearchStatus(searchStatus: Boolean): Boolean {
        this.searchStatus = searchStatus
    }

    /**
    * Gets the countries list through a web service.
    */
    function getCountriesList(): Void {
        var result: Sequence;
        var countryHttpRequester: CountryHttpRequester =
            CountryHttpRequester {
                location: "http://localhost:8888/countries"
                onDone: function() {
                    countriesComboBox.progressOpacity = 0;
                    result = countryHttpRequester.getResult();
                    var i: Integer = -1;
                    while ( ++i < sizeof result) {
                        insert result[i] into countriesCodes;
                        insert result[++i] into countries
                    }
                }
            }
        countryHttpRequester.start()
    }

    /**
    * Handler for the airports list.
    */
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

    /**
    * Gets the airports list through a web service.
    */
    function getAirportsList(): Void {
        var selected: Object = countriesComboBox.getSelectedItem();
        if (selected != null) {
            if (selectedCountry != selected) {
                airportsComboBox.progressOpacity = 1;
                selectedCountry = selected;
                delete airports;
                delete airportsCodes;

                selectedCountryCode = countriesCodes[
                    Sequences.indexOf(countries, selectedCountry)];
                var result: Sequence;
                var airportHttpRequester: AirportHttpRequester =
                    AirportHttpRequester {
                        location: "http://localhost:8888/countries/{selectedCountryCode}/airports"
                        onDone: function() {
                            airportsComboBox.progressOpacity = 0;
                            result = airportHttpRequester.getResult();
                            var i: Integer = -1;
                            while ( ++i < sizeof result) {
                                insert result[i] into airportsCodes;
                                insert result[++i] into airports
                            }
                        }
                    }
                airportHttpRequester.start()
            }
        }
    }

    /**
    * Gets the meteorological information through a web service.
    */
    function getMeteorologicalInformation(): Void {
        var selected: Object = airportsComboBox.getSelectedItem();
        if (selected != null) {
            if (selectedAirport != selected) {
                selectedAirport = selected;

                var airportCode = airportsCodes[
                    Sequences.indexOf(airports, selectedAirport)];
                var result: Sequence;
                var metInfHttpRequester: MetInfHttpRequester =
                    MetInfHttpRequester {
                        location: "http://localhost:8888/countries/{selectedCountryCode}/airports/{airportCode}"
                        onDone: function() {
                            result = metInfHttpRequester.getResult();
                            meteorologicalInformation = result[0]
                        }
                    }
                metInfHttpRequester.start()
            }
        }
    }

    /**
    * Returns the root of the hierarchy that defines SearchInterface.
    */
    protected override function create(): Node {
        hBox
    }
}


/**
 * This class implements a ComboBox.
 * A ComboBox is a node composed by a Label and a ListView.
 */
protected class ComboBox extends CustomNode {

    /**
    * The width of ComboBox.
    */
    protected var width: Number;

    /**
    * The height of ComboBox.
    */
    protected var height: Number;

    /**
    * The text of the label.
    */
    protected var text: String;

    /**
    * The items of ListView.
    */
    protected var items: Object[];

    /**
    * The opacity of the progress indicator.
    */
    protected var progressOpacity: Float = 1.0;

    /**
    * The Label of ComboBox.
    */
    def label: Label =
        Label {
            text: text
            font: Font {
                name: "Trebuchet MS bold"
                size: 14
            }
            textFill: Color.web("#0093FF")
        }

    /**
    * The ListView of ComboBox.
    */
    def listView: ListView =
        ListView {
            items: bind items
            layoutInfo: LayoutInfo {
                width: width
                height: height / 2
            }
        }

    /**
    * The progress indicator of the list view items.
    */
    def progressIndicator: ProgressIndicator =
        ProgressIndicator {
            translateY: 5
            width: width
            height: height / 2
            progress: -1
            opacity: bind progressOpacity
        }

    /**
    * The flow of items inside ComboBox.
    */
    def flow: Flow =
        Flow {
            vertical: true
            vgap: 5
            nodeHPos: HPos.LEFT
            nodeVPos: VPos.CENTER
            content: [
                label,
                Stack {
                    content: [
                        listView,
                        progressIndicator
                    ]
                }
            ]
        }

     /**
     * Gets the index of the current selection of the ListView.
     */
     public function getSelectedIndex(): Integer {
         listView.selectedIndex
     }

     /**
     * Gets the selected item of the ListView.
     */
     public function getSelectedItem(): Object {
         listView.selectedItem
     }

     /**
    * Returns the root of the hierarchy that defines ComboBox.
    */
    protected override function create(): Node {
        flow
    }
}
