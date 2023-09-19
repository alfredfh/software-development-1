
public class Ship {
	

		//instance variables
		
		private int length;
		private String type;
		private int points;
		
		//Ship constructor
		public Ship () {
			
		} // end constructor
		
		//method for length
		public Ship(int length) {
			setLength(length);
			
		}
		
		//method for type and points
		public Ship(String type, int points) {
		
			setType(type);
			setPoints(points);
		}
		
	
		//getters and setters
			public int getLength() {
				return length;
			}
			public void setLength(int length) {
				this.length = length;	
			}
			
			public String getType() {
				return this.type;
			}
			public void setType(String type) {
				this.type = type;
			}
			
			public int getPoints() {
				return this.points;
			}
			public void setPoints(int points) {
				this.points = points;
			}
	

}//end class
