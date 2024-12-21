package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Day7 {
	
	static String filePath = "src/aoc_2024/Inputs/day7.txt";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filePath));
		
		BigInteger part1 = BigInteger.ZERO;
		
		while (in.hasNextLine()) {
			
			String line[] = in.nextLine().split(":");
			BigInteger value = new BigInteger(line[0]);
			
			BigInteger[] numbers = Arrays.stream(line[1].split(" "))
					.filter(s -> s != "").map(s -> new BigInteger(s)).toArray(BigInteger[]::new);
			
			
			if (checkEquation(value, numbers, new char[] {'+', '*'})) {
				part1 = part1.add(value);
			}
		}
		
		System.out.printf("Part 1: %d\n", part1);
		
		in = new Scanner(new File(filePath));
		
		BigInteger part2 = BigInteger.ZERO;
		
		while (in.hasNextLine()) {
			
			String line[] = in.nextLine().split(":");
			BigInteger value = new BigInteger(line[0]);
			
			BigInteger[] numbers = Arrays.stream(line[1].split(" "))
					.filter(s -> s != "").map(s -> new BigInteger(s)).toArray(BigInteger[]::new);
			
			
			if (checkEquation(value, numbers, new char[] {'+', '*', '|'})) {
				part2 = part2.add(value);
			}
		}
		
		System.out.printf("Part 2: %d\n", part2);
		
		
		in.close();

	}
	
	public static boolean checkEquation(BigInteger value, BigInteger[] numbers, char[] operators) {
		HashSet<BigInteger> answers = new HashSet<BigInteger>();
		answers.add(numbers[0]);
		
		for (int i = 1; i < numbers.length; i++) {
			HashSet<BigInteger> toUse = new HashSet<BigInteger>(answers);
			answers.clear();
			
			for (BigInteger x : toUse) {
				for (char op : operators) {
					switch (op) {
					case '+': {
						answers.add(x.add(numbers[i]));
						break;
					}
					case '*': {
						answers.add(x.multiply(numbers[i]));
						break;
					}
					case '|':
						
						answers.add(new BigInteger(x.toString() + numbers[i].toString()));
					}
				}
			}
		}
		
		if (answers.contains(value)) return true;
		else return false;
	}
	
	//Start with total = numbers[0]
	// OLD RECURSIVE IMPLEMENTATION. 
	//I switched to using a HashSet to store final values and check against the value. See above
	public static boolean checkEquation_Recursive(BigInteger value, BigInteger[] numbers, char[] operators, BigInteger total) {
		
		boolean result = false;
		
		if (total.equals(value)) return true;
		
		for (int i = 1; i < numbers.length; i++) {
			for (char op : operators) {
				switch (op) {
					case '+': {
						result = result || checkEquation_Recursive(value, 
								Arrays.copyOfRange(numbers, i, numbers.length), 
								operators, total.add(numbers[i]));
						break;
					}
					case '*': {
						result = result || checkEquation_Recursive(value, 
								Arrays.copyOfRange(numbers, i, numbers.length), 
								operators, total.multiply(numbers[i]));
						break;
					}
					case '|':
						result = result || checkEquation_Recursive(value, 
								Arrays.copyOfRange(numbers, i, numbers.length), 
								operators, new BigInteger(total.toString() + numbers[i].toString()));
				}
				
			}
		}
		
		return result;
	}

}
