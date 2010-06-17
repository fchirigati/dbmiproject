package dbmi_server.decoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Decoder {
	
	private String day = "";
	
	private String time = "";
	
	private String temperature = "";
	
	private String dewPoint = "";
	
	private String weatherCondition = "";
	
	private String pressure = "";
	
	private List<String> cloudLayers = new ArrayList<String>();
	
	private List<String> windData = new ArrayList<String>();

	public Decoder(String phrase) {
		
		String delims = "[ ]+";
		String[] tokens = phrase.split(delims);
		ArrayList<String> metar = new ArrayList<String>();
		
		for (int i=0; i<tokens.length; i++ )
			metar.add(tokens[i]);
			
			
		// SEPARANDO OS CAMPOS DO ARRAYLIST	
			
			// AEROPORTO
			ArrayList<String> aeroporto = new ArrayList<String>();
			aeroporto.add(metar.get(0));
			
			// DATA E HORA
			ArrayList<String> data_hora = new ArrayList<String>();
			data_hora.add(metar.get(1));
			
			// DIRECAO E VELOCIDADE DO VENTO
			// OBS: A unidade pode estar em kt, mps, mph
			ArrayList<String> vento = new ArrayList<String>();
			vento.add(metar.get(2));
			if (metar.get(3).contains("V") && !metar.get(3).contains("CAVOK")){ vento.add(metar.get(3)); }
						
			// TEMPERATURA E PONTO DE ORVALHO
			int abacaxi = 0;
			ArrayList<String> temperatura = new ArrayList<String>();
			for (int j=0; j<tokens.length; j++ )
			{
				if (metar.get(j).length()==5){
					if (metar.get(j).substring(2,3).contains("/"))
					{
						temperatura.add(metar.get(j));
						abacaxi = j;
					}
				} else if (metar.get(j).length()==6){
					if (metar.get(j).substring(2,4).contains("/"))
					{
						temperatura.add(metar.get(j));
						abacaxi = j;
					}
				} else if (metar.get(j).length()==7){
					if (metar.get(j).substring(3,4).contains("/"))
					{
						temperatura.add(metar.get(j));
						abacaxi = j;
					}
				} else if (metar.get(j).length()==3) {
					if ((metar.get(j).substring(2).contains("/")) || (metar.get(j).substring(0,1).contains("/")))
					{
						temperatura.add(metar.get(j));
						abacaxi = j;
					}
				}
			}
			
			// NUVENS E CAMADAS DE NUVENS
			ArrayList<String> codigos_nuvens = new ArrayList<String>();
			ArrayList<String> nuvens = new ArrayList<String>();
			codigos_nuvens.add("SKC"); // No Clouds
			codigos_nuvens.add("CLR"); // No clouds below 12,000 feet
			codigos_nuvens.add("FEW"); // Few Clouds (1/8 to 2/8 of the sky is covered with clouds)
			codigos_nuvens.add("SCT"); // Scattered Clouds (3/8 to 4/8 of the sky is covered with clouds)
			codigos_nuvens.add("BKN"); // Broken Clouds (5/8 to 7/8 of the sky is covered with clouds) 
			codigos_nuvens.add("OVC"); // Overcast Clouds (8/8 or all of the sky is covered with clouds)
			
			for (int j=0; j<tokens.length; j++ ) {
				for(int k=0; k<6; k++){
					if (metar.get(j).contains(codigos_nuvens.get(k))) {
						nuvens.add(metar.get(j));
					}
				}
				if (metar.get(j).equals("CAVOK")) {
					nuvens.add(metar.get(j));
				}
			}
	
			// CONDICAO CLIMATICA
			ArrayList<String> codigos_condicao = new ArrayList<String>();
			ArrayList<String> condicao = new ArrayList<String>();
			codigos_condicao.add("DZ"); // Drizzle
			codigos_condicao.add("RA"); // Rain
			codigos_condicao.add("SN"); // Snow
			codigos_condicao.add("GR"); // Hail
			codigos_condicao.add("SQ"); // Squalls
			codigos_condicao.add("GS"); // Small Hail
			codigos_condicao.add("SG"); // Snow Grains
			codigos_condicao.add("IC"); // Diamond Dust
			codigos_condicao.add("PE"); // Ice Pellets
			codigos_condicao.add("BR"); // Mist
			codigos_condicao.add("FG"); // Fog
			codigos_condicao.add("FU"); // Smoke
			codigos_condicao.add("HZ"); // Haze
			codigos_condicao.add("VA"); // Volcanic Ash
			codigos_condicao.add("DU"); // Widespread dust
			codigos_condicao.add("SA"); // Sand
			codigos_condicao.add("SS"); // Sandstorm
			codigos_condicao.add("DS"); // Duststorm
			codigos_condicao.add("FC"); // Funnel Cloud
			codigos_condicao.add("MI"); // Shallow
			codigos_condicao.add("BC"); // Patches
			codigos_condicao.add("DR"); // Drifting
			codigos_condicao.add("BL"); // Blowing
			codigos_condicao.add("SH"); // Showers
			codigos_condicao.add("TS"); // Thunderstorm
			codigos_condicao.add("FZ"); // Supercooled (Freezing)
			
			for (int j=2; j<tokens.length; j++ ){
				for(int k=0; k<26; k++){
					if (metar.get(j).contains(codigos_condicao.get(k))){
						
						if (metar.get(j).contains("+")|| metar.get(j).contains("-")){
							if (metar.get(j).substring(1,3).contains(codigos_condicao.get(k))) condicao.add(metar.get(j));}
						else
							if (metar.get(j).substring(0,2).contains(codigos_condicao.get(k))) condicao.add(metar.get(j));}
					
					}
					
				}
			
			ArrayList<String> pressao = new ArrayList<String>();
			/*for (int j=2; j<tokens.length; j++ ){
				isInt = false;
				String p = metar.get(j);
				try{
					int i = Integer.parseInt(p.substring(1));
					isInt = true;
				}
				catch(Exception e){}
				
				if((p.contains("Q")|| p.contains("A")) && p.length()==5 && isInt == true){
					{
						pressao.add(p);
						break;
					}
				*/
			abacaxi = abacaxi + 1;
			pressao.add(metar.get(abacaxi));
			
			// TESTE FELIZES WEEEEEEEEEEEEEEEEEEEEE !!!!!!!!!!!!!!!!!!!!!! =D !
			
			/**System.out.println(aeroporto);
			System.out.println(data_hora);
			System.out.println(vento);
			System.out.println(temperatura);
			System.out.println(nuvens);
			System.out.println(condicao);
			System.out.println(pressao);
			
			System.out.println("------------------------------------");
			
			System.out.println(GetDateHour(data_hora));
			System.out.println(GetWindSpeed(vento));
			System.out.println(GetTemperature(temperatura));
			System.out.println(GetDewPoint(temperatura));
			System.out.println(GetCloudLayers(nuvens));
			System.out.println(GetPressure(pressao));	
			System.out.println(GetWeatherCondition(condicao));*/
			
			GetDateHour(data_hora);
			GetWindSpeed(vento);
			GetTemperature(temperatura);
			GetDewPoint(temperatura);
			GetCloudLayers(nuvens);
			GetPressure(pressao);
			GetWeatherCondition(condicao);
	}
			
	// DATA E HORA
	public void GetDateHour(ArrayList<String> campo){
		
		String data = campo.get(0);
		
		String dia = data.substring(0,2);
		String hora = data.substring(2,4);
		String mins = data.substring(4,6);
		
		String horario ="";
		horario += hora;
		horario += ":";
		horario += mins;
		
		ArrayList<String> date_hour = new ArrayList<String>();
		
		date_hour.add(dia);
		date_hour.add(horario);
		
		Integer month = Calendar.getInstance().get(Calendar.MONTH);
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		
		day = dia + "/" + Integer.toString(month+1) + "/" + Integer.toString(year);
		time = horario + " Z";
	}
	
	// DIRECAO E VELOCIDADE DO VENTO 
	//Estou assumindo aqui que serão passados os campos necessários, 1 ou 2 dependendo do caso
	public void GetWindSpeed(ArrayList<String> campo){
		
		//As strings que eu vou usar
		String windspeed = campo.get(0);
			
		String direction = windspeed.substring(0,3);
		String baseWindSpeed = windspeed.substring(3,5);			
		
		String gustSpeed = "";
		String measure = "";
		
		String lowerRange = "";
		String upperRange = "";
		
		ArrayList<String> finalString = new ArrayList<String>();
		
		//"G" é o código que significa GUST, e isso modifica o formato dos dados			
		if (windspeed.contains("G"))
		{
			gustSpeed = windspeed.substring(6,8);
			measure = windspeed.substring(8);
		}
		else
			measure = windspeed.substring(5);
		
		//caso o campo contenha dois itens, o segundo indica a variação da direção do vento 
		if (campo.size()==2)
		{
			String[] range = campo.get(1).split("V");
			lowerRange = range[0];
			upperRange = range[1];
		}
		
		//começando a montar as strings de saída
		String finalDirection = "Direção do Vento: ";
		if (direction.contains("VRB")) {
			finalDirection.replaceFirst("VRB", "");
			finalDirection += "variável";
		} else {
			while (direction.startsWith("0")) {
				direction = direction.replaceFirst("0", "");
			}
			if (direction.equals("")) {
				direction += "0";
			}
			finalDirection += direction + " graus";
		}
		
		finalString.add(finalDirection);
					
		if (lowerRange != "" && upperRange != "")
		{
			while (lowerRange.startsWith("0")) {
				lowerRange = lowerRange.replaceFirst("0", "");
			}
			while (upperRange.startsWith("0")) {
				upperRange = upperRange.replaceFirst("0", "");
			}
			String finalRange = "Direção variando de " + lowerRange + " até " + upperRange + " graus";
			finalString.add(finalRange);
		}
		
		while (baseWindSpeed.startsWith("0")) {
			baseWindSpeed = baseWindSpeed.replaceFirst("0", "");
		}
		if (baseWindSpeed.equals("")) {
			baseWindSpeed += "0";
		}
			
		String finalSpeed = "Velocidade do Vento: " + baseWindSpeed;
		
		if (gustSpeed != "") {
			while (gustSpeed.startsWith("0")) {
				gustSpeed = gustSpeed.replaceFirst("0", "");
			}
			finalSpeed += ", havendo ventania a " + gustSpeed;
		}
		
		if (measure.contains("KT"))
			finalSpeed += " nós";
		else if (measure.contains("MPS"))
			finalSpeed += " m/s";
		
		finalString.add(finalSpeed);
		
		windData = finalString;
	}
	
	// TEMPERATURA
	public void GetTemperature(ArrayList<String> campo){
		
		String temperature = "";
		String data = campo.get(0);
		
		if (!data.startsWith("/")) {
			if (data.substring(0,2).contains("M")){
				String temperature2 = data.substring(0,3);
				temperature2 = temperature2.replace("M", "-");
				temperature2 += "º Celsius";
				temperature += temperature2;}
			else{
				String temperature3 = data.substring(0,2);
				temperature3 += "º Celsius";
				temperature+=temperature3;}
			
			while ((temperature.startsWith("0")) || ((temperature.startsWith("-")) && (temperature.indexOf("0") == 1))) {
				temperature = temperature.replaceFirst("0", "");
			}
			
			if (temperature.startsWith("º")) {
				this.temperature = "0" + temperature;
			} else {
				this.temperature = temperature;
			}
		}
	}

	
	// PONTO DE ORVALHO
	public void GetDewPoint(ArrayList<String> campo){
		
		String dew_point = "";
		String data = campo.get(0);
		
		if (!data.endsWith("/")) {
			if (data.startsWith("/")) {
				if (data.substring(1,3).contains("M")){
					String dew_point2 = data.substring(1,3);
					dew_point2 = dew_point2.replace("M", "-");
					dew_point2 += "º Celsius";
					dew_point+=dew_point2;}
				else{
					String dew_point3 = data.substring(1,3);
					dew_point3 += "º Celsius";
					dew_point+=dew_point3;}
			} else {
				if (data.substring(3,5).contains("M")){
					String dew_point2 = data.substring(3,6);
					dew_point2 = dew_point2.replace("M", "-");
					dew_point2 += "º Celsius";
					dew_point+=dew_point2;}
				else{
					String dew_point3 = data.substring(3,5);
					dew_point3 += "º Celsius";
					dew_point+=dew_point3;}
			}
			
			while ((dew_point.startsWith("0")) || ((dew_point.startsWith("-")) && (dew_point.indexOf("0") == 1))) {
				dew_point = dew_point.replaceFirst("0", "");
			}
			
			if (dew_point.startsWith("º")) {
				dewPoint = "0" + dew_point;
			} else {
				dewPoint = dew_point;
			}
		}
	}

	
	// CAMADAS DE NUVUENS
	public void GetCloudLayers(ArrayList<String> campo){
	
		ArrayList<String> cloud_layers = new ArrayList<String>();
		String cloud = "";
		String number = "";
		
		for (int i=0; i<campo.size(); i++ ){
			
			if (campo.get(i).equals("CAVOK"))
			{
				String completeInformation = "Não há nuvens abaixo de 5000 pés";
				String completeInformation2 = "A visibilidade é maior que 10 km";
				String completeInformation3 = "Não há nevoieiro, precipitação ou neve";
				cloud_layers.add(completeInformation);
				cloud_layers.add(completeInformation2);
				cloud_layers.add(completeInformation3);
			}
			
			if (campo.get(i).contains("SKC")){ 
				cloud += "Não há nuvens";
				cloud_layers.add(cloud);
				cloud = "";}
		
			if (campo.get(i).contains("CLR")){ 
				cloud += "Não há nuvens abaixo de 12000 pés";
				cloud_layers.add(cloud);
				cloud = "";}
			
			if (campo.get(i).contains("FEW")){
				cloud += "1 ou 2 oktas de nuvens a ";
				number = campo.get(i).substring(3,6);
				
				while (number.startsWith("0")) {
					number = number.replaceFirst("0", "");
				}
				
				cloud += number + "00" + " pés";
				cloud_layers.add(cloud);
				cloud = "";}
			
			if (campo.get(i).contains("SCT")){
				cloud += "3 ou 4 oktas de nuvens a ";
				number = campo.get(i).substring(3,6);
				
				while (number.startsWith("0")) {
					number = number.replaceFirst("0", "");
				}
				
				cloud += number + "00" + " pés";
				cloud_layers.add(cloud);
				cloud = "";}
			
			if (campo.get(i).contains("BKN")){
				cloud += "5 a 7 oktas de nuvens a ";
				number = campo.get(i).substring(3,6);
				
				while (number.startsWith("0")) {
					number = number.replaceFirst("0", "");
				}
				
				cloud += number + "00" + " pés";
				cloud_layers.add(cloud);
				cloud = "";}
			
			if (campo.get(i).contains("OVC")){
				cloud += "8 oktas de nunvens (cobertura sólida de nuvem) a ";
				number = campo.get(i).substring(3,6);
				
				while (number.startsWith("0")) {
					number = number.replaceFirst("0", "");
				}
				
				cloud += number + "00" + " pés";
				cloud_layers.add(cloud);
				cloud = "";}

		}
		
	cloudLayers = cloud_layers;
	}

	// WEATHER CONDITION
	public void GetWeatherCondition(ArrayList<String> campo){
		
		ArrayList<String> weather_condition = new ArrayList<String>();
		
		String condition = "";
		if (!campo.isEmpty()) {
			String data = campo.get(0);
		
			if (data.contains("+")){condition += "Heavy ";}
			if (data.contains("-")){condition += "Light ";}
			
			if (data.contains("DZ")){
				condition += "Drizzle ";
				weather_condition.add(condition);}
			
			if (data.contains("RA")){
				condition += "Rain ";
				weather_condition.add(condition);}
			
			if (data.contains("SN")){
				condition += "Snow ";
				weather_condition.add(condition);}
			
			if (data.contains("GR")){
				condition += "Hail ";
				weather_condition.add(condition);}
			
			if (data.contains("SQ")){
				condition += "Squalls ";
				weather_condition.add(condition);}
			
			if (data.contains("GS")){
				condition += "Small Hail ";
				weather_condition.add(condition);}
			
			if (data.contains("SG")){
				condition += "Snow Grains ";
				weather_condition.add(condition);}
			
			if (data.contains("IC")){
				condition += "Diamond Dust ";
				weather_condition.add(condition);}
			
			if (data.contains("PE")){
				condition += "Ice Pellets ";
				weather_condition.add(condition);}
			
			if (data.contains("BR")){
				condition += "Mist ";
				weather_condition.add(condition);}
			
			if (data.contains("FG")){
				condition += "Fog ";
				weather_condition.add(condition);}
			
			if (data.contains("FU")){
				condition += "Smoke ";
				weather_condition.add(condition);}
			
			if (data.contains("HZ")){
				condition += "Haze ";
				weather_condition.add(condition);}
			
			if (data.contains("VA")){
				condition += "Volcanic Ash ";
				weather_condition.add(condition);}
			
			if (data.contains("DU")){
				condition += "Widespread dust ";
				weather_condition.add(condition);}
			
			if (data.contains("SA")){
				condition += "Sand ";
				weather_condition.add(condition);}
			
			if (data.contains("SS")){
				condition += "Sandstorm ";
				weather_condition.add(condition);}
			
			if (data.contains("DS")){
				condition += "Duststorm ";
				weather_condition.add(condition);}
			
			if (data.contains("FC")){
				condition += "Funnel Cloud ";
				weather_condition.add(condition);}
			
			if (data.contains("MI")){
				condition += "Shallow ";
				weather_condition.add(condition);}
			
			if (data.contains("BC")){
				condition += "Patches ";
				weather_condition.add(condition);}
			
			if (data.contains("DR")){
				condition += "Drifting ";
				weather_condition.add(condition);}
			
			if (data.contains("BL")){
				condition += "Blowing ";
				weather_condition.add(condition);}
			
			if (data.contains("SH")){
				condition += "Showers ";
				weather_condition.add(condition);}
			
			if (data.contains("TS")){
				condition += "Thunderstorm ";
				weather_condition.add(condition);}
			
			if (data.contains("FZ")){
				condition += "Supercooled ";
				weather_condition.add(condition);}
			
			else {}
		}
		
		weatherCondition = condition;
	}
	
	
	// PRESSAO BAROMETRICA
	public void GetPressure (ArrayList<String> campo)
	{
		String pressao = campo.get(0);
		String finalPressure = "";
		if (pressao.contains("Q"))
			finalPressure = pressao.substring(1) + " mmHg";
		else if (pressao.contains("A"))
			finalPressure = pressao.substring(1,3) +"." + pressao.substring(3,5) + " inHg";
		
		pressure = finalPressure;
	}
	
	public MetarInformationObject getInformation() {
		MetarInformationObject metarInformation =
			new MetarInformationObject(day, time, temperature, dewPoint,
					weatherCondition, pressure, cloudLayers, windData);
		
		return metarInformation;
	}
}


	
