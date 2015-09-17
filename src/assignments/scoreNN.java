package assignments;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class scoreNN {
	static String medianError;
	static ArrayList<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		try {
			readFile("C:/hvadendNicklaver.txt");
			fixList();
			errorPercentages();
			String content = list.toString();

			File file = new File("C:/EmpFPNN.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void readFile(String filename) throws IOException
	{
		Scanner s = new Scanner(new File(filename));
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		Collections.sort(list);
		Collections.reverse(list);
	}
	public static void fixList(){
		Collections.sort(list);
		Collections.reverse(list);
	}
	public static void errorPercentages(){
		 
		if (list.size() % 2 == 0)
	        {
	            medianError = list.get(list.size()/2);
	        } else {
	            medianError = list.get((list.size()-1)/2);
	        }
		list.add(System.lineSeparator() + "25% of the errors is under or equal to: " + list.get(list.size()/4));
		list.add(System.lineSeparator() + "50% of the errors is under or equal to: " + medianError);
		list.add(System.lineSeparator() + "75% of the errors is under or equal to: " + list.get((list.size()/4)*3));
		System.out.println("Error values added to document!");
	}
}
