/*
 * MeteorologicalInformation.fx
 *
 * Created on 05/05/2010, 20:50:35
 */

package webinterface.http;

/**
 * This class is used to store the meteorological information of an airport.
 * All the information is stored through the parser, and obtained in the user
 * interface.
 */
public class MeteorologicalInformation {

    /**
    * The code ICAO of the airport.
    */
    var airport: String;

    /**
    * The date of the observation.
    * It must be in the format DD-MM-YYYY. Ex: 05-05-2010.
    */
    var date: String;

    /**
    * The hour of the observation.
    * It must be in the format HH:MM. Ex: 12:00.
    */
    var hour: String;

    /**
    * 
    */

    /**
    * Sets the parameter airport.
    */
    public function setAirport(airport: String) {
        this.airport = airport
    }

    /**
    * Gets the value of the parameter airport.
    */
    public function getAirport() {
        airport
    }

    /**
    * Sets the parameter date.
    */
    public function setDate(date: String) {
        this.date = date
    }

    /**
    * Gets the value of the parameter date.
    */
    public function getDate() {
        date
    }

    /**
    * Sets the parameter hour.
    */
    public function setHour(hour: String) {
        this.hour = hour
    }

    /**
    * Gets the value of the parameter hour.
    */
    public function getHour() {
        hour
    }
}
