import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIFrame extends JFrame {
	private JLabel customerLabel;
	private JTextField nameTextField;
	private JComboBox nameComboxBox; // combobox to hold names of customers
	private JButton addCustomer; // add customer button
	private JButton removeCustomer; // remove customer button

	private JLabel dayLabel;
	private JTextField dayTextField;
	private JComboBox dayComboxBox; // combobox to hold days
	private JButton addDay; // add day button
	private JButton removeDay; // remove day button
	
	private JLabel flightLabel;
	private JTextField flightTextField;
	private JComboBox flightComboxBox; // combobox to hold flights
	
	private JButton book;
	
	private JTextArea area;
	private JButton status;
	private JButton waiting;
	private JButton clear;
	
	
	public GUIFrame() {
		super("Flight Schedular");
		setLayout(new FlowLayout());

		// customer
		customerLabel = new JLabel("Customer Name:");
		add(customerLabel);
		
		nameTextField = new JTextField(10);
		add(nameTextField);
		
		nameComboxBox = new JComboBox(CustomerMgr.getAllCustomerName());
		nameComboxBox.setMaximumRowCount(3);
		nameComboxBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					nameTextField.setText(nameComboxBox.getSelectedItem().toString());
				}
			}
		});
		add(nameComboxBox);
		
		addCustomer = new JButton("Add Customer");
		addCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String msg = CustomerMgr.addCustomer(nameTextField.getText());
				if (msg == null) {
					JOptionPane.showMessageDialog(null, "Add successful.", "OK", JOptionPane.PLAIN_MESSAGE);
					nameComboxBox.addItem(nameTextField.getText().trim());
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(addCustomer);
		
		removeCustomer = new JButton("Remove Customer");
		removeCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String msg = CustomerMgr.removeCustomer(nameComboxBox.getSelectedItem().toString());
				if (msg == null) {
					JOptionPane.showMessageDialog(null, "Remove successful.", "OK", JOptionPane.PLAIN_MESSAGE);
					nameComboxBox.removeAllItems();
					for (String name: CustomerMgr.getAllCustomerName()) {
						nameComboxBox.addItem(name);
					}
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(removeCustomer);
		
		// day
		dayLabel = new JLabel("Flighting Day:");
		add(dayLabel);
		
		dayTextField = new JTextField(10);
		add(dayTextField);
		
		dayComboxBox = new JComboBox(DayMgr.getAllDays());
		dayComboxBox.setMaximumRowCount(3);
		dayComboxBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					dayTextField.setText(dayComboxBox.getSelectedItem().toString());
				}
			}
		});
		add(dayComboxBox);
		
		addDay = new JButton("Add Flight Day");
		addDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String msg = DayMgr.addDay(dayTextField.getText());
				if (msg == null) {
					JOptionPane.showMessageDialog(null, "Add successful.", "OK", JOptionPane.PLAIN_MESSAGE);
					dayComboxBox.addItem(dayTextField.getText().trim());
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(addDay);
		
		removeDay = new JButton("Remove Flight Day");
		removeDay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String msg = DayMgr.removeDay(dayComboxBox.getSelectedItem().toString());
				if (msg == null) {
					JOptionPane.showMessageDialog(null, "Remove successful.", "OK", JOptionPane.PLAIN_MESSAGE);
					dayComboxBox.removeAllItems();
					for (String name: DayMgr.getAllDays()) {
						dayComboxBox.addItem(name);
					}
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(removeDay);
		
		// flight
		flightLabel = new JLabel("Flight Number:");
		add(flightLabel);
		
		flightTextField = new JTextField(10);
		add(flightTextField);
		
		flightComboxBox = new JComboBox(FlightMgr.getAllFilght());
		flightComboxBox.setMaximumRowCount(3);
		flightComboxBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					flightTextField.setText(flightComboxBox.getSelectedItem().toString());
				}
			}
		});
		add(flightComboxBox);
		
		// booking
		book = new JButton("Add Booking");
		book.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String name = nameComboxBox.getSelectedItem().toString();
				String date = dayComboxBox.getSelectedItem().toString();
				String flight = flightComboxBox.getSelectedItem().toString();
				String msg = BookingMgr.addBooking(name, date, flight);
				if (msg == null) {
					JOptionPane.showMessageDialog(null, "Book successful.", "OK", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(book);
		
		// status area
		area = new JTextArea(5, 25);
		add(area);
		
		status = new JButton("Display Status");
		status.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String date = dayComboxBox.getSelectedItem().toString();
				String flight = flightComboxBox.getSelectedItem().toString();
				area.setText(BookingMgr.GetStatus(flight, date));
			}
		});
		add(status);
		
		waiting = new JButton("Display Waiting");
		waiting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String date = dayComboxBox.getSelectedItem().toString();
				String flight = flightComboxBox.getSelectedItem().toString();
				area.setText(BookingMgr.GetWaiting(flight, date));
			}
		});
		add(waiting);
		
		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				area.setText("");
			}
		});
		add(clear);
	}
}
