package gameobjects.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Drawable;
import enums.ImageId;
import gameobjects.GenericItem;
import gameobjects.matrix.WindowMatrix;
import gameobjects.matrix.WindowRow;
import gameobjects.matrix.WindowSquare;
import utils.ImageManager;
import utils.Size;
import utils.Utils;

public class GenericGui extends GenericItem implements Drawable {

	private ImageId windowId;
	private ImageId windowTitle;
	private WindowSquare internalSquare;

	private Point pointInWindow;
	private Size sizeInWindow;

	public GenericGui(ImageId imageId, Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(point, size);
		this.windowId = imageId;
		
		this.pointInWindow = new Point(
				(int)(this.getPoint().getX() + (this.getSize().width * xPercent)), 
				(int)(this.getPoint().getY() + (this.getSize().height * yPercent))
		);

		this.sizeInWindow = new Size(
				(int)(this.getSize().width - (this.getSize().width * xPercent) * 2), 
				(int)(this.getSize().height - (this.getSize().height * downPercent))
		);
		
		// TODO: Delete once testing is completed
		//ImageManager.addImage(imageId, "/Images/Gui/Pause/Window.png", new Size(400, 600));

		// Upgrade
		/*internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		WindowMatrix sections = new WindowMatrix();
		//sections.addColumn(100f, 3, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 2, this.getPointInWindow(), this.getSizeInWindow());
		this.internalSquare.addInternalMatrix(sections);*/

		// Hangar
		internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		/*WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f * 0.5f, 2, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f * 0.25f, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f * 0.25f, 4, this.getPointInWindow(), this.getSizeInWindow());
		
		this.internalSquare.addInternalMatrix(sections);*/
		
		//Point buttonsPoint = internalSquare.getMatrix().getRowByIndex(0).getSquare(1).getPoint().getLocation();
		//Size buttonsSize = internalSquare.getMatrix().getRowByIndex(0).getSquare(1).getSize().getSize();
		
		/*WindowSquare buttons = new WindowSquare(buttonsPoint, buttonsSize);
		WindowMatrix buttonsMatrix = new WindowMatrix();
			buttonsMatrix.addColumn(17.5f, 1, buttonsPoint, buttonsSize);
			buttonsMatrix.addColumn(17.5f, 1, buttonsPoint, buttonsSize);
			buttonsMatrix.addColumn(17.5f, 1, buttonsPoint, buttonsSize);
			buttonsMatrix.addColumn(17.5f, 1, buttonsPoint, buttonsSize);
			buttonsMatrix.addColumn(30f, 1, buttonsPoint, buttonsSize);

		buttons.addInternalMatrix(buttonsMatrix);

		internalSquare.getMatrix().getRowByIndex(0).getSquare(1).addInternalMatrix(buttonsMatrix);*/
		
		// Pause
		//internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		/*WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f / 3, 2, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3, 4, this.getPointInWindow(), this.getSizeInWindow());
		
		this.internalSquare.addInternalMatrix(sections);*/
		
		// Settings
		/*internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 5, 3, this.getPointInWindow(), this.getSizeInWindow());
		
		this.internalSquare.addInternalMatrix(sections);*/
		
		// You Win
		/*internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f / 3, 3, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3 / 2, 2, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3 / 2, 2, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3, 3, this.getPointInWindow(), this.getSizeInWindow());
		
		this.internalSquare.addInternalMatrix(sections);*/
		
		// Accept
		/*internalSquare = new WindowSquare(this.pointInWindow, this.sizeInWindow); 
		WindowMatrix sections = new WindowMatrix();
		//sections.addColumn(100f, 3, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 2, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 2, 3, this.getPointInWindow(), this.getSizeInWindow());
		
		this.internalSquare.addInternalMatrix(sections);*/
	}
	
	public Point getPointInWindow() {
		return this.pointInWindow;
	}
	
	public Size getSizeInWindow() {
		return sizeInWindow;
	}

	@Override
	public void drawElement(Graphics g) {
		g.drawImage(ImageManager.getImage(this.windowId), 
				(int)this.getPoint().getX(), 
				(int)this.getPoint().getY(), 
				this.getSize().width, 
				this.getSize().height, null);

		if(internalSquare != null) {
			Utils.drawGridSystem(g, internalSquare.getMatrix());
		}
	}

	public ImageId getWindowId() {
		return windowId;
	}

	public void setWindowId(ImageId windowId) {
		this.windowId = windowId;
	}

	public WindowSquare getInternalSquare() {
		return internalSquare;
	}

	public void setInternalSquare(WindowSquare internalSquare) {
		this.internalSquare = internalSquare;
	}

	public void setPointInWindow(Point pointInWindow) {
		this.pointInWindow = pointInWindow;
	}

	public void setSizeInWindow(Size sizeInWindow) {
		this.sizeInWindow = sizeInWindow;
	}
	
}
