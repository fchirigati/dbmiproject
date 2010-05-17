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
	public static void main(String[] args) throws SocketException, IOException, InterruptedException {
		// TODO code
		List<String> airports = new ArrayList<String>();
		airports.add("SBGL");
		airports.add("SBGR");
		
		List<String> modificationTimes = null;
		String filesDir = "C:/Users/Fernando Seabra/workspace/FtpClient/files/";
		
		while (true) {
			FtpMetar ftpMetar = new FtpMetar(airports, modificationTimes, filesDir);
			//System.out.println(ftpMetar.getDownloadAirports());
			//System.out.println(modificationTimes);
			
			DataSender dataSender = new DataSender(ftpMetar.getDownloadAirports(), filesDir);
			Thread.sleep(10000);
		}
	}

}
