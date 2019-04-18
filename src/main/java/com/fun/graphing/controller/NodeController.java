package com.fun.graphing.controller;

import java.util.Map;
import java.util.function.BiFunction;

import com.fun.graphing.domain.Node;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;
import com.fun.graphing.service.StateToServiceMap;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class NodeController {
	private Pane drawingPane;
	
	private NodeState nodeState;
	private PaneState paneState;
	
	private static final Map<NodeState, BiFunction<Pane, MouseEvent, Node>> NODE_STATE_TO_SERVICE_MAP = StateToServiceMap.getNodeStateToServiceMap();
	private static final Map<PaneState, BiFunction<Pane, MouseEvent, Node>> PANE_STATE_TO_SERVICE_MAP = StateToServiceMap.getPaneStateToServiceMap();
	
	
	public void setDrawingPane(Pane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	public void enableNodeCreation(ActionEvent actionEvent) {
		nodeState = null;
		paneState = PaneState.CREATION;
	}
	
	public void enableNodeDeletion(ActionEvent actionEvent) {
		nodeState = NodeState.DELETION;
		paneState = null;
	}
	
	public void enableNodeConnection(ActionEvent actionEvent) {
		nodeState = NodeState.CONNECTION;
		paneState = null;
	}
	
	public void handlePaneMouseClick(MouseEvent mouseEvent) {
		BiFunction<Pane, MouseEvent, Node> serviceFunction = PANE_STATE_TO_SERVICE_MAP.get(paneState);
		
		if (serviceFunction == null) {
			return;
		}
		
		serviceFunction.apply(drawingPane, mouseEvent);
	}
	
	public void handleNodeMouseClick(MouseEvent mouseEvent) {
		BiFunction<Pane, MouseEvent, Node> serviceFunction = NODE_STATE_TO_SERVICE_MAP.get(nodeState);
		
		if (serviceFunction == null) {
			return;
		}
		
		serviceFunction.apply(drawingPane, mouseEvent);	
	}
}