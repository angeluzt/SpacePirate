package enums;

import java.net.URL;

public enum Sounds {
	BUTTON_CLICK("Gui/button_click_1.wav"),
	COIN("coin.wav"),
	EXPLOSION("explosion.wav"),
	MAIN_BG_MUSIC("Backgroud_Music/music_zapsplat_space_trivia.wav");
	
	private String filename;
	
	Sounds(final String filename) {
		this.filename = filename;
	}
	
	public URL filepath() {
		return this.getClass().getResource("/Sounds/" + filename);
	}
}