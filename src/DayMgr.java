import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DayMgr {
	/**
	 * Load all day from database.
	 * 
	 * @return add days.
	 */
	public static ArrayList<Day> loadDayFromDB() {
		ArrayList<Day> days = new ArrayList<Day>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Day");
			while (resultSet.next()) {
				String date = resultSet.getString(1);
				Day day = new Day(date);
				days.add(day);
			}
			conn.close();
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * Add day to database.
	 * 
	 * @param day
	 *            the day to add
	 */
	private static void addDay(Day day) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO Day VALUES (?)");
			statement.setString(1, day.getDate());
			statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove day from database.
	 * 
	 * @param day
	 *            the day to remove
	 */
	private static void removeDay(Day day) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:derby:FlightSchedulerDB");
			PreparedStatement statement = conn.prepareStatement("DELETE FROM Day WHERE Date=?");
			statement.setString(1, day.getDate());
			statement.executeUpdate();
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all days.
	 * 
	 * @return all days
	 */
	public static String[] getAllDays() {
		ArrayList<Day> days = loadDayFromDB();
		ArrayList<String> str = new ArrayList<String>();
		for (Day day : days) {
			str.add(day.getDate());
		}
		return str.toArray(new String[str.size()]);
	}
	
	/**
	 * Add day.
	 * @param day the day.
	 * @return null if successful, otherwise return error message
	 */
	public static String addDay(String day) {
		day = day.trim();
		if (day.isEmpty()) {
			return "Day can not empty.";
		}
		String[] days = getAllDays();
		// check day
		for (String d: days) {
			if (d.equals(day)) {
				return day + " already exist.";
			}
		}
		Day d = new Day(day);
		addDay(d);
		return null;
	}
	
	/**
	 * Remove day.
	 * 
	 * @param day
	 *            the day
	 * @return null if successful, otherwise return error message
	 */
	public static String removeDay(String day) {
		day = day.trim();
		if (day.isEmpty()) {
			return "Day can not empty.";
		}
		String[] days = getAllDays();
		// check day
		boolean exist = false;
		for (String d: days) {
			if (d.equals(day)) {
				exist = true;
			}
		}
		if (!exist) {
			return day + " not exist.";
		}
		Day d = new Day(day);
		removeDay(d);
		return null;
	}
}
