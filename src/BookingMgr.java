import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookingMgr {
	/**
	 * Load all booking from database.
	 * 
	 * @return all booking info
	 */
	public static ArrayList<Booking> loadBookingFromDB() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Booking");
			while (resultSet.next()) {
				int customerId = resultSet.getInt(1);
				String date = resultSet.getString(2);
				String flight = resultSet.getString(3);
				int status = resultSet.getInt(4);
				Booking booking = new Booking(customerId, date, flight, status);
				bookings.add(booking);
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
	/**
	 * Load the exist seats of the flight on date.
	 * 
	 * @param flight
	 *            the flight number
	 * @param date
	 *            the date
	 * @return the exist seats
	 */
	public static int loadExistSeats(String flight, String date) {
		int seats = 0;
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM Booking WHERE flight=? AND date=?");
			statement.setString(1, flight);
			statement.setString(2, date);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				seats = resultSet.getInt(1);
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seats;
	}
	
	/**
	 * Get status.
	 * 
	 * @param flight
	 *            the flight number.
	 * @param date
	 *            the date
	 * @return the status of the flight on that date
	 */
	public static String GetStatus(String flight, String date) {
		String str = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("SELECT Firstname, Lastname FROM Booking JOIN Customer ON Booking.CustomerId=Customer.Id WHERE Flight=? AND Date=? AND Status=1");
			statement.setString(1, flight);
			statement.setString(2, date);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				str += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * Get waiting list.
	 * 
	 * @param flight
	 *            the flight number
	 * @param date
	 *            the date
	 * @return the waiting list of the flight on that date
	 */
	public static String GetWaiting(String flight, String date) {
		String str = "";
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("SELECT Firstname, Lastname FROM Booking JOIN Customer ON Booking.CustomerId=Customer.Id WHERE Flight=? AND Date=? AND Status=0");
			statement.setString(1, flight);
			statement.setString(2, date);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				str += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * Add booking to database.
	 * 
	 * @param booking
	 *            the booking to add
	 */
	private static void addBooking(Booking booking) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO Booking VALUES (?, ?, ?, ?)");
			statement.setInt(1, booking.getCustomerId());
			statement.setString(2, booking.getDate());
			statement.setString(3, booking.getFlight());
			statement.setInt(4, booking.getStatus());
			statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add booking.
	 * 
	 * @param name
	 *            the customer name
	 * @param date
	 *            the date
	 * @param flight
	 *            the flight number
	 * @return null if successful, otherwise return error message
	 */
	public static String addBooking(String name, String date, String flight) {
		name = name.trim();
		date = date.trim();
		flight = flight.trim();
		// check name
		ArrayList<Customer> customers = CustomerMgr.loadCustomerFromDB();
		boolean contains = false;
		int customerId = -1;
		for (Customer customer: customers) {
			if (name.equals(customer.getFirstName() + " " + customer.getLastName())) {
				customerId = customer.getId();
				contains = true;
				break;
			}
		}
		if (!contains) {
			return name + " not exist.";
		}
		
		// check flight
		ArrayList<Flight> flights = FlightMgr.loadFlightFromDB();
		contains = false;
		int seats = -1;
		for (Flight f: flights) {
			if (flight.equals(f.getName())) {
				seats = f.getSeats();
				contains = true;
				break;
			}
		}
		if (!contains) {
			return flight + " not exist.";
		}
		
		// get available sets
		int available = seats - loadExistSeats(flight, date);
		if (available > 0) {
			Booking booking = new Booking(customerId, date, flight, 1);
			addBooking(booking);
			return null;
		} else {
			Booking booking = new Booking(customerId, date, flight, 0);
			addBooking(booking);
			return "No available seats for this flight. Add to waiting list.";
		}
	}
}
