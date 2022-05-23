import java.awt.Color;
import objectdraw.*;

public class Square {
	
	private FilledRect square;
	private FramedRect outline;
	private Text t;
	private int number;
	private int color;
	private boolean filled;

	public Square(double x, double y, double width, int color, int count, DrawingCanvas canvas) {
		square = new FilledRect(x, y, width, width, canvas);
		square.setColor(Color.white);
		number = count;
		filled = false;
		
		t = new Text(""+number,x,y,canvas);
		t.setFontSize(10);
		
		outline = new FramedRect(x,y,width,width,canvas);
		this.color = color;
		
		//fill();
	}
	
	public void fill() {
		square.setColor(new Color(color));
		outline.setColor(Color.white);
		filled = true;
	}
	
	public void hideText() {
		t.hide();
	}
	
	public void toggleOutline() {
		if(outline.getColor().equals(Color.white)) outline.setColor(Color.black);
		else outline.setColor(Color.white);	
	}
	
	public boolean filled() {
		return filled;
	}
	
	public int getColor() {
		return color;
	}
	
	public void makeNumVisible() {
		t.move(-t.getWidth(),0);
	}
	
	public FilledRect getRect() {
		return square;
	}
}
