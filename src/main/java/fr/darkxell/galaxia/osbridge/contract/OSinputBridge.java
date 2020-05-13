package fr.darkxell.galaxia.osbridge.contract;

import java.awt.Rectangle;
import java.util.List;

public interface OSinputBridge {

	public abstract List<String> listWindows();
	
	public abstract Rectangle getPosition(String windowname);
	
}
