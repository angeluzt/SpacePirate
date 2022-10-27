package utils;

import java.awt.Toolkit;
import java.util.concurrent.atomic.AtomicInteger;

public class Constants {
    public static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getSize().width;
    public static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getSize().height;
    
    public static final double NANO_SECOND = 1000000000;
    
    public static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    
    public static long SPRITE_DURATION = 70;
}
