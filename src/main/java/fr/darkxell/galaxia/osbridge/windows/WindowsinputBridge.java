package fr.darkxell.galaxia.osbridge.windows;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.platform.WindowUtils;

import fr.darkxell.galaxia.osbridge.contract.OSinputBridge;

public class WindowsinputBridge implements OSinputBridge {

	public List<String> listWindows() {
		final List<String> windowNames = new ArrayList<String>();
		WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
			if (!desktopWindow.getTitle().isEmpty()) {
				windowNames.add(desktopWindow.getTitle());
			}
		});
		return windowNames;
	}

	@Override
	public Rectangle getPosition(String windowname) {
		final Rectangle rect = new Rectangle(0, 0, 0, 0);
		WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
			if (desktopWindow.getTitle().equals(windowname)) 
				rect.setRect(desktopWindow.getLocAndSize());
		});
		return rect;
	}

}
