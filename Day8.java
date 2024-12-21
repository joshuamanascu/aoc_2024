package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day8 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day8.txt"));
		
		
		
		ArrayList<char[]> mapList = new ArrayList<char[]>();
		
		while (in.hasNextLine()) {
			mapList.add(in.nextLine().toCharArray());
		}
		
		char[][] map = mapList.toArray(new char[mapList.size()][mapList.get(0).length]);
		
		// Transpose 
		for (int i = 0; i < map.length; i++) {
		    for (int j = i + 1; j < map.length; j++) {
		        char temp = map[i][j];
		        map[i][j] = map[j][i];
		        map[j][i] = temp;
		    }
		}
		
		HashMap<Character, ArrayList<Point>> locations = new HashMap<Character, ArrayList<Point>>();
		
		//Store a HashMap of all the antennae locations
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != '.') {
					if (locations.containsKey(map[i][j])) {
						locations.get(map[i][j]).add(new Point(i, j));
					}
					else {
						locations.put(map[i][j], new ArrayList<Point>(Arrays.asList(new Point(i, j))));
					}
				}
			}
		}
		
		System.out.printf("Part 1: %d\n", part1(map, locations));
		System.out.printf("Part 2: %d\n", part2(map, locations));
		
		
		in.close();

	}
	
	public static int part2(char[][] map, HashMap<Character, ArrayList<Point>> locations) {
		HashSet<Point> results = new HashSet<Point>();
		
		for (char c : locations.keySet()) {
			ArrayList<Point> list = locations.get(c);
			
			//Get all pairs of points
			for (int i = 0; i < list.size(); i++) {
				for (int j = i + 1; j < list.size(); j++) {
					Point p1 = list.get(i);
					Point p2 = list.get(j);
					
					
					int slopeX = p2.x - p1.x;
					int slopeY = p2.y - p1.y;
					
					int x = p1.x;
					int y = p1.y; //Start from p1. Arbitrary
					
					//Adding
					while (inBounds(x, y, map)) {
						results.add(new Point(x, y));
						x += slopeX;
						y += slopeY;
					}
					
					//Reset
					x = p1.x;
					y = p1.y;
					
					//Subtracting
					while (inBounds(x, y, map)) {
						results.add(new Point(x, y));
						x -= slopeX;
						y -= slopeY;
					}
				}
			}
		}
		
		return results.size();
	}
	
	public static int part1(char[][] map, HashMap<Character, ArrayList<Point>> locations) {
		HashSet<Point> results = new HashSet<Point>();
		
		
		for (char c : locations.keySet()) {
			ArrayList<Point> list = locations.get(c);
			
			//Get all pairs of points
			for (int i = 0; i < list.size(); i++) {
				for (int j = i + 1; j < list.size(); j++) {
					Point p1 = list.get(i);
					Point p2 = list.get(j);
					
					
					//dt = 2d -> t = 2
					int xt = -1*p1.x + 2*p2.x;
					int yt = -1*p1.y + 2*p2.y;
					
					if (inBounds(xt, yt, map)) results.add(new Point(xt, yt));
					
					//dt = -d -> t = -1
					xt = 2*p1.x - p2.x;
					yt = 2*p1.y - p2.y;
					
					if (inBounds(xt, yt, map)) results.add(new Point(xt, yt));
				}
			}
		}
		
		return results.size();
		
	}
	
	public static boolean inBounds(int x, int y, char[][] map) {
		return x >= 0 && x < map.length && y >=0 && y < map[0].length;
	}
	
	public record Point(int x, int y) {};

}
