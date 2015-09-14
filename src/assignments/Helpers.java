package assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class Helpers {
	
	public static List<Double> radioMap(){
		List<Double> RadioMap = new ArrayList<Double>();
		
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
			List<TraceEntry> offlineTrace = tg.getOffline();			
			for(TraceEntry entry: offlineTrace) {
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
				
				RadioMap.add(StrengthAP1);
				RadioMap.add(StrengthAP2);
				RadioMap.add(StrengthAP3);
				RadioMap.add(StrengthAP4);
				RadioMap.add(StrengthAP5);
				RadioMap.add(StrengthAP6);
				RadioMap.add(StrengthAP7);
				RadioMap.add(StrengthAP8);
				RadioMap.add(StrengthAP9);
				RadioMap.add(StrengthAP10);
				RadioMap.add(StrengthAP11);
				
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return RadioMap;
	} 
	
	public static double EuclidianDistance(double ss1, double e1, double ss2, double e2, double ss3, double e3){
		return Math.sqrt(Math.pow(ss1 - e1,2) + Math.pow(ss2 - e2,2) + Math.pow(ss3 - e3, 2));
	}
	
	public static double EuclidianDistanceAll(List<Double> array, List<Double> array2){
		Double ss1 = array.get(0);
		Double ss2 = array.get(1);
		Double ss3 = array.get(2);
		Double ss4 = array.get(3);
		Double ss5 = array.get(4);
		Double ss6 = array.get(5);
		Double ss7 = array.get(6);
		Double ss8 = array.get(7);
		Double ss9 = array.get(8);
		Double ss10 = array.get(9);
		Double ss11 = array.get(10);
		Double e1 = array2.get(0);
		Double e2 = array2.get(1);
		Double e3 = array2.get(2);
		Double e4 = array2.get(3);
		Double e5 = array2.get(4);
		Double e6 = array2.get(5);
		Double e7 = array2.get(6);
		Double e8 = array2.get(7);
		Double e9 = array2.get(8);
		Double e10 = array2.get(9);
		Double e11 = array2.get(10);
		
		return Math.sqrt(Math.pow(ss1 - e1,2) + Math.pow(ss2 - e2,2) + Math.pow(ss3 - e3, 2) + Math.pow(ss4 - e4,2) + Math.pow(ss5 - e5,2) + Math.pow(ss6 - e6,2) + Math.pow(ss7 - e7,2) + Math.pow(ss8 - e8,2) + Math.pow(ss9 - e9,2) + Math.pow(ss10 - e10,2) + Math.pow(ss11 - e11,2));
	}
	
	public static GeoPosition getGeoPosOfAP(MACAddress mac){
		//list of all mac addresses
		//# AP Positions in coordinate system drawn in MannheimDatasets.png
		String AP1 = "00:14:BF:B1:7C:54";
		GeoPosition GPAP1 = new GeoPosition(-23.626, -18.596);
		String AP2 = "00:16:B6:B7:5D:8F";
		GeoPosition GPAP2 = new GeoPosition(-10.702, -18.596);
		String AP3 = "00:14:BF:B1:7C:57";
		GeoPosition GPAP3 = new GeoPosition(8.596, -14.62);
		String AP4 = "00:14:BF:B1:97:8D";
		GeoPosition GPAP4 = new GeoPosition(8.538, -9.298);
		String AP5 = "00:16:B6:B7:5D:9B";
		GeoPosition GPAP5 = new GeoPosition(-1.93, -2.749);
		String AP6 = "00:14:6C:62:CA:A4";
		GeoPosition GPAP6 = new GeoPosition(4.035, -0.468);
		String AP7 = "00:14:BF:3B:C7:C6";
		GeoPosition GPAP7 = new GeoPosition(13.333, -2.69);
		String AP8 = "00:14:BF:B1:97:8A";
		GeoPosition GPAP8 = new GeoPosition(21.17, -2.69);
		String AP9 = "00:14:BF:B1:97:81";
		GeoPosition GPAP9 = new GeoPosition(32.398, -2.69);
		String AP10 = "00:16:B6:B7:5D:8C";
		GeoPosition GPAP10 = new GeoPosition(32.573, 13.86);
		String AP11 = "00:11:88:28:5E:E0";
		GeoPosition GPAP11 = new GeoPosition(7.135, 6.023);
		
		GeoPosition GeoPosAP;
		if(mac.equals(MACAddress.parse(AP1))) GeoPosAP = GPAP1;
		else if (mac.equals(MACAddress.parse(AP2))) GeoPosAP = GPAP2;
		else if (mac.equals(MACAddress.parse(AP3))) GeoPosAP = GPAP3;
		else if (mac.equals(MACAddress.parse(AP4))) GeoPosAP = GPAP4;
		else if (mac.equals(MACAddress.parse(AP5))) GeoPosAP = GPAP5;
		else if (mac.equals(MACAddress.parse(AP6))) GeoPosAP = GPAP6;
		else if (mac.equals(MACAddress.parse(AP7))) GeoPosAP = GPAP7;
		else if (mac.equals(MACAddress.parse(AP8))) GeoPosAP = GPAP8;
		else if (mac.equals(MACAddress.parse(AP9))) GeoPosAP = GPAP9;
		else if (mac.equals(MACAddress.parse(AP10))) GeoPosAP = GPAP10;
		else if (mac.equals(MACAddress.parse(AP11))) GeoPosAP = GPAP11;
		else {
			GeoPosAP = null;
			System.out.println("No mac adress found");
		}
		return GeoPosAP;
	}
	
}
