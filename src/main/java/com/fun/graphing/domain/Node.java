package com.fun.graphing.domain;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Node extends StackPane {
	public static final double NODE_RADIUS = 25;
	
	private static double letter_counter = 65;
	private static int letter_factor = 1;
	
	private Circle circle;
	private Text label;
	
	
	public Node(double xCoord, double yCoord) {
		circle = new Circle(NODE_RADIUS, Color.CYAN);
		
		label = new Text(this.createNodeLabel());
		
		this.getChildren().addAll(circle, label);
		this.relocate(xCoord - NODE_RADIUS, yCoord - NODE_RADIUS);
		
	}
	
	private String createNodeLabel() {
		char letter = (char) letter_counter;
		String label = StringUtils.repeat(letter, letter_factor);
		letter_counter++;
		
		if (letter_counter > 90) {
			letter_counter = 65;
			letter_factor++;
		}
		
		return label;
	}	
}