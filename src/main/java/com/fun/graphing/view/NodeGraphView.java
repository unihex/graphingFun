package com.fun.graphing.view;

import com.fun.graphing.controller.NodeController;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NodeGraphView {
	private NodeController nodeController;
	
	private final static Color BACKGROUND_COLOR = Color.web("#f4aa42");
	
	public NodeGraphView(NodeController nodeController) {
		this.nodeController = nodeController;
	}
	
	public Scene buildView() {
		VBox vBoxLayout = new VBox();
		
		Pane drawingPane = this.buildDrawingPane(vBoxLayout.heightProperty(), vBoxLayout.widthProperty());
		HBox buttonBox = this.buildButtonBox();
		
		nodeController.setDrawingPane(drawingPane);
		
		vBoxLayout.getChildren().addAll(drawingPane, buttonBox);
		Scene scene = new Scene(vBoxLayout, 900, 900);
		
		return scene;
	}
	
	
	private Pane buildDrawingPane(ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width) {
		Pane pane = new Pane();
		
		pane.prefHeightProperty().bind(height);
		pane.prefWidthProperty().bind(width);
		
		BackgroundFill bgFill = new BackgroundFill(BACKGROUND_COLOR, null, null);
		Background background = new Background(bgFill);
		
		pane.setBackground(background);
		pane.setOnMouseClicked(nodeController.new HandlePaneMouseClick());
		
		return pane;
	}
	
	private HBox buildButtonBox() {
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_CENTER);
		hBox.setSpacing(100);
		
		Button createNodesButton = new Button("Create Nodes");
		createNodesButton.setOnAction(nodeController.new EnableNodeCreation());
		
		Button deleteNodesButton = new Button("Delete Nodes");
		deleteNodesButton.setOnAction(nodeController.new EnableNodeDeletion());
		
		Button connectNodesButton = new Button("Connect Nodes");
		connectNodesButton.setOnAction(nodeController.new EnableNodeConnection());
		
		Button traverseNodesButton = new Button("Traverse Nodes");
		
		hBox.getChildren().addAll(createNodesButton, deleteNodesButton, connectNodesButton, traverseNodesButton);
		
		return hBox;
	}
}