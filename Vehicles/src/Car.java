
public class Car extends Vehicle{
	
	private String fuelType = "";
	
	public Car(String rn) {
		
		super(rn);
		
	}//constructor
	
	public Car() {
		
	}//constructor
	
	//getter setter Methods 
	public String getFuelType () {return fuelType;}
	public void setFuelType(String f) {fuelType = f;}

}//class
