package fr.darkxell.galaxia.resources;

import java.awt.image.BufferedImage;

public abstract class ImagesSheet {

	protected BufferedImage sourcematerial;
	protected int tileWidth;
	protected int tileHeight;

	public ImagesSheet(BufferedImage src, int w, int h) {
		this.sourcematerial = src;
		this.tileHeight = h;
		this.tileWidth = w;
	}

	protected BufferedImage getSub(int x, int y) {
		return sourcematerial == null ? null
				: sourcematerial.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}

}
