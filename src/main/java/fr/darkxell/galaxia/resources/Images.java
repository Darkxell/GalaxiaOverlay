package fr.darkxell.galaxia.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.darkxell.galaxia.overlay.PGOverlay;

public enum Images {

	PlanetBG("planetbg.png"),
	GalaxyLogo("galaxy.png"),
	Aitems("aitems.png");

	public final BufferedImage i;

	private Images(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(PGOverlay.LOADER.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		i = image;
	}

}
