package assignments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.lang.Double;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class scoreNN {
	static Double medianError;
	static List<Double> list = new ArrayList<Double>();
	static ArrayList<String> listErrors = new ArrayList<String>();
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Please enter name of txt-file with error distances (include .txt) : ");
	        String readFile = null;
	        try {
	            readFile = reader.readLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        System.out.println("You entered : " + readFile);
	        
			readFile(readFile);
			errorPercentages();
			String content = listErrors.toString();

			File file = new File("ErrorScore " + readFile);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			
			System.out.println("Error values added to the document called: ErrorScore " + readFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void readFile(String filename) throws IOException
	{
		Scanner s = new Scanner(new File(filename));
		//s.useDelimiter(", *");
		while (s.hasNextLine()){
			String line = s.nextLine();
			System.out.println(line);
			list.add(Double.parseDouble(line));
		}
		s.close();
		Collections.sort(list);
//		Collections.reverse(list);
	}
	
	public static void errorPercentages(){
		 
		if (list.size() % 2 == 0)
	        {
	            medianError = list.get(list.size()/2);
	        } else {
	            medianError = list.get((list.size()-1)/2);
	        }
		listErrors.add("Errorvalues are:" + list.toString());
		listErrors.add(System.lineSeparator() + "Best error distance: " + list.get(0) + " meters");
		listErrors.add(System.lineSeparator() + "25% of the errors are under or equal to: " + list.get(list.size()/4) + " meters");
		listErrors.add(System.lineSeparator() + "50% of the errors are under or equal to: " + medianError + " meters");
		listErrors.add(System.lineSeparator() + "67% of the errors are under or equal to: " + list.get((list.size()*67)/100) + " meters");
		listErrors.add(System.lineSeparator() + "75% of the errors are under or equal to: " + list.get((list.size()/4)*3) + " meters");
		listErrors.add(System.lineSeparator() + "Worst error distance: " + list.get(list.size()-1) + " meters");
	}
}
