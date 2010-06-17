package dbmi_server.decoder;

import java.util.ArrayList;
import java.util.List;

public class MetarInformationObject {
	
	private String day;
	
	private String time;
	
	private String temperature;
	
	private String dewPoint;
	
	private String weatherCondition;
	
	private String pressure;
	
	private List<String> cloudLayers = new ArrayList<String>();
	
	private List<String> windData = new ArrayList<String>();

	public MetarInformationObject (String day, String time, String temperature, String dewPoint, String weatherCondition, String pressure, List<String> cloudLayers, List<String> windData) {
		this.day = day;
		this.time = time;
		this.temperature = temperature;
		this.dewPoint = dewPoint;
		this.weatherCondition = weatherCondition;
		this.pressure = pressure;
		this.cloudLayers = cloudLayers;
		this.windData = windData;
	}

	public String getDay() {
		return day;
	}

	public String getTime() {
		return time;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getDewPoint() {
		return dewPoint;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public String getPressure() {
		return pressure;
	}

	public List<String> getCloudLayers() {
		return cloudLayers;
	}

	public List<String> getWindData() {
		return windData;
	}
	
	public void addCloudLayer(String cloudLayer) {
		cloudLayers.add(cloudLayer);
	}
	
	public void addWindData(String windData) {
		this.windData.add(windData);
	}
}
