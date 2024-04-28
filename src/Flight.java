public class Flight {
	private String name;
	private int seats;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the flight name
	 * @param seats
	 *            the seats
	 */
	public Flight(String name, int seats) {
		this.name = name;
		this.seats = seats;
	}

	/**
	 * Get the name of the flight.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the flight.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the number of seats of the flight.
	 * 
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Set the number of seats of the flight.
	 * 
	 * @param seats
	 *            the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
}
