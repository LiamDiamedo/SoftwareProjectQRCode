package ie.itsligo.roomroute;

import javax.swing.*;


import java.awt.*; 
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent; 

public class EntryQRCode implements ActionListener{ //implements ActionListener {
	
	 public String S;  //the string that will contain all our information
	 public boolean Restart=false;  //our restart value for the main
	 
	 String[] itemsYear = {"2016", "2017", "2018"}; //Array for the Year on the ComboBox
	 String[] itemsMonth = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	 String[] itemsDay = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

	 private JFrame frame = new JFrame ("QRCode");     //define a Frame
	 private JPanel[] panels = new JPanel [6];         //define how many rows
	 private JTextField DayField = new JTextField();   //define every Fields, button, ComboBox
	 private JTextField TimeFieldStart = new JTextField();
	 private JTextField TimeFieldEnd = new JTextField();
	 private JTextField SubjectField = new JTextField();
	 private JTextField RoomField = new JTextField();
	 private JButton SendButton = new JButton("Send");
	 private JComboBox Year = new JComboBox(itemsYear);
	 private JComboBox Month = new JComboBox(itemsMonth);
	 private JComboBox Day = new JComboBox(itemsDay);
	 
	 
	 JLabel labelDay = new JLabel("Day : ");    //Define Text also
	 JLabel labelHour = new JLabel("From : ");
	 JLabel labelTo = new JLabel("To : ");
	 JLabel labelSubj = new JLabel("Subject : ");
	 JLabel labelRoom = new JLabel("Room : ");
	 
	 
	 
	 public boolean TryAgain (){  //Return our value for the main (to know if send was pressed or not)
		 return Restart;
	 }
	 
	 public void Reset(){         //Reset this value after one loop in the main
		 Restart=false;
	 }
	 
	 
	 public void buildGUI() {  //Here this is to build our menu

		    /*
			 *  The calculator is laid out in a frame.  The frame contains a number of rows.
			 *  Each row contains a number of buttons.
			 */
		 
		 //We are forced to use a calendar, i couldn't use the one from DataTreatement because here it is not created yet...
		 
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, 1);
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); //our format
		 
		 String Date = format1.format(cal.getTime());
		 
		 String delims2 = "[-]+"; //here we split by space and :
		 String[] ArrayDateActual = Date.split(delims2); //we store the splits in ArrayData
		 
		 int TODAY = cal.get(Calendar.DAY_OF_WEEK); //here we get the number corresponding to the day
		 
		 String[] DayOfWeek= {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};  //here we get all days of week
		 
		 //Preselection of my Combobox according to the current Date
		 Year.setSelectedItem(ArrayDateActual[0]); 
		 Month.setSelectedItem(ArrayDateActual[1]);
		 Day.setSelectedItem(ArrayDateActual[2]);
		 String DayActual = DayOfWeek[TODAY]; //Here we match between the day of the week and the number, to get a string
		 
		 
		 
		 
		 	frame.setLocationRelativeTo(null); //Center the menu when it appears
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		    
		    JPanel contentPane = (JPanel) frame.getContentPane(); 
		    
		    // initialize panels 
		    for (int i = 0; i < panels.length; i++) { 
		      panels[i] = new JPanel (); 
		    } 
		    
		    //Row 0
		    DayField.setColumns(30); //Configure the lenght of our TextField
		    panels[0].add (labelDay); //our Label
		    DayField.setText (DayActual);  //preset the text
		    DayField.setHorizontalAlignment (JTextField.RIGHT);  //justify
		    panels[0].add (DayField);   //here is our first FieldText
		    
		    
		    //Row 1
		    panels[1].add (labelHour);
		    TimeFieldStart.setColumns(14); //to adjust the length i decided to take 14
		    TimeFieldStart.setText ("09h00"); 
		    TimeFieldStart.setHorizontalAlignment (JTextField.RIGHT); 
		    panels[1].add (TimeFieldStart); 
		    panels[1].add (labelTo);
		    TimeFieldEnd.setColumns(14); 
		    TimeFieldEnd.setText ("11h00"); 
		    TimeFieldEnd.setHorizontalAlignment (JTextField.RIGHT); 
		    panels[1].add (TimeFieldEnd); 
		    
		    //Row 2
		    panels[2].add (labelSubj);
		    SubjectField.setColumns(30); 
		    SubjectField.setText ("Electrical Engineering"); 
		    SubjectField.setHorizontalAlignment (JTextField.RIGHT); 
		    panels[2].add (SubjectField); 
		    
		    //Row 3
		    panels[3].add (labelRoom);
		    RoomField.setColumns(30); 
		    RoomField.setText ("E2004"); 
		    RoomField.setHorizontalAlignment (JTextField.RIGHT); 
		    panels[3].add (RoomField); 
		    
		    // Row 4
		    panels[4].add(Year); //here it is our Combobox
		    panels[4].add(Month);
		    panels[4].add(Day);
		    
		    //Row 5
		    SendButton.setActionCommand ("SEND");  //our send button with the ActionListener
		    SendButton.addActionListener (this);
		    panels[5].add(SendButton);
		    
		    
		    
		    
		    contentPane.setLayout (new BoxLayout (contentPane, BoxLayout.Y_AXIS)); 
		    for (JPanel jPanel : panels) { 
		      contentPane.add (jPanel); 
		    } 

		    frame.pack ();   //print the Window
		    frame.setVisible (true); 
		    
	 }
	 
	 
	 
	 public void actionPerformed (ActionEvent e) {  //it the button is pressed here we go
		    String actionCommand = e.getActionCommand(); 
		    if (actionCommand == null || actionCommand.trim().length() <= 0) { 
		      return; 
		    } 
 
		      if (actionCommand.equals ("SEND")) {  //there we send the info and set Restart to say it to the main
		    	  S = "Day:" + DayField.getText() + "\nTime:"+ TimeFieldStart.getText() + " to " + TimeFieldEnd.getText() + "\nSubject:" + SubjectField.getText() + "\nRoom:" + RoomField.getText() + "\nDate:" + Year.getSelectedItem().toString() + "/" + Month.getSelectedItem().toString()+ "/" + Day.getSelectedItem().toString();
		    	  Restart=true;
		      }
	 }
	 
	 public String ReturnString(){ //just to send the info in the main.
		 return S;
	 }
}
