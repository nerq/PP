package assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
			HashMap<MACAddress, Double> knownMac = new HashMap<MACAddress, Double>(){{
				put(MACAddress.parse("00:11:88:28:5E:E0"), (double) -100); 
				put(MACAddress.parse("00:16:B6:B7:5D:8C"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:81"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:8A"), (double) -100);
				put(MACAddress.parse("00:14:BF:3B:C7:C6"), (double) -100);
				put(MACAddress.parse("00:14:6C:62:CA:A4"), (double) -100);
				put(MACAddress.parse("00:16:B6:B7:5D:9B"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:8D"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:7C:57"), (double) -100);
				put(MACAddress.parse("00:16:B6:B7:5D:8F"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:7C:54"), (double) -100);
			}};
			LinkedList<MACAddress> listOfMacInTrace = new LinkedList<MACAddress>();
				listOfMacInTrace.addAll(entry.getSignalStrengthSamples().getSortedAccessPoints());
			Iterator<MACAddress> listIterator = listOfMacInTrace.iterator();
			while(listIterator.hasNext()){
					MACAddress currentMac = listIterator.next();
					if(knownMac.containsKey(currentMac)){
						knownMac.put(currentMac, entry.getSignalStrengthSamples().getAverageSignalStrength(currentMac));
					}
			}
			onlineMap.put(entry.getGeoPosition(), knownMac);
		}
		
		HashMap<GeoPosition, Map<MACAddress, Double>> offlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
		for(TraceEntry entry: offlineTrace){			
			HashMap<MACAddress, Double> knownMac = new HashMap<MACAddress, Double>(){{
				put(MACAddress.parse("00:11:88:28:5E:E0"), (double) -100); 
				put(MACAddress.parse("00:16:B6:B7:5D:8C"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:81"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:8A"), (double) -100);
				put(MACAddress.parse("00:14:BF:3B:C7:C6"), (double) -100);
				put(MACAddress.parse("00:14:6C:62:CA:A4"), (double) -100);
				put(MACAddress.parse("00:16:B6:B7:5D:9B"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:97:8D"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:7C:57"), (double) -100);
				put(MACAddress.parse("00:16:B6:B7:5D:8F"), (double) -100);
				put(MACAddress.parse("00:14:BF:B1:7C:54"), (double) -100);
			}};
			LinkedList<MACAddress> listOfMacInTrace = new LinkedList<MACAddress>();
				listOfMacInTrace.addAll(entry.getSignalStrengthSamples().getSortedAccessPoints());
			Iterator<MACAddress> listIterator = listOfMacInTrace.iterator();
			while(listIterator.hasNext()){
					MACAddress currentMac = listIterator.next();
					if(knownMac.containsKey(currentMac)){
						knownMac.put(currentMac, entry.getSignalStrengthSamples().getAverageSignalStrength(currentMac));
					}
			}
			offlineMap.put(entry.getGeoPosition(), knownMac);
		}
		
		HashMap<GeoPosition, Map<GeoPosition, Double>> distances = new HashMap<GeoPosition, Map<GeoPosition, Double>>();
		for(Entry<GeoPosition, Map<MACAddress, Double>> value : onlineMap.entrySet()){
			ArrayList<Double> onlineValues = new ArrayList<Double>();
			for(Entry<MACAddress, Double> value2 : value.getValue().entrySet()){
				onlineValues.add(value2.getValue());
			}
			HashMap<GeoPosition, Double> innerDistances = new HashMap<GeoPosition, Double>();
			for(Entry<GeoPosition, Map<MACAddress, Double>> value3 : offlineMap.entrySet()){
				ArrayList<Double> offlineValues = new ArrayList<Double>();
				for(Entry<MACAddress, Double> value4 : value3.getValue().entrySet()){
					offlineValues.add(value4.getValue());
					}
				innerDistances.put(value3.getKey(), Helpers.EuclidianDistanceAll(onlineValues, offlineValues));
				}
			innerDistances = sortByValues(innerDistances);
			distances.put(value.getKey(), innerDistances);
		}
		
		System.out.println(distances.values().iterator().next());
		
	}
	
	private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue())
	                  .compareTo(((Map.Entry) (o2)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
		
}
