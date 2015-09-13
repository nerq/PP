package assignments;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class Empirical_FP_NN{

	public static void main(String[] args){
		double ss1, e1, ss2, e2, ss3, e3;
		
		String offlinePath = "data/MU.1.5meters.offline__170009_1.trace";
		String onlinePath = "data/MU.1.5meters.online__170010_1.trace";
		
		//Construct parsers
		File offlineFile = new File(offlinePath);
		Parser offlineParser = new Parser(offlineFile);
		System.out.println("Offline File: " +  offlineFile.getAbsoluteFile());
				
		File onlineFile = new File(onlinePath);
		Parser onlineParser = new Parser(onlineFile);
		System.out.println("Online File: " + onlineFile.getAbsoluteFile());
				
		//Construct trace generator
		TraceGenerator tg;
		try {
			int offlineSize = 25;
			int onlineSize = 5;
			tg = new TraceGenerator(offlineParser, onlineParser,offlineSize,onlineSize);
					
			//Generate traces from parsed files
			tg.generate();
			
			//Iterate the trace generated from the offline file
			List<TraceEntry> offlineTrace = tg.getOffline();			
			for(TraceEntry entry: offlineTrace) {
				//Print out coordinates for the collection point and the number of signal strength samples
				String StrongestAP = entry.getSignalStrengthSamples().getSortedAccessPoints().subList(0, 1).toString()
					.replace("[", "")  //remove the right bracket
					.replace("]", "");  //remove the left bracket
				MACAddress StrongestAPMAC = MACAddress.parse(StrongestAP);
				GeoPosition StrongestAPGeo = Helpers.getGeoPosOfAP(StrongestAPMAC);
				//System.out.println("OFFLINE: " + entry.getGeoPosition().toString() + " - " + entry.getSignalStrengthSamples().getSortedAccessPoints().subList(0, 1));
				System.out.println("");
				System.out.println("Closest AP: " + entry.getSignalStrengthSamples().getSortedAccessPoints().subList(0, 1));
				System.out.println("Location of that AP: " + StrongestAPGeo);
				System.out.println("Our actual location: " + entry.getGeoPosition().toString());
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		GeoPosition PositionOfAP = null;
		String MacString = "00:14:BF:B1:7C:54";
		MACAddress Mac = MACAddress.parse(MacString);
		PositionOfAP = Helpers.getGeoPosOfAP(Mac);
		System.out.println(PositionOfAP);
	}
	
	
	
}