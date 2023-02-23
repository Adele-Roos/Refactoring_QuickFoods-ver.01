
// class that creates an instance of 'Customer'
public class Customer {
	
	// has the following properties
	String name;
	String contactNumber;
	String houseNumber;
	String streetAddress;
	String locationCity;
	String email;
	
	//creates an empty constructor with no parameters
	public Customer() {
		
	}
	
	// constructor takes six parameters and sets the values of the fields to the values of the parameters
	public Customer(String name, String contactNumber, String houseNumber, String streetAddress, String locationCity, String email) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.houseNumber = houseNumber;
		this.streetAddress = streetAddress;
		this.locationCity = locationCity;
		this.email = email;
	}

}
