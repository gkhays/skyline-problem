package org.poc.algorithm.skyline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RectangleFillTest extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5929152677270232275L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 20; i++) {
			draw(g);
		}
	}

	public void draw(Graphics g) {
		Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		g.setColor(c);
		g.fillRect((int) (Math.random() * 400), (int) (Math.random() * 300), (int) (Math.random() * 40),
				(int) (Math.random() * 40));
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().add(new RectangleFillTest(), BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 300);
		f.setVisible(true);
	}

}
