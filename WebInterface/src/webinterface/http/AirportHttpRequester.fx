/*
 * AirportHttpRequester.fx
 *
 * Created on 05/05/2010, 21:16:50
 */

package webinterface.http;

import java.io.InputStream;
import javafx.data.pull.Event;

/**
 * The HttpRequester for getting the available airports.
 */
public class AirportHttpRequester extends HttpRequester {

    /**
    * The function onInput.
    */
    protected override function onInput(input: InputStream) {
        var parser: AirportParser =
            AirportParser {
                input: input
            }
        result = parser.parse()
    }
}


/**
 * The Parser of AirportHttpRequester.
 */
protected class AirportParser extends Parser {

    /**
    * The function onEvent.
    */
    protected override function onEvent(event: Event) {

    }
}