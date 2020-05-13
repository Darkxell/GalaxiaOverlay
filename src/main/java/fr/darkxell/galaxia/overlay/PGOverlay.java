package fr.darkxell.galaxia.overlay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.darkxell.galaxia.overlay.plugins.AncientHUE;
import fr.darkxell.galaxia.overlay.plugins.GalaxiaPlugin;
import fr.darkxell.galaxia.overlay.plugins.R5lootPlugin;
import fr.darkxell.galaxia.runtime.PGRunInfo.GameSize;

@SuppressWarnings("unused")
public class PGOverlay {

	private JWindow frame;
	private TransPane pane;
	public GameSize gamesize = GameSize.s800x600;
	private int globalPosX, globalPosY;

	public static final ClassLoader LOADER = PGOverlay.class.getClassLoader();

	private ArrayList<OverlayPlugin> plugins = new ArrayList<OverlayPlugin>(10);

	public PGOverlay(Rectangle info) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame = new JWindow();
		frame.setBackground(new Color(0, 0, 0, 0));
		this.pane = new TransPane(info.width, info.height, this);
		frame.add(this.pane);
		frame.setAlwaysOnTop(true);
		this.setLocation(info.x, info.y);
		frame.pack();
		frame.setVisible(true);

		// this.plugins.add(new UIHitboxesPlugin(this));
		this.plugins.add(new R5lootPlugin(this));
		this.plugins.add(new GalaxiaPlugin(this));
		//this.plugins.add(new AncientHUE(this));
	}

	public void setLocation(int x, int y) {
		globalPosX = x;
		globalPosY = y;
		frame.setLocation(x, y);
	}

	public int getGlobalPosX() {
		return this.globalPosX;
	}

	public int getGlobalPosY() {
		return this.globalPosY;
	}

	public void refreshONTOP() {
		// This is gimmicky, blame microsoft.
		frame.setAlwaysOnTop(false);
		frame.setAlwaysOnTop(true);
	}

	public void setSize(int width, int height) {
		this.frame.setSize(width, height);
		this.pane.customResize(width, height);
	}

	/** Called 60 times per second by an independent updater. */
	public void tick() {
		for (int i = 0; i < plugins.size(); i++)
			plugins.get(i).tick();
		pane.repaint();
	}

	private class TransPane extends JPanel {

		private static final long serialVersionUID = -8878819377156064029L;
		private int width, height;
		private final PGOverlay parent;

		public TransPane(int width, int height, PGOverlay parent) {
			setOpaque(false);
			setLayout(new GridBagLayout());
			this.width = width;
			this.height = height;
			this.parent = parent;
		}

		public void customResize(int width, int height) {
			this.width = width;
			this.height = height;
			this.setSize(width, height);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(this.width, this.height);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();

			for (int i = 0; i < parent.plugins.size(); i++) {
				parent.plugins.get(i).draw(g2d, this);
			}

			g2d.dispose();
		}

	}

}
