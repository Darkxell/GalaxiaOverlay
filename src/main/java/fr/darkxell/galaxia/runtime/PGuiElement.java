package fr.darkxell.galaxia.runtime;

import java.awt.Point;

import fr.darkxell.galaxia.osbridge.contract.OSFrameInfo;
import fr.darkxell.galaxia.osbridge.windows.WindowsFrameInfo;

/**
 * Enum that lists the different main UI elements in the game, and offers
 * utility methods to compute their position in game.
 */
public enum PGuiElement {

	UI_LEFTPANNEL(419, 429, Anchors.ANCHOR_TOPLEFT),
	UI_RIGHTPANNEL(529, 429, Anchors.ANCHOR_TOPRIGHT),
	UI_TOPMENU(365, 21, Anchors.ANCHOR_TOP),
	UI_MAINUI(828, 127, Anchors.ANCHOR_BOTTOM);

	public final int width;
	public final int height;
	public final byte anchor;

	private PGuiElement(int width, int height, byte anchor) {
		this.width = width;
		this.height = height;
		this.anchor = anchor;
	}

	/**
	 * Gets the position on the global overlay given a local point in this UI
	 * element.
	 */
	public Point getRealPositionAtLocale(int x, int y, int pgWindowWidth, int pgWindowHeight) {
		OSFrameInfo frameinfo = new WindowsFrameInfo();
		switch (anchor) {
		case Anchors.ANCHOR_TOPLEFT:
			return new Point(x + frameinfo.getMarginHorizontal(), y + frameinfo.getHeaderHeight());
		case Anchors.ANCHOR_TOP:
			return new Point((pgWindowWidth / 2) - (UI_TOPMENU.width / 2) + x, y + frameinfo.getHeaderHeight());
		case Anchors.ANCHOR_TOPRIGHT:
			return new Point(pgWindowWidth - UI_RIGHTPANNEL.width + x + frameinfo.getMarginHorizontal(),
					y + frameinfo.getHeaderHeight());
		case Anchors.ANCHOR_BOTTOM:
			return new Point(pgWindowWidth / 2 - 393 + frameinfo.getMarginHorizontal() + x,
					pgWindowHeight - UI_MAINUI.height + frameinfo.getHeaderHeight() + y);
		default:
			return new Point(0, 0);
		}
	}

}
