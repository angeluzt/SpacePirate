package gameobjects.textarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import commoninterfaces.Clickable;
import commoninterfaces.Drawable;
import gameobjects.GenericItem;
import gameobjects.gui.GenericGui;
import utils.Bounds;
import utils.Constants;
import utils.Size;

public class InfoTextArea extends GenericItem implements Drawable, Clickable  {

    private int fontSize;
    private Font font = new Font("Comic Sans MS", Font.BOLD, fontSize);
	private List<TextLine> strings = new ArrayList<>();
	private Scroll scroll;
	private boolean firstView = true;
	private double totalSize = 0;

	public InfoTextArea(Bounds bounds) {
		super(bounds);
		
		fontSize = (int)((this.getSize().width * 7) / 200);
		font = new Font("Comic Sans MS", Font.BOLD, fontSize);
		
		Size newScrollSize = new Size(20, this.getSize().height);
		Point newScrollPoint = new Point(this.getPoint().x + this.getSize().width - 20, this.getPoint().y);

		scroll = new Scroll(new Bounds(newScrollPoint, newScrollSize));
	}

	public InfoTextArea(Bounds bounds, String content) {
		super(bounds);
		
		this.splitContent(content);
		fontSize = (int)((this.getSize().width * 7) / 200);
		font = new Font("Arial Black", Font.BOLD, fontSize);
	}
	
	public void splitContent(String content) {
		short state = 0;

		StringBuilder currentLine = new StringBuilder();
		StringBuilder currentWord = new StringBuilder();
		// add empty space at the end to avoid validations
		content += " ";

		for(int index = 0; index < content.length(); index++) {
			switch (state) {
			// collect word
			case 0:
				if(!Character.isSpaceChar(content.charAt(index))) {
					//System.out.println(content.charAt(index));
					currentWord.append(content.charAt(index));
				} else {
					//System.out.println("Empty space");
					state = 1;
					index--;
				}
						
				break;

			// add line if current word & space is more than 45 characters
			case 1:
				// if we add the current word to current line, we have more than 45 characters
				if(currentLine.length() + currentWord.length() + 1 > 45) {
					this.strings.add(new TextLine(currentLine.toString()));

					currentLine.setLength(0);
					currentLine.append(currentWord.toString() + content.charAt(index));
					currentWord.setLength(0);
				// the current word + space is not higher than 45 characters
				} else {
					currentLine.append(currentWord.toString() + content.charAt(index));
					//System.out.println(currentLine);

					currentWord.setLength(0);
				}

				state = 0;
	
				break;
			}
		}
	}

	@Override
	public void drawElement(Graphics g) {
		g.setColor(Constants.disabledBtnColor);
		g.fillRect(this.getPoint().x, this.getPoint().y, this.getSize().width, this.getSize().height);

		scroll.drawElement(g);

		g.setColor(Color.WHITE);
        g.setFont(font);
        FontMetrics m = g.getFontMetrics(font);
        

        // if first time opened, then set the location to every line
        if(firstView) {
        	totalSize = fontSize * (m.getAscent() + m.getDescent()) / m.getAscent();
        	this.setLinesLocation(totalSize);
        }

		for (int i = 0; i < strings.size(); i++) {
			if(strings.get(i).getLocation().y < this.getPoint().y + this.getSize().height //) {
					&& strings.get(i).getLocation().y > this.getPoint().y) {
				g.drawString(strings.get(i).getLineText(), strings.get(i).getLocation().x, strings.get(i).getLocation().y);
			}
		}
	}
	
	private void setLinesLocation(double totalSize) {
		int currentLineHeight = 0;
		int textHeight = 0;
		for (int i = 0; i < strings.size(); i++) {
			currentLineHeight = (int)((this.getPoint().y + i * totalSize) + totalSize);
			textHeight += currentLineHeight;
			
			strings.get(i).setLocation(new Point(this.getPoint().x, currentLineHeight));
		}

		this.firstView = false;
		scroll.setScrollInternalHight(textHeight);
	}

	@Override
	public void removeComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isElementClicked(Point point, GenericGui currentUi) {
		if(this.strings.isEmpty()) {
			return;
		}

		if(scroll.downClicked(point)) {
			if(this.strings.get(this.strings.size() - 1).getLocation().y + this.totalSize > this.getPoint().y + this.getSize().height) {
				for (int i = 0; i < this.strings.size(); i++) {
					this.strings.get(i).decreaseSize((int) this.totalSize);
				}
			}
		} else if(scroll.upClicked(point)) {
			if(this.strings.get(0).getLocation().y - this.totalSize < this.getPoint().y) {
				for (int i = 0; i < this.strings.size(); i++) {
					this.strings.get(i).increaseSize((int) this.totalSize);
				}
			}
		}
	}
}
