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
		List<Double> ComparisonList = new ArrayList<Double>();
		List<Double> RadioMap = new ArrayList<Double>();
		
		RadioMap.addAll(Helpers.radioMap());
		
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
		
		Double StrengthAP1, StrengthAP2, StrengthAP3, StrengthAP4, StrengthAP5, StrengthAP6, StrengthAP7, StrengthAP8, StrengthAP9, StrengthAP10, StrengthAP11;  
		
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
			List<TraceEntry> onlineTrace = tg.getOnline();			
			for(TraceEntry entry: onlineTrace) {
				String CurrentList = entry.getSignalStrengthSamples().getSortedAccessPoints().toString();
				if(CurrentList.contains(AP1)){
					StrengthAP1 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP1));
				}
				else{StrengthAP1 = (double) -96;}
				
				if(CurrentList.contains(AP2)){
					StrengthAP2 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP2));
				}
				else{StrengthAP2 = (double) -96;}
				
				if(CurrentList.contains(AP3)){
					StrengthAP3 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP3));
				}
				else{StrengthAP3 = (double) -96;}
				
				if(CurrentList.contains(AP4)){
					StrengthAP4 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP4));
				}
				else{StrengthAP4 = (double) -96;}
				
				if(CurrentList.contains(AP5)){
					StrengthAP5 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP5));
				}
				else{StrengthAP5 = (double) -96;}
				
				if(CurrentList.contains(AP6)){
					StrengthAP6 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP6));
				}
				else{StrengthAP6 = (double) -96;}
				
				if(CurrentList.contains(AP7)){
					StrengthAP7 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP7));
				}
				else{StrengthAP7 = (double) -96;}
				
				if(CurrentList.contains(AP8)){
					StrengthAP8 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP8));
				}
				else{StrengthAP8 = (double) -96;}
				
				if(CurrentList.contains(AP9)){
					StrengthAP9 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP9));
				}
				else{StrengthAP9 = (double) -96;}
				
				if(CurrentList.contains(AP10)){
					StrengthAP10 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP10));
				}
				else{StrengthAP10 = (double) -96;}
				
				if(CurrentList.contains(AP11)){
					StrengthAP11 = entry.getSignalStrengthSamples().getAverageSignalStrength(MACAddress.parse(AP11));
				}
				else{StrengthAP11 = (double) -96;}
				
				ComparisonList.add(StrengthAP1);
				ComparisonList.add(StrengthAP2);
				ComparisonList.add(StrengthAP3);
				ComparisonList.add(StrengthAP4);
				ComparisonList.add(StrengthAP5);
				ComparisonList.add(StrengthAP6);
				ComparisonList.add(StrengthAP7);
				ComparisonList.add(StrengthAP8);
				ComparisonList.add(StrengthAP9);
				ComparisonList.add(StrengthAP10);
				ComparisonList.add(StrengthAP11);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int lower1 = 0;
		int upper1 = 11;
		int lower2 = 2;
		int upper2 = 13;
		int counter = 25;
		int counter2 = 25;
		double distance, bestDistance = 200;
		double bestX = 0, bestY = 0;
		while(counter > 0){
			while(counter2 > 0){
				distance = Helpers.EuclidianDistanceAll(ComparisonList.subList(lower1, upper1), RadioMap.subList(lower2, upper2));
				lower2 = lower2 + 11;
				upper2 = lower2 + 11;
				System.out.println(distance);
				if(distance <= bestDistance){
					bestDistance = distance;
					bestX = RadioMap.get(lower2-2);
					bestY = RadioMap.get(lower2-1);
					}
				counter2 --;
			}
			lower1 = lower1 + 11;
			upper1 = upper1 + 11;
			counter--;
			lower2 = 0;
			upper2 = 11;
			counter2 = 25;
			System.out.println("BestDistance: " + bestDistance);
			System.out.println("Current location?: " + bestX + bestY);
			bestDistance = 200;
		}
	}
}