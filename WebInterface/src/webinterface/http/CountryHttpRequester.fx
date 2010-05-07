/*
 * CountryHttpRequester.fx
 *
 * Created on 05/05/2010, 21:09:01
 */

package webinterface.http;

import java.io.InputStream;
import javafx.data.pull.Event;

/**
 * The HttpRequester for getting the available countries.
 */
public class CountryHttpRequester extends HttpRequester {

    /**
    * The function onInput.
    */
    protected override function onInput(input: InputStream) {
        var parser: CountryParser =
            CountryParser {
                input: input
            }
        result = parser.parse()
    }
}


/**
 * The Parser of CountryHttpRequester.
 */
protected class CountryParser extends Parser {

    /**
    * The function onEvent.
    */
    protected override function onEvent(event: Event) {

    }
}