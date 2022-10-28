package gameobjects.gui;

import java.awt.Graphics;
import java.awt.Point;

import commoninterfaces.Clickable;
import enums.ImageId;
import gameobjects.button.ButtonWithActive;
import gameobjects.button.Icon;
import gameobjects.matrix.WindowMatrix;
import gameobjects.matrix.WindowSquare;
import utils.ImageManager;
import utils.Size;
import utils.Trigonometry;

public class Pause extends GenericGui implements Clickable {

	private ButtonWithActive settings;
	private ButtonWithActive menu;
	private ButtonWithActive hangar;
	private ButtonWithActive accept;
	
	private Icon pauseTxt;
	private Icon scoreTxt, pauseTable;
	
	public Pause(Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(ImageId.PAUSE_WINDOW, point, size, xPercent, yPercent, downPercent);

		ImageManager.addImage(ImageId.PAUSE_WINDOW, new Size(400, 600));

		WindowMatrix sections = new WindowMatrix();
		sections.addColumn(100f / 3, 2, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(100f / 3, 4, this.getPointInWindow(), this.getSizeInWindow());
		this.getInternalSquare().addInternalMatrix(sections);
		
		WindowSquare currentSquare = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(0);

		Size newSize = new Size((int) (currentSquare.getSize().width * 0.8),(int) (currentSquare.getSize().height * 0.7));
		Point externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(0).getPoint();
		Size externalSize = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(0).getSize();
		settings = new ButtonWithActive(
					ImageId.SETINGS_BTN, 
					ImageId.SETINGS_ACT_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize
				);
		
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(1).getPoint();
		menu = new ButtonWithActive(
					ImageId.MENU_BTN, 
					ImageId.MENU_ACT_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
		
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(2).getPoint();
		hangar = new ButtonWithActive(
					ImageId.HANGAR_BTN, 
					ImageId.HANGAR_ACT_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
		
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(2).getSquare(3).getPoint();
		accept = new ButtonWithActive(
					ImageId.ACCEPT_BTN, 
					ImageId.ACCEPT_ACT_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);

		currentSquare = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0);
		newSize = new Size((int) (currentSquare.getSize().width * 0.5),(int) (currentSquare.getSize().height * 0.7));
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0).getPoint();
		externalSize = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0).getSize();
		pauseTxt = new Icon(
					ImageId.MAP_TXT,
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
		
		currentSquare = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0);
		newSize = new Size((int) (currentSquare.getSize().width * 0.7),(int) (currentSquare.getSize().height * 0.35));
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0).getPoint();
		externalSize = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0).getSize();
		scoreTxt = new Icon(
					ImageId.SCORE_TXT,
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);

		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(1).getPoint();
		externalSize = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(1).getSize();
		pauseTable = new Icon(
					ImageId.PAUSE_TABLE,
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		settings.drawElement(g);
		menu.drawElement(g);
		hangar.drawElement(g);
		accept.drawElement(g);
		
		pauseTxt.drawElement(g);
		scoreTxt.drawElement(g);
		pauseTable.drawElement(g);
	}

	@Override
	public boolean isElementClicked(Point point) {

		settings.isElementClicked(point);
		menu.isElementClicked(point);
		hangar.isElementClicked(point);
		accept.isElementClicked(point);

		return false;
	}
}
