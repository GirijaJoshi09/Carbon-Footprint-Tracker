import java.util.*;

// Activity class for general activities
class Activity {
	String activityType;
	double activityparameter; // Duration in minutes

	public Activity(String activityType, double activityparameter) {
		this.activityType = activityType;
		this.activityparameter = activityparameter;
	}

	public String toString() {
		return activityType + ": " + activityparameter + " kg CO2";
	}
}

// Node class to represent each key-value pair in HashMap
class Node<K, V> {
	K key;
	V value;
	Node<K, V> next;

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

// Custom HashMap class
class MyHashMap<K, V> {
	private static final int CAPACITY = 200;
	private Node<K, V>[] buckets;

	public MyHashMap() {
		buckets = new Node[CAPACITY];
	}

	private int getHash(K key) {
		return Math.abs(Objects.hashCode(key)) % CAPACITY;
	}

	public void put(K key, V value) {
		int index = getHash(key);
		Node<K, V> newNode = new Node<>(key, value);

		if (buckets[index] == null) {
			buckets[index] = newNode;
		} else {
			Node<K, V> current = buckets[index];
			while (current != null) {
				if (current.key.equals(key)) {
					current.value = value;
					return;
				}
				if (current.next == null) {
					current.next = newNode;
					return;
				}
				current = current.next;
			}
		}
	}

	public V get(K key) {
		int index = getHash(key);
		Node<K, V> current = buckets[index];

		while (current != null) {
			if (current.key.equals(key)) {
				return current.value;
			}
			current = current.next;
		}
		return null;
	}
}

// User class for login details
class User {
	String username;
	String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

class calculate {
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in).useDelimiter("\n");
	int n;
	String username;
	String currentUser;
	double elec, ng, ho, coal, lpg, propane;
	double dietSpending, pharmaceuticals, clothing, paperProducts, itEquipment;
	double tvRadio, motorVehicles, furniture, hotelsRestaurants;
	double phoneCosts, bankingFinance, insurance, education, recreation;
	String dietType;

	PriorityQueue<Activity> pq = new PriorityQueue<>(
			(a, b) -> Double.compare(b.activityparameter, a.activityparameter));
	private final MyHashMap<String, User> userDatabase = new MyHashMap<>();
	MyHashMap<String, ArrayList<Activity>> householdActivities = new MyHashMap<>();

	public void signUp() {
		System.out.print("Enter the username to sign up: ");
		String username = sc.next().trim();
		System.out.print("Enter password: ");
		String password = sc.next();

		if (userDatabase.get(username) == null) {
			userDatabase.put(username, new User(username, password));
			System.out.println("Sign-up successful! Enter the details : ");
		} else {
			System.out.println("Username already exists. Try another.");
		}
	}

	public boolean login() {
		/*
		 * System.out.print("Enter username: "); String username = sc.next().trim();
		 * System.out.print("Enter password: "); String password = sc.next().trim();
		 * 
		 * User user = userDatabase.get(username); if (user != null &&
		 * user.password.equals(password)) { System.out.println("Login successful!");
		 * currentUser = username; return true; } else {
		 * System.out.println("Invalid username or password."); return false; }
		 */
		System.out.print("Enter username: ");
		username = sc.next().trim();
		System.out.print("Enter password: ");
		String password = sc.next().trim();

		User user = userDatabase.get(username);
		if (user != null && user.password.equals(password)) {
			System.out.println("Login successful!");
			currentUser = username; // Set currentUser to the logged-in username
			return true;
		} else {
			System.out.println("Invalid username or password.");
			return false;
		}
	}

	void household() {
		ArrayList<Activity> householdList = new ArrayList<>();
		System.out.print("\nEnter how many people are in your household : ");
		n = sc.nextInt();
		System.out.print("\nEnter the amount of electricity in kWh : ");
		elec = sc.nextDouble();
		System.out.print("\nEnter the amount of natural gas in kWh : ");
		ng = sc.nextDouble();
		System.out.print("\nEnter the amount of heating oil in litres : ");
		ho = sc.nextDouble();
		System.out.print("\nEnter the amount of coal in kWh : ");
		coal = sc.nextDouble();
		System.out.print("\nEnter the amount of LPG in litres : ");
		lpg = sc.nextDouble();
		System.out.print("\nEnter the amount of propane in litres : ");
		propane = sc.nextDouble();
		// Constants for average carbon outputs in kg CO2 per unit
		double ELECTRICITY_CO2 = 0.92; // per kWh (average)
		double NATURAL_GAS_CO2 = 0.185; // per kWh
		double HEATING_OIL_CO2 = 0.269; // per kWh
		double COAL_CO2 = 0.820; // per kWh
		double LPG_CO2 = 2.94; // per liter
		double PROPANE_CO2 = 2.52; // per liter
		// Calculate the carbon footprint for each energy source
		double carbonElectricity = elec * ELECTRICITY_CO2;
		double carbonNaturalGas = ng * NATURAL_GAS_CO2;
		double carbonHeatingOil = ho * HEATING_OIL_CO2;
		double carbonCoal = coal * COAL_CO2;
		double carbonLPG = lpg * LPG_CO2;
		double carbonPropane = propane * PROPANE_CO2;

		// Add all the activities to the priority queue
		pq.add(new Activity("Electricity", carbonElectricity));
		pq.add(new Activity("Natural Gas", carbonNaturalGas));
		pq.add(new Activity("Heating Oil", carbonHeatingOil));
		pq.add(new Activity("Coal", carbonCoal));
		pq.add(new Activity("LPG", carbonLPG));
		pq.add(new Activity("Propane", carbonPropane));
		householdList.add(new Activity("Electricity", carbonElectricity));
		householdList.add(new Activity("Natural Gas", carbonNaturalGas));
		householdList.add(new Activity("Heating Oil", carbonHeatingOil));
		householdList.add(new Activity("Coal", carbonCoal));
		householdList.add(new Activity("LPG", carbonLPG));
		householdList.add(new Activity("Propane", carbonPropane));
		householdActivities.put(currentUser, householdList);

		// Print out the sorted activities with their carbon footprints
		System.out.println("\nCarbon Footprint Breakdown:");
		while (!pq.isEmpty()) {
			Activity activity = pq.poll();
			System.out.println(activity);
		}
		// Calculate the total carbon footprint for the entire household
		double totalCarbonFootprint = carbonElectricity + carbonNaturalGas + carbonHeatingOil + carbonCoal + carbonLPG
				+ carbonPropane;
		System.out.println("\nTotal Carbon Footprint for " + n + " people: " + totalCarbonFootprint + " kg CO2");
	}

	void secondary() {
		ArrayList<Activity> householdList = new ArrayList<>();
		System.out.print("\nChoose your diet type (Vegetarian / Meat Eater): ");
		dietType = sc1.next().toLowerCase();
		System.out.print("Enter annual spending on Food and Drink: ");
		dietSpending = sc.nextDouble();
		System.out.print("\nEnter annual spending on Pharmaceuticals: ");
		pharmaceuticals = sc.nextDouble();
		System.out.print("\nEnter annual spending on Clothes, textiles and shoes: ");
		clothing = sc.nextDouble();
		System.out.print("\nEnter annual spending on Paper-based products: ");
		paperProducts = sc.nextDouble();
		System.out.print("\nEnter annual spending on Computers and IT equipment: ");
		itEquipment = sc.nextDouble();
		System.out.print("\nEnter annual spending on Television, radio and phone equipment: ");
		tvRadio = sc.nextDouble();
		System.out.print("\nEnter annual spending on Motor vehicles: ");
		motorVehicles = sc.nextDouble();
		System.out.print("\nEnter annual spending on Furniture and other manufactured goods: ");
		furniture = sc.nextDouble();
		System.out.print("\nEnter annual spending on Hotels, restaurants, pubs: ");
		hotelsRestaurants = sc.nextDouble();
		System.out.print("\nEnter annual spending on Telephone call costs: ");
		phoneCosts = sc.nextDouble();
		System.out.print("\nEnter annual spending on Banking and finance: ");
		bankingFinance = sc.nextDouble();
		System.out.print("\nEnter annual spending on Insurance: ");
		insurance = sc.nextDouble();
		System.out.print("\nEnter annual spending on Education: ");
		education = sc.nextDouble();
		System.out.print("\nEnter annual spending on Recreational and cultural activities: ");
		recreation = sc.nextDouble();
		// Constants for carbon output in kg CO2 per unit or dollar spent
		double MEAT_EATER_CO2 = 5.0;
		double VEGETARIAN_CO2 = 2.5;
		double PHARMACEUTICALS_CO2 = 1.9;
		double CLOTHING_CO2 = 2.5;
		double PAPER_CO2 = 2.2;
		double IT_EQUIPMENT_CO2 = 1.8;
		double TV_RADIO_CO2 = 1.5;
		double MOTOR_VEHICLE_CO2 = 2.4;
		double FURNITURE_CO2 = 2.0;
		double HOTEL_RESTAURANTS_CO2 = 3.0;
		double PHONE_COSTS_CO2 = 0.7;
		double BANKING_FINANCE_CO2 = 1.2;
		double INSURANCE_CO2 = 1.0;
		double EDUCATION_CO2 = 0.9;
		double RECREATION_CO2 = 1.1;
		// Calculate the carbon footprint for each category
		double carbonFood = 0 ;
		if ("meat eater".equals(dietType)) {
			 carbonFood = dietSpending * MEAT_EATER_CO2;
		} else if ("vegetarian".equals(dietType)) { 
			 carbonFood = dietSpending * VEGETARIAN_CO2;
		}

		double carbonPharmaceuticals = pharmaceuticals * PHARMACEUTICALS_CO2;
		double carbonClothing = clothing * CLOTHING_CO2;
		double carbonPaperProducts = paperProducts * PAPER_CO2;
		double carbonITEquipment = itEquipment * IT_EQUIPMENT_CO2;
		double carbonTVRadio = tvRadio * TV_RADIO_CO2;
		double carbonMotorVehicles = motorVehicles * MOTOR_VEHICLE_CO2;
		double carbonFurniture = furniture * FURNITURE_CO2;
		double carbonHotelsRestaurants = hotelsRestaurants * HOTEL_RESTAURANTS_CO2;
		double carbonPhoneCosts = phoneCosts * PHONE_COSTS_CO2;
		double carbonBankingFinance = bankingFinance * BANKING_FINANCE_CO2;
		double carbonInsurance = insurance * INSURANCE_CO2;
		double carbonEducation = education * EDUCATION_CO2;
		double carbonRecreation = recreation * RECREATION_CO2;

		pq.add(new Activity("Food and Drink -" + dietType + "", carbonFood));
		pq.add(new Activity("Pharmaceuticals", carbonPharmaceuticals));
		pq.add(new Activity("Clothes and textiles", carbonClothing));
		pq.add(new Activity("Paper-based products", carbonPaperProducts));
		pq.add(new Activity("Computers and IT equipment", carbonITEquipment));
		pq.add(new Activity("TV, Radio, and Phone equipment", carbonTVRadio));
		pq.add(new Activity("Motor Vehicles", carbonMotorVehicles));
		pq.add(new Activity("Furniture", carbonFurniture));
		pq.add(new Activity("Hotels and Restaurants", carbonHotelsRestaurants));
		pq.add(new Activity("Telephone costs", carbonPhoneCosts));
		pq.add(new Activity("Banking and Finance", carbonBankingFinance));
		pq.add(new Activity("Insurance", carbonInsurance));
		pq.add(new Activity("Education", carbonEducation));
		pq.add(new Activity("Recreation and Cultural activities", carbonRecreation));

		householdList.add(new Activity("Food and Drink (" + dietType + ")", carbonFood));
		householdList.add(new Activity("Pharmaceuticals", carbonPharmaceuticals));
		householdList.add(new Activity("Clothes and textiles", carbonClothing));
		householdList.add(new Activity("Paper-based products", carbonPaperProducts));
		householdList.add(new Activity("Computers and IT equipment", carbonITEquipment));
		householdList.add(new Activity("TV, Radio, and Phone equipment", carbonTVRadio));
		householdList.add(new Activity("Motor Vehicles", carbonMotorVehicles));
		householdList.add(new Activity("Furniture", carbonFurniture));
		householdList.add(new Activity("Hotels and Restaurants", carbonHotelsRestaurants));
		householdList.add(new Activity("Telephone costs", carbonPhoneCosts));
		householdList.add(new Activity("Banking and Finance", carbonBankingFinance));
		householdList.add(new Activity("Insurance", carbonInsurance));
		householdList.add(new Activity("Education", carbonEducation));
		householdList.add(new Activity("Recreation and Cultural activities", carbonRecreation));
		// householdActivities.put(currentUser, householdList);
		if (username != null) {
			householdActivities.put(username, householdList);
		} else {
			System.out.println("");
		}
		System.out.println("\nCarbon Footprint Breakdown:");
		double totalCarbonFootprint = 0;
		while (!pq.isEmpty()) {
			Activity activity = pq.poll();
			System.out.println(activity);
			totalCarbonFootprint += activity.activityparameter;
		}
		System.out.println("\nTotal Carbon Footprint for " + n + " people: " + totalCarbonFootprint + " kg CO2");
	}

	public double travel() {
		ArrayList<Activity> householdList = new ArrayList<>();

		double hours_car;
		double hours_bike;
		double hours_publictransport;
		double hours_flight;
		int daysPerWeek;
		double emissionRate_Car = 0.12;
		double emissionRate_bike = 0.07;
		double emissionRate_publicTransport = 0.04;
		double emissionRate_flight = 0.15;
		System.out.print("\nEnter the days per week : ");
		daysPerWeek = sc.nextInt();
		System.out.print("\nEnter the time you travel by car in hours : ");
		hours_car = sc.nextDouble();
		System.out.print("\nEnter the time you travel by bike in hours : ");
		hours_bike = sc.nextDouble();
		System.out.print("\nEnter the time you travel by public transport in hours : ");
		hours_publictransport = sc.nextDouble();
		System.out.print("\nEnter the time you travel by flight in hours : ");
		hours_flight = sc.nextDouble();
		double result_car = emissionRate_Car * hours_car * daysPerWeek;
		double result_bike = emissionRate_bike * hours_bike * daysPerWeek;
		double result_publicTransport = emissionRate_publicTransport * hours_publictransport * daysPerWeek;
		double result_flight = emissionRate_flight * hours_flight * daysPerWeek;
		pq.add(new Activity("Car", result_car));
		pq.add(new Activity("Bike", result_bike));
		pq.add(new Activity("Public Transport", result_publicTransport));
		pq.add(new Activity("Flight", result_flight));
		householdList.add(new Activity("Car", result_car));
		householdList.add(new Activity("Bike", result_bike));
		householdList.add(new Activity("Public Transport", result_publicTransport));
		householdList.add(new Activity("Flight", result_flight));
		// householdActivities.put(username, householdList);

		double total = result_car + result_bike + result_publicTransport + result_flight;
		if (username != null) {
			householdActivities.put(username, householdList);
		} else {
			System.out.println("");
		}
		System.out.print("\nCarbon Footprint Breakdown:");
		double totalCarbonFootprint = 0;
		System.out.print("\nWeekly Carbon Footprint : " + total + " kg CO2");
		while (!pq.isEmpty()) {
			Activity activity = pq.poll();
			System.out.println(activity);
		}
		Suggestion suggestion = new Suggestion();
		suggestion.provideSuggestion(result_car, result_bike, result_publicTransport, result_flight);
		return total;

	}
}

class Suggestion {

	public void provideSuggestion(double carCarbon, double bikeCarbon, double publicTransportCarbon,
			double flightCarbon) {
		// Determine the transport activity with the highest carbon footprint
		double maxEmission = Math.max(Math.max(carCarbon, bikeCarbon), Math.max(publicTransportCarbon, flightCarbon));

		if (maxEmission == carCarbon) {
			System.out.println(
					"\nSuggestion: The carbon footprint for using a car is the highest.");
			System.out.println("Using public transport reduce your carbon footprint by 66.83%.");
			System.out.println("Using  bike will reduce your carbon footprint by 46.67%.");
			System.out.println("Consider using a public transport instead for a greener option!");
		} else if (maxEmission == publicTransportCarbon) {
			System.out.println("\nGreat job! Using a public transport has a relatively low carbon footprint. Keep it up!");
			} else if (maxEmission == bikeCarbon) {

				System.out.println("\nSuggestion: The carbon footprint for bike is higher.");
				System.out.println("Using public transport reduce your carbon footprint by 38.2%.");
				System.out.println("Consider using a public transport instead for a greener option!");
		} else if (maxEmission == flightCarbon) {
			System.out.println(
					"\nSuggestion: Flights have the highest carbon footprint. If possible, try reducing the number of flights or choose greener alternatives like train travel.");
		}
	}
}

public class Carbon_footprint {
	public static void main(String[] args) {
		calculate calc = new calculate();
		// Activity parameter = pq.get(0);
		// Suggestion obj = new Suggestion();
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		while (!loggedIn) {
			System.out.print("\n***** Welcome to Carbon Foot Print Tracker 😊😊 *****");
			System.out.println("\n1. Sign Up\n2. Login\n3. Exit");
			System.out.print("Choose an option: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				calc.signUp();
				calc.household();
				calc.secondary();
				calc.travel();
				System.out.print("\n------------Thankyou for visiting us !! 🙏🙏 ----------------");
				break;
			case 2:
				loggedIn = calc.login();
				if (loggedIn) {
					calc.travel();
				}
				break;
			case 3:
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid option. Try again.");
			}
		}
		sc.close();
	}
}
