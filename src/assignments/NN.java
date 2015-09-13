package assignments;

import java.util.*;
import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.Statistics;
import org.pi4.locutil.trace.TraceEntry;


public class NN{

	public static void main(String[] args){
		GeoPosition PositionOfAP = null;
		String MacString = "00:14:BF:B1:7C:54";
		MACAddress Mac = MACAddress.parse(MacString);
		PositionOfAP = Helpers.getGeoPosOfAP(Mac);
		System.out.println(PositionOfAP);
	}
	
}