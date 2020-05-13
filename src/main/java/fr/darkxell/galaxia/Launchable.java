package fr.darkxell.galaxia;

import java.awt.Rectangle;

import fr.darkxell.galaxia.logs.Logger;
import fr.darkxell.galaxia.osbridge.contract.OSinputBridge;
import fr.darkxell.galaxia.osbridge.windows.WindowsinputBridge;
import fr.darkxell.galaxia.overlay.PGOverlay;
import fr.darkxell.galaxia.pcap.PGPacketReader;
import fr.darkxell.galaxia.runtime.PGRunInfo;

public class Launchable {

	public static int ontopcounter = 0;

	public static void main(String[] args) {
		Logger.i("Starting!");
		OSinputBridge inputbridge = new WindowsinputBridge();

		// Checks if PG is actually launched to do shit, aborts otherwise.
		Logger.i("Checking if Pirate Galaxy frame is visible...");
		final String framename = PGRunInfo.INSTANCE.checkFrameName(inputbridge);
		boolean pglaunched = framename != null;
		if (!pglaunched) {
			Logger.f("Pirate galaxy couldn't be found! Aborting...");
			return;
		}

		// Gets the window location at this time
		Rectangle position = inputbridge.getPosition(framename);
		Logger.d(position.toString());

		// Creates the move/resize updater
		PGOverlay overlay = new PGOverlay(position);
		overlay.gamesize = PGRunInfo.INSTANCE.getGameSize(position.width, position.height);
		Updater updater = new Updater(10) {

			@Override
			public void update() {
				Rectangle p = inputbridge.getPosition(framename);
				overlay.setLocation(p.x, p.y);
				overlay.setSize(p.width, p.height);
				overlay.gamesize = PGRunInfo.INSTANCE.getGameSize(p.width, p.height);

				if (ontopcounter >= 30) {
					overlay.refreshONTOP();
					ontopcounter = 0;
				} else
					ontopcounter++;

			}
		};
		updater.run();

		// Creates the PGOverlay ticker
		Updater ticker = new Updater(60) {

			@Override
			public void update() {
				overlay.tick();
			}
		};
		ticker.run();
		
		PGPacketReader packetreader = new PGPacketReader();
		packetreader.start();
	}

}
