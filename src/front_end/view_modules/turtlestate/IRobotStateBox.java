package front_end.view_modules.turtlestate;

import front_end.view_modules.IRobotAcceptor;
import front_end.view_modules.IViewModule;
import integration.languages.ILanguageSwitcher;
import integration.observe.IObserver;

public interface IRobotStateBox extends IViewModule, 
										ILanguageSwitcher, 
										IObserver,
										IRobotAcceptor
										{
	
	public int getRobotID();
}
