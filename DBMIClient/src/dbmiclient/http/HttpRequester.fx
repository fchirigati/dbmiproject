/*
 * HttpRequester.fx
 *
 * Created on 04/05/2010, 20:37:56
 */

package dbmiclient.http;

import javafx.io.http.HttpRequest;
import java.io.InputStream;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * This abstract class implements a general HttpRequest.
 * A child class must be created for each different request.
 * Each HttpRequester is related to one Parser.
 */
protected abstract class HttpRequester {

    /**
    * Target location for the HttpRequest.
    */
    public var location: String;

    /**
    * Function called when the HTTP operation is finished.
    */
    public var onDone: function(): Void;

    /**
    * The HTTP response, after parsing.
    * By default, it is a Sequence.
    * This parameter MUST be modified in function onInput.
    */
    protected var result: Sequence;

    /**
    * Boolean that indicates that the HTTP operation has started.
    */
    var started: Boolean = false;

    /**
    * Boolean that indicates that the HTTP operation has finished.
    */
    var finished: Boolean = false;

    /**
    * The HttpRequest.
    */
    def httpRequest: HttpRequest =
        HttpRequest {
            location: bind location
            method: HttpRequest.GET
            onStarted: function() {
                started = true
            }
            /*onRead: function(bytes: Long) {
                println("Bytes read: {bytes}");
            }*/
            onInput: onInput
            onDone: function() {
                if (httpRequest.doneConnect) {
                    finished = true;
                }
                onDone()
            }
        }

    /**
    * Starts the HTTP operation.
    */
    public function start() {
        httpRequest.start()
    }

    /**
    * Indicates if the request has started.
    */
    public function hasStarted(): Boolean {
        if (started) {
            true
        } else {
            false
        }
    }

    /**
    * Indicates if the request has finished.
    */
    public function hasFinished(): Boolean {
        if (finished) {
            true
        } else {
            false
        }
    }

    /**
    * Returns the value of result.
    * Note that result is the HTTP response already parsed.
    */
    public function getResult() {
        result
    }

    /**
    * Function onInput for httpRequest.
    * This function MUST be override in the child class.
    * It basically parses the HTTP response (XML).
    * Depending on the request, the input parser will be different.
    * This function MUST modify the parameter result.
    */
    protected function onInput(input: InputStream) {}
}