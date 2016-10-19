package applicationController;

import appScene.errorViewer.ErrorViewerFactory;
import appScene.errorViewer.IErrorViewer;

import appScene.scriptViewer.IScriptViewer;
import appScene.scriptViewer.ScriptViewerFactory;

import appScene.textEditor.ITextEditor;
import appScene.textEditor.TextEditorFactory;

import appScene.toolbar.IToolbar;
import appScene.toolbar.ToolbarFactory;

import appScene.turtleBox.ITurtleBox;
import appScene.turtleBox.TurtleBoxFactory;

import appScene.variableViewer.ConcreteVariableViewer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ApplicationController {

    private GridPane myApplicationView;
    private Scene myScene;
    private Group myRoot;
    private String TITLE = "SLOGO";
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private ITurtleBox myTurtleBox;
    private ConcreteVariableViewer myVariableViewer;
    private IScriptViewer myScriptViewer;

    public ApplicationController () {
        myApplicationView = new GridPane();
    }

    public Scene init ( int aWidth, int aHeight) {
        myToolbar = ToolbarFactory.buildToolbar(aWidth, aHeight / 20);
        myTurtleBox = TurtleBoxFactory.buildTurtleBox(2*aWidth/3, 2*aHeight/3);
        myTextEditor = TextEditorFactory.buildTextEditor( 2*aWidth/3, aHeight / 3);
        myErrorViewer = ErrorViewerFactory.buildErrorViewer( aWidth/3, aHeight / 3);
        myVariableViewer = new ConcreteVariableViewer( aWidth / 6, aHeight / 3);
        myScriptViewer = ScriptViewerFactory.buildViewerFactory( aWidth / 6, aHeight / 3); // double check these values

        myRoot = new Group();
        myRoot.getChildren().addAll(myApplicationView);
        myScene = new Scene(myRoot, aWidth, aHeight + aHeight/20 + 10, Color.WHITE);
        /*
         * myApplicationView.add( myToolbar.getInstanceAsNode(), 0, 0, GridPane.REMAINING, 1);
         * myApplicationView.add( myTextEditor.getInstanceAsNode(), 0, 2, 1, 2);
         * myApplicationView.add( myTurtleBox.getInstanceAsNode(), 0, 1, 2, 1);
         * myApplicationView.add( myErrorViewer.getInstanceAsNode(), 1, 1, 1, 1);
         * myApplicationView.add(myVariableViewer.getInstanceAsNode(), 3, 2, 1, 1);
         */
        myApplicationView.add(myToolbar.getInstanceAsNode(), 		0, 0, GridPane.REMAINING, 1);
        myApplicationView.add(myTurtleBox.getInstanceAsNode(), 		0, 1, 1, 1);
        myApplicationView.add(myTextEditor.getInstanceAsNode(), 	0, 2, 1, 1);
        
        myApplicationView.add(myVariableViewer.getInstanceAsNode(), 1, 1, 1, 1);
        myApplicationView.add(myScriptViewer.getInstanceAsNode(),   2, 1, 1, 1);
        
        myApplicationView.add(myErrorViewer.getInstanceAsNode(), 	1, 2, 2, 1);
        
        return myScene;
    }

    public String getTitle () {
        return TITLE;
    }
}
