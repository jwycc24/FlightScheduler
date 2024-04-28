import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerMgr {
	/**
	 * Load all customer from database.
	 * 
	 * @return the customer list
	 */
	public static ArrayList<Customer> loadCustomerFromDB() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Customer");
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				Customer.index = id + 1;
				Customer customer = new Customer(id, firstName, lastName);
				customers.add(customer);
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	/**
	 * Add customer to database.
	 * 
	 * @param customer
	 *            the customer to add
	 */
	private static void addCustomer(Customer customer) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?)");
			statement.setInt(1, customer.getId());
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove customer from database.
	 * 
	 * @param customer
	 *            the customer to remove
	 */
	private static void removeCustomer(Customer customer) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("DELETE FROM Customer WHERE FirstName=? AND LastName=?");
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all customer name.
	 * 
	 * @return all customer name
	 */
	public static String[] getAllCustomerName() {
		ArrayList<Customer> customers = loadCustomerFromDB();
		ArrayList<String> names = new ArrayList<String>();
		for (Customer customer : customers) {
			String name = customer.getFirstName() + " " + customer.getLastName();
			if (name.length() > 12) {
				name = name.substring(0, 12);
			}
			names.add(name);
		}
		return names.toArray(new String[names.size()]);
	}
	
	/**
	 * Add customer.
	 * @param name the customer name.
	 * @return null if successful, otherwise return error message
	 */
	public static String addCustomer(String name) {
		String[] field = name.trim().split(" ");
		if (field.length != 2) {
			return "Name must contains first and last name.";
		}
		String[] customers = getAllCustomerName();
		// check name
		for (String customer: customers) {
			if (customer.equals(name.trim())) {
				return name.trim() + " already exist.";
			}
		}
		Customer customer = new Customer(field[0], field[1]);
		addCustomer(customer);
		return null;
	}
	
	/**
	 * Remove customer
	 * 
	 * @param name
	 *            the customer name
	 * @return null if remove successful, otherwise return error message
	 */
	public static String removeCustomer(String name) {
		String[] field = name.trim().split(" ");
		if (field.length != 2) {
			return "Name must contains first and last name.";
		}
		String[] customers = getAllCustomerName();
		// check name
		boolean exist = false;
		for (String customer: customers) {
			if (customer.equals(name.trim())) {
				exist = true;
			}
		}
		if (!exist) {
			return name.trim() + " not exist.";
		}
		Customer customer = new Customer(field[0], field[1]);
		removeCustomer(customer);
		return null;
	}
}
