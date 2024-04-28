import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class FlightMgr {
	/**
	 * Load all flight from database.
	 * 
	 * @return the flights list
	 */
	public static ArrayList<Flight> loadFlightFromDB() {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightScheduler");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM Flight");
			while (resultSet.next()) {
				String name = resultSet.getString(1);
				int seats = resultSet.getInt(2);
				Flight flight = new Flight(name, seats);
				flights.add(flight);
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flights;
	}
	
	/**
	 * Get all flight.
	 * 
	 * @return all days
	 */
	public static String[] getAllFilght() {
		ArrayList<Flight> flights = loadFlightFromDB();
		ArrayList<String> str = new ArrayList<String>();
		for (Flight flight : flights) {
			str.add(flight.getName());
		}
		return str.toArray(new String[str.size()]);
	}
}
