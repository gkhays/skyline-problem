package org.poc.algorithm.skyline;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.poc.algorithm.skyline.SkylineProblem.Building;
import org.poc.algorithm.skyline.SkylineProblem.Skyline;

public class SolutionPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5560759162218687805L;
	
	private int maxBuildingHeight = 0;
	private List<Building> buildings;
	private ArrayList<Skyline> skylineList;
	
	public SolutionPane(List<SkylineProblem.Building> buildings, ArrayList<Skyline> skylineList) {
		addBuildings(buildings, skylineList);
	}
	
	public void addBuildings(List<SkylineProblem.Building> buildings, ArrayList<Skyline> skylineList) {
		this.buildings = buildings;
		this.skylineList = skylineList;

		// Get it to work first; then optimize. I say this because we will have to loop
		// through the list twice. :(
		// We should probably get the max height when we are accumulating the building
		// values.
		for (Building b : buildings) {
			if (b.height > maxBuildingHeight) {
				maxBuildingHeight = b.height;
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);		
		
		float lineWidth = 3;
		g2.setStroke(new BasicStroke(lineWidth));
		
		int lastXr = 0;
		for (Building b : buildings) {
			int delta = (maxBuildingHeight - b.height) * 10;
			g.drawRect(b.left * 10, 50 + delta, (b.right - b.left) * 10, b.height * 10);
			lastXr = b.left * 10 + (b.right - b.left) * 10;
		}		

		// Separate the buildings by 20 increments.
		lastXr += 20;
		for (Building b : buildings) {
			int delta = (maxBuildingHeight - b.height) * 10;
			g.fillRect(b.left * 10 + lastXr, 50 + delta, (b.right - b.left) * 10, b.height * 10);
		}
		
		// Plot the solution as points overlaid upon the skyline. Account for the line
		// thickness by adding 3.
		g2.setColor(Color.red);
		for (Skyline s : skylineList) {
			int delta = (maxBuildingHeight - s.height) * 10;
			if (s.height == 0) {
				// Hack for y=0; double the offset for line thickness.
				g2.fillOval(s.coordinates * 10 + lastXr - Math.round(lineWidth) * 2, 
						delta + 50 - Math.round(lineWidth) * 2,
						10,
						10);
			} else {
				g2.fillOval(s.coordinates * 10 + lastXr - Math.round(lineWidth), 
						delta + 50 - Math.round(lineWidth), 
						10,
						10);
			}
		}
	}
	
}
