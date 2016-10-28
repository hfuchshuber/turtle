package front_end.view_modules.shape_color_module.interfaces;

import front_end.view_modules.ILanguageSwitcher;
import front_end.view_modules.IViewModule;
import javafx.scene.paint.Color;

public interface IColorModule extends IViewModule, ILanguageSwitcher {

	/**
	 * Returns current color mapped to the argument
	 * 
	 * If integer argument is not in map, returns default color
	 * 
	 * @param aColorId
	 * @return
	 */
	public Color getColor(int aColorId);
	
}
