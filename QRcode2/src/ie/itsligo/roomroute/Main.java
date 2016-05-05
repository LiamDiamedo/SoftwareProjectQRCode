package ie.itsligo.roomroute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class Main {

		static EntryQRCode C = new EntryQRCode();  //This is for the Selection Window
		static JTextField textField = null;
		static String qrCodeData;
		static String filePath = "myQRCode.png";
		static QR qr = new QR(qrCodeData, filePath);
		static Room room = new Room();
		static DateTreatement DA = new DateTreatement(); //Just to access to my DataTreatement methods
		static Directions directions = new Directions();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void main(String[] args) throws WriterException, IOException, NotFoundException {
			// Initial hardcoded data for test program 
			C.buildGUI();   //This is for the window
			while(true){    //to restart
			if(C.TryAgain()){   //if the button "SEND" is pressed we send the info again.
			String qrCodeData = C.ReturnString();  //Here we get the info from the differents TextFields
			String filePath = "myQRCode.png";
			
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);	
			
			// create the QR barcode
			qr.createQRCode(qrCodeData, filePath, hintMap, 200, 200);
			System.out.println("QR Code image created successfully!");

			// read the barcode
			String QRdata = qr.readQRCode(filePath, hintMap);
			System.out.println("The barcode reads : " + QRdata);
			
			// Find the room
			String theRoom = room.get(QRdata);
			
			DA.DateHour(room.ReturnValue("Date"), room.ReturnValue("Time")); //Here it is to get the Date and the Time given in the String (that was stored in the Hashmap in Romm.java)
			
			System.out.println("The room is " + theRoom);
			
			// get directions
			if (directions.validate(theRoom) == false) {
				System.out.println("The directions to this room are unknown");
			}
			else {
				System.out.println("DIRECTIONS");
				System.out.println(directions.toBuilding()); //Here we tell the directions one by one
				System.out.println(directions.toFloor());
				System.out.println(directions.toLocation());
			}
			C.Reset(); //we reset our variable of send button to be able to send again.
		}
	}
			
}//while(1)	
		
}
