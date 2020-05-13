package fr.darkxell.galaxia.osbridge.windows;

import fr.darkxell.galaxia.osbridge.contract.OSFrameInfo;

public class WindowsFrameInfo implements OSFrameInfo{

	@Override
	public int getMarginHorizontal() {
		return 3;
	}

	@Override
	public int getHeaderHeight() {
		return 26;
	}

	@Override
	public int getMarginBottom() {
		return 3;
	}

}
