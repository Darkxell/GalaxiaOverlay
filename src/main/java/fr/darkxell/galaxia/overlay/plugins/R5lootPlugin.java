package fr.darkxell.galaxia.overlay.plugins;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import fr.darkxell.galaxia.logs.Logger;
import fr.darkxell.galaxia.overlay.OverlayPlugin;
import fr.darkxell.galaxia.overlay.PGOverlay;
import fr.darkxell.galaxia.resources.Images;
import fr.darkxell.galaxia.runtime.UIHitbox;
import fr.darkxell.galaxia.theory.TheoryAPI;
import fr.darkxell.galaxia.theory.TheoryPlanet;

public class R5lootPlugin extends OverlayPlugin {

	public R5lootPlugin(PGOverlay parent) {
		super(parent);
	}

	/** Selected name last scan */
	private String planetname = "No planet selected";

	
	private long lastAPIupdate = 0l;
	@Override
	public void tick() {
		planetname = UIHitbox.MAIN_target_name.ocr(1000, parent);
		if(System.currentTimeMillis() >= lastAPIupdate + 300000 || lastAPIupdate == 0) {
			// Updates the theory api local cache every 5 minutes
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					Logger.i("Started refreshing Theory api...");
					TheoryAPI.refreshPlanets();
					Logger.i("Finished Theory refresh in " + ((float)(System.currentTimeMillis() - start)/1000f) + " seconds.");
				}
			});
			t.start();
			lastAPIupdate = System.currentTimeMillis();
		}
	}

	@Override
	public void draw(Graphics2D g, JPanel panel) {
//		g.drawImage(UIHitbox.preProcess(UIHitbox.MAIN_target_name.capture(parent)), 0, 0, UIHitbox.MAIN_target_name.w * 3, UIHitbox.MAIN_target_name.h * 3, null);
		if (isSirusPlanet(planetname)) {
			TheoryPlanet pla = TheoryAPI.getPlanet(planetname.trim());

			int offsetX = panel.getWidth() - Images.PlanetBG.i.getWidth() - 5,
					offsetY = panel.getHeight() - Images.PlanetBG.i.getHeight() - 5;
			g.drawImage(Images.PlanetBG.i, offsetX, offsetY, null);

			g.setColor(Color.WHITE);
			Font backupfont = g.getFont();
			g.setFont(new Font("TimesRoman", Font.BOLD, 15));
			g.drawString(planetname, offsetX + 30, offsetY + 18);
			g.setFont(backupfont);

			if (pla == null) {
				g.drawString("Unknown planet!", offsetX + 5, offsetY + 80);
				g.drawString("No one seems to be there.", offsetX + 5, offsetY + 105);
				g.drawString("Please input the blueprints on Theory if you know them!", offsetX + 5, offsetY + 117);
			} else {
				for (int i = 0; i < pla.recap.length; i++) {
					if (pla.recap[i].startsWith("Amarna"))
						g.setColor(Color.CYAN);
					else if (pla.recap[i].startsWith("Soris"))
						g.setColor(Color.ORANGE);
					else if (pla.recap[i].startsWith("Giza"))
						g.setColor(Color.GREEN);
					else if (!pla.recap[i].startsWith(" "))
						g.setColor(Color.LIGHT_GRAY);
					g.drawString(pla.recap[i], offsetX + 5, offsetY + 40 + (10 * i));
					g.setColor(Color.WHITE);
				}

			}

		}
	}

	/**
	 * Array containing the different syllabus used in sirius's planets. Planets are
	 * made of 2 to 6 randomly picked ones.
	 */
	private static final String[] PlanetSY = new String[] { "am", "ath", "bal", "bi", "bis", "cos", "do", "em", "eq",
			"gar", "ge", "ha", "he", "hi", "id", "ig", "iv", "jah", "ker", "lbo", "lin", "mi", "mon", "mus", "nan",
			"nos", "os", "pho", "re", "rian", "sys", "ta", "ton", "tor", "us", "xe", "yn", "ze" };

	/**
	 * Predicate that returns true if the parsed name is very likely a sirius planet
	 * name.
	 */
	private static boolean isSirusPlanet(String name) {
		name = name.toLowerCase();
		int currentindex = 0, sylcount = 0;

		for (int syl = 0;; syl++) {
			boolean currentsylexists = false;
			for (int i = 0; i < PlanetSY.length; i++) {
				if (sylcount >= 7)
					return false;
				if (name.regionMatches(currentindex, PlanetSY[i], 0, PlanetSY[i].length())) {
					currentsylexists = true;
					currentindex += PlanetSY[i].length();
					sylcount++;
					break;
				}
			}
			if (!currentsylexists)
				return syl >= 2 && name.length() < (currentindex + 3);

		}
	}

}
