package front_end.appScene;

import front_end.appScene.errorViewer.ErrorViewerFactory;
import front_end.appScene.errorViewer.IErrorViewer;
import front_end.appScene.helpPage.HelpPage;
import front_end.appScene.scriptViewer.IScriptViewer;
import front_end.appScene.scriptViewer.ScriptViewerFactory;
import front_end.appScene.textEditor.ITextEditor;
import front_end.appScene.textEditor.TextEditorFactory;
import front_end.appScene.toolbar.IToolbar;
import front_end.appScene.toolbar.ToolbarFactory;
import front_end.appScene.turtleBox.ITurtleBox;
import front_end.appScene.turtleBox.TurtleBoxFactory;
import front_end.appScene.variableViewer.IVariableViewer;
import front_end.appScene.variableViewer.VariableViewerFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class ApplicationScene {

    private GridPane myApplicationView;
    private Scene myScene;
    private Group myRoot;
    private IToolbar myToolbar;
    private ITextEditor myTextEditor;
    private IErrorViewer myErrorViewer;
    private ITurtleBox myTurtleBox;
    private IVariableViewer myVariableViewer;
    private IScriptViewer myScriptViewer;
    private HelpPage myHelpPage;

    public ApplicationScene () {
        myApplicationView = new GridPane();
    }

    public Scene initScene (int aWidth, int aHeight) {
        myToolbar = ToolbarFactory.buildToolbar(aWidth, aHeight / 20);
        myTurtleBox = TurtleBoxFactory.buildTurtleBox(2 * aWidth / 3, 2 * aHeight / 3);
        myTextEditor = TextEditorFactory.buildTextEditor(2 * aWidth / 3, aHeight / 3);
        myErrorViewer = ErrorViewerFactory.buildErrorViewer(aWidth / 3, aHeight / 3, myTextEditor);
        myVariableViewer = VariableViewerFactory.buildVariableViewer(aWidth / 6, aHeight / 3);
        myScriptViewer = ScriptViewerFactory.buildViewerFactory(aWidth / 6, aHeight / 3); // double
                                                                                          // //
                                                                                          // check
        myHelpPage = new HelpPage();                                                                                  // these

        myRoot = new Group();
        myRoot.getChildren().addAll(myApplicationView);
        myScene = new Scene(myRoot, aWidth, aHeight + aHeight / 20 + 10, Color.WHITE);
        myApplicationView.add(myToolbar.getInstanceAsNode(), 0, 0, GridPane.REMAINING, 1);
        myApplicationView.add(myTurtleBox.getInstanceAsNode(), 0, 1, 1, 1);
        myApplicationView.add(myTextEditor.getInstanceAsNode(), 0, 2, 1, 1);
        myApplicationView.add(myVariableViewer.getInstanceAsNode(), 1, 1, 1, 1);
        myApplicationView.add(myScriptViewer.getInstanceAsNode(), 2, 1, 1, 1);
        myApplicationView.add(myErrorViewer.getInstanceAsNode(), 1, 2, 2, 1);

        return myScene;
    }

    public Group getMyRoot () {
        return myRoot;
    }

    public HelpPage getMyHelpPage () {
        return myHelpPage;
    }

    public ITurtleBox getMyTurtleBox () {
        return myTurtleBox;
    }

    public IScriptViewer getMyScriptViewer () {
        return myScriptViewer;
    }

    public ITextEditor getMyTextEditor () {
        return myTextEditor;
    }

    public IErrorViewer getMyErrorViewer () {
        return myErrorViewer;
    }

    public IVariableViewer getMyVariableViewer () {
        return myVariableViewer;
    }

    public IToolbar getMyToolbar () {
        return myToolbar;
    }

}
