package dbmi_server.database;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

public class Database {
	
	public void init() {
		System.out.println("Database Init");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		List<Country> countries = new ArrayList<Country>();
		
		/**
		 * United States
		 */
		Country countryUSA = new Country("1", "Estados Unidos");
		countryUSA.getAirports().add(new Airport("KJFK", "Aeroporto Internacional JFK, Nova York", new MetarInformation()));
		countryUSA.getAirports().add(new Airport("KLAX", "Aeroporto Internacional de Los Angeles, Los Angeles", new MetarInformation()));
		countryUSA.getAirports().add(new Airport("KORD", "Aeroporto Internacional de O'Hare, Chicago", new MetarInformation()));
		countryUSA.getAirports().add(new Airport("KMCO", "Aeroporto Internacional de Orlando, Orlando", new MetarInformation()));
		countryUSA.getAirports().add(new Airport("KIAH", "Aeroporto Intercontinental George Bush, Houston", new MetarInformation()));
		countries.add(countryUSA);
		
		/**
		 * Spain
		 */
		Country countrySpain = new Country("2", "Espanha");
		countrySpain.getAirports().add(new Airport("LEMD", "Aeroporto Internacional Madrid-Barajas, Madrid", new MetarInformation()));
		countrySpain.getAirports().add(new Airport("LEBL", "Aeroporto Internacional de Barcelona, Barcelona", new MetarInformation()));
		countries.add(countrySpain);
		
		/**
		 * France
		 */
		Country countryFrance = new Country("3", "França");
		countryFrance.getAirports().add(new Airport("LFPG", "Aeroporto Paris-Charles de Gaulle, Paris", new MetarInformation()));
		countryFrance.getAirports().add(new Airport("LFPO", "Aeroporto Paris-Orly, Paris", new MetarInformation()));
		countryFrance.getAirports().add(new Airport("LFLL", "Aeroporto Lyon-Saint-Exupéry, Lyon", new MetarInformation()));
		countries.add(countryFrance);
		
		/**
		 * Italy
		 */
		Country countryItaly = new Country("4", "Itália");
		countryItaly.getAirports().add(new Airport("LIRF", "Aeroporto Internacional Leonardo da Vinci-Fiumicino, Roma", new MetarInformation()));
		countryItaly.getAirports().add(new Airport("LIMC", "Aeroporto Milão-Malpensa, Milão", new MetarInformation()));
		countries.add(countryItaly);
		
		/**
		 * China
		 */
		Country countryChina = new Country("5", "China");
		countryChina.getAirports().add(new Airport("ZBAA", "Aeroporto Internacional de Pequim, Pequim", new MetarInformation()));
		countryChina.getAirports().add(new Airport("ZGGG", "Aeroporto Internacional de Guangzhou Baiyun, Guangzhou", new MetarInformation()));
		countryChina.getAirports().add(new Airport("ZSPD", "Aeroporto Internacional de Shanghai Pudong, Shanghai", new MetarInformation()));
		countries.add(countryChina);
		
		/**
		 * United Kingdom
		 */
		Country countryUK = new Country("6", "Reino Unido");
		countryUK.getAirports().add(new Airport("EGLL", "Aeroporto Londres Heathrow, Londres, Inglaterra", new MetarInformation()));
		countryUK.getAirports().add(new Airport("EGKK", "Aeroporto Londres Gatwick, Londres, Inglaterra", new MetarInformation()));
		countryUK.getAirports().add(new Airport("EGPH", "Aeroporto de Edimburgo, Edimburgo, Escócia", new MetarInformation()));
		countries.add(countryUK);
		
		/**
		 * Germany
		 */
		Country countryGermany = new Country("7", "Alemanha");
		countryGermany.getAirports().add(new Airport("EDDF", "Aeroporto Internacional de Frankfurt, Frankfurt", new MetarInformation()));
		countryGermany.getAirports().add(new Airport("EDDM", "Aeroporto Internacional de Munique-Franz Josef Strauss, Munique", new MetarInformation()));
		countries.add(countryGermany);
		
		/**
		 * Australia
		 */
		Country countryAustralia = new Country("8", "Austrália");
		countryAustralia.getAirports().add(new Airport("YSSY", "Aeroporto de Sydney, Sydney", new MetarInformation()));
		countryAustralia.getAirports().add(new Airport("YMML", "Aeroporto de Melbourne, Melbourne", new MetarInformation()));
		countries.add(countryAustralia);
		
		/**
		 * Austria
		 */
		Country countryAustria = new Country("9", "Áustria");
		countryAustria.getAirports().add(new Airport("LOWW", "Aeroporto Internacional de Vienna, Vienna", new MetarInformation()));
		countries.add(countryAustria);
		
		/**
		 * Turkey
		 */
		Country countryTurkey = new Country("10", "Turquia");
		countryTurkey.getAirports().add(new Airport("LTFJ", "Aeroporto Internacional de Sabiha Gökçen, Istambul", new MetarInformation()));
		countries.add(countryTurkey);
		
		/**
		 * Thailand
		 */
		Country countryThailand = new Country("11", "Tailândia");
		countryThailand.getAirports().add(new Airport("VTBS", "Aeroporto Internacional de Suvarnabhumi, Bangkok", new MetarInformation()));
		countries.add(countryThailand);
		
		/**
		 * Greece
		 */
		Country countryGreece = new Country("12", "Grécia");
		countryGreece.getAirports().add(new Airport("LGAV", "Aeroporto Internacional de Atenas, Atenas", new MetarInformation()));
		countries.add(countryGreece);
		
		/**
		 * Canada
		 */
		Country countryCanada = new Country("13", "Canadá");
		countryCanada.getAirports().add(new Airport("CYYZ", "Aeroporto Internacional Toronto Pearson, Toronto", new MetarInformation()));
		countryCanada.getAirports().add(new Airport("CYVR", "Aeroporto Internacional de Vancouver, Vancouver", new MetarInformation()));
		countryCanada.getAirports().add(new Airport("CYUL", "Aeroporto Internacional Montreal-Pierre Elliott Trudeau, Montreal", new MetarInformation()));
		countries.add(countryCanada);
		
		/**
		 * Malaysia
		 */
		Country countryMalaysia = new Country("14", "Malásia");
		countryMalaysia.getAirports().add(new Airport("WMKK", "Aeroporto Internacional de Kuala Lumpur, Kuala Lumpur", new MetarInformation()));
		countries.add(countryMalaysia);
		
		/**
		 * Hong Kong (China)
		 */
		Country countryHongKong = new Country("15", "Hong Kong (China)");
		countryHongKong.getAirports().add(new Airport("VHHH", "Aeroporto Internacional de Hong Kong, Hong Kong", new MetarInformation()));
		countries.add(countryHongKong);
		
		/**
		 * Netherlands
		 */
		Country countryNetherlands = new Country("16", "Holanda");
		countryNetherlands.getAirports().add(new Airport("EHAM", "Aeroporto Amsterdam Schiphol, Amsterdam", new MetarInformation()));
		countries.add(countryNetherlands);
		
		/**
		 * Mexico
		 */
		Country countryMexico = new Country("17", "México");
		countryMexico.getAirports().add(new Airport("MMMX", "Aeroporto Internacional da Cidade do México (Benito Juárez), Cidade do México", new MetarInformation()));
		countryMexico.getAirports().add(new Airport("MMUN", "Aeroporto Internacional de Cancún, Cancún", new MetarInformation()));
		countries.add(countryMexico);
		
		/**
		 * Sweden
		 */
		Country countrySweden = new Country("18", "Suécia");
		countrySweden.getAirports().add(new Airport("ESSA", "Aeroporto Stockholm-Arlanda, Stockholm", new MetarInformation()));
		countrySweden.getAirports().add(new Airport("ESGG", "Aeroporto Göteborg-Landvetter, Gothenburg", new MetarInformation()));
		countries.add(countrySweden);
		
		/**
		 * Switzerland
		 */
		Country countrySwitzerland = new Country("19", "Suíça");
		countrySwitzerland.getAirports().add(new Airport("LSZH", "Aeroporto de Zürich, Zurique", new MetarInformation()));
		countrySwitzerland.getAirports().add(new Airport("LSGG", "Aeroporto Internacional Geneva Cointrin, Genebra", new MetarInformation()));
		countries.add(countrySwitzerland);
		
		/**
		 * India
		 */
		Country countryIndia = new Country("20", "Índia");
		countryIndia.getAirports().add(new Airport("VOMM", "Aeroporto Internacional de Chennai, Chennai", new MetarInformation()));
		countries.add(countryIndia);
		
		/**
		 * Belgium
		 */
		Country countryBelgium = new Country("21", "Bélgica");
		countryBelgium.getAirports().add(new Airport("EBBR", "Aeroporto de Brussels, Bruxelas", new MetarInformation()));
		countryBelgium.getAirports().add(new Airport("EBCI", "Aeroporto Brussels South Charleroi, Charleroi", new MetarInformation()));
		countries.add(countryBelgium);
		
		/**
		 * Poland
		 */
		Country countryPoland = new Country("22", "Polônia");
		countryPoland.getAirports().add(new Airport("EPWA", "Aeroporto Frederic Chopin, Warsaw", new MetarInformation()));
		countries.add(countryPoland);
		
		/**
		 * Portugal
		 */
		Country countryPortugal = new Country("23", "Portugal");
		countryPortugal.getAirports().add(new Airport("LPPT", "Aeroporto da Portela, Lisboa", new MetarInformation()));
		countries.add(countryPortugal);
		
		/**
		 * Macau (China)
		 */
		Country countryMacau = new Country("24", "Macau (China)");
		countryMacau.getAirports().add(new Airport("VMMC", "Aeroporto Internacional de Macau, Taipa Island", new MetarInformation()));
		countries.add(countryMacau);
		
		/**
		 * Tunisia
		 */
		Country countryTunisia = new Country("25", "Tunísia");
		countryTunisia.getAirports().add(new Airport("DTTG", "Aeroporto Gabes-Matmata, Gabes", new MetarInformation()));
		countries.add(countryTunisia);
		
		/**
		 * Japan
		 */
		Country countryJapan = new Country("26", "Japão");
		countryJapan.getAirports().add(new Airport("RJAA", "Aeroporto Internacional de Tokyo, Tokyo", new MetarInformation()));
		countries.add(countryJapan);
		
		/**
		 * Egypt
		 */
		Country countryEgypt = new Country("27", "Egito");
		countryEgypt.getAirports().add(new Airport("HECA", "Aeroporto Internacional do Cairo, Cairo", new MetarInformation()));
		countryEgypt.getAirports().add(new Airport("HESH", "Aeroporto Internacional Sharm el-Sheikh, Sharm El Sheikh", new MetarInformation()));
		countryEgypt.getAirports().add(new Airport("HEGN", "Aeroporto Internacional de Hurghada, Hurghada", new MetarInformation()));
		countries.add(countryEgypt);
		
		/**
		 * Croatia
		 */
		Country countryCroatia = new Country("28", "Croácia");
		countryCroatia.getAirports().add(new Airport("LDZA", "Aeroporto de Zagreb, Zagreb", new MetarInformation()));
		countries.add(countryCroatia);
		
		/**
		 * Singapore
		 */
		Country countrySingapore = new Country("29", "Singapura");
		countrySingapore.getAirports().add(new Airport("WSSS", "Aeroporto Singapore Changi, Changi", new MetarInformation()));
		countries.add(countrySingapore);
		
		/**
		 * South Africa
		 */
		Country countrySouthAfrica = new Country("30", "África do Sul");
		countrySouthAfrica.getAirports().add(new Airport("FAJS", "Aeroporto Internacional OR Tambo, Johannesburg", new MetarInformation()));
		countrySouthAfrica.getAirports().add(new Airport("FACT", "Aeroporto Internacional de Cape Town, Cape Town", new MetarInformation()));
		//countrySouthAfrica.getAirports().add(new Airport("FADN", "Aeroporto Internacional de Durban, Durban", new MetarInformation()));
		countries.add(countrySouthAfrica);
		
		/**
		 * Morocco
		 */
		Country countryMorocco = new Country("31", "Marrocos");
		countryMorocco.getAirports().add(new Airport("GMMN", "Aeroporto Internacional Mohammed V, Casablanca", new MetarInformation()));
		countries.add(countryMorocco);
		
		/**
		 * Czech Republic
		 */
		Country countryCzechRepublic = new Country("32", "República Tcheca");
		countryCzechRepublic.getAirports().add(new Airport("LKPR", "Aeroporto Ruzyne, Praga", new MetarInformation()));
		countries.add(countryCzechRepublic);
		
		/**
		 * Denmark
		 */
		Country countryDenmark = new Country("33", "Dinamarca");
		countryDenmark.getAirports().add(new Airport("EKCH", "Aeroporto de Copenhagen, Copenhagen", new MetarInformation()));
		countries.add(countryDenmark);
		
		/**
		 * Ireland
		 */
		Country countryIreland = new Country("34", "Irlanda");
		countryIreland.getAirports().add(new Airport("EIDW", "Aeroporto de Dublin, Dublin", new MetarInformation()));
		countries.add(countryIreland);
		
		/**
		 * Republic of Korea
		 */
		Country countryKorea = new Country("35", "Republica da Coréia");
		countryKorea.getAirports().add(new Airport("RKSI", "Aeroporto Internacional Incheon, Seoul", new MetarInformation()));
		countries.add(countryKorea);
		
		/**
		 * New Zeland
		 */
		Country countryNewZeland = new Country("36", "Nova Zelândia");
		countryNewZeland.getAirports().add(new Airport("NZAA", "Aeroporto de Auckland, Auckland", new MetarInformation()));
		countries.add(countryNewZeland);
		
		/**
		 * Indonesia
		 */
		Country countryIndonesia = new Country("37", "Indonésia");
		countryIndonesia.getAirports().add(new Airport("WIII", "Aeroporto Internacional Soekarno–Hatta, Jakarta", new MetarInformation()));
		countries.add(countryIndonesia);
		
		/**
		 * Saudi Arabia
		 */
		Country countrySaudiArabia = new Country("38", "Arábia Saudita");
		countrySaudiArabia.getAirports().add(new Airport("OEJN", "Aeroporto Internacional King Abdulaziz, Jeddah", new MetarInformation()));
		countries.add(countrySaudiArabia);
		
		/**
		 * Taiwan (China)
		 */
		Country countryTaiwan = new Country("39", "Taiwan");
		countryTaiwan.getAirports().add(new Airport("RCTP", "Aeroporto Internacional Taiwan Taoyuan, Taoyuan County", new MetarInformation()));
		countries.add(countryTaiwan);
		
		/**
		 * Brazil
		 */
		Country countryBrazil = new Country("40", "Brasil");
		countryBrazil.getAirports().add(new Airport("SBGR", "Aeroporto Internacional de São Paulo-Guarulhos, Guarulhos", new MetarInformation()));
		countryBrazil.getAirports().add(new Airport("SBBR", "Aeroporto Internacional de Brasília - Presidente Juscelino Kubitschek, Brasília", new MetarInformation()));
		countryBrazil.getAirports().add(new Airport("SBGL", "Aeroporto Internacional do Galeão Antonio Carlos Jobim, Rio de Janeiro", new MetarInformation()));
		countryBrazil.getAirports().add(new Airport("SBSV", "Aeroporto Internacional de Salvador - Deputado Luís Eduardo Magalhães, Salvador", new MetarInformation()));
		countryBrazil.getAirports().add(new Airport("SBPA", "Aeroporto Internacional Salgado Filho, Porto Alegre", new MetarInformation()));
		countryBrazil.getAirports().add(new Airport("SBEG", "Aeroporto Internacional de Manaus - Eduardo Gomes, Manaus", new MetarInformation()));
		countries.add(countryBrazil);
		
		/**
		 * Lebanon
		 */
		Country countryLebanon = new Country("41", "Líbano");
		countryLebanon.getAirports().add(new Airport("OLBA", "Aeroporto Internacional Beirut Rafic Hariri, Beirut", new MetarInformation()));
		countries.add(countryLebanon);
		
		/**
		 * United Arab Emirates
		 */
		Country countryUnitedArabEmirates = new Country("42", "Emirados Árabes Unidos");
		countryUnitedArabEmirates.getAirports().add(new Airport("OMAA", "Aeroporto Internacional de Abu Dhabi, Abu Dhabi", new MetarInformation()));
		countryUnitedArabEmirates.getAirports().add(new Airport("OMDB", "Aeroporto Internacional de Dubai, Al Garhoud", new MetarInformation()));
		countries.add(countryUnitedArabEmirates);
		
		/**
		 * Philippines
		 */
		Country countryPhilippines = new Country("43", "Filipinas");
		countryPhilippines.getAirports().add(new Airport("RPLL", "Aeroporto Internacional Ninoy Aquino, Manila ", new MetarInformation()));
		countries.add(countryPhilippines);
		
		/**
		 * Hungary
		 */
		Country countryHungary = new Country("44", "Hungria");
		countryHungary.getAirports().add(new Airport("LHBP", "Aeroporto Internacional de Budapest Ferihegy, Budapest ", new MetarInformation()));
		countries.add(countryHungary);
		
		/**
		 * Ukraine
		 */
		Country countryUkraine = new Country("45", "Ucrânia");
		countryUkraine.getAirports().add(new Airport("UKKK", "Aeroporto Internacional de Kiev (Zhuliany), Kiev ", new MetarInformation()));
		countries.add(countryUkraine);
		
		/**
		 * Norway
		 */
		Country countryNorway = new Country("46", "Noruega");
		countryNorway.getAirports().add(new Airport("ENGM", "Aeroporto Oslo Gardermoen, Oslo ", new MetarInformation()));
		countries.add(countryNorway);
		
		/**
		 * Argentina
		 */
		Country countryArgentina = new Country("47", "Argentina");
		countryArgentina.getAirports().add(new Airport("SABE", "Aeroporto Jorge Newbery, Buenos Aires ", new MetarInformation()));
		countries.add(countryArgentina);
		
		/**
		 * Dominican Republic
		 */
		Country countryDominicanRepublic = new Country("48", "República Dominicana");
		countryDominicanRepublic.getAirports().add(new Airport("MDPC", "Aeroporto Internacional de Punta Cana, Punta Cana ", new MetarInformation()));
		countries.add(countryDominicanRepublic);
		
		/**
		 * Luxembourg
		 */
		Country countryLuxembourg = new Country("49", "Luxemburgo");
		countryLuxembourg.getAirports().add(new Airport("ELLX", "Aeroporto Findel, Luxembourg City ", new MetarInformation()));
		countries.add(countryLuxembourg);
		
		/**
		 * Vietnan
		 */
		Country countryVietnan = new Country("50", "Vietnã");
		countryVietnan.getAirports().add(new Airport("VVTS", "Aeroporto Internacional Tan Son Nhat, Ho Chi Minh City ", new MetarInformation()));
		countries.add(countryVietnan);
				
		try {
			pm.makePersistentAll(countries);
		} finally {
			pm.close();
		}
	}
}
