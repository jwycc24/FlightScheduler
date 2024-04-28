public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	static int index = 0;

	/**
	 * Constructor.
	 * 
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 */
	public Customer(String firstName, String lastName) {
		this.id = index++;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 *            the id
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 */
	public Customer(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Get the first name of the customer.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of the customer.
	 * 
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name of the customer.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of the customer.
	 * 
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the id of the customer.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id of the customer.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
