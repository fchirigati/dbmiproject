/*
 * MetInfHttpRequester.fx
 *
 * Created on 05/05/2010, 21:17:07
 */

package webinterface.http;

import java.io.InputStream;
import javafx.data.pull.Event;
import javafx.data.pull.PullParser;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * The HttpRequester for getting the meteorological information of an airport.
 */
public class MetInfHttpRequester extends HttpRequester {

    /**
    * The function onInput.
    */
    protected override function onInput(input: InputStream) {
        var parser: MetInfParser =
            MetInfParser {
                input: input
            }
        parser.parse();
        result = parser.addMetInf();
        input.close()
    }
}


/**
 * The Parser of MetInfHttpRequester.
 */
protected class MetInfParser extends Parser {

    /**
    * The meteorological information object.
    */
    var metInf: MeteorologicalInformation = new MeteorologicalInformation();

    /**
    * A boolean to indicate if it is a cloud or a wind array.
    */
    var isCloud: Boolean;

    /**
    * The function onEvent.
    */
    protected override function onEvent(event: Event) {
        if (event.type == PullParser.END_VALUE) {
            if (event.level == 0) {
                if (event.name == "day") {
                    metInf.setDay(event.text)
                }
                if (event.name == "time") {
                    metInf.setHour(event.text)
                }
                if (event.name == "temperature") {
                    metInf.setTemperature(event.text)
                }
                if (event.name == "dew_point") {
                    metInf.setDewPoint(event.text)
                }
                if (event.name == "weather_condition") {
                    metInf.setWeatherCondition(event.text)
                }
                if (event.name == "pressure") {
                    metInf.setPressure(event.text)
                }
            }
        }
        else if (event.type == PullParser.START_ARRAY) {
            if (event.name == "cloud_layers") {
                isCloud = true
            } else {
                isCloud = false
            }
        }

        else if (event.type == PullParser.END_ARRAY_ELEMENT) {
            if (isCloud) {
                metInf.addCloudLayer(event.text)
            } else {
                metInf.addWindData(event.text)
            }
        }
    }

    protected function addMetInf(): Sequence {
        insert metInf into result;
        result
    }
}