package fr.darkxell.galaxia.runtime;

import java.util.List;

import fr.darkxell.galaxia.logs.Logger;
import fr.darkxell.galaxia.osbridge.contract.OSFrameInfo;
import fr.darkxell.galaxia.osbridge.contract.OSinputBridge;
import fr.darkxell.galaxia.osbridge.windows.WindowsFrameInfo;

/** Singleton containing information about the running PG instance. */
public class PGRunInfo {

	public static final PGRunInfo INSTANCE = new PGRunInfo();

	private PGRunInfo() {
	}

	/**
	 * @return pirate galaxy's frame name. Will return null if it cannot be found.
	 */
	public String checkFrameName(OSinputBridge inputbridge) {
		List<String> windowslist = inputbridge.listWindows();
		String framename = null;
		for (int i = 0; i < windowslist.size(); ++i) {
			Logger.d("Checking windows : " + windowslist.get(i));
			if (windowslist.get(i).contains("PirateGalaxy") && windowslist.get(i).contains("Version")) {
				framename = windowslist.get(i);
				Logger.i("Found frame : " + framename);
				break;
			}
		}
		return framename;
	}

	/**
	 * Computes and returns the most probable current gamesize. Note that this
	 * method may not return the actual size, especially if the frame is in
	 * fullscreen, but will return a very close approximation matching on of pirate
	 * galaxy's preset resolution (100% accurate for most players).
	 */
	public GameSize getGameSize(int width, int height) {
		OSFrameInfo frameinfo = new WindowsFrameInfo();
		width -= frameinfo.getMarginHorizontal() * 2;
		height -= frameinfo.getHeaderHeight() + frameinfo.getMarginBottom();
		GameSize closest = null;
		int closestDistance = Integer.MAX_VALUE;
		for (GameSize s : GameSize.values()) {
			int localdistance = s.distance(width, height);
			if (closest == null || closestDistance >= localdistance) {
				closest = s;
				closestDistance = localdistance;
			}
		}
		return closest;
	}

	public enum GameSize {

		s800x600(800, 600, "800 x 600 (4:3)"), s960x600(960, 600, "960 x 600 (16:10)"),
		s1024x768(1024, 768, "1024 x 768 (4:3)"), s1060x600(1060, 768, "1060 x 600"),
		s1067x600(1067, 600, "1067 x 600 (16:9)"), s1152x864(1152, 864, "1152 x 864 (4:3)"),
		s1176x664(1176, 664, "1176 x 664 (16:9)"), s1280x720(1280, 720, "1280 x 720 (16:9)"),
		s1280x768(1280, 768, "1280 x 768 (5:3)"), s1280x800(1360, 800, "1280 x 800 (16:10)"),
		s1280x960(1280, 960, "1280 x 960 (4:3)"), s1280x1024(1280, 1024, "1280 x 1024"),
		s1360x768(1360, 768, "1360 x 768 (16:9)"), s1366x768(1366, 768, "1366 x 768 (16:9)"),
		s1440x900(1440, 900, "1440 x 900 (16:10)"), s1600x900(1600, 900, "1600 x 900 (16:9)"),
		s1600x1024(1600, 1024, "1600 x 1024"), s1680x1050(1680, 1050, "1680 x 1050 (16:10)"),
		s1768x992(1768, 992, "1768 x 992"), s1920x1080(1920, 1080, "1920 x 1080 (16:9)");

		public final int width;
		public final int height;
		public final String name;

		private GameSize(int w, int h, String n) {
			this.width = w;
			this.height = h;
			this.name = n;
		}

		int distance(GameSize size) {
			return Math.abs(width - size.width) + Math.abs(height - size.height);
		}

		int distance(int w, int h) {
			return Math.abs(width - w) + Math.abs(height - h);
		}

		@Override
		public String toString() {
			return name;
		}

	}

}
