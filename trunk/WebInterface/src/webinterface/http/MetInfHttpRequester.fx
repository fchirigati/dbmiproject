/*
 * MetInfHttpRequester.fx
 *
 * Created on 05/05/2010, 21:17:07
 */

package webinterface.http;

import java.io.InputStream;
import javafx.data.pull.Event;

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
        result = parser.parse();
        input.close()
    }
}


/**
 * The Parser of MetInfHttpRequester.
 */
protected class MetInfParser extends Parser {

    /**
    * The function onEvent.
    */
    protected override function onEvent(event: Event) {

    }
}