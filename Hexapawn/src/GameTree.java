/* Thought Questions:
 * 
 * 1)
 * A 3 x 4 board has 6003 board positions, and a 3 x 5 board has 215150 board positions.
 * I found the size, or the number of nodes, of each game tree by recursively traversing 
 * the tree and incrementing the count. Because the number of nodes in the tree 
 * corresponds to the total number of board positions, I found the number of board
 * positions by creating Game Trees with boards of size 3 x 4 and 3 x 5 and printing
 * out their sizes. 
 * 
 * 2)
 * H.E.R will win more frequently. My testing began by pitting a random player against a
 * computer player. If the computer player plays second, I found that after a large number
 * of trials (around 1000), the computer player has pruned the tree so that no matter where
 * the random player first moves, the computer player's move options all result in a winning strategy.
 * After pitting two computer players against each other, I found a similar result:
 * not only did the second player always win, but the first player has pruned its own tree to only
 * the root node, suggesting that every single move of the first computer player has resulted in a loss.
 * Both of these experiments support the conclusion that the second player has a winning strategy no matter where
 * the first player moves. Thus, because H.E.R moves second, H.E.R will win more frequently. 
 * 
 * For larger boards however, this conclusion does not seem to hold. For a 3 x 4 board, after pitting
 * 2 computer players against each other. the first player actually won more frequently. After looking at
 * the game tree, the first player has pruned its tree down to 2 moves. After either move,
 * the second player immediately resigns, suggesting that all moves from that position result in a loss.
 * A 3 x 5 board, after conducting the same test, produces very similar results, the only different being that
 * because the computer players take more trials to prune a larger game tree, the frequency of player 1 wins slightly 
 * increases. Thus, for larger boards, one player still seems to develop a winning strategy, however, that player is not
 * always player 2. 
 * 
 * 3)
 * The HexMove class would have an addition method that returns the mirror move, which can be calculated using
 * the board size, the to and from coordinates, and simple arithmetic. The HexBoard class would have
 * and additional instance variable that keeps track of the reverse board, using the reverse move method.
 * Each board in the GameTree would thus represent both a board state and its reflection, and pruning a GameTree
 * would also prune its reflection.
 */

import structure5.ReadStream;
import structure5.Vector;

public class GameTree {
	
	//HexBoard hex;
	private Vector<GameTree> children;
	private HexBoard hex;
	private GameTree parent;
	private char color;
	private HexMove move;
	
	public GameTree(HexBoard hex, char color, GameTree parent) {
		this.hex = hex;
		this.parent = parent;
		this.color = color;
		
		children = new Vector<GameTree>();
		char oppColor = (color==HexBoard.BLACK) ? HexBoard.WHITE : HexBoard.BLACK;
		
		if(!hex.win(oppColor)) {
			for(int i = 0; i<hex.moves(color).size(); i++) {
				HexMove move = (HexMove) hex.moves(color).get(i);
				GameTree child = new GameTree( new HexBoard(hex, move), oppColor, this);
				child.move = move;
				children.add(child);
			}
		}
	}
	
	public HexBoard getBoard() {
		return hex;
	}
	
	public HexMove getMove() {
		return move;
	}
	
	public Vector<GameTree> getChildren() {
		return children;
	}
	
	public GameTree getParent() {
		return parent;
	}
	
	public void remove() {
		if(parent != null) {
			parent.getChildren().remove(this);
		}	
	}
	
	public int getSize() {
		
		int size = 0;
		// if this is the root, set size to 1 in order to count itself
		if(parent==null) size = 1;
		
		for(GameTree child : children) {
			size += 1 + child.getSize();
		}
		return size;
	}
	
	public int numLeaves() {
		int leaves = 0;
		
		if(getChildren().size()==0) return 1;
		else {
			for(GameTree child : children) {
				leaves += child.numLeaves();
			}
			return leaves;
		}
	}
	
		/** Displays the Children of the Node
		* @param node The node whose children are to be printed
		* @post prints children of node
		**/
	public static void printChildren(GameTree node) {
		String s="";
		s=s+"\nParent: \n"+node.getBoard();
		Vector children = node.getChildren();
		for (int i=0; i<children.size(); i++)
		{
			GameTree child = (GameTree)(children.get(i));
			s=s+"Child " +i+": \n"+child.getBoard();
		}
		s=s+"\n**************** End of Children ******************* \n\n\n";
		
		System.out.println(s);
	}
	
	
	/** Method for browsing trees in list format
		NB:  This method is included for debugging purposes
		Invoking it causes the system to go into an infinite loop
		*@param gt The tree that is to be browsed
		*@post user may browse tree ad infinitum
		**/
	public static void treeBrowser(GameTree gt) {
		do{
			GameTree.printChildren(gt);
			System.out.print("Enter a node to display further children "+
							 "(-1 for parent): ");
			int index;
			ReadStream r = new ReadStream();
			index = r.readInt();
			if (index == -1 && gt.getParent() != null) gt = gt.getParent();
			else if (index != -1)
			{
				gt=(GameTree)(gt.getChildren().get(index));
			}
		} while (true);
	}
	
	/** Main method inclusing for debugging purposes.  
		* Displays the number of leaves and size of the tree
		* Then allows the user to transvers the tree, displaying
		* game boards as one does so
		**/
	public static void main(String args[]) {
		HexBoard board = new HexBoard(3,3);
		HexBoard board2 = new HexBoard(3,4);
		HexBoard board3 = new HexBoard(3,5);
		GameTree gt = new GameTree(board, HexBoard.WHITE, null);
		GameTree gt2 = new GameTree(board2, HexBoard.WHITE, null);
		GameTree gt3 = new GameTree(board3, HexBoard.WHITE, null);
		
		System.out.println(gt.getSize());
		System.out.println(gt.numLeaves());
		
		System.out.println(gt2.getSize());
		System.out.println(gt3.getSize());
		
		GameTree.treeBrowser(gt);
	}
}
