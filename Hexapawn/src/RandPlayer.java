public class RandPlayer implements Player{

	private char color;
	
	public RandPlayer(char color) {
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
	public String toString() {
		return "Random Player";
	}
	
	public Player play(GameTree node, Player opponent) {
		
		System.out.println(node.getBoard());
		
		if(node.getBoard().win(opponent.getColor())) {
			System.out.println(opponent.toString() + " won!");
			return opponent;
		}
		
		int options = node.getChildren().size();
		int randNum = (int) (Math.random() * options);
		
		GameTree randTree = node.getChildren().get(randNum);
		System.out.println("Random Player made the move: " + randTree.getMove());
		
		return opponent.play(randTree, this);
	}
	
	public static void main(String[] args) {
		
		RandPlayer player1 = new RandPlayer(HexBoard.WHITE);
		RandPlayer player2 = new RandPlayer(HexBoard.BLACK);
		
		GameTree gt = new GameTree(new HexBoard(), HexBoard.WHITE, null);
		Player winner = player1.play(gt, player2);
	}
}
