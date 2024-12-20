package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {
	
	static HashMap<Integer, ArrayList<Integer>> priors;
	static ArrayList<int[]> updates;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day5.txt"));
		// (k, {v1,v2,v3}) means that k must be before v1,v2,v3
		priors = new HashMap<Integer, ArrayList<Integer>>();
		
		String s = in.nextLine();
		while (!s.equals("")) {
			
			String values[] = s.split("\\|");
			int k = Integer.valueOf(values[0]);
			int v = Integer.valueOf(values[1]);
			
			if (priors.containsKey(k)) {
				priors.get(k).add(v);
			}
			else {
				priors.put(k, new ArrayList<Integer>(Arrays.asList(v)));
			}
			
			s = in.nextLine();
		}
		
		updates = new ArrayList<int[]>();
		
		while (in.hasNextLine()) {
			int[] update = Arrays.stream(in.nextLine().split(",")).mapToInt(str -> Integer.valueOf(str)).toArray();
			
			updates.add(update);
		}
		
		System.out.printf("Part 1: %d\n", part1());
		
		System.out.printf("Part 2: %d\n", part2());
		
		
		in.close();

	}
	
	public static int part2() {
		int result = 0;
		
		for (int update[] : updates) {
			
			if (!checkUpdate(update, priors)) {
				update = Arrays.stream(update).boxed().sorted(new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						if (priors.containsKey(o1) && priors.get(o1).contains(o2)) {
							return -1;
						}
						else if (priors.containsKey(o2) && priors.get(o2).contains(o1)) {
							return 1;
						}
						else {
							return 0;
						}
					}
				}).mapToInt(x -> x).toArray();
				
				result += update[update.length/2]; //Add middle element
			}
		}
		
		return result;
	}
	
	public static int part1() {
		
		int result = 0;
		
		for (int update[] : updates) {
			
			if (checkUpdate(update, priors)) {
				result += update[update.length/2]; //Add middle element
			}
		}
		
		return result;
	}
	
	public static boolean checkUpdate(int[] update, HashMap<Integer, ArrayList<Integer>> priors) {
		HashSet<Integer> buildup = new HashSet<Integer>();
		buildup.add(update[0]); //Add first element in the update
		
		for (int i = 1; i < update.length; i++) {
			if (priors.containsKey(update[i])) {
				ArrayList<Integer> rules = priors.get(update[i]);
				
				for (int x : rules) {
					if (buildup.contains(x)) {
						return false;
					}
				}
			}
			
			buildup.add(update[i]);
		}
		
		return true;
	}

}
