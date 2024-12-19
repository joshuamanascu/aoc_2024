package aoc_2024;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day1 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day1.txt"));
		
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		
		//Input
		while (in.hasNextInt()) {
			left.add(in.nextInt());
			right.add(in.nextInt());
		}
		
		System.out.printf("Part 1: %d\n", part1(left, right));
		System.out.printf("Part 2: %d\n", part2(left, right));
		
		in.close();

	}
	
	public static int part1(ArrayList<Integer> left, ArrayList<Integer> right) {
		Collections.sort(left);
		Collections.sort(right);
		
		int totalDistance = 0;
		
		for (int i = 0; i < left.size(); i++) {
			int distance = left.get(i) - right.get(i);
			
			totalDistance += Math.abs(distance);
		}
		
		return totalDistance;
	}
	
	public static int part2(ArrayList<Integer> left, ArrayList<Integer> right) {
		int simScore = 0;
		
		//(value, count in right)
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int x : right) {
			if (map.containsKey(x)) {
				map.put(x, map.get(x) + 1);
			}
			else {
				map.put(x, 1);
			}
		}
		
		for (int x : left) {
			if (map.containsKey(x)) {
				simScore += x * map.get(x);
			}
		}
		
		return simScore;
	}

}
