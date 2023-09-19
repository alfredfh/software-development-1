
public class game {

	//instance variables
	private final static int GRID_WIDTH = 10;
	private final static int GRID_HEIGHT = 10;
		
	//main method
	public static void main(String[] args) {
		
		//Array of ships defining the length
	    Ship[] ships = new Ship[] {
	            new Ship(5),
	            new Ship(4),
	            new Ship(3),
	            new Ship(2),
	            new Ship(1),
	            
	    };
	    
	    //instance of 2d array of battleship game board
	    BattleshipBoard battleshipBoard = new BattleshipBoard(GRID_WIDTH, GRID_HEIGHT);
	    //calling the method to place the ships on the board
	    battleshipBoard.place(ships);
	    //print the board on the console if user wants
		//battleshipBoard.printBoard();
	    
	    //calling method from Display class to start game
		Display.printMenu();
		//asking user for their name
		Users.Name();
		//calling method that displays the menu of options.
		
		battleshipBoard.mainMenu();
		 
		
		}//end class
		
	

}//end main
