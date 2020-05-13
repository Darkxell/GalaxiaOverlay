package fr.darkxell.galaxia.overlay.plugins;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import fr.darkxell.galaxia.overlay.OverlayPlugin;
import fr.darkxell.galaxia.overlay.PGOverlay;
import fr.darkxell.galaxia.resources.Images;
import fr.darkxell.galaxia.runtime.PGuiElement;

public class GalaxiaPlugin extends OverlayPlugin {

	public GalaxiaPlugin(PGOverlay parent) {
		super(parent);
	}

	@Override
	public void tick() {
	}

	@Override
	public void draw(Graphics2D g, JPanel panel) {
		Point ui = PGuiElement.UI_LEFTPANNEL.getRealPositionAtLocale(0, 0, parent.gamesize.width, parent.gamesize.height);
		g.drawImage(Images.GalaxyLogo.i, ui.x + 43, ui.y + 10, null);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Running Galaxia overlay", ui.x + 70, ui.y + 15);
	}

}
