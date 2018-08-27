package org.poc.algorithm.skyline;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SolutionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4703150348524360991L;
	
	// TODO: Adjust the size of the frame depending on the width and number of
	// buildings.
	public static final int WIDTH = 900;
	public static final int HEIGHT = 300;
	
	JPanel contentPane;
	SolutionPane solutionPane;
	
	public SolutionFrame(List<SkylineProblem.Building> buildingList) {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		solutionPane  = new SolutionPane(buildingList);
		contentPane.add(solutionPane, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		
		JTextField left = new JTextField();
		left.setColumns(3);
		inputPanel.add(left);
		JLabel leftLabel = new JLabel("Left:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(left);
		inputPanel.add(leftLabel);
		
		JTextField right = new JTextField();
		right.setColumns(3);
		inputPanel.add(right);
		JLabel rightLabel = new JLabel("Right:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(right);
		inputPanel.add(rightLabel);
		
		JTextField height = new JTextField();
		height.setColumns(3);
		inputPanel.add(height);
		JLabel heightLabel = new JLabel("Height:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(height);
		inputPanel.add(heightLabel);
		
		contentPane.add(inputPanel, BorderLayout.WEST);
	}
	
}
