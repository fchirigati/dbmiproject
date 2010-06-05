/*
 * Parser.fx
 *
 * Created on 04/05/2010, 21:58:29
 */

package webinterface.http;

import javafx.data.pull.PullParser;
import java.io.InputStream;
import javafx.data.pull.Event;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * This abstract class implements a general parser for the HTTP response.
 * A child class must be created for each different request.
 * Each Parser is related to one HttpRequester.
 */
protected abstract class Parser {

    /**
    * The input for the parser.
    */
    protected var input: InputStream;

    /**
    * The result of the parser.
    * By default, it is a Sequence.
    */
    protected var result: Sequence;

    /**
    * The PullParser.
    */
    def pullParser: PullParser =
        PullParser {
            input: input
            documentType: PullParser.JSON
            onEvent: onEvent
        }

    /**
    * Parses the input and returns the result.
    */
    protected function parse(): Sequence {
        pullParser.parse();
        result
    }

    /**
    * Function onEvent for pullParser.
    * This function MUST be override in the child class.
    * It basically parses the HTTP response (XML).
    * This function MUST modify the parameter result.
    */
    protected function onEvent(event: Event) {}
}
