package fr.darkxell.galaxia.overlay;

import java.awt.Graphics2D;

import javax.swing.JPanel;

/**Describes the behavior of a widget plugin.*/
public abstract class OverlayPlugin {

	public final PGOverlay parent;
	
	public OverlayPlugin(PGOverlay parent) {
		this.parent = parent;
	}
	
	public abstract void tick();
	
	public abstract void draw(Graphics2D g,JPanel panel);
	
}
