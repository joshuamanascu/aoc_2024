package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day3.txt"));
		
		StringBuilder inputBuilder = new StringBuilder();
		
		while (in.hasNext()) {
			inputBuilder.append(in.next());
		}
		
		String input = inputBuilder.toString();
		
		System.out.printf("Part 1: %d\n", part1(input));
		System.out.printf("Part 2: %d\n", part2(input));
		
		in.close();
	}
	
	public static int part2(String input) {
		Pattern p = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)"); // 
		Matcher m = p.matcher(input);
		
		ArrayList<String> results = new ArrayList<String>();
		
		int result = 0;
		boolean enabled = true;
		
		while (m.find()) {
			String match = m.group();
			
			if (match.equals("do()")) enabled = true;
			else if (match.equals("don't()")) enabled = false;
			else if (enabled) {
				results.add(match);
			}
			
			
		}
		
		p = Pattern.compile("\\d+");
		
		for (String s: results) {
			m = p.matcher(s);
			
			m.find();
			int num1 = Integer.valueOf(m.group());
			
			m.find();
			int num2 = Integer.valueOf(m.group());
			
			result += num1*num2;
		}
		
		return result;
		
	}
	
	
	public static int part1(String input) {
		Pattern p = Pattern.compile("mul\\(\\d+,\\d+\\)"); // mul(\d,\d)
		Matcher m = p.matcher(input);
		
		ArrayList<String> results = new ArrayList<String>();
		
		int result = 0;
		
		while (m.find()) {
			results.add(m.group());
		}
		
		p = Pattern.compile("\\d+");
		
		for (String s: results) {
			m = p.matcher(s);
			
			m.find();
			int num1 = Integer.valueOf(m.group());
			
			m.find();
			int num2 = Integer.valueOf(m.group());
			
			result += num1*num2;
		}
		
		return result;
		
	}
	
	
}
