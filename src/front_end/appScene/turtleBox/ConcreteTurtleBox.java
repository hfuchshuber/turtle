package front_end.appScene.turtleBox;

import back_end.model.robot.IViewRobot;
import integration.languages.Languages;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

class ConcreteTurtleBox implements ITurtleBox {

	ScrollPane myScroller;
	Pane mySandbox;
	ColorPicker myBackgroundColorPicker;
	IViewRobot myRobot;
	
	ConcreteTurtleBox(int width, int height){
		myScroller = new ScrollPane();
		myScroller.setPrefSize(width, height);
		myScroller.setMinSize(width, height);
		myScroller.setMaxSize(width, height);
		
		
		mySandbox = new Pane();
		mySandbox.setPrefSize(width, height);
		mySandbox.setMinSize(width, height);
		mySandbox.setMaxSize(width, height);
		
		myScroller.setContent(mySandbox);

		initColorPicker();
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getInstanceAsNode() {
		return myScroller;
	}

	@Override
	public void addRobot(IViewRobot aRobot) {
		myRobot = aRobot;

	}
	
	@Override
	public void update() {
		if(myRobot == null) return;
		
		System.out.println(myRobot.getY());
		
	}
	
	private void initColorPicker(){
		myBackgroundColorPicker = new ColorPicker();
		myBackgroundColorPicker.setOnAction(
				e -> mySandbox.setBackground( new Background( new BackgroundFill(
										myBackgroundColorPicker.getValue(),
										CornerRadii.EMPTY, 
										Insets.EMPTY
										))));
		mySandbox.getChildren().add(myBackgroundColorPicker);
	}

	@Override
	public void switchLanguage(Languages aLanguage) {
		// TODO Auto-generated method stub
		
	}


}