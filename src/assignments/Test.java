package assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		/*for(TraceEntry entry: offlineTrace) {	
			mapBuilder.addMapEntry(entry);
		}*/
		
		//
		HashMap<GeoPosition, Map<MACAddress, Double>> onlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
		for(TraceEntry entry: onlineTrace){
			for(MACAddress address: entry.getSignalStrengthSamples().getSortedAccessPoints()){
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
				
				if(mapUsingMac.containsKey(address)){
					mapUsingMac.put(address, entry.getSignalStrengthSamples().getAverageSignalStrength(address));
				}
				onlineMap.put(entry.getGeoPosition(), mapUsingMac);
			}
		}
		
		HashMap<GeoPosition, Map<MACAddress, Double>> offlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
		for(TraceEntry entry: offlineTrace){
			for(MACAddress address: entry.getSignalStrengthSamples().getSortedAccessPoints()){
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
				
				if(mapUsingMac.containsKey(address)){
					mapUsingMac.put(address, entry.getSignalStrengthSamples().getAverageSignalStrength(address));
				}
				offlineMap.put(entry.getGeoPosition(), mapUsingMac);
			}
		}
		
		for(Entry<GeoPosition, Map<MACAddress, Double>> value : onlineMap.entrySet()){
			ArrayList<Double> onlineValues = new ArrayList<Double>();
			for(Entry<MACAddress, Double> value2 : value.getValue().entrySet()){
				onlineValues.add(value2.getValue());
			}
			for(Entry<GeoPosition, Map<MACAddress, Double>> value3 : offlineMap.entrySet()){
				ArrayList<Double> offlineValues = new ArrayList<Double>();
				for(Entry<MACAddress, Double> value4 : value3.getValue().entrySet()){
					offlineValues.add(value4.getValue());
					}
				System.out.println(Helpers.EuclidianDistanceAll(onlineValues, offlineValues));
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
