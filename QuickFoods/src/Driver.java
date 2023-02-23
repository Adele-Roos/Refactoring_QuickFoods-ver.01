
// class Driver has three instance variables: 'drivers', 'area', and 'deliveries'
public class Driver {
    private String drivers;
    private String area;
    private int deliveries;
    
    // constructor that takes three string arguments and initialises the instance variables with the values passed as arguments.
    public Driver(String string0, String string1, String string2) {
        this.drivers = string0;
        this.area = string1;
        try {
            this.deliveries = Integer.parseInt(string2.trim());
        } catch (NumberFormatException e) {
        	// if the string argument cannot be parsed into a valid integer
            System.out.println("Error: Input string could not be parsed into a valid integer.");
            // deliveries is set to 0 in case of an error
            this.deliveries = 0;
        }
    }
    
    // method which returns the values of the drivers
	public String getName() {
        return drivers;
    }
	
	// method which returns the values of the area
    public String getArea() {
        return area;
    }

    // method which returns the values of the deliveries
    public int getDeliveries() {
        return deliveries;
    }
}


