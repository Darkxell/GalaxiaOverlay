package fr.darkxell.galaxia.runtime;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import fr.darkxell.galaxia.overlay.PGOverlay;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

/**
 * Enum that lists the different game UI element hitboxes relative to a
 * PGuiElement object.
 */
public enum UIHitbox {

	LOG_open(PGuiElement.UI_LEFTPANNEL, 2, 1, 30, 35),
	LOG_close(PGuiElement.UI_LEFTPANNEL, 2, 38, 30, 20),
	LOG_tab_mission(PGuiElement.UI_LEFTPANNEL, 41, 40, 66, 17),
	LOG_tab_log(PGuiElement.UI_LEFTPANNEL, 108, 40, 67, 17),
	LOG_log_latestentry(PGuiElement.UI_LEFTPANNEL, 36, 61, 370, 31),

	TOP_profile(PGuiElement.UI_TOPMENU, 2, 1, 30, 20),
	TOP_friends(PGuiElement.UI_TOPMENU, 34, 1, 30, 20),
	TOP_guild(PGuiElement.UI_TOPMENU, 66, 1, 30, 20),
	TOP_squad(PGuiElement.UI_TOPMENU, 98, 1, 30, 20),
	TOP_leaderboards(PGuiElement.UI_TOPMENU, 130, 1, 30, 20),
	TOP_shop(PGuiElement.UI_TOPMENU, 209, 1, 30, 20),
	TOP_news(PGuiElement.UI_TOPMENU, 241, 1, 30, 20),
	TOP_options(PGuiElement.UI_TOPMENU, 273, 1, 30, 20),
	TOP_help(PGuiElement.UI_TOPMENU, 305, 1, 30, 20),
	TOP_quit(PGuiElement.UI_TOPMENU, 337, 1, 30, 20),

	MAIN_chatwindowbutton(PGuiElement.UI_MAINUI, 209, 17, 30, 18),
	MAIN_leavechat(PGuiElement.UI_MAINUI, 241, 17, 30, 18),
	MAIN_chatname(PGuiElement.UI_MAINUI, 273, 17, 77, 18),
	MAIN_chatinput(PGuiElement.UI_MAINUI, 368, 13, 213, 25),
	MAIN_life(PGuiElement.UI_MAINUI, 657, 115, 22, 12),
	MAIN_rank(PGuiElement.UI_MAINUI, 539, 114, 20, 10),
	MAIN_target_life(PGuiElement.UI_MAINUI, 8, 115, 22, 12),
	MAIN_target_rank(PGuiElement.UI_MAINUI, 38, 57, 20, 13),
	MAIN_target_name(PGuiElement.UI_MAINUI, 34, 84, 142, 12),
	MAIN_target_info(PGuiElement.UI_MAINUI, 119, 104, 30, 19),
	MAIN_energy(PGuiElement.UI_MAINUI, 210, 95, 98, 15),
	MAIN_cryonite(PGuiElement.UI_MAINUI, 332, 95, 99, 15),
	MAIN_gold(PGuiElement.UI_MAINUI, 455, 95, 91, 15),
	MAIN_item_1(PGuiElement.UI_MAINUI, 214, 48, 37, 37),
	MAIN_item_2(PGuiElement.UI_MAINUI, 260, 48, 37, 37),
	MAIN_item_3(PGuiElement.UI_MAINUI, 306, 48, 37, 37),
	MAIN_item_4(PGuiElement.UI_MAINUI, 352, 48, 37, 37),
	MAIN_item_5(PGuiElement.UI_MAINUI, 398, 48, 37, 37),
	MAIN_item_6(PGuiElement.UI_MAINUI, 444, 48, 37, 37),
	MAIN_item_7(PGuiElement.UI_MAINUI, 490, 48, 37, 37),
	MAIN_item_8(PGuiElement.UI_MAINUI, 536, 48, 37, 37),
	MAIN_drone_1(PGuiElement.UI_MAINUI, 582, 43, 26, 26),
	MAIN_drone_2(PGuiElement.UI_MAINUI, 608, 43, 26, 26),
	MAIN_drone_3(PGuiElement.UI_MAINUI, 582, 69, 26, 26),
	MAIN_drone_4(PGuiElement.UI_MAINUI, 608, 69, 26, 26),
	MAIN_drone_5(PGuiElement.UI_MAINUI, 582, 95, 26, 26),
	MAIN_drone_6(PGuiElement.UI_MAINUI, 608, 95, 26, 26),

	;

	public final PGuiElement parent;
	public final int x, y, w, h;

	private UIHitbox(PGuiElement parent, int x, int y, int w, int h) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public int getRelativeX(int pgWindowWidth) {
		return (int) parent.getRealPositionAtLocale(x, 0, pgWindowWidth, 0).getX();
	}

	public int getRelativeY(int pgwindowHeight) {
		return (int) parent.getRealPositionAtLocale(0, y, 0, pgwindowHeight).getY();
	}

	private String lastOCR;
	private long lastOCRtime = 0;

	/**
	 * Returns an OCR string of the element.
	 * 
	 * @param mili a confiance range. If an ocr was performed in the given last
	 *             miliseconds, it will return a copy of that ocr instead of
	 *             performing a new one.
	 */
	public String ocr(int mili, PGOverlay overlay) {
		if (mili > 0 && System.currentTimeMillis() <= lastOCRtime + mili && lastOCRtime > 0)
			return lastOCR;
		BufferedImage capture = capture(overlay);
		capture = preProcess(capture);
		try {
			ITesseract instance = new Tesseract();
			instance.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
			instance.setTessVariable("user_defined_dpi", "70");
			String result = instance.doOCR(capture);
			lastOCRtime = System.currentTimeMillis();
			lastOCR = result;
			return result;
		} catch (Exception | Error e) {
			e.printStackTrace();
			lastOCRtime = System.currentTimeMillis();
			lastOCR = "error";
			return lastOCR;
		}
	}

	public BufferedImage capture(PGOverlay overlay) {
		return capture(overlay.getGlobalPosX(), overlay.getGlobalPosY(), overlay.gamesize.width,
				overlay.gamesize.height);
	}

	public BufferedImage capture(int overlayX, int overlayY, int pgWindowWidth, int pgWindowHeight) {
		Robot robot;
		Rectangle screenRect = new Rectangle(overlayX + getRelativeX(pgWindowWidth),
				overlayY + getRelativeY(pgWindowHeight), w, h);
		try {
			robot = new Robot();
			// Logger.d("Taking screencapture at :" + screenRect);
			return robot.createScreenCapture(screenRect);
		} catch (AWTException e) {
			e.printStackTrace();
			return new BufferedImage(screenRect.width, screenRect.height, BufferedImage.TYPE_3BYTE_BGR);
		}
	}

	/**
	 * Preprocesses an image for OCR. Makes white text black on white, removes
	 * noise...
	 */
	public static BufferedImage preProcess(BufferedImage capture) {
		return criaImagemCinza(capture);
	}

	private static BufferedImage criaImagemCinza(BufferedImage input) {
		int inputW = input.getWidth(), inputH = input.getHeight();
		// Create a new buffer to BYTE_GRAY
		BufferedImage img = new BufferedImage(inputW, inputH, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = img.getRaster();
		WritableRaster rasterJPEG = input.getRaster();
		// Foreach pixel we transform it to Gray Scale binary and put it on the same
		// image
		for (int h = 0; h < inputH; h++)
			for (int w = 0; w < inputW; w++) {
				int[] p = new int[4];
				rasterJPEG.getPixel(w, h, p);
				p[0] = (int) (0.3 * p[0]);
				p[1] = (int) (0.59 * p[1]);
				p[2] = (int) (0.11 * p[2]);
				int y = p[0] + p[1] + p[2];
				raster.setSample(w, h, 0, y > 170 ? 0 : 255);
			}
		return img;
	}
}
