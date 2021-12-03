import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

public class Fleet {
	
	private static final int INVADER_ROW = 6;
	private static final int INVADER_COL = 9;
	private Invader[][] fleet;
	
	public Fleet ( Image a, Image b, Image c, Image d, Image e, Image f, DrawingCanvas canvas ) {
		
		fleet = new Invader [ INVADER_ROW ][ INVADER_COL ];
		Image i;
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			
			if (row == 0) i = a;
			else if(row == 1) i = b;
			else if(row == 2) i = c;
			else if(row == 3) i = d;
			else if(row == 4) i = e;
			else i = f;
			
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
				
				fleet[row][col] = new Invader(i, 40*(col) + 20*(col+1), 60*row, canvas);
			}
			
			
		}
		
		
	}
	
	public Invader[][] getFleet() {
		return fleet;
	}
	
	public void addFleetTarget(Mothership m) {
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
				fleet[row][col].addinvaderTarget(m.getImage());
			}
		}
	}
	
	public void toggleRunning() {
		
		for ( int row = 0 ; row < fleet.length ; row++ ) {
			
			for ( int col = 0 ; col < fleet[row].length ; col++ ) {
				
				fleet[row][col].toggleRunning();
			}
		}
	}
	
	
}
