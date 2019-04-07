package com.fun.graphing.controller;

import java.util.Map;
import java.util.function.BiFunction;

import com.fun.graphing.domain.Node;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;
import com.fun.graphing.service.StateToServiceMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	public class EnableNodeCreation implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			nodeState = null;
			paneState = PaneState.CREATION;
		}
		
	}
	
	public class EnableNodeDeletion implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			nodeState = NodeState.DELETION;
			paneState = null;
		}
	}
	
	public class EnableNodeConnection implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			nodeState = NodeState.CONNECTION;
			paneState = null;
		}
	}
	
	public class HandlePaneMouseClick implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			BiFunction<Pane, MouseEvent, Node> serviceFunction = PANE_STATE_TO_SERVICE_MAP.get(paneState);
			
			if (serviceFunction == null) {
				return;
			}
			
			serviceFunction.apply(drawingPane, event);
		}
	}
	
	public class HandleNodeMouseClick implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			BiFunction<Pane, MouseEvent, Node> serviceFunction = NODE_STATE_TO_SERVICE_MAP.get(nodeState);
			
			if (serviceFunction == null) {
				return;
			}
			
			serviceFunction.apply(drawingPane, event);
		}
	}
}