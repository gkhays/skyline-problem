package org.poc.algorithm.skyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * I found a skyline solution here: <a href=
 * "https://github.com/TheAlgorithms/Java/tree/master/SkylineProblem">SkylineProblem</a>.
 * But had to read up to help understand. I used this article: <a href=
 * "https://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/">
 * Divide and Conquer | Set 7 (The Skyline Problem)</a>.
 * 
 * First indicate how many buildings, then follow with the (triplet) coordinates
 * for each. The last line is the computed solution.
 * 
 * <pre>
 * {@code
 * 4
 * 1,11,5
 * 2,6,7
 * 3,13,9
 * 14,3,25
 * 1,11,3,13,9,0,14,3,25,0}
 * </pre>
 * 
 * @author sangjun2
 * @author ghays
 */
public class SkylineProblem {

	Building[] building;
	int count;
	
	boolean isDemo;
	
	public SkylineProblem() {
		this(false);
	}
	
	public SkylineProblem(boolean isDemo) {
		this.isDemo = isDemo;
	}
	
	public void run() {
		if (isDemo) {
			this.building = new Building[8];
			this.add(1, 11, 5);
			this.add(2, 6, 7);
			this.add(3, 13, 9);
			this.add(12, 7, 16);
			this.add(14, 3, 25);
			this.add(19, 18, 22);
			this.add(23, 13, 29);
			this.add(24, 4, 28);
		} else {
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter the number of buildings: ");
			int num = sc.nextInt();
			this.building = new Building[num];

			System.out.println("Now enter the coordinates for each on a separate line.");
			for (int i = 0; i < num; i++) {
				String input = sc.next();
				String[] data = input.split(",");
				this.add(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
			}
			System.out.print("Solution: ");
			this.print(this.findSkyline(0, num - 1));
			sc.close();
		}
		
		System.out.print("Solution: ");
		ArrayList<Skyline> skylineList = this.findSkyline(0, building.length - 1);
		this.print(skylineList);
		
		showVisualization(building, skylineList);
	}
	
	private void showVisualization(Building[] b, ArrayList<Skyline> skylineList) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// TODO: Eliminate redundant parameters.
				SolutionPane solutionPane = new SolutionPane(Arrays.asList(b), skylineList);
				SolutionFrame frame = new SolutionFrame(Arrays.asList(b), skylineList);
				frame.setTitle("Skyline Solution");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(solutionPane);
				// frame.pack();
				frame.setLocationByPlatform(true);
				frame.setResizable(false);
				frame.setSize(SolutionFrame.WIDTH, SolutionFrame.HEIGHT);
				frame.setVisible(true);
			}
		});		
	}
	
	public void add(int left, int height, int right) {
		building[count++] = new Building(left, height, right);
	}
	
	public void print(ArrayList<Skyline> skyline) {
		Iterator<Skyline> it = skyline.iterator();
		
		while(it.hasNext()) {
			Skyline temp = it.next();
			System.out.print(temp.coordinates + "," + temp.height);
			if(it.hasNext()) {
				System.out.print(",");
			}
		}
		
	}
	
	public ArrayList<Skyline> findSkyline(int start, int end) {
		if(start == end) {
			ArrayList<Skyline> list = new ArrayList<>();
			list.add(new Skyline(building[start].left, building[start].height));
			list.add(new Skyline(building[end].right, 0));
			
			return list;
		}
		
		int mid = (start + end) / 2;
		
		ArrayList<Skyline> sky1 = this.findSkyline(start, mid);
		ArrayList<Skyline> sky2 = this.findSkyline(mid + 1, end);
		
		return this.mergeSkyline(sky1, sky2);
	}
	
	public ArrayList<Skyline> mergeSkyline(ArrayList<Skyline> sky1, ArrayList<Skyline> sky2) {
		int currentH1 = 0, currentH2 = 0;
		ArrayList<Skyline> skyline = new ArrayList<>();
		int maxH = 0;
		
		while(!sky1.isEmpty() && !sky2.isEmpty()) {
			if(sky1.get(0).coordinates < sky2.get(0).coordinates) {
				int currentX = sky1.get(0).coordinates;
				currentH1 = sky1.get(0).height;
				
				if(currentH1 < currentH2) {
					sky1.remove(0);
					if(maxH != currentH2) skyline.add(new Skyline(currentX, currentH2));
				} else {
					maxH = currentH1;
					sky1.remove(0);
					skyline.add(new Skyline(currentX, currentH1));
				}
			} else {
				int currentX = sky2.get(0).coordinates;
				currentH2 = sky2.get(0).height;
				
				if(currentH2 < currentH1) {
					sky2.remove(0);
					if(maxH != currentH1) skyline.add(new Skyline(currentX, currentH1));
				} else {
					maxH = currentH2;
					sky2.remove(0);
					skyline.add(new Skyline(currentX, currentH2));
				}
			}
		}

		while(!sky1.isEmpty()) {
			skyline.add(sky1.get(0));
			sky1.remove(0);
		}
		
		while(!sky2.isEmpty()) {
			skyline.add(sky2.get(0));
			sky2.remove(0);
		}
		
		return skyline;
	}
	
	public class Skyline {
		public int coordinates;
		public int height;
		
		public Skyline(int coordinates, int height) {
			this.coordinates = coordinates;
			this.height = height;
		}
	}
	
	public class Building {
		public int left;
		public int height;
		public int right;
		
		public Building(int left, int height, int right) {
			this.left = left;
			this.height = height;
			this.right = right;
		}
	}
	
	public static void main(String[] args) {SkylineProblem skylineProblem;
		if (args[0].equals("demo")) {
			skylineProblem = new SkylineProblem(true);
		} else {
			skylineProblem = new SkylineProblem();
		}
		skylineProblem.run();
	}

}
