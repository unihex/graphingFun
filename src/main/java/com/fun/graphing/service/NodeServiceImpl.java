package com.fun.graphing.service;

import com.fun.graphing.domain.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class NodeServiceImpl implements NodeService {
	
	public Node createNode(Pane drawingPane, MouseEvent mouseEvent) {
		double xCoord = mouseEvent.getSceneX();
		double yCoord = mouseEvent.getSceneY();
		
		Node node = new Node(xCoord, yCoord);
		drawingPane.getChildren().add(node);
		
		return node;
	}

	public Node deleteNode(Pane drawingPane, MouseEvent mouseEvent) {
		return null;
		
	}

	public Node connectNodes(Pane drawingPane, MouseEvent mouseEvent) {
		return null;
	}
}