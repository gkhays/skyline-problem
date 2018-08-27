package org.poc.algorithm.skyline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleRectangle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3720268714900222766L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		// Upside down.
		g.setColor(Color.blue);
		g.fillRect(10, 10, 50, 100);
		g.setColor(Color.red);
		g.fillRect(50 + 10 + 5, 10, 25, 75);

		// From the bottom.
		g.setColor(Color.green);
		g.fillRect(10, 100 + 50, 50, 100);
		g.setColor(Color.orange);
		g.fillRect(50 + 10 + 5, 100 + 50 + 25, 25, 75);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().add(new SimpleRectangle(), BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 300);
		f.setVisible(true);
	}

}
