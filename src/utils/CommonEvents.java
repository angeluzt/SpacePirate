package utils;

import gameobjects.gui.GenericGui;

public class CommonEvents {

	public static void closeGame() {
		System.exit(0);
	}
	
	public static void closeWindowOpenedOnTop(GenericGui mainUI, GenericGui topWindowUI) {
		topWindowUI.setVisible(false);
		topWindowUI.setFocused(false);
		
		mainUI.setFocused(true);
	}
	
	public static void openWindowOnTop(GenericGui mainUI, GenericGui topWindowUI) {
		topWindowUI.setVisible(true);
		topWindowUI.setFocused(true);
		
		mainUI.setFocused(false);
	}
}
