package ie.itsligo.roomroute;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//import TestSon.QRSound;



public class Directions {
	private final int ROOM_LENGTH = 5; // size of the room string
	private char building ;
	private char floor;
	private String locaationOnFloor = null;
	public Directions() {

	}

	
	public char getBuilding() {
		return building;
	}


	public void setBuilding(char building) {
		this.building = building;
	}


	public char getFloor() {
		return floor;
	}


	public void setFloor(char floor) {
		this.floor = floor;
	}


	public String getLocaationOnFloor() {
		return locaationOnFloor;
	}

	public void setLocaationOnFloor(String locaationOnFloor) {
		this.locaationOnFloor = locaationOnFloor;
	}

	/*
	 * This method takes in a room eg E2004 and splits up into the correct block
	 * (Engineering, Science, Business, etc) The correct floor The correct room
	 * number
	 */
	public boolean validate(String room) {
		if (room.length() != ROOM_LENGTH) {
			return false;
		}
		if (Character.isLetter(room.charAt(0)) == false) {
			return false; // room must start with a letter
		}
		for (int i = 1; i < ROOM_LENGTH; i++) {
			if (Character.isDigit(room.charAt(i)) == false) {
				return false; // room must start with a letter
			}
		}
		
		// all ok - store the info
		building = room.charAt(0);
		floor = room.charAt(1);
		locaationOnFloor = room.substring(2);
		
		return true;
	}
	
	public static void delayfor(int n)
	{
		try {
		    Thread.sleep(n * 1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}

	
	public static void playit(int soundRequired) {
		String fn = null;
		File sound; 
        URL location = Directions.class.getProtectionDomain().getCodeSource().getLocation();
        
        switch (soundRequired) {
		case 0:
			fn = location.getFile() + "../src/resources/CaseA.wav"; //Here is the URL string to get to the file
			break;
		case 1:
			fn = location.getFile() + "../src/resources/CaseB.wav";
			break;
		case 2:
			fn = location.getFile() + "../src/resources/CaseCD.wav";
			break;
		case 3:
			fn = location.getFile() + "../src/resources/CaseE.wav";
			break;
		case 4:
			fn = location.getFile() + "../src/resources/CaseF.wav";
			break;
		case 5:
			fn = location.getFile() + "../src/resources/Case0.wav";
			break;
		case 6:
			fn = location.getFile() + "../src/resources/Case1.wav";
			break;
		case 7:
			fn = location.getFile() + "../src/resources/Case2.wav";
			break;
		case 8:
			fn = location.getFile() + "../src/resources/Case006007.wav";
			break;
		case 9:
			fn = location.getFile() + "../src/resources/Case003.wav";
			break;
		case 10:
			fn = location.getFile() + "../src/resources/Case004.wav";
			break;
		case 11:
			fn = location.getFile() + "../src/resources/DefaultBuilding.wav";
			break;
		case 12:
			fn = location.getFile() + "../src/resources/DefaultFloor.wav";
			break;
		case 13:
			fn = location.getFile() + "../src/resources/DefaultRoom.wav";
			break;
		default:
			break;

		}

		// Go for it!
		try {
			// Open an audio input stream.
			sound = new File(fn);  //here we play the file
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			// plays
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	//SOUND

	
	/*
	 * Get directions to building
	 */
	public String toBuilding() {
		String directions = null;
		switch (this.building) {
		case 'A':
			playit(0);    //here we choose the track we want to play
			delayfor(7); //we wait an adapted time till the track ends.
			directions = "From reception, walk straight ahead and then turn to your right";
			break;
		case 'B':
			playit(1);
			delayfor(12);
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
			break;
		case 'C':
			playit(2);
			delayfor(15);
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			break;
		case 'D':
			playit(2);
			delayfor(15);
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			break;
		case 'E':
			playit(3);
			delayfor(9);
			directions = "From reception, move the the centre of reception and turn left into the engineering building";
			break;
		case 'F':
			playit(4);
			delayfor(11);
			directions = "From reception, walk outside and turn to your right.  Walk past the engineering building and the F block is straigt in front";
			break;
		default:
			playit(11);
			delayfor(5);
			directions = "Sorry, that building is not recognised";
			break;
			
		}
		return(directions);
	}
	
	/*
	 * Get directions to floor
	 */
	public String toFloor() {
		String directions = null;
		switch (this.floor) {
		case '0':
			playit(5);
			delayfor(4);
			directions = "Stay on this floor";
			break;
		case '1':
			playit(6);
			delayfor(5);
			directions = "Ascend the stairs or take the lift to the first floor";			
			break;
		case '2':
			playit(7);
			delayfor(6);
			directions = "Ascend two flight of stairs or take the lift to the second floor";			
			break;
		default:
			playit(12);
			delayfor(5);
			directions = "Sorry, floor " + this.floor + " is not recognised";
			break;
			
		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toLocation() {
		String directions = null;
		switch (this.locaationOnFloor) {
		case "006":
			playit(8);
			delayfor(6);
			directions = "This is a room to the right on this level";
			break;
		case "007":
			playit(8);
			delayfor(6);
			directions = "This is a room to the right on this level";			
			break;
		case "003":
			playit(9);
			delayfor(7);
			directions = "This is the last room to the right on this level";			
			break;
		case "004":
			playit(10);
			delayfor(8);
			directions = "This is the second last room to the right on this level";			
			break;
		default:
			playit(13);
			delayfor(5);
			directions = "Sorry, that room in not recognised";
			break;
			
		}
		return(directions);
	}

}