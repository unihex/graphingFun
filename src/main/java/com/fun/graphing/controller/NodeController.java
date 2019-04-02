package com.fun.graphing.controller;

import com.fun.graphing.service.NodeService;

public class NodeController {
	
	private NodeService nodeService;
	
	public NodeController(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}