package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day4.txt"));
		
		ArrayList<String> wordSearch = new ArrayList<String>();
		
		while (in.hasNextLine()) {
			wordSearch.add(in.nextLine());
		}
		
		int result = 0;
		
		for (int i = 0; i < wordSearch.get(0).length(); i++) {
			for (int j = 0; j < wordSearch.size(); j++) {
				result += checkPositionPart1(wordSearch, i, j);
			}
		}
		
		System.out.printf("Part 1: %d\n", result);
		
		result = 0;
		for (int i = 1; i < wordSearch.get(0).length() - 1; i++) {
			for (int j = 1; j < wordSearch.size() -1; j++) {
				if (checkPositionPart2(wordSearch, i, j)) result++;
			}
		}
		
		System.out.printf("Part 2: %d\n", result);
		
		
		in.close();

	}
	
	//Check from the middle 'A'. Don't check edges
	public static boolean checkPositionPart2(ArrayList<String> wordSearch, int i, int j) {
		
		if (wordSearch.get(j).charAt(i) != 'A') return false;
		
		// \ diagonal
		boolean backslash = 
				(wordSearch.get(j-1).charAt(i-1) == 'M' && wordSearch.get(j+1).charAt(i+1) == 'S')
				||
				(wordSearch.get(j-1).charAt(i-1) == 'S' && wordSearch.get(j+1).charAt(i+1) == 'M');
		
		// / diagonal
		boolean forwardSlash =
				(wordSearch.get(j-1).charAt(i+1) == 'M' && wordSearch.get(j+1).charAt(i-1) == 'S')
				||
				(wordSearch.get(j-1).charAt(i+1) == 'S' && wordSearch.get(j+1).charAt(i-1) == 'M');
		
		return backslash && forwardSlash;
		
	}
	
	
	
	public static int checkPositionPart1(ArrayList<String> wordSearch, int i, int j) {
		
		int width = wordSearch.get(0).length();
		int height = wordSearch.size();
		
		int result = 0;
		
		//Check bounds first, then check if XMAS appears
		if (i + 3 < width) { //RIGHT
			String test = wordSearch.get(j).substring(i, i+4);
			if (test.equals("XMAS")) result++;
		}
		if (i - 3 >= 0) { //LEFT
			String test = wordSearch.get(j).substring(i-3, i+1);
			if (test.equals("SAMX")) result++;
		}
		if (j - 3 >= 0) { //UP
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j-3).charAt(i);
			testArr[1] = wordSearch.get(j-2).charAt(i);
			testArr[2] = wordSearch.get(j-1).charAt(i);
			testArr[3] = wordSearch.get(j).charAt(i);
			
			String test = new String(testArr);
			
			if (test.equals("SAMX")) result++;
		}
		if (j + 3 < height) { //DOWN
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j).charAt(i);
			testArr[1] = wordSearch.get(j+1).charAt(i);
			testArr[2] = wordSearch.get(j+2).charAt(i);
			testArr[3] = wordSearch.get(j+3).charAt(i);
			
			String test = new String(testArr);
			
			if (test.equals("XMAS")) result++;
		}
		if (j + 3 < height && i + 3 < width) { //DIAG - DOWN and RIGHT
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j).charAt(i);
			testArr[1] = wordSearch.get(j+1).charAt(i+1);
			testArr[2] = wordSearch.get(j+2).charAt(i+2);
			testArr[3] = wordSearch.get(j+3).charAt(i+3);
			
			String test = new String(testArr);
			
			if (test.equals("XMAS")) result++;
		}
		if (j + 3 < height && i - 3 >= 0) { //DIAG - DOWN and LEFT
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j).charAt(i);
			testArr[1] = wordSearch.get(j+1).charAt(i-1);
			testArr[2] = wordSearch.get(j+2).charAt(i-2);
			testArr[3] = wordSearch.get(j+3).charAt(i-3);
			
			String test = new String(testArr);
			
			if (test.equals("XMAS")) result++;
		}
		if (j - 3 >= 0 && i + 3 < width) { //DIAG - UP and RIGHT
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j-3).charAt(i);
			testArr[1] = wordSearch.get(j-2).charAt(i+1);
			testArr[2] = wordSearch.get(j-1).charAt(i+2);
			testArr[3] = wordSearch.get(j).charAt(i+3);
			
			String test = new String(testArr);
			
			if (test.equals("SAMX")) result++;
		}
		if (j - 3 >= 0 && i - 3 >= 0) { //DIAG - UP and LEFT
			char[] testArr = new char[4];
			testArr[0] = wordSearch.get(j-3).charAt(i);
			testArr[1] = wordSearch.get(j-2).charAt(i-1);
			testArr[2] = wordSearch.get(j-1).charAt(i-2);
			testArr[3] = wordSearch.get(j).charAt(i-3);
			
			String test = new String(testArr);
			
			if (test.equals("SAMX")) result++;
		}
		
		
		return result;
		
	}

}
