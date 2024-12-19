package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day2.txt"));
		
		int numSafe = 0;
		ArrayList<Integer> report = new ArrayList<Integer>();
		
		while (in.hasNextLine()) {
			report.addAll(Arrays.stream(in.nextLine().split(" ")).
					mapToInt(s -> Integer.valueOf(s)).boxed().toList());
			
			if (isSafe(report)) numSafe++;
			
			report.clear();
		}
		
		System.out.printf("Part 1: %d\n", numSafe);
		
		in = new Scanner(new File("src/aoc_2024/Inputs/day2.txt"));
		
		numSafe = 0;
		report = new ArrayList<Integer>();
		
		while (in.hasNextLine()) {
			report.addAll(Arrays.stream(in.nextLine().split(" ")).
					mapToInt(s -> Integer.valueOf(s)).boxed().toList());
			
			if (dampenerSafe(report)) numSafe++;
			
			report.clear();
		}
		
		System.out.printf("Part 2: %d\n", numSafe);
		
		in.close();

	}
	
	public static boolean dampenerSafe(ArrayList<Integer> report) {
		if (isSafe(report)) return true;
		
		for (int i = 0; i < report.size(); i++) {
			ArrayList<Integer> modifiedReport = new ArrayList<Integer>(report);
			modifiedReport.remove(i);
			
			if (isSafe(modifiedReport)) return true;
		}
		
		return false;
	}
	
	public static boolean isSafe(ArrayList<Integer> report) {
		boolean increasing;
		
		if (report.get(0) > report.get(1)) { //Decreasing
			increasing = false;
		}
		else if (report.get(0) < report.get(1)) { //Increasing
			increasing = true;
		}
		else {
			return false;
		}
		
		for (int i = 0; i < report.size() - 1; i++) {
			int difference = report.get(i) - report.get(i+1);
			
			//If the difference matches the direction
			if (difference < 0 != increasing) {
				return false;
			}
			
			if (Math.abs(difference) < 1 || Math.abs(difference) > 3) {
				return false;
			}
		}
		
		return true;
		
	}

}
