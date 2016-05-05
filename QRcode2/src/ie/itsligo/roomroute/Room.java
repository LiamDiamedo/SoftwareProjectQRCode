package ie.itsligo.roomroute;

import java.util.HashMap;

public class Room {
	private String room;
	public HashMap Tab = new HashMap();//we create a Hasmap to store our values in peers Key-Value
	
	public Room() {
	}
	
	public String get(String data)
	{
		
		
		String delims = "[\n:]+"; //here we split by space and :
		String[] ArrayData = data.split(delims); //we store the splits in ArrayData
		
		
		
		for (int i=0;i<ArrayData.length;i=i+2){ //we take care of every case of our ArrayData
			Tab.put(ArrayData[i], new String(ArrayData[i+1])); //we associate our key with our values
		}
		
		room = (String) Tab.get("Room"); //we get the room thanks to the key "Room"
		return(room);
	}

	public String ReturnValue(String i){    //to return the HashMAp elements
		return (String) Tab.get(i);
	}
}
