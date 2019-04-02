package com.fun.graphing;

import com.fun.graphing.controller.NodeController;
import com.fun.graphing.service.NodeService;
import com.fun.graphing.service.NodeServiceImpl;
import com.fun.graphing.view.NodeGraphView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphingApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Graphing Application");
		Scene root = this.setupNodeGraphingApplication();
		
		primaryStage.setScene(root);
		primaryStage.show();
	}
	
	private Scene setupNodeGraphingApplication() {
		NodeService service = new NodeServiceImpl();
		NodeController controller = new NodeController(service);
		NodeGraphView view = new NodeGraphView(controller);
		
		Scene root = view.buildView();
		
		return root;
	}
	
}