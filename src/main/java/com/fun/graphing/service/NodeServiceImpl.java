package com.fun.graphing.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.fun.graphing.controller.NodeController;
import com.fun.graphing.domain.Node;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;

import javafx.scene.input.MouseEvent;

public class NodeServiceImpl implements NodeService {
	
	private NodeController nodeController;
	
	private Map<PaneState, Consumer<MouseEvent>> paneStateMap;
	private Map<NodeState, Consumer<MouseEvent>> nodeStateMap;
	
	public NodeServiceImpl() {
		this.paneStateMap = this.buildPaneStateMap();
		this.nodeStateMap = this.buildNodeStateMap();
	}
	
	@Override
	public void setNodeController(NodeController nodeController) {
		this.nodeController = nodeController;
	}
	
	@Override
	public void createNode(MouseEvent mouseEvent) {
		double xCoord = mouseEvent.getSceneX();
		double yCoord = mouseEvent.getSceneY();
		
		Node node = new Node(xCoord, yCoord);
		
		node.setOnMouseClicked(nodeController::handleNodeMouseClick);
		nodeController.tellViewToAddNode(node);
	}

	@Override
	public void deleteNode(MouseEvent mouseEvent) {
		Node node = (Node) mouseEvent.getSource();
		nodeController.tellViewToRemoveElement(node);
	}

	@Override
	public void connectNodes(MouseEvent mouseEvent) {
		return;
	}
	
	//Extreme functional programming below!!
	@Override
	public void executePaneStateMethod(PaneState paneState, MouseEvent mouseEvent) {
		Map<PaneState, Consumer<MouseEvent>> paneStateMap = this.paneStateMap;
		Consumer<MouseEvent> serviceMethod = paneStateMap.get(paneState);
		
		if (serviceMethod != null) {
			serviceMethod.accept(mouseEvent);
		}
	}
	
	@Override
	public void executeNodeStateMethod(NodeState nodeState, MouseEvent mouseEvent) {
		Map<NodeState, Consumer<MouseEvent>> nodeStateMap = this.nodeStateMap;
		Consumer<MouseEvent> serviceMethod = nodeStateMap.get(nodeState);
		
		if (serviceMethod != null) {
			serviceMethod.accept(mouseEvent);
		}
	}
	
	private Map<PaneState, Consumer<MouseEvent>> buildPaneStateMap() {
		Map<PaneState, Consumer<MouseEvent>> paneStateMap = new HashMap<>();
		
		paneStateMap.put(PaneState.CREATION, this::createNode);
		return paneStateMap;
	}
	
	private Map<NodeState, Consumer<MouseEvent>> buildNodeStateMap() {
		Map<NodeState, Consumer<MouseEvent>> nodeStateMap = new HashMap<>();
		
		nodeStateMap.put(NodeState.DELETION, this::deleteNode);
		nodeStateMap.put(NodeState.CONNECTION, this::connectNodes);
		
		return nodeStateMap;
	}
}