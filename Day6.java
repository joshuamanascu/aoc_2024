package aoc_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day6 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("src/aoc_2024/Inputs/day6.txt"));
		
		
		
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
		
		
		System.out.printf("Part 1: %d\n", part1(map));
		System.out.printf("Part 2: %d\n", part2(map));
		
		
		
		in.close();

	}
	
	public static int part2(char[][] map) {
		
		int result = 0;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '.' && checkLoop(map, i, j)) result++;
			}
		}
		
		return result;
	}
	
	public static boolean checkLoop(char[][] map, int obsX, int obsY) {
		HashSet<State> set = new HashSet<State>();
		
		map[obsX][obsY] = '#'; //Add obstacle
		
		int x = 0,y = 0;
		
		
		//Find starting position
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '^') {
					x = i;
					y = j;
					break;
				}
			}
			
			if (x != 0) break;
		}
		
		int dx = 0, dy = -1; //Start by going up
		
		while (x >= 0 && x < map.length && y >=0 && y < map[0].length) { //While in bounds
			
			if (set.contains(new State(x, y, dx, dy))) {
				map[obsX][obsY] = '.'; //remove obstacle
				return true;
			}
			
			set.add(new State(x, y, dx, dy)); //Add position and direction
			
			int newX = x + dx;
			int newY = y + dy;
			
			if ((newX >= 0 && newX < map.length && newY >=0 && newY < map[0].length) == false) break;
			
			if (map[newX][newY] == '#') { //If hitting an obstacle
				int temp = dx;
				dx = -dy;
				dy = temp; //90 degree rotation
			}
			else {
				x = newX;
				y = newY;
			}
		}
		
		map[obsX][obsY] = '.'; //remove obstacle
		return false;
		
	}
	
	public static int part1(char[][] map) {
		boolean visited[][] = new boolean[map.length][map[0].length];
		
		int x = 0,y = 0;
		
		
		//Find starting position
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == '^') {
					x = i;
					y = j;
					break;
				}
			}
			
			if (x != 0) break;
		}
		
		//(dx,dy)  -> (-dy, dx)
		
		int dx = 0, dy = -1; //Start by going up
		
		while (x >= 0 && x < map.length && y >=0 && y < map[0].length) { //While in bounds
			
			visited[x][y] = true;
			
			int newX = x + dx;
			int newY = y + dy;
			
			if ((newX >= 0 && newX < map.length && newY >=0 && newY < map[0].length) == false) break;
			
			if (map[newX][newY] == '#') { //If hitting an obstacle
				int temp = dx;
				dx = -dy;
				dy = temp; //90 degree rotation
			}
			else {
				x = newX;
				y = newY;
			}
		}
		
		//Count number of visited spots
		int result = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (visited[i][j]) result++;
			}
		}
		
		return result;
	}
	
	public record State(int x, int y, int dx, int dy) {};

}
