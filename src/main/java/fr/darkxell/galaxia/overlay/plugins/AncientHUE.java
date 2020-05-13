package fr.darkxell.galaxia.overlay.plugins;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import fr.darkxell.galaxia.overlay.OverlayPlugin;
import fr.darkxell.galaxia.overlay.PGOverlay;
import fr.darkxell.galaxia.resources.sheets.AncientItemsSheet;
import fr.darkxell.galaxia.runtime.UIHitbox;

/**
 * A plugin that makes ancient items have a 180d rotated HUE instead of 0d. Gold
 * prints have a default of a 90d rotation. This does not affect the scrolling
 * menus in the shop, only hangar items and the main bottom HUD.
 */
public class AncientHUE extends OverlayPlugin {

	public AncientHUE(PGOverlay parent) {
		super(parent);
	}

	@Override
	public void tick() {
	}

	@Override
	public void draw(Graphics2D g, JPanel panel) {
		// BufferedImage i1 = UIHitbox.MAIN_item_1.capture(parent);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_BLASTER),
				UIHitbox.MAIN_item_1.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_1.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_COLLECTOR),
				UIHitbox.MAIN_item_2.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_2.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_DROID),
				UIHitbox.MAIN_item_3.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_3.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_AFTERBURNER),
				UIHitbox.MAIN_item_4.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_4.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_AIM),
				UIHitbox.MAIN_item_5.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_5.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_SHIELD),
				UIHitbox.MAIN_item_6.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_6.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_REPAIRTARGET),
				UIHitbox.MAIN_item_7.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_7.getRelativeY(parent.gamesize.height), null);
		g.drawImage(AncientItemsSheet.INSTANCE.getItem(AncientItemsSheet.OFFSET_STUN),
				UIHitbox.MAIN_item_8.getRelativeX(parent.gamesize.width),
				UIHitbox.MAIN_item_8.getRelativeY(parent.gamesize.height), null);
	}

}
