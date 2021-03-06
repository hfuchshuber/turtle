package front_end.view_modules.turtleBox.turtleMovement;

class MovementCalculator {

	private int myWidth, myHeight;
	
	MovementCalculator(int aWidth,int aHeight) {
		myWidth = aWidth;
		myHeight = aHeight;
	}
	
	int getWidth() {
		return myWidth;
	}
	
	int getHeight() {
		return myHeight;
	}
	
	double translateXCoordinate(double aX) {
    	return aX + myWidth / 2;
    }
    
    
    double translateYCoordinate(double aY){
    	return - aY + myHeight / 2;
    }
	
}
