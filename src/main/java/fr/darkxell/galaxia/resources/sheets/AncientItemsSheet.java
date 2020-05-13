package fr.darkxell.galaxia.resources.sheets;

import java.awt.image.BufferedImage;

import fr.darkxell.galaxia.resources.Images;
import fr.darkxell.galaxia.resources.ImagesSheet;

public class AncientItemsSheet extends ImagesSheet {

	public static final AncientItemsSheet INSTANCE = new AncientItemsSheet();

	private AncientItemsSheet() {
		super(Images.Aitems.i, 39, 39);
	}

	public BufferedImage getItem(int offset) {
		return this.getSub(0, offset);
	}

	public BufferedImage getCooldown(int offset) {
		return this.getSub(1, offset);
	}

	public BufferedImage getTrans(int offset) {
		return this.getSub(2, offset);
	}

	public static final int OFFSET_BLASTER = 0;
	public static final int OFFSET_ROCKET = 1;
	public static final int OFFSET_SHIELD = 2;
	public static final int OFFSET_DROID = 3;
	public static final int OFFSET_FIELD = 4;
	public static final int OFFSET_AFTERBURNER = 5;
	public static final int OFFSET_BOMB = 6;
	public static final int OFFSET_COLLECTOR = 7;
	public static final int OFFSET_AIM = 8;
	public static final int OFFSET_REPAIRTARGET = 9;
	public static final int OFFSET_SCRAMBLER = 10;
	public static final int OFFSET_TAUNT = 11;
	public static final int OFFSET_AGGROBEACON = 12;
	public static final int OFFSET_RESURECTOR = 13;
	public static final int OFFSET_PROTECTOR = 14;
	public static final int OFFSET_DOME = 15;
	public static final int OFFSET_STUN = 16;
	public static final int OFFSET_THERMOBLAST = 17;
	public static final int OFFSET_BUFF = 18;
	public static final int OFFSET_SPEED = 19;
	public static final int OFFSET_PERFORATOR = 20;
	public static final int OFFSET_SNIPER = 21;
	public static final int OFFSET_ORBITALSTRIKE = 22;
	public static final int OFFSET_ATTACKDROID = 23;
	public static final int OFFSET_RADAR = 24;
	public static final int OFFSET_STICKYBOMB = 25;
	public static final int OFFSET_MINE = 26;
	public static final int OFFSET_BUFFTURRET = 27;
	public static final int OFFSET_REPAIRTURRET = 28;
	public static final int OFFSET_CHAIN = 29;
	public static final int OFFSET_MAGNET = 30;
	public static final int OFFSET_LEAP = 31;
	public static final int OFFSET_CORRUPTION = 32;
	public static final int OFFSET_COVER = 33;
	public static final int OFFSET_SCOPESHIFTER = 34;
	public static final int OFFSET_INVERTER = 35;
	public static final int OFFSET_DEFLECTIONDROID = 36;

}
