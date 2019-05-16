package com.fun.graphing.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	
	private Map<Node, Edge> neighborMap;
	
	public Node(double centerX, double centerY) {
		neighborMap = new HashMap<>();
		
		circle = new Circle(centerX, centerY, NODE_RADIUS, Color.CYAN);
		
		label = new Text(this.createNodeLabel());
		
		this.getChildren().addAll(circle, label);
		
		//The starting point for a stackpane is the upper left
		this.relocate(centerX - NODE_RADIUS, centerY - NODE_RADIUS);
	}
	
	public double getCenterX() {
		return this.circle.getCenterX();
	}
	
	public double getCenterY() {
		return this.circle.getCenterY();
	}
	
	public void addNeighbor(Node node, Edge edge) {
		this.neighborMap.put(node, edge);
	}
	
	public void removeNeighbor(Node node) {
		this.neighborMap.remove(node);
	}
	
	public boolean isNeighbor(Node node) {
		return this.neighborMap.containsKey(node);
	}
	
	public Collection<Edge> getEdges() {
		return this.neighborMap.values();
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