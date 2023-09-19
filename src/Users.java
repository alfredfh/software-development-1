import javax.swing.JOptionPane;

public class Users {
	
	//instance variables
	private static String Name;
	static int choice;
	
	//constructor
	public Users(String Name){
		setName(Name);
	}
	
	//method to get user name
	public static String Name() {
		Name = JOptionPane.showInputDialog("Please enter your name: ");
		JOptionPane.showMessageDialog(null, "Welcome: " + Name);
		return Name;
	}
	
	//getters and setters
	public static String getName() {
		return Name;
	}

	public static void setName(String name) {
		Name = name;
	}
	
	//method to get user choice in main menu options
	public static int getIntegerMenuOption() {
		choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter an option: "));
		return choice;
	}


}