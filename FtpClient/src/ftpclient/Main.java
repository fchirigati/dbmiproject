package ftpclient;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 * @author Fernando Seabra
 */
public class Main {

	/**
	 * Main method.
	 */
	public static void main(String[] args) throws SocketException, IOException,
			InterruptedException {
		List<String> airports = new ArrayList<String>();
		List<String> modificationTimes = null;
		String filesDir = "E:/Documents/Workspace/DBMI/Files/";
		
		//AirportsRequester airportsRequester = new AirportsRequester();
		//airports = airportsRequester.getAirports();
		//System.out.println(airports);
		airports.add("SBGL");
		airports.add("SBGR");
		
		while (true) {
			FtpMetar ftpMetar = new FtpMetar(airports, modificationTimes, filesDir);
			modificationTimes = FtpMetar.modificationTimesList;
			//System.out.println(ftpMetar.getDownloadAirports());
			//System.out.println(modificationTimes);
			
			DataSender dataSender = new DataSender(ftpMetar.getDownloadAirports(),
					filesDir);
			dataSender.sendData();
			Thread.sleep(10);
		}
	}

}
