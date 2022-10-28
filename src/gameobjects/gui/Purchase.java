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

public class Purchase extends GenericGui implements Clickable {

	private ButtonWithActive close;
	private ButtonWithActive question;
	private ButtonWithActive accept;
	
	private Icon amount;
	
	public Purchase(Point point, Size size, float xPercent, float yPercent, float downPercent) {
		super(ImageId.PAUSE_WINDOW, point, size, xPercent, yPercent, downPercent);

		ImageManager.addImage(ImageId.PAUSE_WINDOW, new Size(400, 600));

		WindowMatrix sections = new WindowMatrix();
		sections.addColumn(70f, 1, this.getPointInWindow(), this.getSizeInWindow());
		sections.addColumn(30f, 3, this.getPointInWindow(), this.getSizeInWindow());
		this.getInternalSquare().addInternalMatrix(sections);
		
		WindowSquare currentSquare = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0);

		Size newSize = new Size((int) (currentSquare.getSize().width * 0.7),(int) (currentSquare.getSize().width * 0.7));
		Point externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0).getPoint();
		Size externalSize = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(0).getSize();
		close = new ButtonWithActive(
					ImageId.WINDOW_ACCEPT_CLOSE_BTN, 
					ImageId.WINDOW_ACCEPT_CLOSE_A_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize
				);
		
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(1).getPoint();
		question = new ButtonWithActive(
					ImageId.WINDOW_ACCEPT_FAQ_BTN, 
					ImageId.WINDOW_ACCEPT_FAQ_ACT_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
		
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(1).getSquare(2).getPoint();
		accept = new ButtonWithActive(
					ImageId.WINDOW_ACCEPT_BTN, 
					ImageId.WINDOW_ACCEPT_A_BTN, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize.getSize()
				);
		
		currentSquare = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0);

		newSize = new Size((int) (currentSquare.getSize().width * 0.7),(int) (currentSquare.getSize().width * 0.3));
		externalPoint = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0).getPoint();
		externalSize = this.getInternalSquare().getMatrix().getRowByIndex(0).getSquare(0).getSize();
		amount = new Icon(
					ImageId.WINDOW_ACCEPT_TABLE, 
					Trigonometry.centerSquareInsideanother(externalPoint, externalSize, newSize), 
					newSize
				);
	}

	@Override
	public void drawElement(Graphics g) {
		super.drawElement(g);

		close.drawElement(g);
		question.drawElement(g);
		accept.drawElement(g);
		
		amount.drawElement(g);
	}

	@Override
	public boolean isElementClicked(Point point) {

		close.isElementClicked(point);
		question.isElementClicked(point);
		accept.isElementClicked(point);

		return false;
	}

}
