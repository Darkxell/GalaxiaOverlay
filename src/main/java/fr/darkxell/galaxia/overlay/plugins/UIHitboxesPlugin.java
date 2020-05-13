package fr.darkxell.galaxia.overlay.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import fr.darkxell.galaxia.overlay.OverlayPlugin;
import fr.darkxell.galaxia.overlay.PGOverlay;
import fr.darkxell.galaxia.runtime.PGuiElement;
import fr.darkxell.galaxia.runtime.UIHitbox;

public class UIHitboxesPlugin extends OverlayPlugin {

	public UIHitboxesPlugin(PGOverlay parent) {
		super(parent);
	}

	private int framecounter = 0;

	@Override
	public void tick() {
		framecounter++;
	}

	@Override
	public void draw(Graphics2D g, JPanel panel) {
		g.setColor(Color.CYAN);
		g.drawString("Overlay test ( frame " + framecounter + " )", 40, 40);
		for (int i = 0; i < PGuiElement.values().length; i++) {
			PGuiElement element = PGuiElement.values()[i];
			Point ui = element.getRealPositionAtLocale(0, 0, parent.gamesize.width, parent.gamesize.height);
			g.drawRect(ui.x, ui.y, element.width, element.height);
		}

		g.setColor(Color.GREEN);
		for (int i = 0; i < UIHitbox.values().length; i++) {
			UIHitbox element = UIHitbox.values()[i];
			int tempX = element.getRelativeX(parent.gamesize.width),
					tempY = element.getRelativeY(parent.gamesize.height);
			g.drawRect(tempX, tempY, element.w, element.h);
		}

		// Outline the overlay frame
		g.setColor(Color.PINK);
		g.drawRect(0, 0, panel.getWidth() - 1, panel.getHeight() - 1);
	}

}
