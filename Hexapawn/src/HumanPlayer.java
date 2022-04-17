import java.util.Iterator;
import structure.ReadStream;
import structure5.Vector;

public class HumanPlayer implements Player{

	private char color;
	
	public HumanPlayer(char color) {
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public String toString() {
		return "Human Player";
	}
	
	public Player play(GameTree node, Player opponent) {
		
		System.out.println(node.getBoard());
		
		if(node.getBoard().win(opponent.getColor())) {
			System.out.println(opponent.toString() + " won!");
			return opponent;
		}
		
        Vector<GameTree> moves;
        ReadStream r = new ReadStream();
        int yourMove;
        
        moves = node.getChildren();
        Iterator<GameTree> i = moves.iterator();
        int j = 0;
        while (i.hasNext()) {
            System.out.println(j+". "+i.next().getMove());
            j++;
        }
        yourMove = r.readInt();

        GameTree next = moves.elementAt(yourMove);
        
        return opponent.play(next, this);
	}
	
	public static void main(String[] args) {
		HumanPlayer player1 = new HumanPlayer(HexBoard.WHITE);
		RandPlayer player2 = new RandPlayer(HexBoard.BLACK);
		
		GameTree gt = new GameTree(new HexBoard(), HexBoard.WHITE, null);
		Player winner = player1.play(gt, player2);
	}
}
