import objectdraw.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Stack;

public class TowersOfHanoi extends WindowController implements KeyListener{
	
	private FilledRect background;
	private Text undoText;
	private FramedRect undoBut;
	private Text saveText;
	private FramedRect saveBut;
	private Text resetText;
	private FramedRect resetBut;
	private Text autoplayText;
	private FramedRect autoplayBut;
	
	private Block selectedBlock;
	private Location current;
	private Pole previousPole;
	private Stack<Move> moveHistory;
	private MoveAction ma;
	private int numMoves;
	
	private final static int WIDTH = 800;
	private final static int HEIGHT = 800;
	
	private Pole p1;
	private Pole p2;
	private Pole p3;
	
	public void begin() {
		background = new FilledRect(0, 0, WIDTH, HEIGHT, canvas);
		background.setColor(Color.WHITE);
		
		undoText = new Text("Undo", canvas.getWidth() - 100, 50, canvas);
		undoBut = new FramedRect(undoText.getX() - 10, undoText.getY() - 10, 
				undoText.getWidth() + 20, undoText.getHeight() + 20, canvas);
		
		saveText = new Text("Save", canvas.getWidth() - 200, 50, canvas);
		saveBut = new FramedRect(saveText.getX() - 10, saveText.getY() - 10, 
				saveText.getWidth() + 20, saveText.getHeight() + 20, canvas);
		
		autoplayText = new Text("Autoplay", canvas.getWidth()-300, 50, canvas);
		autoplayBut = new FramedRect(autoplayText.getX() - 10, autoplayText.getY() - 10, 
				autoplayText.getWidth() + 20, autoplayText.getHeight() + 20, canvas);
		
		resetText = new Text("Reset", canvas.getWidth()-400, 50, canvas);
		resetBut = new FramedRect(resetText.getX() - 10, resetText.getY() - 10, 
				resetText.getWidth() + 20, resetText.getHeight() + 20, canvas);
		
		p1 = new Pole(new Location(150, 600), "p1", canvas);
		p2 = new Pole(new Location(400, 600), "p2", canvas);
		p3 = new Pole(new Location(650, 600), "p3", canvas);
		
		moveHistory = new Stack<Move>();
		
		setBlocks(3);
		
		requestFocus();
		addKeyListener(this);
		canvas.addKeyListener(this);
	}
	
	public void setBlocks(int num) {
		p1.reset();
		p2.reset();
		p3.reset();
		Block.setNumBlocks(num);
		for(int i = 1; i <= num; i++) {
			p1.add(new Block(new Location(0,0), i, canvas));
		}
		ma = new MoveAction(num, p1, p3, p2);
	}
	
	public void autoplay() {
		for(Move m : ma.getAuto()) {
			m.getEnd().add(m.getStart().getTop());
			m.getStart().remove();
			//pause(100);
		}
	}
	
	public void undo() {
		Move lastMove = moveHistory.pop();
		lastMove.getStart().add(lastMove.getEnd().getTop());
		lastMove.getEnd().remove();
	}
	
	public void reset() {
		p1.reset();
		p2.reset();
		p3.reset();
		selectedBlock = null;
		previousPole = null;
		for (int i = moveHistory.size(); i>0; i--) {
			moveHistory.pop();
		}
		setBlocks(Block.getNumBlocks());
	}
	
	public void save() {
		
	}
	
	public boolean win() {	
		return p3.win();
	}
	
	public void keyTyped(KeyEvent e) {
		if (Character.getNumericValue(e.getKeyChar())>=3 && Character.getNumericValue(e.getKeyChar())<=7) {
			setBlocks(Character.getNumericValue(e.getKeyChar()));
		}
	}
	
	public void onMousePress(Location point) {
		current = point;
		// if mouse is in a block, set selected block to that block
		if (!p1.isEmpty() && p1.getTop().contains(point)) {
			selectedBlock = p1.getTop();
			previousPole = p1;
			previousPole.remove();
		}
		else if (!p2.isEmpty() && p2.getTop().contains(point)) {
			selectedBlock = p2.getTop();
			previousPole = p2;
			previousPole.remove();
		}
		else if (!p3.isEmpty() && p3.getTop().contains(point)) {
			selectedBlock = p3.getTop();
			previousPole = p3;
			previousPole.remove();
		}
		
		else if (autoplayBut.contains(point)) {
			autoplay();
		}
		else if (undoBut.contains(point)) {
			undo();
		}
		else if (resetBut.contains(point)) {
			reset();
		}
		else if (saveBut.contains(point)) {
			save();
		}
	}
	
	public void onMouseDrag(Location point) {
		if (selectedBlock != null) {
			double x = point.getX()-current.getX();
			double y = point.getY()-current.getY();
			selectedBlock.move(x,y);
			current = point;
		}
	}
	
	public void onMouseRelease(Location point) {
		if (selectedBlock != null) {
			if(selectedBlock.onPole(p1) && p1.validMove(selectedBlock)) {
				p1.add(selectedBlock);
				moveHistory.push(new Move(previousPole, p1));
			}
			else if(selectedBlock.onPole(p2) && p2.validMove(selectedBlock)) {
				p2.add(selectedBlock);
				moveHistory.push(new Move(previousPole, p2));
			}
			else if(selectedBlock.onPole(p3) && p3.validMove(selectedBlock)) {
				p3.add(selectedBlock);
				moveHistory.push(new Move(previousPole, p3));
			}
			else {
				previousPole.add(selectedBlock);
			}
			previousPole = null;
			selectedBlock = null;
		}
	}
	
	public void simulate() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to Towers of Hanoi");
		System.out.print("Number of Blocks: ");
		Pole startPole = new Pole(new Location(0,0), canvas);
		Pole endPole = new Pole(new Location(0,0), canvas);

		
		boolean win = false;
		begin();
		
		setBlocks(s.nextInt());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
		while(win == false) {
			
			boolean setup = true;
			
			while (setup) {
				
				System.out.print("Tower to Move From: ");
				int start = s.nextInt();
				
				if(start == 0 && !p1.isEmpty()) {
					startPole = p1;
					selectedBlock = p1.getTop();
					setup = false;
				}
				else if(start == 1 && !p2.isEmpty()) {
					startPole = p2;
					selectedBlock = p2.getTop();
					setup = false;
				}
				else if(start == 2 && !p3.isEmpty()) {
					startPole = p3;
					selectedBlock = p3.getTop();
					setup = false;
				}
				else {
					System.out.println("Invalid Input");
				}
			}
			
			boolean setup2 = true;
			
			while (setup2) {
				
				System.out.print("Tower to Move To: ");
				int end = s.nextInt();
				
				if(end == 0) {
					endPole = p1;
					setup2 = false;
				}
				else if(end == 1) {
					endPole = p2;
					setup2 = false;

				}
				else if(end == 2) {
					endPole = p3;
					setup2 = false;

				}
				else {
					System.out.println("Invalid Input");
				}
			}
			
			endPole.add(startPole.remove());
			System.out.println("Current Game: ");
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);
			
			if (win()) {
				System.out.println("You Won!");
				win = true;
			}
		}
	}
	
	public static void main(String[] args) {
		
		if(args.length > 0 && args[0] == "--cli") {
			new TowersOfHanoi().simulate();
		}
		else {
			new TowersOfHanoi().startController(WIDTH, HEIGHT);
		}
	}

	public void keyPressed(KeyEvent e) {
		// Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
	}
	
	public void onMouseClick(Location point) {
		// Auto-generated method stub
	}
}




