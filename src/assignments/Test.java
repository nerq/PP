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
		
		for(TraceEntry entry: offlineTrace) {
			MACAddress AP1 = MACAddress.parse("00:14:BF:B1:7C:54");
			MACAddress AP2 = MACAddress.parse("00:16:B6:B7:5D:8F");
			MACAddress AP3 = MACAddress.parse("00:14:BF:B1:7C:57");
			MACAddress AP4 = MACAddress.parse("00:14:BF:B1:97:8D");
			MACAddress AP5 = MACAddress.parse("00:16:B6:B7:5D:9B");
			MACAddress AP6 = MACAddress.parse("00:14:6C:62:CA:A4");
			MACAddress AP7 = MACAddress.parse("00:14:BF:3B:C7:C6");
			MACAddress AP8 = MACAddress.parse("00:14:BF:B1:97:8A");
			MACAddress AP9 = MACAddress.parse("00:14:BF:B1:97:81");
			MACAddress AP10 = MACAddress.parse("00:16:B6:B7:5D:8C");
			MACAddress AP11 = MACAddress.parse("00:11:88:28:5E:E0");
			Double SSAP1, SSAP2, SSAP3, SSAP4, SSAP5, SSAP6, SSAP7, SSAP8, SSAP9, SSAP10, SSAP11;
			
			mapBuilder.addMapEntry(entry);
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
		System.out.println(mapBuilder.radioMap);
	}
		
}
