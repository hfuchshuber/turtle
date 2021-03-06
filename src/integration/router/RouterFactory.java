package integration.router;

import front_end.appScene.ApplicationScene;

/**
 * Factory class for Router. Hides instantiation
 * 
 * @author George Bernard
 */
public class RouterFactory {

	private RouterFactory(){
		// Does Nothing
	}
	
	/**
	 * 	Returns a new instance of IRouter
	 *  
	 * @param aAppScene - the application scene
	 * @return new instance of IRouter
	 */
	public static IRouter build(ApplicationScene aAppScene){
		return new ConcreteRouter(aAppScene);
	}
	
}
