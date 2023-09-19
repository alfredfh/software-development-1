
import javax.swing.JOptionPane;

public class Display {
	
	//constructor
	public Display() {
		
	}
	
	//Main menu method to display intro 
	public static void printMenu() {
		String output = "";
		output = "Welcome to the battleship game!!" + "\n" + "\n" + "\n"
				
				
				+ "               # # # #  ( )\r\n"
				+ "                                  ___#_#___|__\r\n"
				+ "                              _  |____________|  _\r\n"
				+ "                       _=====| | |            | | |==== _\r\n"
				+ "                 =====| |.---------------------------. | |====\r\n"
				+ "   <--------------------'   .  .  .  .  .  .  .  .   '----------------/\r\n"
				+ "     \\                                                             /\r\n"
				+ "      \\___________________________________AFH_________/\r\n"
				+ "  wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\r\n"
				+ "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\r\n"
				+ "   wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww "
				+ "\n" + "\n" + "\n" + "Let the game BEGIN!!";
	
		JOptionPane.showMessageDialog(null, output);
	}
	
	//method to display option screen
	public static void printMainMenuOptions() {
		JOptionPane.showMessageDialog(null, "0 - Play New Game \n"
				+ "1 - Print game rules \n"
				+ "2 - Debug Mode \n"
				+ "3 - Leader Board \n"+ 
				"4 - Exit game \n");
		
	}
	
	//method to display game rules
	public static void gameRules() {
		JOptionPane.showMessageDialog(null,	"The aim of the game is destroy all the ships and gain the highest score" + "\n"
				+ "The ships and points are as follows:" + "\n" + "\n"
				+ "SHIPS" + "                    " + "POINTS" + "\n"
				+ "Aircraft Carrier       " + " 2" + "\n"
				+ "Battleship" + "              " + " 4" + "\n"
				+ "Submarine" + "             " + " 6" + "\n"
				+ "Destroyer" + "               " + " 8" + "\n"
				+ "Patrol boat" + "             " + " 10" + "\n"+"\n");
	}

}//end class
