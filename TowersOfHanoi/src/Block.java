import objectdraw.*;
import java.awt.Color;

public class Block {

	private FilledRect block;
	private double width;
	private double height;
	private int blockNum;
	
	// total number of blocks (3-7)
	private static int numBlocks = 3;
	// total height of all blocks (constant)
	private final int totalHeight = 250;
	// array of colors
	private final Color[] colors = {Color.red, Color.orange, Color.yellow, 
			Color.green, Color.blue, Color.magenta, Color.cyan};
	
	public Block(Location center, int blockNum, DrawingCanvas canvas) {
		this.blockNum = blockNum;
		// center is the bottom middle point of the block
		
		// calculate height of each block
		height = totalHeight/numBlocks;
		// set width of block using its number
		switch(blockNum) {
			case 1: width = 180; break;
			case 2: width = 140; break;
			case 3: width = 100; break;
			case 4: width = 70; break;
			case 5: width = 40; break;
			case 6: width = 30; break;
			case 7: width = 20; break;
		}
		// set color using its number
		Color c = colors[blockNum-1];
		block = new FilledRect(center.getX() - width/2, center.getY() - height, width, height, canvas);
		block.setColor(c);
	}
	
	public double getWidth() {
		return block.getWidth();
	}
	
	public double getHeight() {
		return block.getHeight();
	}
	
	// sets total number of blocks (from 3-7)
	public static void setNumBlocks(int num) {
		if(num <= 7 && num >= 3) {
			numBlocks = num;
		}
	}
	
	public void move(double dx, double dy) {
		block.move(dx, dy);
	}
	
	// moves center bottom of the block to specified coords
	public void moveTo(double x, double y) {
		block.moveTo(x-width/2, y - height);
	}
	
	public void erase() {
		block.removeFromCanvas();
	}
	
	public boolean contains(Location pointer) {
		return block.contains(pointer);
	}
	
	public boolean onPole(Pole p) {
		return block.overlaps(p.getBeam());
	}
	
	public static int getNumBlocks() {
		return numBlocks;
	}
	
	public int getBlockNum() {
		return blockNum;
	}	
	
	public void changeColor() {
		block.setColor(Color.yellow);
	}

	public String toString() {
		int num = numBlocks - blockNum + 1;
		return "" + num;
	}
}
