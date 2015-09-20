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
import org.pi4.locutil.PositioningError;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class Empirical_FP_kNN {
	public static void main(String[] args) {
		System.out.println("start!");
		int largeSize = 100;
		for(int penis = 0 ; penis < largeSize ; penis++)
			{
			TraceGenerator tg = Helpers.createTraceGenerator(25, 5);
			tg.generate();
			Helpers mapBuilder = new Helpers();
			List<TraceEntry> onlineTrace = tg.getOnline();
			List<TraceEntry> offlineTrace = tg.getOffline();
			
			HashMap<GeoPosition, Map<MACAddress, Double>> onlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
			for(TraceEntry entry: onlineTrace){			
				HashMap<MACAddress, Double> knownMac = new HashMap<MACAddress, Double>();
				LinkedList<MACAddress> listOfMacInTrace = new LinkedList<MACAddress>();
					listOfMacInTrace.addAll(entry.getSignalStrengthSamples().getSortedAccessPoints());
				Iterator<MACAddress> listIterator = listOfMacInTrace.iterator();
				while(listIterator.hasNext()){
					MACAddress currentMac = listIterator.next();
					knownMac.put(currentMac, entry.getSignalStrengthSamples().getAverageSignalStrength(currentMac));
				}
				onlineMap.put(entry.getGeoPosition(), knownMac);
			}
			
			HashMap<GeoPosition, Map<MACAddress, Double>> offlineMap = new HashMap<GeoPosition, Map<MACAddress, Double>>();
			for(TraceEntry entry: offlineTrace){			
				HashMap<MACAddress, Double> knownMac = new HashMap<MACAddress, Double>();
				
				LinkedList<MACAddress> listOfMacInTrace = new LinkedList<MACAddress>();
					listOfMacInTrace.addAll(entry.getSignalStrengthSamples().getSortedAccessPoints());
				Iterator<MACAddress> listIterator = listOfMacInTrace.iterator();
				while(listIterator.hasNext()){
						MACAddress currentMac = listIterator.next();
						knownMac.put(currentMac, entry.getSignalStrengthSamples().getAverageSignalStrength(currentMac));
				}
				
				offlineMap.put(entry.getGeoPosition(), knownMac);
			}
			
			HashMap<GeoPosition, Map<GeoPosition, Double>> distances = new HashMap<GeoPosition, Map<GeoPosition, Double>>();
			for(Entry<GeoPosition, Map<MACAddress, Double>> value : onlineMap.entrySet()){
				HashMap<GeoPosition, Double> innerDistances = new HashMap<GeoPosition, Double>();
				ArrayList<Double> onlineValues = new ArrayList<Double>();
				ArrayList<MACAddress> knownAP = new ArrayList<MACAddress>();
				knownAP.add(MACAddress.parse("00:11:88:28:5E:E0")); 
				knownAP.add(MACAddress.parse("00:16:B6:B7:5D:8C"));
				knownAP.add(MACAddress.parse("00:14:BF:B1:97:81"));
				knownAP.add(MACAddress.parse("00:14:BF:B1:97:8A"));
				knownAP.add(MACAddress.parse("00:14:BF:3B:C7:C6"));
				knownAP.add(MACAddress.parse("00:14:6C:62:CA:A4"));
				knownAP.add(MACAddress.parse("00:16:B6:B7:5D:9B"));
				knownAP.add(MACAddress.parse("00:14:BF:B1:97:8D"));
				knownAP.add(MACAddress.parse("00:14:BF:B1:7C:57"));
				knownAP.add(MACAddress.parse("00:16:B6:B7:5D:8F"));
				knownAP.add(MACAddress.parse("00:14:BF:B1:7C:54"));
				HashMap<MACAddress, Double> innerOnline = new HashMap<MACAddress, Double>();
					for(Entry<MACAddress, Double> value2 : value.getValue().entrySet())
						{
						innerOnline.put(value2.getKey(), value2.getValue());
						}	
					for(Entry<GeoPosition, Map<MACAddress, Double>> value3 : offlineMap.entrySet())
						{
						int breakValue = 0;
						ArrayList<Double> offlineValues = new ArrayList<Double>();
						for(Entry<MACAddress, Double> value4 : value3.getValue().entrySet()){
							if(knownAP.contains(value4.getKey()))
							{
								offlineValues.add(value4.getValue());
								if(innerOnline.containsKey(value4.getKey()))
									{
										onlineValues.add(innerOnline.get(value4.getKey()));
									}
								else
									{
										onlineValues.add((double) -96);
										breakValue++;
									}
							}
						}
						if(breakValue < 3)
							{
							innerDistances.put(value3.getKey(), Helpers.EuclidianDistanceArray(offlineValues, onlineValues));
							}
						else
							{
							innerDistances.put(value3.getKey(), 10000.0);
							}
						
						onlineValues = new ArrayList<Double>();
						}
				
				innerDistances = sortByValues(innerDistances);
				distances.put(value.getKey(), innerDistances);
				if(!innerDistances.containsValue(10000.0)){
				final Set<Entry<GeoPosition, Double>> mapValues = innerDistances.entrySet();
			    final int maplength = mapValues.size();
			    final Entry<GeoPosition,Double>[] test = new Entry[maplength];
			    mapValues.toArray(test);
			    int k = 3;
			    double x = (double) 0, y = (double) 0;
			    	for(int i = 0; i < k ; i++)
			    		{
			    			x = x + test[i].getKey().getX();
			    			y = y + test[i].getKey().getY();
			    		}
			    		GeoPosition average = new GeoPosition();
			    		x = x/k;
			    		y = y/k;
			    		average.setX(x);
					    average.setY(y);
					    average.setZ(0);
					    System.out.println(value.getKey().distance(average));
				}
			}
		}
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
