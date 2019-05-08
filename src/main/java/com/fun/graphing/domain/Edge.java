package com.fun.graphing.domain;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class Edge extends Line {
	
	private Node node1;
	private Node node2;
	
	public Edge(Node node1, Node node2) {
		double deltaX = node2.getCenterX() - node1.getCenterX();
		double deltaY = node2.getCenterY() - node1.getCenterY();
		
		Point2D differenceVector = new Point2D(deltaX, deltaY);
		Point2D normalizedDifferenceVector = differenceVector.normalize();
		Point2D translationVector = normalizedDifferenceVector.multiply(Node.NODE_RADIUS);		
		
		double startX = node1.getCenterX() + translationVector.getX();
		double startY = node1.getCenterY() + translationVector.getY();
		
		double endX = node2.getCenterX() - translationVector.getX();
		double endY = node2.getCenterY() - translationVector.getY();
		
		this.setStartX(startX);
		this.setStartY(startY);
		
		this.setEndX(endX);
		this.setEndY(endY);
		
		this.node1 = node1;
		this.node2 = node2;
	}
	
}