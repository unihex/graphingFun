package com.fun.graphing.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import com.fun.graphing.controller.NodeController;
import com.fun.graphing.domain.Edge;
import com.fun.graphing.domain.Node;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;

import javafx.scene.input.MouseEvent;

public class NodeServiceImpl implements NodeService {
	
	private NodeController nodeController;
	
	private Map<PaneState, Consumer<MouseEvent>> paneStateMap;
	private Map<NodeState, Consumer<MouseEvent>> nodeStateMap;
	
	private Set<Node> nodesToConnect;
	
	public NodeServiceImpl() {
		this.paneStateMap = this.buildPaneStateMap();
		this.nodeStateMap = this.buildNodeStateMap();
		
		nodesToConnect = new HashSet<>();
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
		nodeController.tellViewToAddElement(node);
	}

	@Override
	public void deleteNode(MouseEvent mouseEvent) {
		Node node = (Node) mouseEvent.getSource();
		nodesToConnect.remove(node);
		
		Collection<Edge> edgeCollection = node.getEdges();
		
		for (Edge edge : edgeCollection) {
			Optional<Node> remainingNodeOption = edge.getOtherNode(node);
			
			remainingNodeOption.ifPresent(n -> n.removeNeighbor(node));
			
			nodeController.tellViewToRemoveElement(edge);
		}
		
		nodeController.tellViewToRemoveElement(node);
	}

	@Override
	public void connectNodes(MouseEvent mouseEvent) {
		Node node = (Node) mouseEvent.getSource();
		
		nodesToConnect.add(node);
		
		if (nodesToConnect.size() < 2) {
			return;
		}
		
		Iterator<Node> connectionIterator = nodesToConnect.iterator();
		Node currentNode = connectionIterator.next();
		
		while (connectionIterator.hasNext()) {
			Node nextNode = connectionIterator.next();
			
			if (currentNode.isNeighbor(nextNode)) {
				continue;
			}
			
			Edge edge = new Edge(currentNode, nextNode);
			currentNode.addNeighbor(nextNode, edge);
			nextNode.addNeighbor(currentNode, edge);
			
			nodeController.tellViewToAddElement(edge);
			
			currentNode = nextNode;
		}
		
		nodesToConnect.clear();
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