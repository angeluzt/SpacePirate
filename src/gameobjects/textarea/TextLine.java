package gameobjects.textarea;

import java.awt.Point;

public class TextLine {
	private String lineText;
	private Point location;
	private boolean hiden;

	public TextLine(String lineText) {
		//this.location = location;
		this.lineText = lineText;
		//this.hiden = hiden;
	}
	
	public void increaseSize(int quantity) {
		location.y += quantity;
	}
	
	public void decreaseSize(int quantity) {
		location.y -= quantity;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getLineText() {
		return lineText;
	}

	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public boolean isHiden() {
		return hiden;
	}

	public void setHiden(boolean hiden) {
		this.hiden = hiden;
	}
}
