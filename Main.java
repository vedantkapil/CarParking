//module CarParking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String [] args) throws NumberFormatException, IOException {
	
	File file=new File("C:\\Users/vedant.kapil/Downloads/input.txt");
	File outputFile = new File("C:\\Users/vedant.kapil/Downloads/output.txt");

	FileWriter myWriter1 = new FileWriter("C:\\Users/vedant.kapil/Downloads/output.txt");
	BufferedWriter myWriter = new BufferedWriter(myWriter1);
	Scanner sc = new Scanner(file);
	String []parkingSlot;
	String inputString="";
	int count=0;
	int flag=0;
	String firstLine=sc.nextLine();
	System.out.println(firstLine);
	String temp="";
	if(count==0 && !firstLine.toLowerCase().contains("create_parking"))
	{
		myWriter.write("Please create a parking space first ");
		myWriter.newLine();
		flag=1;
	}
	if(flag==0) {
	if(firstLine.trim().toLowerCase().contains("create_parking"))
	{
		temp=firstLine.trim().split(" ")[1];	
		//System.out.println(temp);
		temp=temp.trim();
		myWriter.write("Create a parking of "+temp+" slots.");
		myWriter.newLine();
	}
	parkingSlot=new String[Integer.parseInt(temp)];
	HashMap<String,Integer> hash=new HashMap();
	while (sc.hasNextLine()) {
		inputString=sc.nextLine();
		inputString=inputString.trim().toLowerCase();
		System.out.println("-------------"+inputString+"-----------------");
	
	
	if(inputString.contains("park") && inputString.contains("driver_age")) {
		hash.put(inputString.split(" ")[1], Integer.parseInt(inputString.split(" ")[3]));
		int slotNumber=0;
		int tempFlag=0;
		for(int i=0;i<parkingSlot.length;i++) {
			if(parkingSlot[i]==null || parkingSlot[i]=="null")
			{
				parkingSlot[i]=inputString.split(" ")[1];
				slotNumber=i;
				tempFlag=1;
				break;
			}	
		}
			int newSlotNumber=slotNumber+1;
			if(tempFlag==0)
			{
				myWriter.write("No space left in the parking lot");
				hash.remove(inputString.split(" ")[1]);
				myWriter.newLine();
			}
			else {	
		myWriter.write("Car with vehicle registeration number "+inputString.split(" ")[1]+" has been parked at slot "+newSlotNumber+".");
		myWriter.newLine();
	}}
	
	if(inputString.contains("slot_numbers_for_driver_of_age")) {
		String carNumber="";
		int tempFlag=0;
		int age=Integer.parseInt(inputString.split(" ")[1]);
		for(Map.Entry<String, Integer> entry:hash.entrySet()) {
			if(entry.getValue()==age) {
				carNumber=carNumber+entry.getKey();
			}
			
		}
		//carNumber=inputString.split(" ")[1];
		System.out.println("carNumber "+hash);
		int tempCount=0;
		for(int j=0;j<parkingSlot.length;j++) {
			if(parkingSlot[j]!=null) {
			System.out.println(parkingSlot[j]);
			if(carNumber.contains(parkingSlot[j])) {
				tempCount++;
				int tempSlot=j+1;
				//System.out.println("Inside if condition :"+tempCount);
				if(tempCount==1) {
					tempFlag=1;
				//System.out.println("inside temp");
					if(tempFlag==1) {
				myWriter.write(""+tempSlot);}
				}
				if(tempCount>1) {
					if(tempFlag==1) {
					myWriter.write(","+tempSlot);
					}
				}
			}
			
		}}
		if(tempFlag!=1)
		{
			myWriter.write("No driver of age "+age+" have parked their car.");
		}
		myWriter.newLine();
	}
	if(inputString.contains("slot_number_for_car_with_number")) {
		String carNumber="";
		int tempFlag=0;
		carNumber=inputString.split(" ")[1];
		System.out.println(carNumber);
		for(int j=0;j<parkingSlot.length;j++) {
			if(parkingSlot[j]!=null) {
			if(carNumber.contains(parkingSlot[j])) {
				tempFlag=1;
				int tempSlot=j+1;
				myWriter.write(""+tempSlot);
				myWriter.newLine();
			}
			}
			
	}
		if(tempFlag==0) {
			myWriter.write("No car found!");
			myWriter.newLine();
		}
	}
	if(inputString.contains("leave")){
		String carNumber="";
		int tempFlag=0;
		carNumber=parkingSlot[Integer.parseInt(inputString.split(" ")[1])];
		if(parkingSlot[Integer.parseInt(inputString.split(" ")[1])]!=null) {
		parkingSlot[Integer.parseInt(inputString.split(" ")[1])]=null;
		int age=hash.get(carNumber);
		hash.remove(carNumber);
		myWriter.write("Slot number "+Integer.parseInt(inputString.split(" ")[1])+" vacated, the car with vehicle registration number "+carNumber+" left the space, the driver of the car was of age "+age+".");
		}
		else{
			myWriter.write("No car found in the specified slot!");
		}
		myWriter.newLine();
	}
	if(inputString.contains("vehicle_registration_number_for_driver_of_age")) {
		int age=Integer.parseInt(inputString.split(" ")[1]);
		int tempFlag=0;
		System.out.println(age);
		for(Map.Entry<String, Integer> entry1:hash.entrySet()) {
			System.out.println(" "+entry1.getKey());
			if(entry1.getValue()==age)
			{
				myWriter.write("For the driver age "+age+" the vehicle registeration number is "+entry1.getKey()+".");
				myWriter.newLine();
				tempFlag=1;
			}
		}
		if(tempFlag==0)
		{
			myWriter.write("No vehicle found!");
		}
	}}
	count++;
	}
	myWriter.close();
	}
}
