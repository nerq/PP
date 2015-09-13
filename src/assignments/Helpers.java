package assignments;

import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;

public class Helpers {

	public static double EuclidianDistance(double ss1, double e1, double ss2, double e2, double ss3, double e3) 
	{
		return Math.sqrt(Math.pow(ss1 - e1,2) + Math.pow(ss2 - e2,2) + Math.pow(ss3 - e3, 2));
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
