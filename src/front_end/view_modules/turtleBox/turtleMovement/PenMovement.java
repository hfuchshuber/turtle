package front_end.view_modules.turtleBox.turtleMovement;

import front_end.view_modules.turtleBox.ITurtleBox;
import integration.observe.IObserver;
import javafx.scene.canvas.GraphicsContext;


public class PenMovement implements IObserver {

    private TurtleMovement myMovement;
    
    public PenMovement(TurtleMovement myTurtMove) {
        myMovement = myTurtMove;
    }
    // TODO: What I need from back-end: previous coordinates to draw the line
    // In this case: curX and curY
    private GraphicsContext gc;

    @Override
    public void update() {
        // TODO Auto-generated method stub
        //Need to get TurtleBox
        //gc.setStroke(Color.BLACK);
        //gc.setLineWidth(5);
        //gc.strokeLine(curX, curY, translatedXCoordinate(), translatedYCoordinate());
    }
    
    protected void drawWithPen(ITurtleBox myTurtleBox) {
        //TODO: Fix turtleBox being null
        gc = myTurtleBox.getGC();
    }

}
