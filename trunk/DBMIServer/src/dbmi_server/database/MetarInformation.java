package dbmi_server.database;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MetarInformation {
    
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String day;

    @Persistent
    private String time;

    @Persistent
    private String temperature;
    
    @Persistent
    private String dewPoint;
    
    @Persistent
    private String weatherCondition;
    
    @Persistent
    private String pressure;
    
    @Persistent
    private List<String> cloudLayers;
    
    @Persistent
    private List<String> windData;
    
    @Persistent(mappedBy = "metarInformation")
    private Airport airport;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public List<String> getCloudLayers() {
		return cloudLayers;
	}

	public void setCloudLayers(List<String> cloudLayers) {
		this.cloudLayers = cloudLayers;
	}

	public List<String> getWindData() {
		return windData;
	}

	public void setWindData(List<String> windData) {
		this.windData = windData;
	}

	public Key getKey() {
		return key;
	}

	public Airport getAirport() {
		return airport;
	}
}