package assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.SignalStrengthSamples;
import org.pi4.locutil.trace.TraceEntry;

public class Helpers {
	public Map<GeoPosition, Map<MACAddress, ArrayList<Double>>> radioMap;
	
	public Helpers(){
		radioMap = new HashMap<GeoPosition, Map<MACAddress, ArrayList<Double>>>();
		ArrayList<Double> ssList = new ArrayList<Double>();
		}
	
	public void addMapEntry(TraceEntry entry){
		HashMap<MACAddress, ArrayList<Double>> macMap = new HashMap<MACAddress, ArrayList<Double>>();
		SignalStrengthSamples sss = entry.getSignalStrengthSamples();
		GeoPosition  gp = entry.getGeoPosition();
		
		if(radioMap.containsKey(gp)){
			ArrayList<Double> signalStrengths = new ArrayList<Double>();
			for(MACAddress address : sss.keySet()){
				if(macMap.containsKey(address)){
					ArrayList<Double> ss = radioMap.get(gp).get(address);
					ss.add(sss.getAverageSignalStrength(address));
				}
				else{
			        signalStrengths.add(sss.getAverageSignalStrength(address));
			        macMap.put(address, signalStrengths);
				}
			}
		}

		else{
			ArrayList<Double> signalStrengths = new ArrayList<Double>();
			for(MACAddress address : sss.keySet()){
			    signalStrengths.add(sss.getAverageSignalStrength(address));
			    macMap.put(address, signalStrengths);
				}
			}
		radioMap.put(gp, macMap);
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
	
	public static TraceGenerator createTraceGenerator(int offlineSize, int onlineSize)
	{
		String offlinePath = "data/MU.1.5meters.offline__170009_1.trace", onlinePath = "data/MU.1.5meters.online__170010_1.trace";
		
		//Construct parsers
		File offlineFile = new File(offlinePath);
		Parser offlineParser = new Parser(offlineFile);
		
		File onlineFile = new File(onlinePath);
		Parser onlineParser = new Parser(onlineFile);
		
		//Construct trace generator
		TraceGenerator tg = null;
		try {
			tg = new TraceGenerator(offlineParser, onlineParser,offlineSize,onlineSize);
				
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tg;
	}
	
}
