package ie.itsligo.roomroute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTreatement {
	
	public int[] IntArrayDateActual = new int[3];   //Here this array contains the same data as ArrayDateActual but they are integer
	public String[] ArrayDateActual = new String[3];
	
	
	public void DateHour (String D, String H){ //Here we get the Date and the Hour according to the ComboBox.
	
	 Calendar cal = Calendar.getInstance();
	 //cal.add(Calendar.DATE, 1);
	 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); //setting the Format wanted for our data
	 SimpleDateFormat format2 = new SimpleDateFormat("hh:mm:ss");
	 
	 String Date = format1.format(cal.getTime()); //Here we apply the format
	 String Hour = format2.format(cal.getTime());
	
	 
	 String delims = "[/]+"; //here we split by /
	 String[] ArrayDateSend = D.split(delims); //we store the splits in ArrayDateSend
	 int[] IntArrayDateSend = new int[3];	
	 for (int i=0; i<3;i++) {
		 IntArrayDateSend[i] = Integer.parseInt(ArrayDateSend[i]); //This converts string to integer to be able to compare them. (there was also the "equals" method)
	 }
	 
	 String delims2 = "[-]+"; //here we split by -
	 ArrayDateActual = Date.split(delims2); //we store the splits in ArrayDateActual
	 for (int i=0; i<3;i++) {
		 IntArrayDateActual[i] = Integer.parseInt(ArrayDateActual[i]);
	 }
	 
	 
	 String delims3 = "[h]+"; //here we split by h to get the hour of start of the course
		String[] HourSend = H.split(delims3);
		int HourS = Integer.parseInt(HourSend[0]);
		
	String delims4 = "[:]+"; //here we split by :
		String[] HourActual = Hour.split(delims4);
		int HourA = Integer.parseInt(HourActual[0]);
	 
	 
	 
	 //Under this point I just Put if statement inside others to check everything one by one
	 //I started by the year and went throught the code
	 
	 if (IntArrayDateSend[0]<IntArrayDateActual[0]){ //year passed already
		 System.out.println("Wrong year, already passed");
	 }
	 else if (IntArrayDateSend[0]>IntArrayDateActual[0]){ //not the same year
		 if (IntArrayDateSend[0]-IntArrayDateActual[0]>1){
		 System.out.println("See you in a few years");
		 }
		 else{
			 System.out.println("See you next year");
		 }
	 }
	 else{//the same year we compare month
		 if (IntArrayDateSend[1]<IntArrayDateActual[1]){ //month passed already
			 System.out.println("Wrong month, already passed");
		 }
		 
		 else if (IntArrayDateSend[1]>IntArrayDateActual[1]){ //not the same month
			 if (IntArrayDateSend[1]-IntArrayDateActual[1]>1){
			 System.out.println("See you in a few months");
			 }
			 else{
				 System.out.println("See you next month");
			 }
		 }
		 else {
			 if (IntArrayDateSend[2]<IntArrayDateActual[2]){ //Day passed already
				 System.out.println("Wrong day, already passed");
			 }
			 
			 else if (IntArrayDateSend[2]>IntArrayDateActual[2]){ //not the same day
				 if (IntArrayDateSend[2]-IntArrayDateActual[2]>1){
				 System.out.println("See you in a few days");
				 }
				 else{
					 System.out.println("See you tomorrow");
				 }
			 }
			 else{
				 if (HourS<HourA){ //Hour passed already
					 System.out.println("Wrong hour, already passed, Try again another day");
				 }
				 
				 else if (HourS>HourA){ //In a few hours
					 if (HourS-HourA>1){
					 System.out.println("See you in " + (HourS-HourA) + "hours");
					 }
					 else{
						 System.out.println("Your course is in less than 1 hour !!"); //Course is soon there.
					 }
					 //I could have still going with minutes and second but i thought it wasn't necessary.
				 }
				 
			 }
			 
			 
			 
			 
		 }
	 }
	 
	 
	}
	
	
	public String returnYear() {
		System.out.println("ReturnYEAR: " + ArrayDateActual[0]);
		return (ArrayDateActual[0]);
	}
	public String returnMonth() {
		return ArrayDateActual[1];
	}
	public String returnDay() {
		return ArrayDateActual[2];
	}
	
}
