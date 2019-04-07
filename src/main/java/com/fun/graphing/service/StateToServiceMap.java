package com.fun.graphing.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import com.fun.graphing.domain.Node;
import com.fun.graphing.enums.NodeState;
import com.fun.graphing.enums.PaneState;
import com.fun.graphing.service.NodeServiceImpl;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

//Extreme functional programming
public class StateToServiceMap {
	private static final NodeService NODE_SERVICE = new NodeServiceImpl();
	
	
	public static Map<NodeState, BiFunction<Pane, MouseEvent, Node>> getNodeStateToServiceMap() {
		Map<NodeState, BiFunction<Pane, MouseEvent, Node>> nodeStateToServiceMap = new HashMap<>();
		
		nodeStateToServiceMap.put(NodeState.DELETION, NODE_SERVICE::deleteNode);
		nodeStateToServiceMap.put(NodeState.CONNECTION, NODE_SERVICE::connectNodes);
		
		return nodeStateToServiceMap;
		
	}
	
	public static Map<PaneState, BiFunction<Pane, MouseEvent, Node>> getPaneStateToServiceMap() {
		Map<PaneState, BiFunction<Pane, MouseEvent, Node>> paneStateToServiceMap = new HashMap<>();
		
		paneStateToServiceMap.put(PaneState.CREATION, NODE_SERVICE::createNode);
		return paneStateToServiceMap;
		
	}
}