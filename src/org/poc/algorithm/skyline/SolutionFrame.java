package org.poc.algorithm.skyline;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.poc.algorithm.skyline.SkylineProblem.Skyline;

public class SolutionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4703150348524360991L;
	
	// TODO: Adjust the size of the frame depending on the width and number of
	// buildings.
	public static final int WIDTH = 625;
	public static final int HEIGHT = 300;
	
	JButton btnAdd;
	JTextField left;
	JTextField right;
	JTextField height;
	
	JPanel contentPane;
	SolutionPane solutionPane;
	
	public SolutionFrame(List<SkylineProblem.Building> buildingList, ArrayList<Skyline> skylineList) {
		// TODO: Shouldn't we do all this initialization in the solution panel?
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		solutionPane  = new SolutionPane(buildingList, skylineList);
		contentPane.add(solutionPane, BorderLayout.CENTER);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		left = new JTextField();
		left.setColumns(3);
		inputPanel.add(left);
		JLabel leftLabel = new JLabel("Left:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(left);
		inputPanel.add(leftLabel);
		
		right = new JTextField();
		right.setColumns(3);
		inputPanel.add(right);
		JLabel rightLabel = new JLabel("Right:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(right);
		inputPanel.add(rightLabel);
		
		height = new JTextField();
		height.setColumns(3);
		inputPanel.add(height);
		JLabel heightLabel = new JLabel("Height:");
		leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
		leftLabel.setLabelFor(height);
		inputPanel.add(heightLabel);
		
		btnAdd = new JButton("Add Building");
		btnAdd.setToolTipText("Add a new building");
		inputPanel.add(btnAdd);
		
		long when = 0;
		JCheckBox demoCheckBox = new JCheckBox("Use Demo Data");
		demoCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disableTextInput();
			}			
		});
		demoCheckBox.setSelected(true);
		// Fire checkbox event.
		new ActionEvent(demoCheckBox, ActionEvent.ACTION_PERFORMED, "Demo", when, 0);
		disableTextInput();
		inputPanel.add(demoCheckBox);
		
		contentPane.add(inputPanel, BorderLayout.NORTH);
	}
	
	void disableTextInput() {
		left.setEnabled(false);
		left.setBackground(Color.lightGray);
		right.setEnabled(false);
		right.setBackground(Color.lightGray);
		height.setEnabled(false);
		height.setBackground(Color.lightGray);
		btnAdd.setEnabled(false);
	}
	
}
