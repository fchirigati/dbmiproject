package ftpclient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * FtpMetar downloads METAR files using ftp.
 * @author Fernando Seabra
 */
public class FtpMetar {
	
	/**
	 * List of airports whose METAR files were downloaded.
	 * This means that those METAR files had time modifications.
	 */
	private List<String> downloadAirports = new ArrayList<String>();

	/**
	 * Ftp client to download the content.
	 */
	private FTPClient ftpClient = new FTPClient();
	
	/**
	 * Constructor of FtpMetar.
	 */
	public FtpMetar(List<String> airports, List<String> modificationTimes,
			String downloadDir) throws SocketException, IOException {
		System.out.println("Connecting...");
		connect();
		
		System.out.println("Downloading...");
		download(airports, modificationTimes, downloadDir);
		
		System.out.println("Disconnecting...");
		disconnect();
	}
	
	/**
	 * Getter for downloadAirports.
	 */
	public List<String> getDownloadAirports() {
		return downloadAirports;
	}
	
	/**
	 * Connects to ftp server.
	 */
	private void connect() throws SocketException, IOException {
		try {
			ftpClient.connect("tgftp.nws.noaa.gov");
			
			ftpClient.login("anonymous", null);
			
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			
			ftpClient.changeWorkingDirectory("data/observations/metar/stations");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Downloads the content.
	 */
	private void download(List<String> airports, List<String> modificationTimes,
			String downloadDir) throws IOException {
		if (modificationTimes == null) {
			modificationTimes = new ArrayList<String>();
			for (int i = 0; i < airports.size(); i++) {
				try {
					downloadAirports.add(airports.get(i));
//					modificationTimes.add(ftpClient.getModificationTime(
//							airports.get(i) + ".TXT"));
//					System.out.println(airports.get(i) + ".txt -> ");
//					System.out.println("Last modification: " +
//							ftpClient.getModificationTime(airports.get(i) + ".TXT")
//							+ "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			for (int i = 0; i < airports.size(); i++) {
				try {
					String modificationTime = ftpClient.getModificationTime(
							airports.get(i) + ".TXT");
					System.out.println(airports.get(i) + ".txt -> ");
					System.out.println("Last modification: " + modificationTime +
							"\n");
					if (modificationTime != modificationTimes.get(i)) {
						downloadAirports.add(airports.get(i));
						modificationTimes.set(i, modificationTime);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0; i < downloadAirports.size(); i++) {
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(
						downloadDir + downloadAirports.get(i) + ".txt");
				if (ftpClient.retrieveFile(downloadAirports.get(i) + ".TXT",
						fileOutputStream)) {
					System.out.println(downloadAirports.get(i) + ".txt -> ");
					System.out.println("Download successfully done!\n");
				    fileOutputStream.close();
				} else {
					System.out.println(downloadAirports.get(i) + ".txt -> ");
					System.out.println("Erro in downloading the file. I am sorry.\n");
				    fileOutputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Disconnects from ftp server.
	 */
	private void disconnect() {
		try {
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
