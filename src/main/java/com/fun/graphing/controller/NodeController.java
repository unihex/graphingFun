package com.fun.graphing.controller;

import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;
import com.fun.graphing.service.NodeService;
import com.fun.graphing.view.NodeGraphView;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class NodeController {
	
	private NodeService nodeService;
	
	private NodeGraphView view;
	
	public NodeController(NodeService nodeService) {
		this.nodeService = nodeService;
		this.nodeService.setNodeController(this);	
	}

	public void setView(NodeGraphView view) {
		this.view = view;
	}
	
	public void enableNodeCreation(ActionEvent actionEvent) {
		view.setInstructionsText("Create a node by clicking above the buttons");
		
		view.setPaneState(PaneState.CREATION);
		view.setNodeState(null);
	}
	
	public void enableNodeDeletion(ActionEvent actionEvent) {
		view.setInstructionsText("Delete a node by clicking on the node");
		
		view.setPaneState(null);
		view.setNodeState(NodeState.DELETION);
	}
	
	public void enableNodeConnection(ActionEvent actionEvent) {
		view.setInstructionsText("Connect two nodes by clicking on them");
		
		view.setPaneState(null);
		view.setNodeState(NodeState.CONNECTION);
	}
	
	public void tellViewToAddElement(javafx.scene.Node element) {
		view.addElementToView(element);
	}
	
	public void tellViewToRemoveElement(Object element) {
		view.removeElementFromView(element);
	}
	
	public void handlePaneMouseClick(MouseEvent mouseEvent) {
		PaneState paneState = view.getPaneState();
		
		nodeService.executePaneStateMethod(paneState, mouseEvent);
	}
	
	public void handleNodeMouseClick(MouseEvent mouseEvent) {
		NodeState nodeState = view.getNodeState();
		
		nodeService.executeNodeStateMethod(nodeState, mouseEvent);
	}
}