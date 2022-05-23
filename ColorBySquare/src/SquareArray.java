import objectdraw.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class SquareArray {
	
	private final int BOXWIDTH = 15;
	private final int THRESHOLD = 30;
			
	private Square[][] arr;
	private Map<Integer, Integer> palate;
	
	private int fillCount;
	private boolean completed;
	
	public SquareArray(BufferedImage bimg, DrawingCanvas canvas) {
		
		palate = new HashMap<Integer, Integer>();
		fillCount = 0;
		completed = false;
		
		//BufferedImage bimg = toBufferedImage(img);
		
		arr = new Square[bimg.getWidth()/BOXWIDTH][bimg.getWidth()/BOXWIDTH];

		int i = 0;
		int j = 0;
		int count = 0;
		for(int x = 0; x<=bimg.getWidth()-BOXWIDTH; x+=BOXWIDTH) {
			for(int y = 0; y<=bimg.getHeight()-BOXWIDTH; y+=BOXWIDTH) {
				
				// get subimage
				BufferedImage subimg = bimg.getSubimage(x, y, BOXWIDTH, BOXWIDTH);
				
				// get scaled instance
				BufferedImage scaled = toBufferedImage( subimg.getScaledInstance(1,1,Image.SCALE_REPLICATE) );
				
				// find color of scaled instance
				int c = scaled.getRGB(0, 0);
				
				int number = 0;
				// check for a close existing color
				for(Integer rgb : palate.keySet()) {
					if(colorsAreClose(new Color(c), new Color(rgb), THRESHOLD)) {
						c = rgb;
						number = palate.get(c);
						break;
					}
				}
				
				
				if(palate.getOrDefault(c, -1)==-1) {
					count++;
					number = count;
				}
				
				palate.put(c, number);
				
				// create filled rect
				Square square = new Square(x, y, BOXWIDTH, c, number, canvas);
				
				// add to array
				arr[i][j]=square;
				
				j++;
			}
			j = 0;
			i++;
		}
	}
	
	public Map<Integer,Integer> getMap() {
		return palate;
	}
	
	public boolean colorsAreClose(Color a, Color z, int threshold) {
		int r = (int)a.getRed() - z.getRed();
		int g = (int)a.getGreen()- z.getGreen();
		int b = (int)a.getBlue() - z.getBlue();
		return (r*r + g*g + b*b) <= threshold*threshold;
	}
	
	public static BufferedImage toBufferedImage(Image img) {
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    //BufferedImage bimage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public Square[][] getArray() {
		return arr;
	}
	
	public void inc() {
		fillCount++;
		if(fillCount==(arr.length*arr[0].length)) {
			completed = true;
		}
	}
	
	public boolean completed() {
		return completed;
	}
	
	public static void main(String[] args) {
		
	}
}

