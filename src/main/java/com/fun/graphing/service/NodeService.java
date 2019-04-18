package com.fun.graphing.service;

import com.fun.graphing.controller.NodeController;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;

import javafx.scene.input.MouseEvent;

public interface NodeService {
	
	public abstract void executePaneStateMethod(PaneState paneState, MouseEvent mouseEvent);
	public abstract void executeNodeStateMethod(NodeState nodeState, MouseEvent mouseEvent);
	
	public abstract void setNodeController(NodeController nodeController);
	
	public abstract void createNode(MouseEvent mouseEvent);
	public abstract void deleteNode(MouseEvent mouseEvent);
	public abstract void connectNodes(MouseEvent mouseEvent);
	
}