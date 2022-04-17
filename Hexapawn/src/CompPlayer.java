public class CompPlayer implements Player{

	private char color;
	private GameTree lastMove;
	
	public CompPlayer(char color) {
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public String toString() {
		return "Computer Player";
	}
	
	public Player play(GameTree node, Player opponent) {
		
		System.out.println(node.getBoard());
		
		if(node.getBoard().win(opponent.getColor())) {
			lastMove.remove();
			System.out.println(opponent.toString() + " won!");
			return opponent;
		}

		int options = node.getChildren().size();
		
		if(options==0) {
			System.out.println("Computer Resigns");
			lastMove.remove();
			return opponent;
		}
		
		int randNum = (int) (Math.random() * options);
		
		GameTree randTree = node.getChildren().get(randNum);
		System.out.println("Computer Player made the move: " + randTree.getMove());
		lastMove = randTree;
		
		return opponent.play(randTree, this);
	}
	
	public static void main(String[] args) {
		Player player1 = new CompPlayer(HexBoard.WHITE);
		Player player2 = new CompPlayer(HexBoard.BLACK);
		
		GameTree gt = new GameTree(new HexBoard(), HexBoard.WHITE, null);
		HexBoard board2 = new HexBoard(3,4);
		HexBoard board3 = new HexBoard(3,5);
		GameTree gt2 = new GameTree(board2, HexBoard.WHITE, null);
		GameTree gt3 = new GameTree(board3, HexBoard.WHITE, null);
		
		int player1wins = 0;
		int player2wins = 0;
		
		for(int i = 0; i<1000; i++) {
			Player winner = player1.play(gt3, player2);
			if(winner==player1) player1wins++;
			else player2wins++;
		}
		
		System.out.println(player1wins);
		System.out.println(player2wins);
		GameTree.treeBrowser(gt3);
	}
}
