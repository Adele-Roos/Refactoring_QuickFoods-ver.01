import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Customer newCust = new Customer();
        Restaurant newInf = new Restaurant();
        Object[][] orders;
        String specialInstructions = "";
        String name = "", contactNumber = "", houseNumber = "", streetAddress = "", locationCity = "", email = "";
        ArrayList<Driver> mainListdrivers = new ArrayList<>();
        
        // scanner input of customer information
        try (Scanner input = new Scanner(System.in)) {
            for (int i = 0; i < 1; i++) {
                System.out.print("Enter name and surname: ");
                name = input.nextLine();

                System.out.print("Enter contact number: ");
                contactNumber = input.nextLine();

                System.out.print("Enter house number: ");
                houseNumber = input.nextLine();

                System.out.print("Enter street name: ");
                streetAddress = input.nextLine();

                System.out.print("Enter city name: ");
                locationCity = input.nextLine();

                System.out.print("Enter your email: ");
                email = input.next();

                newCust = new Customer(name, contactNumber, houseNumber, streetAddress, locationCity, email);
            }

            try (Scanner inputR = new Scanner(System.in)) {
				// loops through to add restaurant information to the string array
				String restaurantName = "", restaurantLocation = "", restaurantContactNumber = "";
					for (int j = 0; j < 1; j++) {
					    System.out.print("Enter Restaurant name: ");
					    restaurantName = inputR.nextLine();
	
					    System.out.print("Enter Restaurant location(city or town): ");
					    restaurantLocation = inputR.nextLine();
	
					    System.out.print("Enter Restaurant contact number: ");
					    restaurantContactNumber = inputR.nextLine();
					    
					    // calling the findDriver method and passing the 'restaurantLocation' as argument, the result is assigned to 'mainListdrivers' variable
					    mainListdrivers = Main.findDriver(restaurantLocation);
					}
				
				// Scanner to capture order information	
				try (
				Scanner orderSC = new Scanner(System.in)) {
					System.out.print("Enter number of items: ");
					int n = orderSC.nextInt();
					orderSC.nextLine();
					String orderDetails = "";
					orders = new Object[n][3];
					String[] yourItems = new String[n];
					// loop to capture order quantity, name and price
					for (int i = 0; i < n; i++) {
					    System.out.print("Enter order details (Quantity, Name, Price (exp 100.00): ");
					    orderDetails = orderSC.nextLine();
					    String[] orderDetailsArray = orderDetails.split(", ");
					    for (int j = 0; j < 3; j++) {
					        orders[i][j] = orderDetailsArray[j];
					    }
					    // prints order to console
					    yourItems[i] = orders[i][0] + " x " + orders[i][1] + " (" + orders[i][2] + ")";
					    System.out.println("Your item: " + yourItems[i]);
					}
					// asks for and prints out special instructions
					System.out.print("Any special instructions? ");
					specialInstructions = orderSC.nextLine();
					System.out.println(specialInstructions);


					// calculates the total of each item and the final total of the order
					double total = 0;
					double orderTotal = 0;
					for (int j = 0; j < orders.length; j++) {
					    int quantity = Integer.parseInt((String) orders[j][0]);
					    double price = Double.parseDouble((String) orders[j][2]);
					    if (quantity >= 1) {
					        orderTotal = price * quantity;
					        total += orderTotal;
					    }
					}
					
					// calling 'closestDriver' method and passing 'mainListdrivers' as argument, result is saved to 'driverClose' variable
					String driverClose = closestDriver(mainListdrivers);
					
					// creates a new instance of the Restaurant class, with the given parameters passed to the constructor of the class
					newInf = new Restaurant(restaurantName, restaurantLocation, restaurantContactNumber, orderDetails, specialInstructions, total, 123, 345, driverClose, name, contactNumber, houseNumber, streetAddress, locationCity, email);
					// prints out the total
					System.out.printf("Total: R%.2f", total);

					try {
					    writeToInvoice(newInf, newCust, yourItems, email);
					} catch (Exception ex) {
					    System.out.println("Failed to Write");
					}

					orderSC.nextLine();
				}
			}
        } catch (NumberFormatException e) {

            e.printStackTrace();
        }
    }

    // method to read drivers text file, compare that against the restaurant location input from the user

    public static ArrayList<Driver> findDriver(String restaurantLocation) {
        ArrayList<Driver> drivers = new ArrayList<Driver>();
        try (BufferedReader br = new BufferedReader(new FileReader("driver-info.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] driverDetails = line.split(",");

                if (driverDetails[1].strip().equals(restaurantLocation)) {

                    drivers.add(new Driver(driverDetails[0], driverDetails[1], driverDetails[2]));
                
                    System.out.println(Arrays.toString(driverDetails));
                }
            }

            if (drivers.isEmpty()) {
                System.out.println("Sorry! Our drivers are too far away from you to ba able to deliver to your location.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // method to check the value of number of deliveries (index 2) each of the drivers
    // in the area has. Calculates the one with the least to assign to the delivery
    public static String closestDriver(ArrayList<Driver> drivers) {
        String driverClosest = "";
        int minIndex = 0;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < drivers.size(); i++) {
            int currentValue = drivers.get(i).getDeliveries();
            System.out.println(currentValue);
            if (currentValue < minValue) {
                minValue = currentValue;
                minIndex = i;
            }
        }
        driverClosest = drivers.get(minIndex).getName();
        return driverClosest;
    }
    
    // method to write a text file called invoice.txt and populate it with input and other variables obtained
    public static void writeToInvoice(Restaurant restaurant, Customer customer, String[] yourItems, String email) throws IOException {
		 
    	BufferedWriter bw = null;
    	    
    	File file = new File("invoice.txt");
    	    
    	if (!file.exists()) {
    		file.createNewFile();
    	}
    	try {
    		bw = new BufferedWriter(new FileWriter(file));
    		bw.write("Order number " + restaurant.orderNumber);
    		bw.newLine();
    		
    		bw.write("Customer: " + customer.name);
    		bw.newLine();
    		
    		bw.write("Email: " + customer.email);
    		bw.newLine();
    		
    		bw.write("Phone number: " + customer.contactNumber);
    		bw.newLine();
    		
    		bw.write("Location: " + customer.locationCity);
    		bw.newLine();
    		bw.newLine();
    	  
    		bw.write("You have the following from " + restaurant.restaurantName + " in " + restaurant.restaurantLocation + ":");
    		bw.newLine();
    		bw.newLine();
    		
    		for (int i = 0; i < yourItems.length; i++) {
    			bw.write(yourItems[i]);
    			bw.newLine();
    		}
    		bw.newLine();
    		
    		bw.write("Special Instructions: " + restaurant.specialInstructions);
    		bw.newLine();
    		bw.newLine();
    		
    		bw.write("Total Amount: R" + restaurant.totalAmount);
    		bw.newLine();
    		bw.newLine();
    		
    		bw.write(restaurant.driver + " is the nearest to the restaurant and will be delivering your order at: ");
    		bw.newLine();
    		bw.newLine();
    		
    		bw.write(customer.houseNumber + " " + customer.streetAddress);
    		bw.newLine();
    		
    		bw.write(customer.locationCity);
    		bw.newLine();
    		bw.newLine();
    		
    		bw.write("If you need to contact the restaurant, their number is: " + restaurant.contactNumber + ".");
    		bw.newLine();
    		
    		System.out.println("\nFile written Successfully");

    	} catch (IOException ioe) {
    		ioe.printStackTrace();
    		        
    	} finally { 
    		try {
    			if (bw != null) {
    				bw.close();
    			}
    		} catch (Exception ex) {
    			System.out.println("Error in closing the BufferedWriter" + ex);
    		}
    	}
    }

}
