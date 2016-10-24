package appScene.turtleBox;

import appScene.IViewer;
import integration.IObserver;
import model.robot.IViewRobot;

public interface ITurtleBox extends IViewer, IObserver  {

    /**
     * Draw the moves from robot over all turtles
     * @param Robot from back-end (this will later be of type robot)
     */
    public void addRobot(IViewRobot robot);
    
}
