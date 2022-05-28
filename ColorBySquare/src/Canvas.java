import objectdraw.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Canvas extends WindowController {
	
	private final String file = "colorfulcat.jpeg";
	
	private static final int WIDTH = 1100;
	private static final int HEIGHT = 800;
	
	private static final int IMGWIDTH = 600;
	private static final int IMGHEIGHT = 600;
	
	private static final int PALATEWIDTH = 20;
	private static final int PADDING = 20;
	
	private int selectedColor;
	private Square[] palate;
	private SquareArray sq;
	private Square lastColor;
	
	private Text winText;
	
	private FramedRect inc;
	private FramedRect dec;

	public void begin() {
		
		canvas.disableAutoRepaint();
		
		BufferedImage in;
		try {
			in = ImageIO.read(new File(file));
			
			BufferedImage scaled = SquareArray.toBufferedImage( 
					in.getScaledInstance(IMGWIDTH,IMGHEIGHT,Image.SCALE_REPLICATE) );
			
			sq = new SquareArray(scaled, canvas);
			
			// add gui features
			palate = new Square[sq.getMap().keySet().size()];
			int x = 650;
			int y = 50;
			
			winText = new Text("Picture Completed!", WIDTH/2-200, HEIGHT-150, canvas);
			winText.setFontSize(50);
			winText.hide();
			
			for(int i = 0; i<palate.length; i++) {
				int color = (int) sq.getMap().keySet().toArray()[i];
				int number = sq.getMap().get(color);
				Square s = new Square(x, y, PALATEWIDTH, color, number, canvas);
				palate[i] = s;
				s.fill();
				s.makeNumVisible();
				
				x += PALATEWIDTH + PADDING;
				if(x > WIDTH-PADDING) {
					y += PADDING;
					x = 650;
				}
			}
			
			int buttonWidth = 80;
			inc = new FramedRect(canvas.getWidth()-2*PADDING-2*buttonWidth, PADDING, buttonWidth, PADDING, canvas);
			Text incText = new Text("Easier", canvas.getWidth()-PADDING-2*buttonWidth, PADDING, canvas);
			dec = new FramedRect(canvas.getWidth()-PADDING-buttonWidth, PADDING, buttonWidth, PADDING, canvas);
			Text decText = new Text("Harder", canvas.getWidth()-buttonWidth, PADDING, canvas);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		canvas.repaint();
	}
	
	public void onMouseClick(Location pointer) {
		for(Square option : palate) {
			if(option.getRect().contains(pointer)) {
				if(lastColor!=null) {
					lastColor.toggleOutline();
				}
				selectedColor = option.getColor();
				lastColor = option;
				option.toggleOutline();
			}
		}
		for(Square[] row : sq.getArray()) {
			for(Square s : row) {
				if(s.getRect().contains(pointer) && s.getColor()==selectedColor && !s.filled()) {
					s.fill();
					s.hideText();
					sq.inc();
					// check for win condition
					if(sq.completed()) {
						winText.show();
					}
				}
			}
		}
		
		if(inc.contains(pointer)) {
			SquareArray.incWidth();
			canvas.clear();
			begin();
		}
		if(dec.contains(pointer)) {
			SquareArray.decWidth();
			canvas.clear();
			begin();
		}
	}
	
	public void onMouseDrag(Location pointer) {
		for(Square[] row : sq.getArray()) {
			for(Square s : row) {
				if(s.getRect().contains(pointer) && s.getColor()==selectedColor && !s.filled()) {
					s.fill();
					s.hideText();
					sq.inc();
					// check for win condition
					if(sq.completed()) {
						winText.show();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Canvas().startController(WIDTH, HEIGHT);
	}
}
