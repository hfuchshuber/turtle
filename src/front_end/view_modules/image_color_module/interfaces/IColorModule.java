package front_end.view_modules.image_color_module.interfaces;

import front_end.acceptor.IColorPaletteAcceptor;
import front_end.view_modules.IViewModule;
import integration.observe.IObserver;
import javafx.scene.paint.Color;

public interface IColorModule extends IViewModule, IColorPaletteAcceptor {

	/**
	 * Returns current color mapped to the argument
	 * 
	 * If integer argument is not in map, returns default color
	 * 
	 * @param aColorId
	 * @return
	 */
	public Color getColor(int aColorId);
	
	public int getColorAmount();
	
	public void newColorRow(Color aColor);
	
}
