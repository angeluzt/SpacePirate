package utils;

import java.awt.Color;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import enums.Sounds;

public class Constants {
	public static boolean SHOW_GRID = true;
	
    public static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getSize().width;
    public static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getSize().height;
    
    public static final double NANO_SECOND = 1000000000;
    
    public static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    
    public static long SPRITE_DURATION = 70;
    
    public static float MUSIC_LEVEL = 1;
    public static float SOUND_EFFECTS_LEVEL = 1;
    public static Map<Sounds, URL> sounds = new HashMap<>();
    
    // color when button disabled
    public static Color disabledBtnColor = new Color(79, 77, 71, 120);
}
