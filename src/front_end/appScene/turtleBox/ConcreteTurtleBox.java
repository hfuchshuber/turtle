package front_end.appScene.turtleBox;

import back_end.model.robot.IViewRobot;
import integration.languages.Languages;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

class ConcreteTurtleBox implements ITurtleBox {

	ScrollPane myScroller;
	Pane mySandbox;
	ColorPicker myBackgroundColorPicker;
	IViewRobot myRobot;
	ImageView myTurtle;
	Group root;
	Canvas drawingCanvas;
	GraphicsContext gc;
	private int myWidth;
	private int myHeight;
	TurtleMovement myTurtMove;
	private boolean turtDisplayed;

	private final int CHARACTER_SIZE = 50;
	
	ConcreteTurtleBox(int width, int height){
	        myWidth = width;
	        myHeight = height;
	        myTurtMove = new TurtleMovement(this, myWidth, myHeight);
	        root = new Group();
	        drawingCanvas = new Canvas(width, height);
	        gc = drawingCanvas.getGraphicsContext2D();
		myScroller = new ScrollPane();
		myScroller.setPrefSize(width, height);
		myScroller.setMinSize(width, height);
		myScroller.setMaxSize(width, height);
		
		mySandbox = new Pane();
                mySandbox.setPrefSize(width, height);
                mySandbox.setMinSize(width, height);
                mySandbox.setMaxSize(width, height);

		myScroller.setContent(mySandbox);
		mySandbox.getChildren().add(drawingCanvas);
                initBox(width, height);
		
	}
	
	public GraphicsContext getGC() {
	    return gc;
	}
	
	public ImageView getTurtle() {
	    return myTurtle;
	}
	
	public void initBox(int width, int height) {
	           initColorPicker();
	           loadDefaultTurtle(width, height);
	}
	
	public void loadDefaultTurtle(int width, int height) {
	    Image character = new Image(getClass().getClassLoader().getResourceAsStream("turtle.png"));
	    myTurtle = new ImageView(character);
	    myTurtle.setFitHeight(CHARACTER_SIZE);
	    myTurtle.setFitWidth(CHARACTER_SIZE);
	    myTurtle.setX(width/2);
	    myTurtle.setY(height/2);
	    turtDisplayed = true;
	    mySandbox.getChildren().add(myTurtle);
	}
	
	@Override
	public void reset() {
	    gc.clearRect(0, 0, myWidth, myHeight);
	    removeTurtle();
	    initBox(myWidth, myHeight);
	}
	
	public void showTurtle() {
	    if(!turtDisplayed) {
	        mySandbox.getChildren().add(myTurtle);
	    }
	    turtDisplayed = true;
	}
	
	public void removeTurtle() {
	    turtDisplayed = false;
	    mySandbox.getChildren().remove(myTurtle);
	}

	@Override
	public Node getInstanceAsNode() {
		return myScroller;
	}

	@Override
	public void giveRobot(IViewRobot aRobot) {
		myRobot = aRobot;

	}
	
	public IViewRobot getRobot() {
	    return myRobot;
	}
	
	@Override
	public void update() {
		if(myRobot == null) {
		    return;
		}
		
		myTurtMove.updateTurtle();
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
