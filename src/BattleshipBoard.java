

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BattleshipBoard {
	
	//instance variables
    private final int rows;
    private final int cols;
    final int MAX_GUESSES = 10;
    final int MAX_POINTS = 30;
    int pointsCounter =0;
    private char[][] board;
    public static int numOfGuess = 0;
    
    //array of ships names and points
    Ship[] ships = new Ship[] {
            new Ship("Aircraft Carrier", 2),
            new Ship( "Battleship", 4),
            new Ship("Submarine", 6),
            new Ship("Destroyer", 8),
            new Ship("Patrol Boat", 10),
    };
      
    //2d array of chars with rows and cols
    public BattleshipBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new char[rows][cols];
    }

    //placing ships method
    public void place(Ship[] ships) {
    
    //sort array to place larger ship first
    Arrays.sort(ships, new Comparator<Ship>() {

        @Override
        public int compare(Ship s1, Ship s2) {
            return Integer.valueOf(s1.getLength()).compareTo(Integer.valueOf(s2.getLength()));
        }
    });
    
    //looping on the board in and placing a char
    for (int j = 0; j < rows; j++)
        for (int k = 0; k < cols; k++)
            board[j][k] = '-'; // Empty position
    //new 2d array of chars of checked
    char[][] checked = new char[rows][cols];
    Random random = new Random();
    
    //looping the ships lengths, rows and cols to check board
    for (int i = ships.length - 1; i >=0; i--) {
        for (int j = 0; j < rows; j++)
            for (int k = 0; k < cols; k++)
                checked[j][k] = 'U'; // Unchecked position
        boolean placed = false;
        while (! placed) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            if (checked[r][c] == 'U') {
                checked[r][c] = 'C'; // Checked position
                if (board[r][c] == '-') {
                    int direction = random.nextInt(4);
                    // 0 = North; 1 = East; 2 = South; 3 = West;
                    if (canPlace(ships[i], r, c, direction)) {
                        place(ships[i], r, c, direction);
                        placed = true;
                        }//close if place direction
                    } //close if random direction              
                }//close if checked position
            }//close while not placed what to do
        }//close nested loop based on ship length and unchecked positions
    }// close place method

    //method to place the direction of the ships with switch case depending on the size, row and ols
    private void place(Ship ship, int row, int col, int direction) {
        int size = ship.getLength();
        switch (direction) {
        case 0: // Upward
            for (int  i = row; i >= row - (size - 1); i--)
                board[i][col] = 'S';
                      
            break;

        case 1: // to the right
            for (int i = col; i <= col + (size - 1); i++)
                board[row][i] = 'S';
            break;

        case 2: // downward
            for (int i = row; i <= row + (size - 1); i++)
                board[i][col] = 'S';
            break;

        default: // to the left
            for (int i = col; i >= col - (size - 1); i--) 
                board[row][i] = 'S';
            break;
        }//end switch
        
        //looping on the board and changing the char depending on the ship length
        for (int i = 0; i < board.length; i++) { 
    	    for (int j = 0; j < board[i].length; j++) { 
		if (ship.getLength() == 5 && board[i][j] == 'S' ) {
			board[i][j] = 'A';
		}else if (ship.getLength() == 4 && board[i][j] == 'S' ) {
			board[i][j] = 'B';
		}else if (ship.getLength() == 3 && board[i][j] == 'S' ) {
			board[i][j] = 'M';
		}else if (ship.getLength() == 2 && board[i][j] == 'S' ) {
			board[i][j] = 'D';
		}else if (ship.getLength() == 1 && board[i][j] == 'S' ) {
			board[i][j] = 'P';
		}
   
    	    }//end for loop that changes chars col
        }//end for loop that changes chars row
    }//end method

    //boolean to see if the ships can be places depending if there is room available
    private boolean canPlace(Ship ship, int row, int col, int direction) {
        int length = ship.getLength();
        boolean thereIsRoom = true;
        
        switch (direction) {
        case 0: // upward
            if (row - (length - 1) < 0)
                thereIsRoom = false;
            else 
                for (int  i = row; i >= row - (length - 1) && thereIsRoom; i--)
                    thereIsRoom = thereIsRoom & (board[i][col] == '-');
            break;

        case 1: // to the right
            if (col + (length - 1) >= cols)
                thereIsRoom = false;
            else
                for (int i = col; i <= col + (length - 1) && thereIsRoom; i++)
                    thereIsRoom = thereIsRoom & (board[row][i] == '-');
            break;

        case 2: // downward
            if (row + (length - 1) >= rows)
                thereIsRoom = false;
            else
                for (int i = row; i <= row + (length - 1) && thereIsRoom; i++)
                    thereIsRoom  = thereIsRoom & (board[i][col] == '-');
            break;

        default: // to the left
            if (col - (length - 1) < 0) 
                thereIsRoom = false;
            else
                for (int i = col; i >= col - (length - 1) && thereIsRoom; i--) 
                    thereIsRoom = thereIsRoom & (board[row][i] == '-');
            break;
        }
        return thereIsRoom;
    }//end method
        
    //method to print the board on the console for debug mode
    public void printBoard() {
        for (int i = 0; i < rows; i++)
        	System.out.println( Arrays.toString(board[i]));
    }
    
    //method to check the user input, display results and validate entries
    public void play() {
    	
    	//instance variables
		String  enterGuessRow, enterGuessCol;
		int inputRow = 0, inputCol = 0;
		
		//for loop to check board row and col
	    for (int i = 0; i < board.length; i++) { 
    	    for (int j = 0; j < board[i].length; j++) { 
    	//do while loop based on the user input, number of guesses and total points
	    do {
	    	
	    	//JPanel to allow user to input row and column in one window
	    	JTextField xField = new JTextField(10);
	    	JTextField yField = new JTextField(10);
	  	    JPanel myPanel = new JPanel();
	  	    myPanel.add(new JLabel("Row(0-9):"));
	  	    myPanel.add(xField);
	  	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  	    myPanel.add(new JLabel("Column(0-9):"));
	      	myPanel.add(yField);
	      	JOptionPane.showConfirmDialog(null, myPanel, null, JOptionPane.YES_NO_OPTION);
	        
	      	
      		enterGuessRow = xField.getText();
      		enterGuessCol = yField.getText();
      		//convert to int
			inputRow = Integer.parseInt(enterGuessRow);
			inputCol = Integer.parseInt(enterGuessCol);
      	
    	    // if statement when input is valid			    	    	    			
			if ((inputRow >= 0 && inputRow < rows) && (inputCol >= 0 && inputCol < cols)){
					numOfGuess++;
			//if statement when input is char 'F' meaning user already fired at this box 
			//and 'G' meaning that is the location of the destroyed ship
			if(board[inputRow][inputCol] == 'F') {	
					JOptionPane.showMessageDialog(null, "Already fired");
					numOfGuess--;
	    	}else if(board[inputRow][inputCol] =='G') {	
					JOptionPane.showMessageDialog(null, "Location of destroyed ship! - Sorry lost a guess and didn't gain points!!");
			}
			//if statement to check the for the Aircraft carrier and the looping on the board 
			//and changing the chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'A') {
					pointsCounter = pointsCounter + ships[0].getPoints();
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[0].getType() + "\n"+ "You get: " +ships[0].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
			    	    for (int y = 0; y < board[k].length; y++) {
				if(	board[k][y] == 'A') {
					board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Aircraft carrier validation
			
			//if statement to check the for the Battelship and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'B' ) {
					pointsCounter = pointsCounter + ships[1].getPoints();	
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[1].getType() + "\n"+ "You get: " +ships[1].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'B') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Battleship validation
			
			//if statement to check the for the Submarine and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'M' ) {
	    			pointsCounter = pointsCounter + ships[2].getPoints();		
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[2].getType() + "\n"+ "You get: " +ships[2].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'M') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement to check the for the Destroyer and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'D' ) {
					pointsCounter = pointsCounter + ships[3].getPoints();	
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[3].getType() + "\n"+ "You get: " +ships[3].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'D') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement to check the for the Patrol Boat and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'P' ) {
	        		pointsCounter = pointsCounter + ships[4].getPoints();			
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[4].getType() + "\n"+ "You get: " +ships[4].getPoints() + " points !" );
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'M') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement for a miss
			else if (board[inputRow][inputCol] == '-'){
		    		JOptionPane.showMessageDialog(null,  "Miss!");
					}board[inputRow][inputCol] = 'F';
				
			}//end else if
			
	    }//end do from do-while loop
	    
	    //validation of input not outside parameters
	    while((inputRow < 0 && inputRow >= rows  ) || (inputCol < 0 && inputCol >= cols));
	    
	    //if statement to end game if guesses reach MAX and points reach MAX
		if(numOfGuess == MAX_GUESSES || pointsCounter == MAX_POINTS ){
				JOptionPane.showMessageDialog(null, "Game Over"); 
				JOptionPane.showMessageDialog(null, Users.getName()+ " it took you: "  + numOfGuess + 
											" guesses, and you Scored: " + pointsCounter + "!!");
				
				//restart game option but losing inputed data
				//Game.main(null);
				
				//go to mainmenu can't restart another game
				mainMenu();
		
				}//close if 
    	    }//close start of inner loop
	    }//close start of outer loop
    }//close play method
    	    
    
	
	//method to check the user input, display results and validate entries
    public void debugMode() {
    	
    	//instance variables
		String  enterGuessRow, enterGuessCol;
		int inputRow = 0, inputCol = 0;
		
		//for loop to check board row and col
	    for (int i = 0; i < board.length; i++) { 
    	    for (int j = 0; j < board[i].length; j++) { 
    	//do while loop based on the user input, number of guesses and total points
	    do {showShips();
	    	printBoard();
	    	
	    	//JPanel to allow user to input row and column in one window
	    	JTextField xField = new JTextField(10);
	    	JTextField yField = new JTextField(10);
	  	    JPanel myPanel = new JPanel();
	  	    myPanel.add(new JLabel("Row(0-9):"));
	  	    myPanel.add(xField);
	  	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  	    myPanel.add(new JLabel("Column(0-9):"));
	      	myPanel.add(yField);
	      	JOptionPane.showConfirmDialog(null, myPanel, null, JOptionPane.YES_NO_OPTION);
	        
	      	
      		enterGuessRow = xField.getText();
      		enterGuessCol = yField.getText();
      		//convert to int
			inputRow = Integer.parseInt(enterGuessRow);
			inputCol = Integer.parseInt(enterGuessCol);
      	
    	    // if statement when input is valid			    	    	    			
			if ((inputRow >= 0 && inputRow < rows) && (inputCol >= 0 && inputCol < cols)){
					numOfGuess++;
			//if statement when input is char 'F' meaning user already fired at this box 
			//and 'G' meaning that is the location of the destroyed ship
			if(board[inputRow][inputCol] == 'F') {	
					JOptionPane.showMessageDialog(null, "Already fired");
					numOfGuess--;
	    	}else if(board[inputRow][inputCol] =='G') {	
					JOptionPane.showMessageDialog(null, "Location of destroyed ship! - Sorry lost a guess and didn't gain points!!");
			}
			//if statement to check the for the Aircraft carrier and the looping on the board 
			//and changing the chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'A') {
					pointsCounter = pointsCounter + ships[0].getPoints();
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[0].getType() + "\n"+ "You get: " +ships[0].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
			    	    for (int y = 0; y < board[k].length; y++) {
				if(	board[k][y] == 'A') {
					board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Aircraft carrier validation
			
			//if statement to check the for the Battelship and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'B' ) {
					pointsCounter = pointsCounter + ships[1].getPoints();	
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[1].getType() + "\n"+ "You get: " +ships[1].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'B') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Battleship validation
			
			//if statement to check the for the Submarine and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'M' ) {
	    			pointsCounter = pointsCounter + ships[2].getPoints();		
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[2].getType() + "\n"+ "You get: " +ships[2].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'M') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement to check the for the Destroyer and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'D' ) {
					pointsCounter = pointsCounter + ships[3].getPoints();	
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[3].getType() + "\n"+ "You get: " +ships[3].getPoints() + " points !");
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'D') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement to check the for the Patrol Boat and the looping on the board 
			//and changing the remaining chars to 'G' to state it is destroyed
			if (board[inputRow][inputCol] == 'P' ) {
	        		pointsCounter = pointsCounter + ships[4].getPoints();			
					JOptionPane.showMessageDialog(null, "Hit!  " + "You have sunk: " + ships[4].getType() + "\n"+ "You get: " +ships[4].getPoints() + " points !" );
				for (int k = 0; k < board.length; k++) { 
		    	    for (int y = 0; y < board[k].length; y++) {
			if(	board[k][y] == 'M') {
				board[k][y] = 'G';
						}//end if
			    	 }//end inner for loop 
				 }//end outer for loop
			}//end Submarine validation
			
			//if statement for a miss
			else if (board[inputRow][inputCol] == '-'){
		    		JOptionPane.showMessageDialog(null,  "Miss!");
					}board[inputRow][inputCol] = 'F';
				
			}//end else if
			
	    }//end do from do-while loop
	    
	    //validation of input not outside parameters
	    while((inputRow < 0 && inputRow >= rows  ) || (inputCol < 0 && inputCol >= cols));
	    
	    //if statement to end game if guesses reach MAX and points reach MAX
		if(numOfGuess == MAX_GUESSES || pointsCounter == MAX_POINTS ){
				JOptionPane.showMessageDialog(null, "Game Over"); 
				JOptionPane.showMessageDialog(null, Users.getName()+ " it took you: "  + numOfGuess + 
											" guesses, and you Scored: " + pointsCounter + "!!");
				
				
				//restart game option
				//Game.main(null);
				
				//go to mainmenu
			
				mainMenu();
		
				
				}//close if 
    	    }//close start of inner loop
	    }//close start of outer loop
    }//close play method
	
    
  //method to display ships for debug mode
    public void showShips() {
    	ArrayList<String> AircraftCarrier = new ArrayList<>();
    	ArrayList<String> Battleship = new ArrayList<>();
    	ArrayList<String> Submarine = new ArrayList<>();
    	ArrayList<String> Destroyer = new ArrayList<>();
    	ArrayList<String> PatrolBoat = new ArrayList<>();
    	
    	String aircraftCarrierString = "";
    	String battleshipString = "";
    	String submarineString = "";
    	String destroyerString = "";
    	String patrolBoatString = "";
    	
    	// looping through the board and extracting chars and stroing the locations in different arrays based 
    	for (int i = 0; i < board.length; i++) { 
    	    for (int j = 0; j < board[i].length; j++) { 
    	    	
    	    	if (board[i][j] == 'A' ) {
    	    		aircraftCarrierString = ( (i) + ":" + (j) );
    	    		AircraftCarrier.add(aircraftCarrierString);
				}else if(board[i][j] == 'B' ) {
					battleshipString = ( (i) + ":" + (j)  );
					Battleship.add(battleshipString);
				}else if (board[i][j] == 'M' ) {
	    			submarineString = ( (i) + ":" + (j) );
	    			Submarine.add(submarineString);
				}else if (board[i][j] == 'P' ) {
	        		destroyerString = ( (i) + ":" + (j));
					Destroyer.add(destroyerString);
				}else if (board[i][j] == 'D' ) {
		        	patrolBoatString = ((i) + ":" + (j) );
		        	PatrolBoat.add(patrolBoatString);
				}//end if
    	    }//end inner loop
    	}  //end outer loop
    	
    	//display the ships and locations
    	JOptionPane.showMessageDialog(null, "The following numbers represent the random ships location: \n" 
        	    + ships[0].getType()+ " " + Arrays.toString(AircraftCarrier.toArray()) + "\n"
        		+ ships[1].getType()+ " " + Arrays.toString(Battleship.toArray()) + "\n"
        	    + ships[2].getType()+ " " + Arrays.toString(Submarine.toArray()) + "\n"
        	    + ships[3].getType()+ " " + Arrays.toString(PatrolBoat.toArray()) + "\n"
        	    + ships[4].getType()+ " " + Arrays.toString(Destroyer.toArray()));
    	
    }//end method
    
    //option menu method
    public void mainMenu() {
    	
    	//instance variables
		int choice;
		boolean exit = false;
	
		//while loop with switch case depending on user choice
		while (!exit) {
			Display.printMainMenuOptions();
			
			choice = Users.getIntegerMenuOption();
			switch (choice) {
			case 0:
				printMessages("You chose to play the game");
				play();
				break;
			case 1:
				Display.gameRules();
				break;
			case 2:
				printMessages("You chose Debug Mode");
				debugMode(); 
				break;
			case 3:
				printMessages("You chose to view Leaderboard");
				leaderBoard();
				break;	
			case 4:
				printMessages("You chose to exit");
				exitGame();
				break;
				
			}//end switch
		}//end while
	}//end method
	
    //exit method for ending game.
	private void exitGame() {
			printExitMessage();
			System.exit(0);
	}//end method
	
	//method to print messages
	public void printMessages(String message) {
			JOptionPane.showMessageDialog(null, message);
	}//end method
	
	//exit message method
	public void printExitMessage() {
			JOptionPane.showMessageDialog(null, "Goodbye!!");
	}//end method
	
    //method to display leaderboard		
	public void leaderBoard() {
			JOptionPane.showMessageDialog(null,Users.getName() + " you are the leader! \n"
			+ "Your high score is: " + pointsCounter);
	}//end method
	
}//end class