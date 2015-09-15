package assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class Test {
	public static void main(String[] args) {
		System.out.println("start!");
		TraceGenerator tg = Helpers.createTraceGenerator(25, 5);
		tg.generate();
		Helpers mapBuilder = new Helpers();
		List<TraceEntry> onlineTrace = tg.getOnline();
		List<TraceEntry> offlineTrace = tg.getOffline();
		
		//Create offline map
		for(TraceEntry entry: offlineTrace) {	
			mapBuilder.addMapEntry(entry);
		}
		
		//
		for(TraceEntry entry: onlineTrace){
			Double SSAP1, SSAP2, SSAP3, SSAP4, SSAP5, SSAP6, SSAP7, SSAP8, SSAP9, SSAP10, SSAP11;
			ArrayList<Double> onlineSS = new ArrayList<Double>();
			HashMap<MACAddress, Double> mapUsingMac = new HashMap<MACAddress, Double>();
			
			mapUsingMac.put(MACAddress.parse("00:14:BF:B1:7C:54"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:16:B6:B7:5D:8F"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:BF:B1:7C:57"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:BF:B1:97:8D"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:16:B6:B7:5D:9B"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:6C:62:CA:A4"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:BF:3B:C7:C6"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:BF:B1:97:8A"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:14:BF:B1:97:81"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:16:B6:B7:5D:8C"), (double) -100);
			mapUsingMac.put(MACAddress.parse("00:11:88:28:5E:E0"), (double) -100);
			
			String AP1 = "00:14:BF:B1:7C:54";
			String AP2 = "00:16:B6:B7:5D:8F";
			String AP3 = "00:14:BF:B1:7C:57";
			String AP4 = "00:14:BF:B1:97:8D";
			String AP5 = "00:16:B6:B7:5D:9B";
			String AP6 = "00:14:6C:62:CA:A4";
			String AP7 = "00:14:BF:3B:C7:C6";
			String AP8 = "00:14:BF:B1:97:8A";
			String AP9 = "00:14:BF:B1:97:81";
			String AP10 = "00:16:B6:B7:5D:8C";
			String AP11 = "00:11:88:28:5E:E0";
			
			for(MACAddress address: entry.getSignalStrengthSamples().getSortedAccessPoints()){
				if(mapUsingMac.containsKey(address)){
					mapUsingMac.put(address, entry.getSignalStrengthSamples().getAverageSignalStrength(address));
					System.out.println(mapUsingMac);
				}
			}
		}
		//tag et online entry
			//sorter for signalstyrker (tilføj -100 til dem der mangler)
		//hent ét offline entry
			//sorter for signalstyrker (tilføj -100 til dem der mangler)
		//lav matematik
			//gem geopos + distance i HashMap<Double,GeoPos>
		//REPEAT for alle online entries
		//Sort efter HashMap efter distance og pluk k antal ud. 
		//lav k -nearest formel, hvis k>1
	}
		
}
