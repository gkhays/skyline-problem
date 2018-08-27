package org.poc.algorithm.skyline;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.poc.algorithm.skyline.SkylineProblem.Building;

public class SolutionPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5560759162218687805L;
	
	private int maxBuildingHeight = 0;
	private List<Building> buildings;
	
	public SolutionPane(List<SkylineProblem.Building> buildings) {
		addBuildings(buildings);
	}
	
	public void addBuildings(List<SkylineProblem.Building> buildings) {
		this.buildings = buildings;

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
		
		g2.setStroke(new BasicStroke(3));
		
		int lastXr = 0;
		for (Building b : buildings) {
			int delta = (maxBuildingHeight - b.height) * 10;
			g.drawRect(b.left * 10, 50 + delta, (b.right - b.left) * 10, b.height * 10);
			lastXr = b.left * 10 + (b.right - b.left) * 10;
		}
		
		// Plot some points to become familiar with "anti" coordinate system.
		// g2.setColor(Color.red);
		// g2.drawOval(5, 5, 10, 10);
		// g2.drawOval(this.getWidth() - 20, this.getHeight() - 20, 10, 10);
		// TODO: Plot the points of the solution.
		// Separate the buildings by 20 increments.
		lastXr += 20;
		for (Building b : buildings) {
			int delta = (maxBuildingHeight - b.height) * 10;
			g.fillRect(b.left * 10 + lastXr, 50 + delta, (b.right - b.left) * 10, b.height * 10);
		}
	}
	
}
