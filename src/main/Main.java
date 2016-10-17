package main;

import applicationController.ApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for creating an animated scene.
 * 
 */
public class Main extends Application {

    private ApplicationController myApplicationController;
    private final int SIZE = 600;
    /**
     * Sets the Game up at the beginning.
     * 
     * @return nothing
     */
    @Override
    public void start (Stage s) {
        myApplicationController = new ApplicationController();
        //s.setTitle(myApplicationController.getTitle());

        Scene scene = myApplicationController.init(SIZE, SIZE);

        s.setScene(scene);
        s.show();

    }
    
    public static void main (String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

}