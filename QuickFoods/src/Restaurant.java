
// class that creates an instance of 'Restaurant' and extends to 'Customer'
public class Restaurant extends Customer {
	
	// has the following properties
	String restaurantName;
	String restaurantLocation;
	String restaurantContactNumber;
	String orderDetails;
	int qtyMeals;
	String [] mealNames;
	double [] mealPrices;
	String specialInstructions;
	double totalAmount;
	int orderNumber = 345;
	static int counter = 345;
	String driver;
	public String roundedTotal;
	
	// an empty constructor that calls the empty constructor of the Customer class.
	public Restaurant()
	{
	    super();
	}
	
	// calls the constructor of the Customer class and passes the last 8 parameters to it
	public Restaurant(String restaurantName, String restaurantLocation, String restaurantContactNumber,
			String orderDetails, String specialInstructions, double totalAmount, int orderNumber, int counter, 
			String driver, String name, String contactNumber, String houseNumber, String streetAddress, String locationCity, String email) 
		{
		// initialises the remaining properties with the values of the first 7 parameters    
		super(name, contactNumber, houseNumber, streetAddress, locationCity, email);
		this.restaurantName = restaurantName;
		this.restaurantLocation = restaurantLocation;
		this.restaurantContactNumber = restaurantContactNumber;
		this.orderDetails = orderDetails;
		this.specialInstructions = specialInstructions;
		this.totalAmount = totalAmount;
		this.orderNumber = counter;
		Restaurant.counter = counter;
		this.driver = driver;	
	}
}