package gameobjects.textarea;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import commoninterfaces.Drawable;
import gameobjects.GenericItem;
import utils.Bounds;

public class InfoTextArea extends GenericItem implements Drawable {

	private String content;

	public InfoTextArea(Bounds bounds, String content) {
		super(bounds);
		
		this.content = content;
	}

	@Override
	public void drawElement(Graphics g) {
		/*Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f)
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);*/
	}

	@Override
	public void removeComponents() {
		// TODO Auto-generated method stub
		
	}

}
