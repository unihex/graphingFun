package com.fun.graphing.service;

import com.fun.graphing.domain.Node;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public interface NodeService {
	public abstract Node createNode(Pane drawingPane, MouseEvent mouseEvent);
	public abstract Node deleteNode(Pane drawingPane, MouseEvent mouseEvent);
	
	public abstract Node connectNodes(Pane drawingPane, MouseEvent mouseEvent);
	
}