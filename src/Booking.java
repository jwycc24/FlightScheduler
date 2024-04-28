public class Booking {
	private int customerId;
	private String date;
	private String flight;
	private int status; // 1 is booking, 0 is waiting

	/**
	 * Constructor.
	 * 
	 * @param customerId
	 *            the customer id
	 * @param date
	 *            the date
	 * @param flight
	 *            the flight number
	 * @param status
	 *            the status
	 */
	public Booking(int customerId, String date, String flight, int status) {
		this.customerId = customerId;
		this.date = date;
		this.flight = flight;
		this.status = status;
	}

	/**
	 * Get customer id.
	 * 
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Set customer id.
	 * 
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Get date.
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Get flight name.
	 * 
	 * @return the flight
	 */
	public String getFlight() {
		return flight;
	}

	/**
	 * Set flight name.
	 * 
	 * @param flight
	 *            the flight to set
	 */
	public void setFlight(String flight) {
		this.flight = flight;
	}
	
	/**
	 * Get booking status.
	 * 
	 * @return the booking status.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Set booking status.
	 * 
	 * @param status
	 *            the booking status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
