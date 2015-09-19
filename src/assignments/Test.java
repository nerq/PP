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
import java.util.Set;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class Test {
	public static void main(String[] args) {
		Double avgError = (double) 0;
		System.out.println("start!");
		int largeSize = 5;
		for(int penis = 0 ; penis < largeSize ; penis++){
		TraceGenerator tg = Helpers.createTraceGenerator(25, 5);
		tg.generate();
		Helpers mapBuilder = new Helpers();
		List<TraceEntry> onlineTrace = tg.getOnline();
		List<TraceEntry> offlineTrace = tg.getOffline();
		
		final Double defaultSignal = (double) 0;
		
		HashMap<GeoPosition, Map<MACAddress, Double>> onlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
		for(TraceEntry entry: onlineTrace){			
			HashMap<MACAddress, Double> knownMac = new HashMap<MACAddress, Double>(){{
				put(MACAddress.parse("00:11:88:28:5E:E0"), defaultSignal); 
				put(MACAddress.parse("00:16:B6:B7:5D:8C"), defaultSignal);
				put(MACAddress.parse("00:14:BF:B1:97:81"), defaultSignal);
				put(MACAddress.parse("00:14:BF:B1:97:8A"), defaultSignal);
				put(MACAddress.parse("00:14:BF:3B:C7:C6"), defaultSignal);
				put(MACAddress.parse("00:14:6C:62:CA:A4"), defaultSignal);
				put(MACAddress.parse("00:16:B6:B7:5D:9B"), defaultSignal);
				put(MACAddress.parse("00:14:BF:B1:97:8D"), defaultSignal);
				put(MACAddress.parse("00:14:BF:B1:7C:57"), defaultSignal);
				put(MACAddress.parse("00:16:B6:B7:5D:8F"), defaultSignal);
				put(MACAddress.parse("00:14:BF:B1:7C:54"), defaultSignal);
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
		
		HashMap<GeoPosition, Map<GeoPosition, Double>> distances = new HashMap<GeoPosition, Map<GeoPosition, Double>>();
		for(Entry<GeoPosition, Map<MACAddress, Double>> value : onlineMap.entrySet()){
			ArrayList<Double> onlineValues = new ArrayList<Double>();
			for(Entry<MACAddress, Double> value2 : value.getValue().entrySet()){
				onlineValues.add(value2.getValue());
			}
			HashMap<GeoPosition, Double> innerDistances = new HashMap<GeoPosition, Double>();
			for(Entry<GeoPosition, Map<MACAddress, Double>> value3 : onlineMap.entrySet()){
				Double estimatedValues = null;
				for(Entry<MACAddress, Double> value4 : value3.getValue().entrySet()){
					Double Pd0 = (double) 1;
					Double signalStrength = value4.getValue();
					Double n = 3.415;
					Double d = value.getKey().distance(value3.getKey());
					Double calculatedModelDistance;
					calculatedModelDistance = Pd0 * signalStrength - 10*n*Math.log(d/Pd0);
					estimatedValues = calculatedModelDistance;
				}
				innerDistances.put(value3.getKey(), estimatedValues);
			}
			innerDistances = sortByValues(innerDistances);
			distances.put(value.getKey(), innerDistances);
			final Set<Entry<GeoPosition, Double>> mapValues = innerDistances.entrySet();
		    final int maplength = mapValues.size();
		    final Entry<GeoPosition,Double>[] test = new Entry[maplength];
		    mapValues.toArray(test);
		    int k = 2;
		    double x = (double) 0, y = (double) 0;
		    for(int i = 0; i < k ; i++)
		    {
		    	x = x + test[k].getKey().getX();
		    	y = y + test[k].getKey().getY();
		    }
		    GeoPosition average = new GeoPosition();
		    x = x/k;
		    y = y/k;
		    average.setX(x);
		    average.setY(y);
		    average.setZ(0);
		    avgError = average.distance(value.getKey());
		}
		System.out.println(avgError/distances.size());
	};
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
