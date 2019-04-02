package com.fun.graphing.service;

import com.fun.graphing.domain.Node;

import javafx.scene.layout.Pane;

public interface NodeService {
	public abstract void createNodes(Pane drawingPane, double xCoord, double yCoord);
	public abstract void deleteNode(Pane drawingPane, Node node);
	
	public abstract void connectNodes(Pane drawingPane, Node node);
	
}