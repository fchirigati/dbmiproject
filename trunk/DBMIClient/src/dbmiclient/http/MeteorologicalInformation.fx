/*
 * MeteorologicalInformation.fx
 *
 * Created on 05/05/2010, 20:50:35
 */

package dbmiclient.http;

/**
 * This class is used to store the meteorological information of an airport.
 * All the information is stored through the parser, and obtained in the user
 * interface.
 */
public class MeteorologicalInformation {

    /**
    * The date of the observation.
    * It must be in the format DD-MM-YYYY. Ex: 05-05-2010.
    */
    var day: String;

    /**
    * The hour of the observation.
    * It must be in the format HH:MM. Ex: 12:00.
    */
    var hour: String;

    /**
    * Temperature.
    */
    var temperature: String;

    /**
    * Dew point.
    */
    var dewPoint: String;

    /**
    * Weather condition.
    */
    var weatherCondition: String;

    /**
    * Pressure.
    */
    var pressure: String;

    /**
    * Cloud layers.
    */
    var cloudLayers: String[];

    /**
    * Wind data.
    */
    var windData: String[];

    /**
    * Sets the parameter date.
    */
    public function setDay(day: String) {
        this.day = day
    }

    /**
    * Gets the value of the parameter date.
    */
    public function getDay() {
        day
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

    public function setTemperature(temperature: String) {
        this.temperature = temperature
    }

    public function getTemperature() {
        temperature
    }

    public function setDewPoint(dewPoint: String) {
        this.dewPoint = dewPoint
    }

    public function getDewPoint() {
        dewPoint
    }

    public function setWeatherCondition(weatherCondition: String) {
        this.weatherCondition = weatherCondition
    }

    public function getWeatherCondition() {
        weatherCondition
    }

    public function setPressure(pressure: String) {
        this.pressure = pressure
    }

    public function getPressure() {
        pressure
    }

    public function addCloudLayer(cloudLayer: String) {
        insert cloudLayer into cloudLayers
    }

    public function getCloudLayers() {
        cloudLayers
    }

    public function addWindData(windData: String) {
        insert windData into this.windData
    }

    public function getWindData() {
        windData
    }
}
